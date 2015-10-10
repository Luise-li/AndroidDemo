package com.luise.zara.fragment;

import com.luise.zara.BoysActivity;
import com.luise.zara.DiscountActivity;
import com.luise.zara.FashionTrendsActivity;
import com.luise.zara.GirlsActivity;
import com.luise.zara.HotActivity;
import com.luise.zara.NewActivity;
import com.luise.zara.R;
import com.luise.zara.ScannerActivity;
import com.luise.zara.SearchActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class HomeFragment extends Fragment implements OnClickListener {
	// declare the fragment layout view.
	private View mView;
	// declare every widget view.
	private ImageView mIvSearch, mScanner;
	private RelativeLayout mRlNew, mRlHot, mRlDiscount, mRlFashionTrends,
			mRlGirls, mBoys;

	public HomeFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_home_layout, container,
				false);
		initView();
		return mView;
	}

	private void initView() {
		mIvSearch = (ImageView) mView.findViewById(R.id.header_iv_search);
		mScanner = (ImageView) mView.findViewById(R.id.header_iv_scanner);

		mRlNew = (RelativeLayout) mView.findViewById(R.id.homeFrag_ll_new);
		mRlHot = (RelativeLayout) mView.findViewById(R.id.homeFrag_ll_hot);
		mRlDiscount = (RelativeLayout) mView
				.findViewById(R.id.homeFrag_ll_discount);
		mRlFashionTrends = (RelativeLayout) mView
				.findViewById(R.id.homeFrag_ll_fashiontrend);
		mRlGirls = (RelativeLayout) mView.findViewById(R.id.homeFrag_ll_girls);
		mBoys = (RelativeLayout) mView.findViewById(R.id.homeFrag_ll_boys);

		// bind events
		mIvSearch.setOnClickListener(this);
		mScanner.setOnClickListener(this);
		mRlNew.setOnClickListener(this);
		mRlHot.setOnClickListener(this);
		mRlDiscount.setOnClickListener(this);
		mRlFashionTrends.setOnClickListener(this);
		mRlGirls.setOnClickListener(this);
		mBoys.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.header_iv_search:
			startActivity(new Intent(getActivity(), SearchActivity.class));
			break;
		case R.id.header_iv_scanner:
			startActivity(new Intent(getActivity(), ScannerActivity.class));
			break;
		case R.id.homeFrag_ll_new:
			startActivity(new Intent(getActivity(), NewActivity.class));
			break;
		case R.id.homeFrag_ll_hot:
			startActivity(new Intent(getActivity(), HotActivity.class));
			break;
		case R.id.homeFrag_ll_discount:
			startActivity(new Intent(getActivity(), DiscountActivity.class));
			break;
		case R.id.homeFrag_ll_fashiontrend:
			startActivity(new Intent(getActivity(), FashionTrendsActivity.class));
			break;
		case R.id.homeFrag_ll_girls:
			startActivity(new Intent(getActivity(), GirlsActivity.class));
			break;
		case R.id.homeFrag_ll_boys:
			startActivity(new Intent(getActivity(), BoysActivity.class));
			break;

		}

	}


	@Override
	public void onDestroyView() {
		super.onDestroyView();
		mView = null;
	}
}
