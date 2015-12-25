package com.dyyx.androidhello;

import android.os.Bundle;
import android.view.View;

import com.dyyx.androidhello.base.BaseActivity;

public class SpActivity extends BaseActivity {

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sp);

		

	}

	public void clickHandle(View view) {
		int vid = view.getId();
		if (vid == R.id.btnGetAppDirInfo) {

		
			return;
		}

		

	}


}
