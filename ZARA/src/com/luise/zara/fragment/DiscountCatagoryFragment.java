package com.luise.zara.fragment;

import java.util.ArrayList;

import com.luise.zara.CartActivity;
import com.luise.zara.DetailForDiscountCatagoryActivity;
import com.luise.zara.R;
import com.luise.zara.utils.Constant;
import com.luise.zara.utils.HeaderText;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class DiscountCatagoryFragment extends Fragment implements
		OnCheckedChangeListener, OnPageChangeListener, OnClickListener {
	private View mView, mHeader;

	private ImageView mIvBack, mIvCart;

	private RadioGroup mRadioGroup;
	private ViewPager mViewPager;
	private ArrayList<View> mPagerViewArray;
	private RadioButton mRbtnTRF, mRbtnWoman, mRbtnMan, mRbtnChild;
	private View mTRFView, mWomanView, mManView, mChildView;

	private Intent intent = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// inflate ViewPager.
		mPagerViewArray = new ArrayList<View>();
		LayoutInflater inflater = getLayoutInflater(savedInstanceState);
		mTRFView = inflater.inflate(R.layout.viewpager_trf_layout, null);
		mWomanView = inflater.inflate(R.layout.viewpager_women_layout, null);
		mManView = inflater.inflate(R.layout.viewpager_man_layout, null);
		mChildView = inflater.inflate(R.layout.viewpager_child_layout, null);
		// add to collection.
		mPagerViewArray.add(mTRFView);
		mPagerViewArray.add(mWomanView);
		mPagerViewArray.add(mManView);
		mPagerViewArray.add(mChildView);

		initPagerView();
	}

	private void initPagerView() {
		// bind event for TRF menu.
		mTRFView.findViewById(R.id.viewpager_trf_ll_1).setOnClickListener(this);
		mTRFView.findViewById(R.id.viewpager_trf_ll_2).setOnClickListener(this);
		mTRFView.findViewById(R.id.viewpager_trf_ll_3).setOnClickListener(this);
		mTRFView.findViewById(R.id.viewpager_trf_ll_4).setOnClickListener(this);
		mTRFView.findViewById(R.id.viewpager_trf_ll_5).setOnClickListener(this);
		mTRFView.findViewById(R.id.viewpager_trf_ll_6).setOnClickListener(this);
		mTRFView.findViewById(R.id.viewpager_trf_ll_7).setOnClickListener(this);
		mTRFView.findViewById(R.id.viewpager_trf_ll_8).setOnClickListener(this);
		mTRFView.findViewById(R.id.viewpager_trf_ll_9).setOnClickListener(this);
		mTRFView.findViewById(R.id.viewpager_trf_ll_10)
				.setOnClickListener(this);
		mTRFView.findViewById(R.id.viewpager_trf_ll_11)
				.setOnClickListener(this);
		mTRFView.findViewById(R.id.viewpager_trf_ll_12)
				.setOnClickListener(this);
		// bind event for woman menu.
		mWomanView.findViewById(R.id.viewpager_woman_ll_1).setOnClickListener(
				this);
		mWomanView.findViewById(R.id.viewpager_woman_ll_2).setOnClickListener(
				this);
		mWomanView.findViewById(R.id.viewpager_woman_ll_3).setOnClickListener(
				this);
		mWomanView.findViewById(R.id.viewpager_woman_ll_4).setOnClickListener(
				this);
		mWomanView.findViewById(R.id.viewpager_woman_ll_5).setOnClickListener(
				this);
		mWomanView.findViewById(R.id.viewpager_woman_ll_6).setOnClickListener(
				this);
		mWomanView.findViewById(R.id.viewpager_woman_ll_7).setOnClickListener(
				this);
		mWomanView.findViewById(R.id.viewpager_woman_ll_8).setOnClickListener(
				this);
		mWomanView.findViewById(R.id.viewpager_woman_ll_9).setOnClickListener(
				this);
		mWomanView.findViewById(R.id.viewpager_woman_ll_10).setOnClickListener(
				this);
		mWomanView.findViewById(R.id.viewpager_woman_ll_11).setOnClickListener(
				this);
		mWomanView.findViewById(R.id.viewpager_woman_ll_12).setOnClickListener(
				this);
		// bind event for man menu.
		mManView.findViewById(R.id.viewpager_man_ll_1).setOnClickListener(this);
		mManView.findViewById(R.id.viewpager_man_ll_2).setOnClickListener(this);
		mManView.findViewById(R.id.viewpager_man_ll_3).setOnClickListener(this);
		mManView.findViewById(R.id.viewpager_man_ll_4).setOnClickListener(this);
		mManView.findViewById(R.id.viewpager_man_ll_5).setOnClickListener(this);
		mManView.findViewById(R.id.viewpager_man_ll_6).setOnClickListener(this);
		mManView.findViewById(R.id.viewpager_man_ll_7).setOnClickListener(this);
		mManView.findViewById(R.id.viewpager_man_ll_8).setOnClickListener(this);
		mManView.findViewById(R.id.viewpager_man_ll_9).setOnClickListener(this);
		mManView.findViewById(R.id.viewpager_man_ll_10)
				.setOnClickListener(this);
		mManView.findViewById(R.id.viewpager_man_ll_11)
				.setOnClickListener(this);
		mManView.findViewById(R.id.viewpager_man_ll_12)
				.setOnClickListener(this);
		// bind event for child menu.
		mChildView.findViewById(R.id.viewpager_child_ll_1).setOnClickListener(
				this);
		mChildView.findViewById(R.id.viewpager_child_ll_2).setOnClickListener(
				this);
		mChildView.findViewById(R.id.viewpager_child_ll_3).setOnClickListener(
				this);
		mChildView.findViewById(R.id.viewpager_child_ll_4).setOnClickListener(
				this);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_discount_catagory_layout,
				container, false);
		initView();
		return mView;
	}

	private void initView() {

		// set title.
		mHeader = mView.findViewById(R.id.discoiuntCatagoryFrag_include);
		if (mHeader != null) {
			System.out.println("mHeader²»Îª¿Õ");
		} else {
			System.out.println("mHeaderÎª¿Õ");
		}
		HeaderText mHeaderText = new HeaderText(mHeader);
		mHeaderText.setLeft(R.drawable.header_back);
		mHeaderText.setTitle(R.string.DiscountCatagoryFragemnt_title);
		mHeaderText.setRightVisiable(true);
		mHeaderText.setRight(R.drawable.navigation_cart_unchoosed);

		mIvBack = (ImageView) mView.findViewById(R.id.header_text_iv_left);
		mIvCart = (ImageView) mView.findViewById(R.id.header_text_iv_right);
		// bind event.
		mIvBack.setOnClickListener(this);
		mIvCart.setOnClickListener(this);

		// RadioGroup navigation.
		mRadioGroup = (RadioGroup) mView
				.findViewById(R.id.discountCatagoryFrag_rg);
		mRadioGroup.setOnCheckedChangeListener(this);

		// RadioButton
		mRbtnTRF = (RadioButton) mView
				.findViewById(R.id.discountCatagoryFrag_rbtn_trf);
		mRbtnWoman = (RadioButton) mView
				.findViewById(R.id.discountCatagoryFrag_rbtn_woman);
		mRbtnMan = (RadioButton) mView
				.findViewById(R.id.discountCatagoryFrag_rbtn_man);
		mRbtnChild = (RadioButton) mView
				.findViewById(R.id.discountCatagoryFrag_rbtn_child);

		// ViewPager
		mViewPager = (ViewPager) mView
				.findViewById(R.id.discountCatagoryFrag_vp);
		// set adpter.
		mViewPager.setAdapter(new DiscountCatagoryFragPagerAdapter());
		// bind event.
		mViewPager.setOnPageChangeListener(this);

	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.discountCatagoryFrag_rbtn_trf:
			if (mViewPager.getCurrentItem() != 0) {
				mViewPager.setCurrentItem(0);
			}

			break;
		case R.id.discountCatagoryFrag_rbtn_woman:
			if (mViewPager.getCurrentItem() != 1) {
				mViewPager.setCurrentItem(1);
			}
			break;
		case R.id.discountCatagoryFrag_rbtn_man:
			if (mViewPager.getCurrentItem() != 2) {
				mViewPager.setCurrentItem(2);
			}
			break;
		case R.id.discountCatagoryFrag_rbtn_child:
			if (mViewPager.getCurrentItem() != 3) {
				mViewPager.setCurrentItem(3);
			}
			break;

		}

	}

	// page change event.
	@Override
	public void onPageScrollStateChanged(int arg0) {

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int id) {
		switch (id) {
		case 0:
			if (!mRbtnTRF.isChecked()) {
				mRbtnTRF.setChecked(true);
			}
			break;
		case 1:
			if (!mRbtnWoman.isChecked()) {
				mRbtnWoman.setChecked(true);
			}
			break;
		case 2:
			if (!mRbtnMan.isChecked()) {
				mRbtnMan.setChecked(true);
			}
			break;
		case 3:
			if (!mRbtnChild.isChecked()) {
				mRbtnChild.setChecked(true);
			}
			break;

		}
	}

	class DiscountCatagoryFragPagerAdapter extends
			android.support.v4.view.PagerAdapter {

		@Override
		public int getCount() {
			return mPagerViewArray.size();
		}

		@Override
		public boolean isViewFromObject(View v, Object o) {
			return v == o;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			container.addView(mPagerViewArray.get(position));
			return mPagerViewArray.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(mPagerViewArray.get(position));
		}

	}

	/**
	 * <pre>
	 *  number represent catagory:
	 *  the first number represent kinds,up to 4.
	 *  the second number represent item,up to 12 or 4 . For example:
	 *  101 1:TRF 01:coat.
	 *  101 102 103 ... 112 .
	 *  201 202 203 ... 212 .
	 *  301 302 303 ... 312 .
	 *  401 402 403 404 .
	 * </pre>
	 * 
	 * 
	 * */
	@Override
	public void onClick(View v) {
		if (intent == null) {
			intent = new Intent(getActivity(),
					DetailForDiscountCatagoryActivity.class);
		}

		switch (v.getId()) {
		case R.id.header_text_iv_left:
			getActivity().finish();
			break;
		case R.id.header_text_iv_right:
			startActivity(new Intent(getActivity(), CartActivity.class));
			break;

		// TRF
		case R.id.viewpager_trf_ll_1:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_TRF_1));

			break;
		case R.id.viewpager_trf_ll_2:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_TRF_2));

			break;
		case R.id.viewpager_trf_ll_3:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_TRF_3));

			break;
		case R.id.viewpager_trf_ll_4:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_TRF_4));
			break;
		case R.id.viewpager_trf_ll_5:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_TRF_5));
			break;
		case R.id.viewpager_trf_ll_6:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_TRF_6));
			break;
		case R.id.viewpager_trf_ll_7:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_TRF_7));
			break;
		case R.id.viewpager_trf_ll_8:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_TRF_8));
			break;
		case R.id.viewpager_trf_ll_9:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_TRF_9));
			break;
		case R.id.viewpager_trf_ll_10:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_TRF_10));
			break;
		case R.id.viewpager_trf_ll_11:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_TRF_11));
			break;
		case R.id.viewpager_trf_ll_12:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_TRF_12));
			break;
		// woman
		case R.id.viewpager_woman_ll_1:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_WOMAN_1));

			break;
		case R.id.viewpager_woman_ll_2:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_WOMAN_2));
			break;
		case R.id.viewpager_woman_ll_3:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_WOMAN_3));
			break;
		case R.id.viewpager_woman_ll_4:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_WOMAN_4));
			break;
		case R.id.viewpager_woman_ll_5:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_WOMAN_5));
			break;
		case R.id.viewpager_woman_ll_6:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_WOMAN_6));
			break;
		case R.id.viewpager_woman_ll_7:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_WOMAN_7));
			break;
		case R.id.viewpager_woman_ll_8:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_WOMAN_8));
			break;
		case R.id.viewpager_woman_ll_9:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_WOMAN_9));
			break;
		case R.id.viewpager_woman_ll_10:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_WOMAN_10));
			break;
		case R.id.viewpager_woman_ll_11:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_WOMAN_11));
			break;
		case R.id.viewpager_woman_ll_12:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_WOMAN_12));
			break;
		// man
		case R.id.viewpager_man_ll_1:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_MAN_1));
			break;
		case R.id.viewpager_man_ll_2:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_MAN_2));
			break;
		case R.id.viewpager_man_ll_3:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_MAN_3));
			break;
		case R.id.viewpager_man_ll_4:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_MAN_4));
			break;
		case R.id.viewpager_man_ll_5:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_MAN_5));
			break;
		case R.id.viewpager_man_ll_6:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_MAN_6));
			break;
		case R.id.viewpager_man_ll_7:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_MAN_7));
			break;
		case R.id.viewpager_man_ll_8:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_MAN_8));
			break;
		case R.id.viewpager_man_ll_9:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_MAN_9));
			break;
		case R.id.viewpager_man_ll_10:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_MAN_10));
			break;
		case R.id.viewpager_man_ll_11:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_MAN_11));
			break;
		case R.id.viewpager_man_ll_12:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_MAN_12));
			break;
		// child
		case R.id.viewpager_child_ll_1:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_CHILD_1));

			break;
		case R.id.viewpager_child_ll_2:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_CHILD_2));
			break;
		case R.id.viewpager_child_ll_3:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_CHILD_3));
			break;
		case R.id.viewpager_child_ll_4:
			startActivity(intent.putExtra("FLAG", Constant.FLAG_CHILD_4));
			break;

		}
		System.out.println("0000:" + intent.getFlags());

	}

}
