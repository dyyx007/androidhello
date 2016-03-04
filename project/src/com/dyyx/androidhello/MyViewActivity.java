package com.dyyx.androidhello;

import android.os.Bundle;

import com.dyyx.androidhello.base.BaseActivity;
//import android.support.v7.app.ActionBarActivity;

//public class MainActivity extends ActionBarActivity {
public class MyViewActivity extends BaseActivity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.myview);
		
		
	}

	
}
