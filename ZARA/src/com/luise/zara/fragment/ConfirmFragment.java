package com.luise.zara.fragment;

import java.util.List;

import com.luise.zara.R;
import com.luise.zara.adapter.ConfirmListViewAdapter;
import com.luise.zara.bean.Cart;
import com.luise.zara.utils.HeaderText;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ConfirmFragment extends Fragment implements OnClickListener {
	private View mView, mHeader;
	private List<Cart> list;
	private ListView mListView;

	private TextView mTvGoodsTotalPrice, mTvActualPrice, mTvConfirm;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getActivity().getIntent();
		list = intent.getParcelableArrayListExtra("cartGoodsArray");

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_confirm_layout, container,
				false);
		return mView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		// set title.
		mHeader = view.findViewById(R.id.confirmFrag_include);
		HeaderText mHeaderText = new HeaderText(mHeader);
		mHeaderText.setLeft(R.drawable.header_back);
		mHeaderText.setTitle(R.string.ConfirmFragemnt_title);
		mHeaderText.setRightVisiable(false);

		// bind event for title.
		view.findViewById(R.id.header_text_iv_left).setOnClickListener(this);
		// listView
		mListView = (ListView) view.findViewById(R.id.confirmFrag_lv_goodShow);

		mListView.setAdapter(new ConfirmListViewAdapter(getActivity(), list));

		//
		mTvGoodsTotalPrice = (TextView) view
				.findViewById(R.id.confirmFrag_tv_toatalPrice);
		mTvActualPrice = (TextView) view
				.findViewById(R.id.confirmFrag_tv_actualPrice);
		int length = list.size();
		String totalPrice = list.get(length - 1).getTotalPrice();
		mTvGoodsTotalPrice.setText(totalPrice);

		float tempActualPrice = Float.valueOf(totalPrice) - getCoupun();
		mTvActualPrice.setText(tempActualPrice + "");

		mTvConfirm = (TextView) view
				.findViewById(R.id.confirmFrag_tv_confirmPay);
		mTvConfirm.setOnClickListener(this);

	}

	private float getCoupun() {
		return 0;

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.header_text_iv_left:
			getActivity().finish();
			break;
		case R.id.confirmFrag_tv_confirmPay:
			Toast.makeText(getActivity(), "正在跳转到支付宝...", Toast.LENGTH_SHORT)
					.show();
			break;

		}

	}

}
