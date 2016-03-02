package com.dyyx.androidhello;

import android.os.Bundle;
import android.view.View;

import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.util.DialogUtil;

public class ScrollLayoutActivity extends BaseActivity {


	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scroll_layout);
		
	    

	}

	public void clickHandle(View view) {
		int vid = view.getId();

		if (vid == R.id.btn01) {
			
			
			DialogUtil.show(this, "title", "view1 button click", 0, "ok");
		
            return;
		}
		
		if (vid == R.id.btn02) {
			DialogUtil.show(this, "title", "view2 button click", 0, "ok");
			return;
		}
		
		
		if (vid == R.id.btn03) {
			DialogUtil.show(this, "title", "view3 button click", 0, "ok");
			return;
		}

	}


}
