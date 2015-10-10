package com.luise.zara.adapter;

import java.util.List;

import org.apache.http.protocol.ResponseConnControl;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.luise.zara.R;
import com.luise.zara.bean.Goods;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

public class DetailGridViewAdapter extends BaseAdapter {
	private Context mContext;
	private List<Goods> list;
	private static String url = "http://192.168.99.3:8888/ZARZManager/images";
	private ImageLoader loader;

	public DetailGridViewAdapter(Context context, List<Goods> arr) {
		mContext = context;
		list = arr;
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
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(
					R.layout.item_detailfrag_gv_layout, parent, false);
		}

		TextView mTvName = (TextView) convertView
				.findViewById(R.id.item_detailfrag_tv_name);
		TextView mTvDiscount = (TextView) convertView
				.findViewById(R.id.item_detailfrag_tv_discountprice);
		TextView mTvPrice = (TextView) convertView
				.findViewById(R.id.item_detailfrag_tv_price);

		mTvPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
		mTvPrice.getPaint().setAntiAlias(true);

		mTvName.setText(list.get(position).getGoodsName().trim());
		mTvDiscount.setText(list.get(position).getGoodsDiscount().trim());
		mTvPrice.setText(list.get(position).getGoodsPrice().trim());

		final ImageView imageView = (ImageView) convertView
				.findViewById(R.id.item_detailfrag_iv_image);

		RequestQueue mQueue = Volley.newRequestQueue(mContext);
		ImageRequest request = new ImageRequest(url + "/"
				+ list.get(position).getGoodsImage(), new Listener<Bitmap>() {

			@Override
			public void onResponse(Bitmap bitmap) {
				imageView.setImageBitmap(bitmap);

			}
		}, 500, 500, ScaleType.FIT_CENTER, Config.RGB_565,
				new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("Image Request error:", error.toString());
					}
				});

		mQueue.add(request);

		loader = new ImageLoader(mQueue, null);

		return convertView;
	}
}
