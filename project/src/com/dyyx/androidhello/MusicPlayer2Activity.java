package com.dyyx.androidhello;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.service.MusicPlayerBindService;
import com.dyyx.androidhello.util.DyyxCommUtil;
import com.dyyx.androidhello.util.LogUtil;

public class MusicPlayer2Activity extends BaseActivity implements OnClickListener  {

    Button btnPlay;
    Button btnStop;
    Button btnPause;
    Button btnClose;
    Button btnExit;
    
    MusicPlayerBindService musicService;
    
    
	final static String TAG = "MusicPlayer2Activity";
	final static String SERVICE_NAME="com.dyyx.androidhello.service.MusicPlayerBindService";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.music_player);

		btnPlay = (Button) findViewById(R.id.btnPlay);
		btnStop = (Button) findViewById(R.id.btnStop);
		btnPause = (Button) findViewById(R.id.btnPause);
		btnClose = (Button) findViewById(R.id.btnClose);
		btnExit = (Button) findViewById(R.id.btnExit);
		
		btnPlay.setOnClickListener(this);
		btnStop.setOnClickListener(this);
		btnPause.setOnClickListener(this);
		btnClose.setOnClickListener(this);
		btnExit.setOnClickListener(this);
		
		
	
		Intent intent = new Intent(SERVICE_NAME);
		startService(intent);
		
		
		bindService(intent, sc, Context.BIND_AUTO_CREATE);

	}

	
	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.btnPlay:
			LogUtil.log(TAG, "onClick: playing muic");
			musicService.play();
			break;
		case R.id.btnStop:
			
			Toast toast=Toast.makeText(getApplicationContext(), DyyxCommUtil.NO_STOP_MSG, Toast.LENGTH_SHORT); 
			toast.show();
			
			//LogUtil.log(TAG, "onClick: stoping music");
			//musicService.stop();
			break;
		case R.id.btnPause:
			LogUtil.log(TAG, "onClick: pausing music");
			musicService.pause();
			break;
		case R.id.btnClose:
			LogUtil.log(TAG, "onClick: close");
			this.finish();
			break;
		case R.id.btnExit:
			LogUtil.log(TAG, "onClick: exit");
			Intent intent = new Intent(SERVICE_NAME);
			stopService(intent);
			this.finish();
			break;
		}
		
	}
	
	private ServiceConnection sc = new ServiceConnection() {
		@Override
		public void onServiceDisconnected(ComponentName name) {
			musicService = null;
			LogUtil.log(TAG, "onServiceDisconnected");
		}
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			MusicPlayerBindService.MyBinder mb = (MusicPlayerBindService.MyBinder)service;
			
			musicService = mb.getService();
			if (service != null) {
				musicService.play();
			}
			LogUtil.log(TAG, "onServiceConnected");
		}
	};
	
}
