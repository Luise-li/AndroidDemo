package com.luise.zara.fragment;

import java.util.ArrayList;

import com.luise.zara.CartActivity;
import com.luise.zara.R;
import com.luise.zara.SearchActivity;
import com.luise.zara.adapter.FashionListViewAdapter;
import com.luise.zara.bean.FashionGoods;
import com.luise.zara.utils.HeaderText;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class FashionFragment extends Fragment implements OnClickListener,
		OnCheckedChangeListener, OnPageChangeListener {
	private View mView, mHeader;
	private RadioGroup mRadioGroup;
	private RadioButton mRbtnPrintFlower, mRbtnWhite, mRbtnDanLing;

	private View mPrintFlower, mWhite, mDanLing;
	private ArrayList<View> mViewPagerArray;

	private ViewPager mViewPager;

	private ListView mLvPrintFlower, mLvWhite, mLvDanNing;
	private FashionListViewAdapter mFLVAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// instatiante collection object.
		mViewPagerArray = new ArrayList<View>();
		LayoutInflater inflater = getLayoutInflater(savedInstanceState);
		// inflate layout for every page.
		mPrintFlower = inflater
				.inflate(R.layout.viewpager_fashion_layout, null);
		mWhite = inflater.inflate(R.layout.viewpager_fashion_layout, null);
		mDanLing = inflater.inflate(R.layout.viewpager_fashion_layout, null);

		mLvPrintFlower = (ListView) mPrintFlower
				.findViewById(R.id.viewpager_fashion_lv);
		mLvWhite = (ListView) mWhite.findViewById(R.id.viewpager_fashion_lv);
		mLvDanNing = (ListView) mDanLing
				.findViewById(R.id.viewpager_fashion_lv);

		FashionListViewAdapter mFLVPrintFlowerAdapter = new FashionListViewAdapter(
				getActivity(), getData(0));
		mLvPrintFlower.setAdapter(mFLVPrintFlowerAdapter);
		FashionListViewAdapter mFLVWriteAdapter = new FashionListViewAdapter(
				getActivity(), getData(1));
		mLvWhite.setAdapter(mFLVWriteAdapter);
		FashionListViewAdapter mFLVDanNingAdapter = new FashionListViewAdapter(
				getActivity(), getData(2));
		mLvDanNing.setAdapter(mFLVDanNingAdapter);

		// add pages to collection.
		mViewPagerArray.add(mPrintFlower);
		mViewPagerArray.add(mWhite);
		mViewPagerArray.add(mDanLing);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_fashion_layout, container,
				false);

		return mView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		// set title.
		mHeader = view.findViewById(R.id.fashionFrag_include);
		HeaderText mHeaderText = new HeaderText(mHeader);
		mHeaderText.setLeft(R.drawable.header_search);
		mHeaderText.setTitle(R.string.FashionFragemnt_title);
		mHeaderText.setRightVisiable(true);
		mHeaderText.setRight(R.drawable.navigation_cart_unchoosed);
		// bind event for title.
		view.findViewById(R.id.header_text_iv_left).setOnClickListener(this);
		view.findViewById(R.id.header_text_iv_right).setOnClickListener(this);

		mRadioGroup = (RadioGroup) view.findViewById(R.id.fashionFrag_rg);
		// bind event for RadioGroup.
		mRadioGroup.setOnCheckedChangeListener(this);

		mRbtnPrintFlower = (RadioButton) view
				.findViewById(R.id.fashionFrag_rbtn_printflower);
		mRbtnWhite = (RadioButton) view
				.findViewById(R.id.fashionFrag_rbtn_white);
		mRbtnDanLing = (RadioButton) view
				.findViewById(R.id.fashionFrag_rbtn_danling);

		mViewPager = (ViewPager) view.findViewById(R.id.fashionFrag_vp);
		// set Adapter.
		mViewPager.setAdapter(new FashionPagerAdapter());
		// bind event.
		mViewPager.setOnPageChangeListener(this);

	}

	// deal with click event.
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.header_text_iv_left:
			startActivity(new Intent(getActivity(), SearchActivity.class));
			break;
		case R.id.header_text_iv_right:
			startActivity(new Intent(getActivity(), CartActivity.class));
			break;

		}

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.fashionFrag_rbtn_printflower:
			if (mViewPager.getCurrentItem() != 0) {
				mViewPager.setCurrentItem(0);
			}

			break;
		case R.id.fashionFrag_rbtn_white:
			if (mViewPager.getCurrentItem() != 1) {
				mViewPager.setCurrentItem(1);
			}
			break;
		case R.id.fashionFrag_rbtn_danling:
			if (mViewPager.getCurrentItem() != 2) {
				mViewPager.setCurrentItem(2);
			}
			break;

		}
	}

	// deal with page change event.
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
			if (!mRbtnPrintFlower.isChecked()) {
				mRbtnPrintFlower.setChecked(true);
			}

			break;
		case 1:
			if (!mRbtnWhite.isChecked()) {
				mRbtnWhite.setChecked(true);
			}

			break;
		case 2:
			if (!mRbtnDanLing.isChecked()) {
				mRbtnDanLing.setChecked(true);
			}

			break;

		}

	}

	class FashionPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return mViewPagerArray.size();
		}

		@Override
		public boolean isViewFromObject(View v, Object o) {
			return v == o;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			container.addView(mViewPagerArray.get(position));
			return mViewPagerArray.get(position);
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(mViewPagerArray.get(position));
		}

	}

	public ArrayList<FashionGoods> getData(int position) {

		ArrayList<FashionGoods> arr = new ArrayList<FashionGoods>();
		switch (position) {
		case 0:
			FashionGoods printFlowerGoods = new FashionGoods();
			printFlowerGoods.setId(R.drawable.fashion_printflower_1);
			printFlowerGoods.setName("夏日打底印花");

			FashionGoods printFlowerGoods2 = new FashionGoods();
			printFlowerGoods2.setId(R.drawable.fashion_printflower_2);
			printFlowerGoods2.setName("印花上衣/花朵印花短裤");
			arr.add(printFlowerGoods);
			arr.add(printFlowerGoods2);
			arr.add(printFlowerGoods);
			arr.add(printFlowerGoods2);
			arr.add(printFlowerGoods);
			arr.add(printFlowerGoods2);
			break;
		case 1:
			FashionGoods whiteGoods = new FashionGoods();
			whiteGoods.setId(R.drawable.fashion_white_1);
			whiteGoods.setName("白色透气装");

			FashionGoods whiteGoods2 = new FashionGoods();
			whiteGoods2.setId(R.drawable.fashion_white_2);
			whiteGoods2.setName("透气上衣/透气印花短裙");
			arr.add(whiteGoods);
			arr.add(whiteGoods2);
			arr.add(whiteGoods);
			arr.add(whiteGoods2);
			arr.add(whiteGoods);
			arr.add(whiteGoods2);

			break;
		case 2:
			FashionGoods danNingGoods = new FashionGoods();
			danNingGoods.setId(R.drawable.fashion_danning_1);
			danNingGoods.setName("CROP & CROP");

			FashionGoods danNingGoods2 = new FashionGoods();
			danNingGoods2.setId(R.drawable.fashion_danning_2);
			danNingGoods2.setName("NEW DUNGAREES");
			arr.add(danNingGoods);
			arr.add(danNingGoods2);
			arr.add(danNingGoods);
			arr.add(danNingGoods2);
			arr.add(danNingGoods);
			arr.add(danNingGoods2);
			break;

		}

		return arr;

	}

}
