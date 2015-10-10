package com.luise.zara.fragment;

import java.util.List;

import com.luise.zara.R;
import com.luise.zara.adapter.EditListViewAdapter;
import com.luise.zara.bean.Cart;
import com.luise.zara.utils.HeaderText;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

public class EditFragment extends Fragment implements OnClickListener {
	private View mView, mHeader;
	private List<Cart> list;
	private ListView mListView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getActivity().getIntent();
		list = intent.getParcelableArrayListExtra("cartGoods");
		System.out.println("list:1   " + list);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.fragment_edit_layout, container,
				false);
		return mView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		// header bind event.
		view.findViewById(R.id.edit_header_text_iv_left).setOnClickListener(
				this);
		view.findViewById(R.id.edit_header_text_tv_right).setOnClickListener(
				this);
		mListView = (ListView) view.findViewById(R.id.editFrag_lv);

		System.out.println("list:::::" + list);
		EditListViewAdapter editListViewAdapter = new EditListViewAdapter(
				getActivity(), list);
		mListView.setAdapter(editListViewAdapter);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.edit_header_text_iv_left:
			getActivity().finish();
			break;
		case R.id.edit_header_text_tv_right:
			// complete
			System.out.println("list:" + list);
			break;

		}

	}

}
