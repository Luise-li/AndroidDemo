package com.luise.zara.fragment;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.net.URL;

import com.luise.zara.CartActivity;
import com.luise.zara.MessageActivity;
import com.luise.zara.OrderActivity;
import com.luise.zara.R;
import com.luise.zara.SettingActivity;
import com.luise.zara.bean.User;
import com.luise.zara.utils.HeaderText;

public class MyFragment extends Fragment implements OnClickListener {
	/** declare fragment layout view object */
	private View mView;
	/** declare widget in fragment */
	private ImageView mIvTouxiang;
	private TextView mTvNiChen;
	private LinearLayout mLlOrder;
	private LinearLayout mLlReturngoods;
	private LinearLayout mLlCollect;
	private LinearLayout mLlPersonInfo;
	private LinearLayout mLlLoginInfo;
	private LinearLayout mLlContactus;

	/** temporary save activity that fragment attach. */
	private Activity mActivity;

	private int userId;
	private String mStrNichen;
	private Bitmap bitmap;

	private static final String URL = " http://192.168.99.3:8888/ZARZManager/images";

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			mTvNiChen.setText(mStrNichen);
			mIvTouxiang.setImageBitmap(bitmap);

		};

	};

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = getActivity();
		if (mActivity != null) {
			Intent intent = mActivity.getIntent();

			User user = intent.getParcelableExtra("user");
			// System.out.println(user.getId());
			// System.out.println(user.getImageName());
			userId = user.getId();
			String imageName = user.getImageName();
			mStrNichen = user.getPet();
			obtainImage(imageName);

		}

	};

	private void obtainImage(final String imageName) {
		new Thread() {
			public void run() {
				getImage(URL + "/" + imageName);
			};
		}.start();

	}

	protected void getImage(String url) {
		try {
			URL u = new URL(url);
			InputStream is = u.openStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			bitmap = BitmapFactory.decodeStream(bis);
			mHandler.sendEmptyMessage(1);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_my_layout, container, false);
		// …Ë÷√±ÍÃ‚
		View header = mView.findViewById(R.id.myFragment_include);
		HeaderText headerText = new HeaderText(header);
		headerText.setLeft(R.drawable.my_setting);
		headerText.setTitle(R.string.myFragemnt_title);
		headerText.setRightVisiable(true);
		headerText.setRight(R.drawable.my_message);
		mView.findViewById(R.id.header_text_iv_left).setOnClickListener(this);
		mView.findViewById(R.id.header_text_iv_right).setOnClickListener(this);

		initView(mView);

		mLlOrder.setOnClickListener(this);
		mLlReturngoods.setOnClickListener(this);
		mLlCollect.setOnClickListener(this);
		mLlPersonInfo.setOnClickListener(this);
		mLlLoginInfo.setOnClickListener(this);
		mLlContactus.setOnClickListener(this);

		return mView;
	}

	private void initView(View view) {
		mIvTouxiang = (ImageView) view.findViewById(R.id.fragMy_iv_touxiang);
		mTvNiChen = (TextView) view.findViewById(R.id.fragMy_tv_nichen);

		mLlOrder = (LinearLayout) view.findViewById(R.id.fragMy_ll_order);
		mLlReturngoods = (LinearLayout) view
				.findViewById(R.id.fragMy_ll_returngoods);
		mLlCollect = (LinearLayout) view.findViewById(R.id.fragMy_ll_collect);
		mLlPersonInfo = (LinearLayout) view
				.findViewById(R.id.fragMy_ll_peroninfo);
		mLlLoginInfo = (LinearLayout) view
				.findViewById(R.id.fragMy_ll_logininfo);
		mLlContactus = (LinearLayout) view
				.findViewById(R.id.fragMy_ll_contactus);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.header_text_iv_left:
			startActivity(new Intent(getActivity(), SettingActivity.class));
			break;
		case R.id.header_text_iv_right:
			startActivity(new Intent(getActivity(), MessageActivity.class));
			break;
		case R.id.fragMy_ll_touxiang:

			break;
		case R.id.fragMy_ll_order:
			Intent iToOrder = new Intent(mActivity, OrderActivity.class);
			iToOrder.putExtra("userId", userId);
			startActivity(iToOrder);
			break;
		case R.id.fragMy_ll_returngoods:

			break;
		case R.id.fragMy_ll_collect:

			break;
		case R.id.fragMy_ll_peroninfo:

			break;
		case R.id.fragMy_ll_logininfo:

			break;
		case R.id.fragMy_ll_contactus:

			break;

		}

	}

}
