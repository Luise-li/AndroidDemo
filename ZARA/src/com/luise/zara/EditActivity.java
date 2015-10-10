package com.luise.zara;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.luise.zara.fragment.EditFragment;

public class EditActivity extends FragmentActivity {
	private static final String TAG = "EditActivity";

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		if (getSupportFragmentManager().findFragmentByTag(TAG) == null) {
			getSupportFragmentManager().beginTransaction()
					.add(android.R.id.content, new EditFragment(), TAG)
					.commit();

		}

	}

}
