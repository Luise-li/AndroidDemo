package com.luise.zara;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ListView;

import com.luise.zara.adapter.OrderAdapter;
import com.luise.zara.bean.Order;
import com.luise.zara.utils.HeaderText;

public class OrderActivity extends Activity implements OnClickListener {
	private ListView mLvOrder;

	/** the url to access server */
	private static final String URL = "http://192.168.99.3:8888/ZARZManager/showOrders";

	private int userId;
	private ArrayList<Order> array;

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			System.out.println(array);
			mLvOrder.setAdapter(new OrderAdapter(OrderActivity.this, array));
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_order);

		View header = findViewById(R.id.orderAc_include);
		HeaderText headerText = new HeaderText(header);
		headerText.setLeft(R.drawable.header_back);
		headerText.setTitle(R.string.orderAc_title);
		headerText.setRightVisiable(false);
		headerText.getLeft().setOnClickListener(this);

		mLvOrder = (ListView) findViewById(R.id.orderAc_lv_order);

		getData();

	}

	private void getData() {
		Intent intent = getIntent();
		userId = intent.getIntExtra("userId", 0);
		new Thread() {
			public void run() {
				getCartInfo();
			};
		}.start();
	}

	protected void getCartInfo() {
		HttpPost httpPost = new HttpPost(URL);
		HttpClient httpClient = new DefaultHttpClient();

		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("userId", String.valueOf(userId)));

		UrlEncodedFormEntity urlEncodedFormEntity;
		HttpResponse httpResponse;
		try {
			urlEncodedFormEntity = new UrlEncodedFormEntity(list, "utf-8");
			httpPost.setEntity(urlEncodedFormEntity);
			httpResponse = httpClient.execute(httpPost);
			int code = httpResponse.getStatusLine().getStatusCode();

			if (code == HttpStatus.SC_OK) {
				HttpEntity httpEntity = httpResponse.getEntity();
				String result = EntityUtils.toString(httpEntity);
				System.out.println(result);
				parserToJson(result);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void parserToJson(String json) {
		System.out.println(json);
		try {
			JSONArray jsonArray = new JSONArray(json);
			JSONObject jsonObject;
			array = new ArrayList<Order>();
			Order order;
			for (int i = 0; i < jsonArray.length(); i++) {
				jsonObject = jsonArray.getJSONObject(i);
				int orderId = jsonObject.getInt("orderId");
				String goodsImage = jsonObject.getString("goodsImage");
				String goodsName = jsonObject.getString("goodsName");
				String goodsColor = jsonObject.getString("goodsColor");
				String goodsSize = jsonObject.getString("goodsSize");
				String goodsNum = jsonObject.getString("goodsNum");
				String goodsDiscount = jsonObject.getString("goodsDiscount");
				int orderFlag = jsonObject.getInt("orderFlag");

				order = new Order();
				order.setOrderId(orderId);
				order.setGoodsImage(goodsImage);
				order.setGoodsName(goodsName);
				order.setGoodsColor(goodsColor);
				order.setGoodsSize(goodsSize);
				order.setGoodsNum(goodsNum);
				order.setGoodsDiscount(goodsDiscount);
				order.setOrderFlag(orderFlag);
				array.add(order);
				// 发送消息通知
				mHandler.sendEmptyMessage(0);
			}

		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.header_text_iv_left:
			OrderActivity.this.finish();
			break;

		}

	}
}
