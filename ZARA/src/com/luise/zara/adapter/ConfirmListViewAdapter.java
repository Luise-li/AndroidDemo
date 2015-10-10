package com.luise.zara.adapter;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.protocol.ResponseConnControl;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.luise.zara.ConfirmOrderActivity;
import com.luise.zara.R;
import com.luise.zara.bean.Cart;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Parcelable;
import android.sax.StartElementListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class ConfirmListViewAdapter extends BaseAdapter {
	private Context mContext;
	private List<Cart> list;

	private static final String URL = "http://192.168.99.3:8888/ZARZManager/images";
	private ImageLoader loader;
	private static int index;

	public ConfirmListViewAdapter(Context context, List<Cart> list) {
		super();
		this.mContext = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		System.out.println("list.size():" + list.size());

		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_cart_lv_layout, parent, false);
		}
		initView(convertView, position);

		return convertView;
	}

	private void initView(View convertView, final int position) {
		final ImageView mImage = (ImageView) convertView
				.findViewById(R.id.item_cart_goodsImage);
		ImageView mDelete = (ImageView) convertView
				.findViewById(R.id.item_cart_goods_delete);
		TextView mTvName = (TextView) convertView
				.findViewById(R.id.item_cart_goodsName);
		TextView mTvColor = (TextView) convertView
				.findViewById(R.id.item_cart_goodsColor);
		TextView mTvSize = (TextView) convertView
				.findViewById(R.id.item_cart_goodsSize);
		TextView mTvNum = (TextView) convertView
				.findViewById(R.id.item_cart_goods_num);
		TextView mTvDiscount = (TextView) convertView
				.findViewById(R.id.item_cart_goodsDiscount);
		// show text.
		mTvName.setText(list.get(position).getGoodsName());
		mTvColor.setText(list.get(position).getGoodsColor());
		mTvSize.setText(list.get(position).getGoodsSize());
		mTvNum.setText(list.get(position).getGoodsNum());
		mTvDiscount.setText(list.get(position).getGoodsDiscount());

		// bind event.

		mDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				list.remove(position);
				notifyDataSetChanged();

			}
		});

		// get Image.

		RequestQueue mQueue = Volley.newRequestQueue(mContext);
		ImageRequest request = new ImageRequest(URL + "/"
				+ list.get(position).getGoodsImage(),
				new Response.Listener<Bitmap>() {

					@Override
					public void onResponse(Bitmap bitmap) {
						mImage.setImageBitmap(bitmap);
					}

				}, 500, 500, ScaleType.FIT_CENTER, Config.RGB_565,
				new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("Image Request error:", error.toString());

					}

				});
		mQueue.add(request);
		if (loader == null) {
			loader = new ImageLoader(mQueue, null);
		}

	}

}
