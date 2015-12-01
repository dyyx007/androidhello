package com.dyyx.androidhello;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class LayoutTestActivity extends Activity {
	
	static Map<String,Integer> map = new HashMap<String,Integer>();
	
	static{
		map.put("LinerLayout",R.layout.linear_test);
		map.put("FrameLayout",R.layout.frame_test);
		map.put("TableLayout",R.layout.table_test);
		map.put("RelativeLayout",R.layout.relative_test);
		map.put("AbsoluteLayout",R.layout.absolute_test);
		map.put("TableLayout2", R.layout.table_test2);
	}
	
	@Override  
    protected void onNewIntent(Intent intent) {  
        super.onNewIntent(intent);  
        Log.i("LayoutTestActivity"," onNewIntent "+ intent+","+this);
    }  
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_main);
		//setContentView(R.layout.table_test);
		Log.i("LayoutTestActivity","onCreate "+this);
		String layout = HelloApp.selectedLayout;
		if(layout==null){
			layout = "LinerLayout";
		}
		Integer flag = map.get(layout);
		if(flag==null){
			flag = R.layout.linear_test;
		}
		setContentView(flag.intValue());
		
	
		
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		Log.i("LayoutTestActivity","onStart "+this);
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		Log.i("LayoutTestActivity","onResume "+this);
	}

	@Override
	protected void onPause() {
		super.onPause();
		Log.i("LayoutTestActivity","onPause "+this);
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		Log.i("LayoutTestActivity","onStop "+this);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.i("LayoutTestActivity","onDestroy "+this);
	}
	
}
