package com.luise.zara.fragment;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.luise.zara.CartActivity;
import com.luise.zara.R;
import com.luise.zara.net.AccessInternet;
import com.luise.zara.utils.Constant;
import com.luise.zara.utils.HeaderText;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

public class HotFragment extends Fragment implements OnClickListener {

	private View mView, mHeader;
	private ImageView mIvBack, mIvCart;
	private ImageView imageView1, imageView2, imageView3, imageView4,
			imageView5, imageView6, imageView0;

	private TextView mTvPrice1, mTvDiscount1;
	private TextView mTvPrice2, mTvDiscount2;
	private TextView mTvPrice3, mTvDiscount3;
	private TextView mTvPrice4, mTvDiscount4;

	private static final String URL = "http://192.168.99.3:8888/ZARZManager/hotGoods";
	private static final String URL_IMAGE = "http://192.168.99.3:8888/ZARZManager/images";

	private JSONArray mJsonArray;
	private List<String> imageNameArray;

	private RequestQueue mImageRequestQueue;
	private ImageLoader loader;
	private Handler mHander = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case Constant.HANDLER_SEND_JSONARRAY_PARSE_COMPLETED:
				// parse JsonArray.
				int length = mJsonArray.length();
				if (imageNameArray == null) {
					imageNameArray = new ArrayList<String>();
				}

				try {
					for (int i = 0; i < length; i++) {
						imageNameArray.add(mJsonArray.getString(i).trim());
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}

				System.out.println("imageNameArray:" + imageNameArray);
				// load image and set Image.
				setImage(imageNameArray.get(0), imageView0);
				setImage(imageNameArray.get(1), imageView1);
				setImage(imageNameArray.get(2), imageView2);
				setImage(imageNameArray.get(3), imageView3);
				setImage(imageNameArray.get(5), imageView4);
				setImage(imageNameArray.get(6), imageView5);
				setImage(imageNameArray.get(4), imageView6);

				break;

			default:
				break;
			}

		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		RequestQueue mQueue = Volley.newRequestQueue(getActivity());

		JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(URL,
				new Response.Listener<JSONArray>() {

					@Override
					public void onResponse(JSONArray jsonArray) {
						mJsonArray = jsonArray;
						mHander.sendEmptyMessage(Constant.HANDLER_SEND_JSONARRAY_PARSE_COMPLETED);

						System.out.println("AccessInternet÷–£∫" + jsonArray);
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("JsonArray request error:", error.toString());

					}
				});
		mQueue.add(jsonArrayRequest);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater
				.inflate(R.layout.fragment_hot_layout, container, false);

		// set title.
		mHeader = mView.findViewById(R.id.hotFrag_include);
		HeaderText mHeaderText = new HeaderText(mHeader);
		mHeaderText.setLeft(R.drawable.header_back);
		mHeaderText.setTitle(R.string.HotFragemnt_title);
		mHeaderText.setRightVisiable(true);
		mHeaderText.setRight(R.drawable.navigation_cart_unchoosed);

		mIvBack = (ImageView) mView.findViewById(R.id.header_text_iv_left);
		mIvCart = (ImageView) mView.findViewById(R.id.header_text_iv_right);
		// bind event.
		mIvBack.setOnClickListener(this);
		mIvCart.setOnClickListener(this);

		return mView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		imageView0 = (ImageView) view.findViewById(R.id.hotFrag_iv_1);
		imageView1 = (ImageView) view.findViewById(R.id.hotFrag_iv_2);
		imageView2 = (ImageView) view.findViewById(R.id.hotFrag_iv_3);
		imageView3 = (ImageView) view.findViewById(R.id.hotFrag_iv_4);
		imageView4 = (ImageView) view.findViewById(R.id.hotFrag_iv_5);
		imageView5 = (ImageView) view.findViewById(R.id.hotFrag_iv_6);
		imageView6 = (ImageView) view.findViewById(R.id.hotFrag_iv_7);

		mTvPrice1 = (TextView) view.findViewById(R.id.hotFrag_tv_price_1);
		mTvPrice2 = (TextView) view.findViewById(R.id.hotFrag_tv_price_2);
		mTvPrice3 = (TextView) view.findViewById(R.id.hotFrag_tv_price_3);
		mTvPrice4 = (TextView) view.findViewById(R.id.hotFrag_tv_price_4);

		mTvPrice1.getPaint().setAntiAlias(true);
		mTvPrice1.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		mTvPrice2.getPaint().setAntiAlias(true);
		mTvPrice2.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		mTvPrice3.getPaint().setAntiAlias(true);
		mTvPrice3.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		mTvPrice4.getPaint().setAntiAlias(true);
		mTvPrice4.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

	}

	public void setImage(String imageName, final ImageView view) {
		if (imageName == null || view == null) {
			return;
		}

		if (mImageRequestQueue == null) {
			mImageRequestQueue = Volley.newRequestQueue(getActivity());
		}

		ImageRequest imageRequest = new ImageRequest(URL_IMAGE + "/"
				+ imageName, new Response.Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap bitmap) {
				if (bitmap != null) {
					view.setImageBitmap(bitmap);
				}

			}
		}, 500, 500, ScaleType.FIT_CENTER, Config.RGB_565,
				new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("ImageRequest request error:", error.toString());

					}
				});

		mImageRequestQueue.add(imageRequest);

		loader = new ImageLoader(mImageRequestQueue, null);
		System.out.println("loader complete!");

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.header_text_iv_left:
			getActivity().finish();
			break;
		case R.id.header_text_iv_right:
			startActivity(new Intent(getActivity(), CartActivity.class));
			break;

		}

	}

}
