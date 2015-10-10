package com.luise.zara.adapter;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.http.client.methods.HttpGet;

import com.luise.zara.DetailForOrderActivity;
import com.luise.zara.R;
import com.luise.zara.bean.Cart;
import com.luise.zara.bean.Order;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OrderAdapter extends BaseAdapter {
	private List<Order> list;
	private Context mCtx;
	private LayoutInflater mInflater;
	private static final String URL = "http://192.168.99.3:8888/ZARZManager/images/";

	private Bitmap bitmap;
	private ImageView goodsImage;

	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			goodsImage.setImageBitmap(bitmap);
		};
	};

	public OrderAdapter(Context context, List<Order> list) {
		mCtx = context;
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
		mInflater = LayoutInflater.from(mCtx);
		convertView = mInflater.inflate(R.layout.item_orderac_order_layout,
				parent, false);
		initView(position, convertView);

		return convertView;
	}

	private void initView(final int position, View view) {
		goodsImage = (ImageView) view.findViewById(R.id.item_order_goodsImage);
		TextView goodsName = (TextView) view
				.findViewById(R.id.item_order_goodsName);
		TextView goodsColor = (TextView) view
				.findViewById(R.id.item_order_goodsColor);
		TextView goodsSize = (TextView) view
				.findViewById(R.id.item_order_goodsSize);
		TextView goodsNum = (TextView) view
				.findViewById(R.id.item_order_goods_num);

		ImageView goodsDelete = (ImageView) view
				.findViewById(R.id.item_order_goods_delete);
		TextView goodsDiscount = (TextView) view
				.findViewById(R.id.item_order_goodsDiscount);
		LinearLayout mLinearLayout = (LinearLayout) view
				.findViewById(R.id.item_order_ll);
		// 设置静态内容
		goodsName.setText(list.get(position).getGoodsName());
		goodsColor.setText(list.get(position).getGoodsColor().trim());
		goodsSize.setText(list.get(position).getGoodsSize());
		goodsDiscount.setText(list.get(position).getGoodsDiscount());
		goodsNum.setText(list.get(position).getGoodsNum());
		// 设置订单状态
		TextView orderStatus = (TextView) view
				.findViewById(R.id.item_order_status);
		int orderFlag = list.get(position).getOrderFlag();
		switch (orderFlag) {
		case 1:
			orderStatus.setText(R.string.item_order_wait_bill);
			break;
		case 2:
			orderStatus.setText(R.string.item_order_wait_transporting);
			break;
		case 3:
			orderStatus.setText(R.string.item_order_wait_arrived);
			break;

		}
		// 设置图片
		String goodsImageName = list.get(position).getGoodsImage();
		obtainImage(goodsImage, goodsImageName);

		// 添加删除的点击事件
		goodsDelete.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				list.remove(position);
				notifyDataSetChanged();

			}
		});
		//
		mLinearLayout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent iToDetailOrder = new Intent(mCtx,
						DetailForOrderActivity.class);
				mCtx.startActivity(iToDetailOrder.putExtra("orderId",
						list.get(position).getOrderId()));
			}
		});

	}

	private void obtainImage(final ImageView iv, final String name) {
		new Thread() {
			public void run() {
				getImage(iv, name);

			};
		}.start();

	}

	protected void getImage(ImageView iv, String name) {
		URL u;
		try {
			u = new URL(URL + name);
			InputStream is = u.openStream();
			BufferedInputStream bis = new BufferedInputStream(is);
			bitmap = BitmapFactory.decodeStream(bis);
			mHandler.sendEmptyMessage(0);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
