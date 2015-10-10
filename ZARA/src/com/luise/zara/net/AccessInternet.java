package com.luise.zara.net;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.luise.zara.utils.Constant;

public class AccessInternet {
	private Context mContext;
	private JSONObject mJsonObject;
	private JSONArray mJsonArray;
	private Handler mHandler;

	public AccessInternet(Context context, Handler handler) {
		mContext = context;
		mHandler = handler;
	}

	public JSONObject accessByGetForJSONObject(String url) {
		// create a request queue.
		RequestQueue mQueue = Volley.newRequestQueue(mContext);
		//
		JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,
				new Response.Listener<JSONObject>() {

					@Override
					public void onResponse(JSONObject jsonObject) {
						mJsonObject = jsonObject;
						
						
						System.out.println("AccessInternet÷–£∫" + jsonObject);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("JsonObject request error:", error.toString());
					}
				});

		mQueue.add(jsonObjectRequest);

		return mJsonObject;
	}

	public JSONArray accessByGetForJSONArray(String url) {
		RequestQueue mQueue = Volley.newRequestQueue(mContext);

		JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url,
				new Response.Listener<JSONArray>() {

					@Override
					public void onResponse(JSONArray jsonArray) {
						mJsonArray = jsonArray;

						System.out.println("AccessInternet÷–£∫" + jsonArray);
					}
				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("JsonArray request error:", error.toString());

					}
				});

		mQueue.add(jsonArrayRequest);

		return mJsonArray;

	}
	
	

}
