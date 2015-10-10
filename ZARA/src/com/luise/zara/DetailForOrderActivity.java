package com.luise.zara;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.luise.zara.fragment.DetailForOrderFragment;

public class DetailForOrderActivity extends FragmentActivity {
	private static final String TAG = "DetailForOrderActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getSupportFragmentManager().findFragmentByTag(TAG) == null) {
			getSupportFragmentManager()
					.beginTransaction()
					.add(android.R.id.content, new DetailForOrderFragment(),
							TAG).commit();

		}

	}

}
