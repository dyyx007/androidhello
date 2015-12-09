package com.dyyx.androidhello.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import com.dyyx.androidhello.R;
import com.dyyx.androidhello.util.LogUtil;

public class MusicPlayerBindService extends Service{
	
	private static final String TAG = "MusicPlayerBindService";
	private MediaPlayer mediaPlayer;
	
	
	public final IBinder binder = new MyBinder();
	
	public class MyBinder extends Binder {
		public MusicPlayerBindService getService() {
			return MusicPlayerBindService.this;
		}
	}
	
	
	@Override
	public IBinder onBind(Intent intent) {
		LogUtil.log(TAG, "onBind,"+this);
		//play();
		return binder;
	}
	

	@Override
	public void onCreate() {
		LogUtil.log(TAG, "onCreate,"+this);
		if (mediaPlayer == null) {
			mediaPlayer = MediaPlayer.create(this, R.raw.canon);
			mediaPlayer.setLooping(false);
		}
	}
	@Override
	public void onDestroy() {
		LogUtil.log(TAG, "onDestroy,"+this);
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();	
		}
	}
	@Override
	public void onStart(Intent intent, int startId) {
		LogUtil.log(TAG, "onStart,"+this);
		
	}
	public void play() {
		if (!mediaPlayer.isPlaying()) {
			mediaPlayer.start();
		}
	}
	public void pause() {
		if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			mediaPlayer.pause();
		}
	}
	public void stop() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			try {
				// 在调用stop后如果需要再次通过start进行播放,需要之前调用prepare函数
				mediaPlayer.prepare();
			} catch (Throwable e) {
				//ex.printStackTrace();
				LogUtil.log(TAG, "stop error", e);
			}
		}
	}
}