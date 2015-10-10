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

import com.luise.zara.R;
import com.luise.zara.adapter.DetailGridViewAdapter;
import com.luise.zara.utils.Constant;
import com.luise.zara.utils.HeaderText;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class DetailForFashionFragment extends Fragment implements
		OnClickListener {
	private View mView, mHeader;

	private static final String URL = "http://192.168.99.3:8888/ZARZManager/addToCart";

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				Toast.makeText(getActivity(), "1件商品已添加到购物篮", Toast.LENGTH_SHORT)
						.show();
				break;
			case 1:
				Toast.makeText(getActivity(), "商品添加到购物篮失败", Toast.LENGTH_SHORT)
						.show();
				break;

			}
		};
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_fashion_detail_layout,
				container, false);

		return mView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		// set title.
		mHeader = view.findViewById(R.id.fashionFrag_detail_include);
		HeaderText mHeaderText = new HeaderText(mHeader);
		mHeaderText.setLeft(R.drawable.header_back);
		mHeaderText.setTitle(R.string.FashionFragemnt_title);
		mHeaderText.setRightVisiable(false);

		// bind event for title.
		view.findViewById(R.id.header_text_iv_left).setOnClickListener(this);

		TextView mTvPrice1 = (TextView) view
				.findViewById(R.id.fashion_detail_iv_price_1);
		TextView mTvPrice2 = (TextView) view
				.findViewById(R.id.fashion_detail_iv_price_2);
		TextView mTvAddtoCart = (TextView) view
				.findViewById(R.id.fashionfrag_detail_tv_addtocart);

		// draw line.
		mTvPrice1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		mTvPrice1.getPaint().setAntiAlias(true);
		// draw line.
		mTvPrice2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		mTvPrice2.getPaint().setAntiAlias(true);

		mTvAddtoCart.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.fashionfrag_detail_tv_addtocart:
			// update cart data collection.
			new Thread() {
				public void run() {
					AddtoCart(2, 22, "S", "白色", 1);
				};

			}.start();

			break;
		case R.id.header_text_iv_left:
			getActivity().finish();

		}

	}

	private void AddtoCart(int userId, int goodsId, String goodsSize,
			String goodsColor, int goodsNum) {
		HttpPost httpPost = new HttpPost(URL);
		HttpClient httpClient = new DefaultHttpClient();
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("userId", userId + ""));
		list.add(new BasicNameValuePair("goodsId", goodsId + ""));
		list.add(new BasicNameValuePair("goodsSize", goodsSize));
		list.add(new BasicNameValuePair("goodsColor", goodsColor));
		list.add(new BasicNameValuePair("goodsNum", goodsNum + ""));
		UrlEncodedFormEntity entity;

		try {
			entity = new UrlEncodedFormEntity(list, "UTF-8");
			httpPost.setEntity(entity);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			int code = httpResponse.getStatusLine().getStatusCode();
			if (code == 200) {
				HttpEntity httpEntity = httpResponse.getEntity();
				String mResult = EntityUtils.toString(httpEntity, "UTF-8");
				// a tips for add to cart successfully.
				if ("0".equals(mResult.trim())) {
					mHandler.sendEmptyMessage(0);
				} else {
					mHandler.sendEmptyMessage(1);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
