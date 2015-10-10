package com.luise.zara;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.luise.zara.fragment.DetailForDCAFragment;

public class DetailForDiscountCatagoryActivity extends FragmentActivity {
	private static final String TAG = "DetailForDiscountCatagoryActivity";
	private DetailForDCAFragment detailForDCAFragment = null;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);

	}

	@Override
	protected void onStart() {
		super.onStart();
		if (getSupportFragmentManager().findFragmentByTag(TAG) == null) {
			detailForDCAFragment = new DetailForDCAFragment();
			getSupportFragmentManager().beginTransaction()
					.add(android.R.id.content, detailForDCAFragment, TAG)
					.commit();

		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		getSupportFragmentManager().beginTransaction().remove(
				detailForDCAFragment);
	}
}
