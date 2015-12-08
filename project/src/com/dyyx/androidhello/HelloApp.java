package com.dyyx.androidhello;

import android.app.Application;

import com.dyyx.androidhello.util.LogUtil;

public class HelloApp extends Application {
	
	public static String selectedLayout;
	
	

	@Override
	public void onCreate() {

		super.onCreate();
        LogUtil.log("HelloApp", "HelloApp.onCreate");
	}

	@Override
	public void onTerminate() {

		super.onTerminate();
		  LogUtil.log("HelloApp", "HelloApp.onTerminate");

	}

}
