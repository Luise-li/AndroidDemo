package com.luise.zara;

import org.apache.http.client.HttpClient;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.os.Bundle;

public class TestActivity extends Activity {
	private static final String URL = "http://192.168.99.3:8888/ZARZManager/newGoods";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		RequestQueue mQueue = Volley.newRequestQueue(this);

		new Thread() {

		}.start();

	}

}
