package com.luise.zara.adapter;

import java.util.List;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.Volley;
import com.luise.zara.R;
import com.luise.zara.bean.Cart;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

public class EditListViewAdapter extends BaseAdapter {
	private Context mContext;
	private List<Cart> list;
	private static final String URL = "http://192.168.99.3:8888/ZARZManager/images";
	private ImageLoader loader;

	private Spinner mSpSize;
	private String[] sizeArray;

	public EditListViewAdapter(Context context, List<Cart> list) {
		super();
		this.mContext = context;
		this.list = list;
		sizeArray = mContext.getResources().getStringArray(R.array.sizeArray);
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
					R.layout.item_edit_lv_layout, parent, false);
		}

		initView(convertView, position);
		return convertView;
	}

	private void initView(View view, final int position) {

		final ImageView mImage = (ImageView) view
				.findViewById(R.id.item_edit_iv);
		TextView mTvDown = (TextView) view.findViewById(R.id.item_edit_tv_down);
		TextView mTvUp = (TextView) view.findViewById(R.id.item_edit_tv_up);
		TextView mTvNum = (TextView) view.findViewById(R.id.item_edit_tv_num);
		TextView mTvColor = (TextView) view
				.findViewById(R.id.item_edit_tv_color);
		mSpSize = (Spinner) view.findViewById(R.id.item_edit_spinner_size);
		// bind event.
		mTvDown.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				list.get(position).setGoodsNum(numDown(position) + "");
				notifyDataSetChanged();

			}
		});
		mTvUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				list.get(position).setGoodsNum(numUp(position) + "");
				notifyDataSetChanged();

			}
		});
		mSpSize.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int p,
					long id) {
				list.get(position).setGoodsSize(sizeArray[p].trim());
				notifyDataSetChanged();

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

		mTvNum.setText(list.get(position).getGoodsNum());
		mTvColor.setText(list.get(position).getGoodsColor());

		if ("S".equals(list.get(position).getGoodsSize())) {
			mSpSize.setSelection(0);
		} else if ("M".equals(list.get(position).getGoodsSize())) {
			mSpSize.setSelection(1);
		} else if ("L".equals(list.get(position).getGoodsSize())) {
			mSpSize.setSelection(2);
		} else if ("XL".equals(list.get(position).getGoodsSize())) {
			mSpSize.setSelection(3);
		} else if ("XXL".equals(list.get(position).getGoodsSize())) {
			mSpSize.setSelection(4);
		}

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

	//
	private int numUp(int index) {
		String num = list.get(index).getGoodsNum();
		int temp = Integer.valueOf(num);
		if (temp < 999) {
			temp++;
		}

		return temp;
	}

	private int numDown(int index) {
		String num = list.get(index).getGoodsNum();
		int temp = Integer.valueOf(num);
		if (temp > 0) {
			temp--;
		}
		return temp;

	}

}
