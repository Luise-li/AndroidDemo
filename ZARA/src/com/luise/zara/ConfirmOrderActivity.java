package com.luise.zara;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.luise.zara.fragment.ConfirmFragment;

public class ConfirmOrderActivity extends FragmentActivity {
	private static final String TAG = "ConfirmOrderActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getSupportFragmentManager().findFragmentByTag(TAG) == null) {
			getSupportFragmentManager().beginTransaction()
					.add(android.R.id.content, new ConfirmFragment(), TAG)
					.commit();

		}

	}

}
