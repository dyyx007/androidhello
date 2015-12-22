package com.dyyx.androidhello.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.dyyx.androidhello.util.LogUtil;

public class My01Service extends Service {

	private static final String TAG = "My01Service";

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		LogUtil.log(TAG, "onCreate," + this);
		//SAXParserFactory x;
		
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		LogUtil.log(TAG, "onStartCommand," + this);
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		LogUtil.log(TAG, "onDestroy," + this);
	}

	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		LogUtil.log(TAG, "onStart," + this);
	}

}
