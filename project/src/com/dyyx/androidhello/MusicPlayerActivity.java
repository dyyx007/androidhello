package com.dyyx.androidhello;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.util.DyyxCommUtil;
import com.dyyx.androidhello.util.LogUtil;

public class MusicPlayerActivity extends BaseActivity implements OnClickListener  {

    Button btnPlay;
    Button btnStop;
    Button btnPause;
    Button btnClose;
    Button btnExit;
    
	final static String TAG = "MusicPlayerActivity";
	
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

	}

	
	@Override
	public void onClick(View v) {
		
		int op = -1;
		Intent intent = new Intent("com.dyyx.androidhello.service.MusicPlayerService");
		// π„≤•”√
		// Intent intent = new Intent("org.allin.android.musicReceiver");
		switch (v.getId()) {
		case R.id.btnPlay:
			LogUtil.log(TAG, "onClick: playing muic");
			op = 1;
			break;
		case R.id.btnStop:
			
			Toast toast=Toast.makeText(getApplicationContext(), DyyxCommUtil.NO_STOP_MSG, Toast.LENGTH_SHORT); 
			toast.show();
			
			//LogUtil.log(TAG, "onClick: stoping music");
			//op = 2;
			break;
		case R.id.btnPause:
			LogUtil.log(TAG, "onClick: pausing music");
			op = 3;
			break;
		case R.id.btnClose:
			LogUtil.log(TAG, "onClick: close");
			this.finish();
			break;
		case R.id.btnExit:
			LogUtil.log(TAG, "onClick: exit");
			op = 4;
			stopService(intent);
			this.finish();
			break;
		}
		Bundle bundle = new Bundle();
		bundle.putInt("op", op);
		intent.putExtras(bundle);
		startService(intent);
		// sendBroadcast(intent);
		
		
	}
	
}
