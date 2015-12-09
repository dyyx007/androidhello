package com.dyyx.androidhello.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;

import com.dyyx.androidhello.R;
import com.dyyx.androidhello.util.LogUtil;

public class MusicPlayerService extends Service{
	
	private static final String TAG = "MusicPlayerService";
	private MediaPlayer mediaPlayer;
	
	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}
	@Override
	public void onCreate() {
		LogUtil.log(TAG, "onCreate,"+this);
		if (mediaPlayer == null) {
			mediaPlayer = MediaPlayer.create(this, R.raw.music);
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
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				int op = bundle.getInt("op");
				switch (op) {
				case 1:
					play();
					break;
				case 2:
					stop();
					break;
				case 3:
					pause();
					break;
				}
			}
		}
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