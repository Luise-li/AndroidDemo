package com.luise.zara.fragment;

import com.luise.zara.R;
import com.luise.zara.utils.HeaderText;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class DetailForOrderFragment extends Fragment implements OnClickListener {
	private View mView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getActivity().getIntent();
		int orderId = intent.getIntExtra("orderId", 9999);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_detail_order_layout,
				container, false);
		return mView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		// set title.
		View mHeader = view.findViewById(R.id.confirmFrag_include);
		HeaderText mHeaderText = new HeaderText(mHeader);
		mHeaderText.setLeft(R.drawable.header_back);
		mHeaderText.setTitle(R.string.DetailForOrderFragment_title);
		mHeaderText.setRightVisiable(false);

		// bind event for title.
		view.findViewById(R.id.header_text_iv_left).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.header_text_iv_left:
			getActivity().finish();
			break;

		default:
			break;
		}

	}
}
