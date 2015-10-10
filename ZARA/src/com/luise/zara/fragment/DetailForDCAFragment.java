package com.luise.zara.fragment;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.ParseError;
import com.android.volley.Request.Method;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.luise.zara.CartActivity;
import com.luise.zara.R;
import com.luise.zara.adapter.DetailGridViewAdapter;
import com.luise.zara.bean.Goods;
import com.luise.zara.utils.Constant;
import com.luise.zara.utils.HeaderText;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

public class DetailForDCAFragment extends Fragment implements OnClickListener {
	// ����Intent �ж���ʾ�ĸ�����ҳ
	private View mView, mHeader;
	private Intent mIntent;
	private static int FLAG = 0;// ���ݱ�ǣ�������ʾ��Ҫ�Ĳ��֣������ݼ�

	private GridView mGridView;
	private ImageView mIvBack, mIvCart;
	private DetailGridViewAdapter mAdapter;
	private static final String URL = "http://192.168.99.3:8888/ZARZManager/showGoods";

	private String mResult;
	private String parentClass;
	private String childClass;

	private String title = "";
	private MyThread mThread;

	private List<Goods> goodArray;
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case Constant.REQUST_SUCCESS:
				goodArray = parseResults(mResult);
				if (mAdapter == null) {
					mAdapter = new DetailGridViewAdapter(getActivity(),
							goodArray);
				}
				mGridView.setAdapter(mAdapter);

				break;

			}
		};
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mIntent = getActivity().getIntent();
		FLAG = mIntent.getIntExtra("FLAG", 101);

		System.out.println("�ұ�ִ����1");
		System.out.println("FLAG:" + FLAG);
		switch (FLAG) {
		// TRF

		case Constant.FLAG_TRF_1:
			parentClass = "TRF";
			childClass = "����";
			title = "����";
			break;
		case Constant.FLAG_TRF_2:
			parentClass = "TRF";
			childClass = "����ȹ";
			title = "����ȹ";
			break;
		case Constant.FLAG_TRF_3:
			parentClass = "TRF";
			childClass = "������";
			title = "������";
			break;
		case Constant.FLAG_TRF_4:
			parentClass = "TRF";
			childClass = "����";
			title = "����";
			break;
		case Constant.FLAG_TRF_5:
			parentClass = "TRF";
			childClass = "��װ";
			title = "��װ";
			break;
		case Constant.FLAG_TRF_6:
			parentClass = "TRF";
			childClass = "ţ�п�";
			title = "ţ�п�";
			break;
		case Constant.FLAG_TRF_7:
			parentClass = "TRF";
			childClass = "�̿�";
			title = "�̿�";
			break;
		case Constant.FLAG_TRF_8:
			parentClass = "TRF";
			childClass = "����ȹ";
			title = "����ȹ";
			break;
		case Constant.FLAG_TRF_9:
			parentClass = "TRF";
			childClass = "T��";
			title = "T��";
			break;
		case Constant.FLAG_TRF_10:
			parentClass = "TRF";
			childClass = "����";
			title = "����";
			break;
		case Constant.FLAG_TRF_11:
			parentClass = "TRF";
			childClass = "Ь";
			title = "Ь";
			break;
		case Constant.FLAG_TRF_12:
			parentClass = "TRF";
			childClass = "��";
			title = "��";
			break;
		// woman
		case Constant.FLAG_WOMAN_1:
			parentClass = "Ůʿ";
			childClass = "����";
			title = "����";
			break;
		case Constant.FLAG_WOMAN_2:
			parentClass = "Ůʿ";
			childClass = "����ȹ";
			title = "����ȹ";
			break;
		case Constant.FLAG_WOMAN_3:
			parentClass = "Ůʿ";
			childClass = "������";
			title = "������";
			break;
		case Constant.FLAG_WOMAN_4:
			parentClass = "Ůʿ";
			childClass = "����";
			title = "����";
			break;
		case Constant.FLAG_WOMAN_5:
			parentClass = "Ůʿ";
			childClass = "��װ";
			title = "��װ";
			break;
		case Constant.FLAG_WOMAN_6:
			parentClass = "Ůʿ";
			childClass = "ţ�п�";
			title = "ţ�п�";
			break;
		case Constant.FLAG_WOMAN_7:
			parentClass = "Ůʿ";
			childClass = "�̿�";
			title = "�̿�";
			break;
		case Constant.FLAG_WOMAN_8:
			parentClass = "Ůʿ";
			childClass = "����ȹ";
			title = "����ȹ";
			break;
		case Constant.FLAG_WOMAN_9:
			parentClass = "Ůʿ";
			childClass = "T��";
			title = "T��";
			break;
		case Constant.FLAG_WOMAN_10:
			parentClass = "Ůʿ";
			childClass = "����";
			title = "����";
			break;
		case Constant.FLAG_WOMAN_11:
			parentClass = "Ůʿ";
			childClass = "Ь";
			title = "Ь";
			break;
		case Constant.FLAG_WOMAN_12:
			parentClass = "Ůʿ";
			childClass = "��";
			title = "��";
			break;
		// man
		case Constant.FLAG_MAN_1:
			parentClass = "��ʿ";
			childClass = "�п�����";
			title = "�п�����";
			break;
		case Constant.FLAG_MAN_2:
			parentClass = "��ʿ";
			childClass = "����";
			title = "����";
			break;
		case Constant.FLAG_MAN_3:
			parentClass = "��ʿ";
			childClass = "������װ";
			title = "������װ";
			break;
		case Constant.FLAG_MAN_4:
			parentClass = "��ʿ";
			childClass = "����";
			title = "����";
			break;
		case Constant.FLAG_MAN_5:
			parentClass = "��ʿ";
			childClass = "��װ";
			title = "��װ";
			break;
		case Constant.FLAG_MAN_6:
			parentClass = "��ʿ";
			childClass = "ţ�п�";
			title = "ţ�п�";
			break;
		case Constant.FLAG_MAN_7:
			parentClass = "��ʿ";
			childClass = "���ж̿�";
			title = "���ж̿�";
			break;
		case Constant.FLAG_MAN_8:
			parentClass = "��ʿ";
			childClass = "��֯��";
			title = "��֯��";
			break;
		case Constant.FLAG_MAN_9:
			parentClass = "��ʿ";
			childClass = "����";
			title = "����";
			break;
		case Constant.FLAG_MAN_10:
			parentClass = "��ʿ";
			childClass = "��������";
			title = "��������";
			break;
		case Constant.FLAG_MAN_11:
			parentClass = "��ʿ";
			childClass = "Ь";
			title = "Ь";
			break;
		case Constant.FLAG_MAN_12:
			parentClass = "��ʿ";
			childClass = "��";
			title = "��";
			break;
		// child
		case Constant.FLAG_CHILD_1:
			parentClass = "��ͯ";
			childClass = "Ůͯ";
			title = "Ůͯ";
			break;
		case Constant.FLAG_CHILD_2:
			parentClass = "��ͯ";
			childClass = "��ͯ";
			title = "��ͯ";
			break;
		case Constant.FLAG_CHILD_3:
			parentClass = "��ͯ";
			childClass = "ŮӤ";
			title = "ŮӤ";
			break;
		case Constant.FLAG_CHILD_4:
			parentClass = "��ͯ";
			childClass = "��Ӥ";
			title = "��Ӥ";
			break;
		}
		System.out.println("parentClass:" + parentClass + ",childClass:"
				+ childClass + ",title:" + title);

		mThread = new MyThread();
		mThread.start();
	}

	protected List<Goods> parseResults(String result) {
		List<Goods> goodsArray = new ArrayList<Goods>();
		try {
			JSONArray jsonArray = new JSONArray(result);
			int length = jsonArray.length();
			JSONObject jsonObject;
			Goods good;

			for (int i = 0; i < length; i++) {
				jsonObject = jsonArray.getJSONObject(i);
				good = new Goods();
				good.setGoodsId(jsonObject.getInt("goodsId"));
				good.setGoodsImage(jsonObject.getString("goodsImage"));
				good.setGoodsName(jsonObject.getString("goodsName"));
				good.setGoodsPrice(jsonObject.getString("goodsPrice"));
				good.setGoodsDiscount(jsonObject.getString("goodsDiscount"));

				goodsArray.add(good);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return goodsArray;

	}

	private void getJson(final String parentClass, final String goodsClass) {
		HttpPost httpPost = new HttpPost(URL);
		HttpClient httpClient = new DefaultHttpClient();
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("parentClass", parentClass));
		list.add(new BasicNameValuePair("goodsClass", goodsClass));
		UrlEncodedFormEntity entity;

		try {
			entity = new UrlEncodedFormEntity(list, "UTF-8");
			httpPost.setEntity(entity);
			HttpResponse httpResponse = httpClient.execute(httpPost);
			int code = httpResponse.getStatusLine().getStatusCode();
			if (code == 200) {
				HttpEntity httpEntity = httpResponse.getEntity();
				mResult = EntityUtils.toString(httpEntity, "UTF-8");
				System.out.println("mResult" + mResult);
				mHandler.sendEmptyMessage(Constant.REQUST_SUCCESS);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		System.out.println("�ұ�ִ����2");

		mView = inflater.inflate(R.layout.detail_for_dca_layout, container,
				false);
		// set title.
		mHeader = mView.findViewById(R.id.detailFrag_include);
		HeaderText mHeaderText = new HeaderText(mHeader);
		mHeaderText.setLeft(R.drawable.header_back);
		mHeaderText.setTitle(title);
		mHeaderText.setRightVisiable(true);
		mHeaderText.setRight(R.drawable.navigation_cart_unchoosed);

		mIvBack = (ImageView) mView.findViewById(R.id.header_text_iv_left);
		mIvCart = (ImageView) mView.findViewById(R.id.header_text_iv_right);
		// bind event.
		mIvBack.setOnClickListener(this);
		mIvCart.setOnClickListener(this);

		return mView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		mGridView = (GridView) view.findViewById(R.id.detailFrag_gv);

	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		mView = null;
		mAdapter = null;
		goodArray = null;
		mIntent = null;
		mThread = null;
		mResult = null;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.header_text_iv_left:
			getActivity().finish();
			break;
		case R.id.header_text_iv_right:
			startActivity(new Intent(getActivity(), CartActivity.class));
			break;

		}

	}

	class MyThread extends Thread {
		@Override
		public void run() {
			getJson(parentClass, childClass);
		}
	}

}
