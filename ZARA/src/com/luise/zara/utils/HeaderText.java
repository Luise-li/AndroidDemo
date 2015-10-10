package com.luise.zara.utils;

import com.luise.zara.R;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HeaderText {
	private ImageView left;
	private ImageView right;
	private TextView title;

	public HeaderText(View v) {
		left = (ImageView) v.findViewById(R.id.header_text_iv_left);
		right = (ImageView) v.findViewById(R.id.header_text_iv_right);
		title = (TextView) v.findViewById(R.id.header_text_tv_title);
	}

	public ImageView getLeft() {
		return left;
	}

	public void setLeft(int left) {
		this.left.setImageResource(left);
	}

	public ImageView getRight() {
		return right;
	}

	public void setRight(int right) {
		this.right.setImageResource(right);
	}

	public void setRightVisiable(boolean isVisiable) {
		if (isVisiable) {
			this.right.setVisibility(View.VISIBLE);
			return;
		}
		this.right.setVisibility(View.INVISIBLE);
	}

	public TextView getTitle() {
		return title;
	}

	public void setTitle(int title) {
		this.title.setText(title);
	}

	public void setTitle(String title) {
		this.title.setText(title);
	}

}
