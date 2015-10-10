package com.luise.zara;

import com.luise.zara.bean.User;
import com.luise.zara.fragment.CartFragment;
import com.luise.zara.fragment.CatagoryFragment;
import com.luise.zara.fragment.FashionFragment;
import com.luise.zara.fragment.HomeFragment;
import com.luise.zara.fragment.MyFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Window;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainFragActivty extends FragmentActivity implements
		OnCheckedChangeListener {
	// The tag for every fragment.
	public static final String TAG_HOME_FRAGMENT = "home_frag";
	public static final String TAG_CATAGORY_FRAGMENT = "catagory_frag";
	public static final String TAG_FASHION_FRAGMENT = "fashion_frag";
	public static final String TAG_CART_FRAGMENT = "cart_frag";
	public static final String TAG_MY_FRAGMENT = "my_frag";
	// declare fragment.
	private Fragment mFragMy;
	private Fragment mFragHome;
	private Fragment mFragCatagory;
	private Fragment mFragFashion;
	private Fragment mFragCart;
	// declare FragmentManager ,only one.
	private FragmentManager fm;
	// declare RadioGroup.
	private RadioGroup mRadioGroup;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.mainfragactivity_mian);

		mRadioGroup = (RadioGroup) findViewById(R.id.navigation_rg_footer);
		// bind events.
		mRadioGroup.setOnCheckedChangeListener(this);

		// instantiate a fragment for My.
		mFragMy = new MyFragment();
		mFragHome = new HomeFragment();
		mFragCatagory = new CatagoryFragment();
		mFragFashion = new FashionFragment();
		mFragCart = new CartFragment();
		// get Fragment Manager.
		fm = getSupportFragmentManager();
		// jump to the default Fragment,MyFragment.
		if (fm.findFragmentByTag(TAG_MY_FRAGMENT) == null) {
			fm.beginTransaction()
					.add(R.id.mainFragAc_fl_contain, mFragMy, TAG_MY_FRAGMENT)
					.commit();
		}

	}

	// response to click event.
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		switch (checkedId) {
		case R.id.navigation_rb_home:
			if (fm.findFragmentByTag(TAG_HOME_FRAGMENT) == null) {
				fm.beginTransaction()
						.replace(R.id.mainFragAc_fl_contain, mFragHome)
						.commit();
			}
			break;
		case R.id.navigation_rb_catagory:
			if (fm.findFragmentByTag(TAG_CATAGORY_FRAGMENT) == null) {
				fm.beginTransaction()
						.replace(R.id.mainFragAc_fl_contain, mFragCatagory)
						.commit();
			}
			break;
		case R.id.navigation_rb_fashion:
			if (fm.findFragmentByTag(TAG_FASHION_FRAGMENT) == null) {
				fm.beginTransaction()
						.replace(R.id.mainFragAc_fl_contain, mFragFashion)
						.commit();
			}
			break;
		case R.id.navigation_rb_cart:
			if (fm.findFragmentByTag(TAG_CART_FRAGMENT) == null) {
				fm.beginTransaction()
						.replace(R.id.mainFragAc_fl_contain, mFragCart)
						.commit();
			}
			break;
		case R.id.navigation_rb_my:
			if (fm.findFragmentByTag(TAG_MY_FRAGMENT) == null) {
				fm.beginTransaction()
						.replace(R.id.mainFragAc_fl_contain, mFragMy).commit();
			}
			break;

		}

	}

}
