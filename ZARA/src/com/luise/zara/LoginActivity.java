/**
 * This class is the leading class.It will be seen when the application loading first.
 * Then the interface will ask user input username and password to access the app.
 *  @author Luise
 * */
package com.luise.zara;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.luise.zara.bean.User;

public class LoginActivity extends Activity implements OnClickListener {

	/** obtain username and password */
	private EditText mEtUserName;
	private EditText mEtPassword;
	/** declare two button */
	private Button mBtnEnter;
	private LinearLayout mBtnNewAccount;
	/** forget password */
	private TextView mTvForgetPassword;
	/** the url to access server */
	private static final String URL = "http://192.168.99.3:8888/ZARZManager/login";
	/** the temp area to save json data */
	private volatile String result;
	/** login status code */
	private static final int LOGIN_SUCCESS = 0;
	private static final int LOGIN_NOT_USERNAME = 1;
	private static final int LOGIN_UNCORRECT_PASSWORD = 2;

	private User user;

	/** Handler */
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			checkFlag(msg.what);
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);

		/** init widget */
		init();
		/** set event listener */
		mBtnEnter.setOnClickListener(this);
		mBtnNewAccount.setOnClickListener(this);
		mTvForgetPassword.setOnClickListener(this);

	}

	/** init all view object */
	private void init() {
		mEtUserName = (EditText) findViewById(R.id.loginAc_et_email);
		mEtPassword = (EditText) findViewById(R.id.loginAc_et_password);

		mBtnEnter = (Button) findViewById(R.id.loginAc_btn_enter);
		mBtnNewAccount = (LinearLayout) findViewById(R.id.loginAc_ll_newaccount);

		mTvForgetPassword = (TextView) findViewById(R.id.loginAc_tv_forgetpassword);
	}

	@Override
	public void onClick(View v) {
		String email = mEtUserName.getText().toString().trim();
		String password = mEtPassword.getText().toString().trim();
		boolean isLegal = check(email, password);
		if (isLegal) {
			switch (v.getId()) {
			case R.id.loginAc_btn_enter:
				sendToServer(email, password);
				break;
			case R.id.loginAc_ll_newaccount:

				break;
			case R.id.loginAc_tv_forgetpassword:
				Intent intent = new Intent(this, FindPwdActivity.class);
				startActivity(intent);
				break;

			}
		}

	}

	private void checkFlag(int what) {

		switch (what) {
		case LOGIN_SUCCESS:// login success.
			Intent i = new Intent(this, MainFragActivty.class);
			i.putExtra("user", user);
			startActivity(i);
			break;
		case LOGIN_NOT_USERNAME:// username is not exit.
			Toast.makeText(this, R.string.username_not_exist,
					Toast.LENGTH_SHORT).show();
			break;
		case LOGIN_UNCORRECT_PASSWORD:// password is uncorrect.
			Toast.makeText(this, R.string.password_is_uncorrect,
					Toast.LENGTH_SHORT).show();
			break;

		}

	}

	private void sendToServer(String email, String password) {
		VertifyLogin vertifyLogin = new VertifyLogin(URL);
		vertifyLogin.vertify(email, password);

	}

	/** check wether the user input is legal. */
	private boolean check(String email, String password) {
		if ("".equals(email)) {// email is "".

			Toast.makeText(this, R.string.username_illegal_tips,
					Toast.LENGTH_SHORT).show();
			return false;
		}
		if (password.length() < 6) {
			Toast.makeText(this, R.string.password_illegal_tips,
					Toast.LENGTH_SHORT).show();
			return false;
		}
		return true;
	}

	public class VertifyLogin {
		private String uri;

		public VertifyLogin(String uri) {
			this.uri = uri;
		}

		public void vertify(String name, String password) {
			accessToServer(name, password);
		}

		private void accessToServer(final String name, final String password) {
			new Thread() {
				public void run() {
					request(name, password);
				};
			}.start();
		}

		protected void request(String name, String password) {
			HttpPost httpPost = new HttpPost(uri);

			HttpClient httpClient = new DefaultHttpClient();
			List<NameValuePair> list = new ArrayList<NameValuePair>();
			list.add(new BasicNameValuePair("userName", name));
			list.add(new BasicNameValuePair("userPwd", password));

			try {
				UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(
						list, "utf-8");
				httpPost.setEntity(urlEncodedFormEntity);
				HttpResponse httpResponse = httpClient.execute(httpPost);
				int code = httpResponse.getStatusLine().getStatusCode();
				vertifyResult(code, httpResponse);

			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

		public void vertifyResult(int code, HttpResponse httpResponse) {

			if (code == HttpStatus.SC_OK) {
				HttpEntity httpEntity = httpResponse.getEntity();
				try {
					result = EntityUtils.toString(httpEntity);
					parser(result);
					System.out.println(result);

				} catch (ParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public void parser(String json) {
		user = new User();
		try {
			JSONObject jsonObject = new JSONObject(json);
			int flag = jsonObject.getInt("flag");
			int userId = jsonObject.getInt("userId");
			String userImage = jsonObject.getString("userImage");
			String userPet = jsonObject.getString("userPet");

			user.setFlag(flag);
			user.setId(userId);
			user.setImageName(userImage);
			user.setPet(userPet);

			if (flag == LOGIN_SUCCESS) {
				mHandler.sendEmptyMessage(LOGIN_SUCCESS);
			} else if (flag == LOGIN_NOT_USERNAME) {
				mHandler.sendEmptyMessage(LOGIN_NOT_USERNAME);
			} else if (flag == LOGIN_UNCORRECT_PASSWORD) {
				mHandler.sendEmptyMessage(LOGIN_UNCORRECT_PASSWORD);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}
}
