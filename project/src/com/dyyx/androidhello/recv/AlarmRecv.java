package com.dyyx.androidhello.recv;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.dyyx.androidhello.service.LongRunService;
import com.dyyx.androidhello.util.LogUtil;

public class AlarmRecv extends BroadcastReceiver {
	
	 static final String TAG = "AlarmRecv";
	
	 @Override   
     public void onReceive( Context context, Intent intent ) {   

		 LogUtil.log(TAG, "AlarmRecv.onReceive,"+intent);
		 Intent i = new Intent(context, LongRunService.class);
		 
		 context.startService(i);
       }   
}