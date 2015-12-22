package com.dyyx.androidhello.service;

import java.util.Date;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

import com.dyyx.androidhello.recv.AlarmRecv;
import com.dyyx.androidhello.util.LogUtil;

public class LongRunService extends Service {

	private static final String TAG = "LongRunService";

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		LogUtil.log(TAG, "onCreate," + this);

	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		LogUtil.log(TAG, "onStartCommand," + this);

		new Thread(new Runnable() {
			@Override
			public void run() {
				LogUtil.log("LongRunService", "LongRunService executed at " + new Date().toString());
			}
		}).start();
		AlarmManager manager = (AlarmManager) getSystemService(ALARM_SERVICE);
		long oneMinute =   60L * 1000L; 
		long triggerAtTime = SystemClock.elapsedRealtime() + oneMinute;
		Intent i = new Intent(this, AlarmRecv.class);
		PendingIntent pi = PendingIntent.getBroadcast(this, 0, i, 0);
		manager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pi);

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
