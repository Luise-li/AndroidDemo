package com.luise.zara.fragment;

import com.luise.zara.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchFragment extends Fragment implements OnClickListener {
	private View mView;
	private ImageView mIvSearch;
	private TextView mTvCancel;

	private GridView mGridView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_search_layout, container,
				false);
		initView();
		return mView;
	}

	private void initView() {
		mIvSearch = (ImageView) mView.findViewById(R.id.searchFrag_iv_search);
		mTvCancel = (TextView) mView.findViewById(R.id.searchFrag_tv_cancel);
		mGridView = (GridView) mView.findViewById(R.id.searchFrag_gv_showIamge);
		// bind events.
		mIvSearch.setOnClickListener(this);
		mTvCancel.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.searchFrag_iv_search:
			Toast.makeText(getActivity(), " 正在搜索...", Toast.LENGTH_LONG).show();
			break;
		case R.id.searchFrag_tv_cancel:
			// TODO cancel
			Toast.makeText(getActivity(), " 搜索已取消", Toast.LENGTH_SHORT).show();
			break;

		}

	}
}
