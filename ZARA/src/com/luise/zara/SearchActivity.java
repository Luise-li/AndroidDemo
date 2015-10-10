package com.luise.zara;

import com.luise.zara.fragment.SearchFragment;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class SearchActivity extends FragmentActivity {
	private static final String TAG = "SearchActivity";

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);

		if (getSupportFragmentManager().findFragmentByTag(TAG) == null) {
			getSupportFragmentManager().beginTransaction()
					.add(android.R.id.content, new SearchFragment(), TAG)
					.commit();

		}

	}

}
