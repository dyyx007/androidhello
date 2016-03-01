package com.dyyx.androidhello;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dyyx.androidhello.base.BaseActivity;

public class ScrollActivity extends BaseActivity {

	
	LinearLayout layout;

    Button btnScrollTo;

    Button btnScrollBy;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.scroll);
		
		layout = (LinearLayout) findViewById(R.id.layoutScroll);
		btnScrollTo = (Button) findViewById(R.id.btnScrollTo);
		btnScrollBy = (Button) findViewById(R.id.btnScrollBy);

	}

	public void clickHandle(View view) {
		int vid = view.getId();

		if (vid == R.id.btnScrollBy) {
			
			layout.scrollBy(-60, -100);
            return;
		}
		
		if (vid == R.id.btnScrollTo) {
			layout.scrollTo(-60, -100);
			return;
		}

	}

}
