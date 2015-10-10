package com.luise.zara.fragment;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
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

import com.luise.zara.CartActivity;
import com.luise.zara.ConfirmOrderActivity;
import com.luise.zara.EditActivity;
import com.luise.zara.R;
import com.luise.zara.SearchActivity;
import com.luise.zara.adapter.CartListViewAdapter;
import com.luise.zara.bean.Cart;
import com.luise.zara.utils.Constant;
import com.luise.zara.utils.HeaderText;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class CartFragment extends Fragment implements OnClickListener {
	private View mView, mHeader;
	private ListView mListView;
	private String mResult;
	private List<Cart> list;

	private static final String URL = "http://192.168.99.3:8888/ZARZManager/showCart";

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				list = parseResult(mResult);
				CartListViewAdapter cartListViewAdapter = new CartListViewAdapter(
						getActivity(), list);
				mListView.setAdapter(cartListViewAdapter);
				break;

			}
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final int userId = 2;
		new Thread() {
			public void run() {
				getData(userId);
			};
		}.start();

	}

	protected ArrayList<Cart> parseResult(String result) {
		System.out.println("result:" + result);
		JSONObject jsonObject;
		Cart cart;
		ArrayList<Cart> arr = new ArrayList<Cart>();
		try {
			JSONArray jsonArray = new JSONArray(result);
			int length = jsonArray.length();
			System.out.println("length:---" + length);

			int totalNum = 0;
			float totalPrice = 0;

			for (int i = 0; i < length; i++) {
				cart = new Cart();
				jsonObject = jsonArray.getJSONObject(i);
				System.out.println("jsonObject:" + jsonObject);
				cart.setCartId(jsonObject.getInt("cartId"));
				cart.setGoodsImage(jsonObject.getString("goodsImage").trim());

				cart.setGoodsName(jsonObject.getString("goodsName").trim());
				cart.setGoodsColor(jsonObject.getString("goodsColor").trim());
				cart.setGoodsSize(jsonObject.getString("goodsSize").trim());

				String tempNum = jsonObject.getString("goodsNum").trim();
				totalNum += Integer.valueOf(tempNum);
				cart.setGoodsNum(tempNum);
				String tempPrice = jsonObject.getString("goodsDiscount").trim();
				totalPrice += Float.valueOf(tempPrice);
				cart.setGoodsDiscount(tempPrice);

				arr.add(cart);
			}
			cart = new Cart();
			cart.setTotalNum(totalNum + "");
			cart.setTotalPrice(totalPrice + "");
			arr.add(cart);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return arr;

	}

	protected void getData(int userId) {
		HttpPost httpPost = new HttpPost(URL);
		HttpClient httpClient = new DefaultHttpClient();
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("userId", userId + ""));

		UrlEncodedFormEntity entity;

		try {
			entity = new UrlEncodedFormEntity(list, "UTF-8");
			httpPost.setEntity(entity);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			int code = httpResponse.getStatusLine().getStatusCode();
			if (code == 200) {
				HttpEntity httpEntity = httpResponse.getEntity();
				mResult = EntityUtils.toString(httpEntity, "UTF-8");
				System.out.println("mResult:" + mResult);
				mHandler.sendEmptyMessage(0);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_cart_layout, container,
				false);

		return mView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		// set title.
		mHeader = view.findViewById(R.id.cartFrag_include);
		HeaderText mHeaderText = new HeaderText(mHeader);
		mHeaderText.setLeft(R.drawable.header_search);
		mHeaderText.setTitle(R.string.CartFragemnt_title);
		mHeaderText.setRightVisiable(true);
		mHeaderText.setRight(R.drawable.cart_edit);

		// bind event for title.
		view.findViewById(R.id.header_text_iv_left).setOnClickListener(this);
		view.findViewById(R.id.header_text_iv_right).setOnClickListener(this);

		// obtain listView object.
		mListView = (ListView) view.findViewById(R.id.cartFrag_lv);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.header_text_iv_left:
			startActivity(new Intent(getActivity(), SearchActivity.class));
			break;
		case R.id.header_text_iv_right:
			Intent intent = new Intent(getActivity(), EditActivity.class);
			intent.putParcelableArrayListExtra("cartGoods",
					(ArrayList<? extends Parcelable>) list);
			startActivityForResult(intent, Constant.CART_REQUST_CODE);
			break;
		}

	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	}
}
