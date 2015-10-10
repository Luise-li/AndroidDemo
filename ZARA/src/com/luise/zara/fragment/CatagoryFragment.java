package com.luise.zara.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.luise.zara.CartActivity;
import com.luise.zara.DiscountCatagoryActivity;
import com.luise.zara.R;
import com.luise.zara.SearchActivity;
import com.luise.zara.utils.HeaderText;

public class CatagoryFragment extends Fragment implements OnClickListener {
	private View mView, mHeader;
	private ImageView mIvSearch, mIvCart;

	// declare catagory view
	private RelativeLayout mRlDiscount, mRlNew, mRl3, mRl4, mRl5, mRl6;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_catagory_layout, container,
				false);
		initView();
		return mView;
	}

	private void initView() {
		// set title
		mHeader = mView.findViewById(R.id.catagoryFrag_include);
		HeaderText mHeaderText = new HeaderText(mHeader);
		mHeaderText.setLeft(R.drawable.header_search);
		mHeaderText.setTitle(R.string.catagoryFragemnt_title);
		mHeaderText.setRightVisiable(true);
		mHeaderText.setRight(R.drawable.navigation_cart_unchoosed);

		mIvSearch = (ImageView) mView.findViewById(R.id.header_text_iv_left);
		mIvCart = (ImageView) mView.findViewById(R.id.header_text_iv_right);
		// bind event.
		mIvSearch.setOnClickListener(this);
		mIvCart.setOnClickListener(this);

		mRlDiscount = (RelativeLayout) mView
				.findViewById(R.id.catagoryFrag_ll_discount);
		mRlNew = (RelativeLayout) mView.findViewById(R.id.catagoryFrag_ll_new);
		mRl3 = (RelativeLayout) mView.findViewById(R.id.catagoryFrag_ll_3);
		mRl4 = (RelativeLayout) mView.findViewById(R.id.catagoryFrag_ll_4);
		mRl5 = (RelativeLayout) mView.findViewById(R.id.catagoryFrag_ll_5);
		mRl6 = (RelativeLayout) mView.findViewById(R.id.catagoryFrag_ll_6);
		// bind event.
		mRlDiscount.setOnClickListener(this);
		mRlNew.setOnClickListener(this);
		mRl3.setOnClickListener(this);
		mRl4.setOnClickListener(this);
		mRl5.setOnClickListener(this);
		mRl6.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.header_text_iv_left:
			startActivity(new Intent(getActivity(), SearchActivity.class));
			break;
		case R.id.header_text_iv_right:
			startActivity(new Intent(getActivity(), CartActivity.class));
			break;
		case R.id.catagoryFrag_ll_discount:
			startActivity(new Intent(getActivity(),
					DiscountCatagoryActivity.class));
			break;
		case R.id.catagoryFrag_ll_new:
			
			break;
		case R.id.catagoryFrag_ll_3:

			break;
		case R.id.catagoryFrag_ll_4:

			break;
		case R.id.catagoryFrag_ll_5:

			break;
		case R.id.catagoryFrag_ll_6:

			break;

		}

	}
}
