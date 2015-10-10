package com.luise.zara;

import com.luise.zara.utils.HeaderText;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MessageActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_message);

		initView();

	}

	private void initView() {
		// set title.
		View mHeader = findViewById(R.id.messageAc_include);
		HeaderText mHeaderText = new HeaderText(mHeader);
		mHeaderText.setLeft(R.drawable.header_back);
		mHeaderText.setTitle(R.string.messageAc_title);
		mHeaderText.setRightVisiable(false);

		// bind event for title.
		ImageView mIvLeft = (ImageView) findViewById(R.id.header_text_iv_left);
		mIvLeft.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.header_text_iv_left:
			finish();
			break;

		}

	}
}
