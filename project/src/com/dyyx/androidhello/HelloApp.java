package com.dyyx.androidhello;

import android.app.Application;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;

import com.dyyx.androidhello.recv.TestLocalRecv;
import com.dyyx.androidhello.util.DyyxCommUtil;
import com.dyyx.androidhello.util.LogUtil;

public class HelloApp extends Application {
	
	public static String selectedLayout;
	
	public static LocalBroadcastManager lbm;
	TestLocalRecv testLocalRecv = new TestLocalRecv();
	
	
	static final String TAG = "HelloApp";

	@Override
	public void onCreate() {

		super.onCreate();
        LogUtil.log(TAG, "HelloApp.onCreate");
        
        lbm = LocalBroadcastManager.getInstance(this);
        
        IntentFilter filter = new IntentFilter();
        filter.addAction(DyyxCommUtil.BROADCAST_TEST_LOCAL);
        
        lbm.registerReceiver(testLocalRecv, filter);
        
        LogUtil.log(TAG, "LocalBroadcastManager.registerReceiver testLocalRecv");
        
        
	}

	@Override
	public void onTerminate() {

		super.onTerminate();
		  LogUtil.log("HelloApp", "HelloApp.onTerminate");
        lbm.unregisterReceiver(testLocalRecv);
	}

}
