package com.luise.zara.adapter;

import java.util.List;

import com.luise.zara.DetailForFashionActivity;
import com.luise.zara.R;
import com.luise.zara.bean.FashionGoods;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class FashionListViewAdapter extends BaseAdapter implements
		OnClickListener {
	private Context mContext;
	private List<FashionGoods> list;

	public FashionListViewAdapter(Context context, List<FashionGoods> arr) {
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
					R.layout.item_fashionfrag_lv_layout, parent, false);
		}
		init(convertView, position);
		return convertView;
	}

	private void init(View convertView, int position) {
		LinearLayout ll = (LinearLayout) convertView
				.findViewById(R.id.item_fashion_ll);
		ImageView mImage = (ImageView) convertView
				.findViewById(R.id.item_fashion_iv_image);
		TextView mTvName = (TextView) convertView
				.findViewById(R.id.item_fashion_tv_name);

		mImage.setImageResource(list.get(position).getId());
		mTvName.setText(list.get(position).getName());
		ll.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		mContext.startActivity(new Intent(mContext,
				DetailForFashionActivity.class));
	}
}
