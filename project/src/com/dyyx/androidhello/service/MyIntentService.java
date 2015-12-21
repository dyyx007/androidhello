package com.dyyx.androidhello.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.dyyx.androidhello.util.LogUtil;

public class MyIntentService extends IntentService {

	private static final String TAG = "MyIntentService";

	public MyIntentService() {
		super("MyIntentService"); 
	}

	@Override
	protected void onHandleIntent(Intent intent) {

		LogUtil.log(TAG, "Thread id " + Thread.currentThread().getId()+","+this);
		try{
		Thread.sleep(1000L);
		}catch(Throwable e){
			
		}
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "onDestroy,"+this);
	}

}
