package com.dyyx.androidhello;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.service.My01Service;
import com.dyyx.androidhello.service.MyIntentService;
import com.dyyx.androidhello.service.MyTimeService;
import com.dyyx.androidhello.service.MyTimeServiceBinder;
import com.dyyx.androidhello.util.DyyxCommUtil;
import com.dyyx.androidhello.util.LogUtil;

public class ServiceActivity extends BaseActivity implements OnClickListener {

	Button btnUpdateUiUseHandler;
	TextView textViewMsg;

	Button btnStartService;
	Button btnStopService;

	Button btnBindTimeService;
	Button btnUnbindTimeService;
	Button btnGetTimeFromBinder;
	
	Button btnStartIntentService;

	static final int HELLO = 1;

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case HELLO:
					// 在这里可以进行UI操作
					String msgstr = "HELLO,Nice to meet you," + DyyxCommUtil.getNowDateString();
					textViewMsg.setText(msgstr);
					break;
				default:
					break;
			}
		}
	};

	MyTimeServiceBinder binder = null;

	ServiceConnection connection = new ServiceConnection() {
		@Override
		public void onServiceDisconnected(ComponentName name) {
			LogUtil.log(TAG, "onServiceDisconnected,ComponentName="+name);
			binder = null;
		}

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			LogUtil.log(TAG, "onServiceConnected,ComponentName="+name+",service="+service);
			binder = (MyTimeServiceBinder) service;
			LogUtil.log(TAG, "binder="+binder);

		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.service);
		textViewMsg = (TextView) findViewById(R.id.textViewMsg);
		btnUpdateUiUseHandler = (Button) findViewById(R.id.btnUpdateUiUseHandler);

		btnStartService = (Button) findViewById(R.id.btnStartService);
		btnStopService = (Button) findViewById(R.id.btnStopService);

		btnBindTimeService = (Button) findViewById(R.id.btnBindTimeService);
		btnUnbindTimeService = (Button) findViewById(R.id.btnUnbindTimeService);
		btnGetTimeFromBinder = (Button) findViewById(R.id.btnGetTimeFromBinder);

		btnStartIntentService = (Button) findViewById(R.id.btnStartIntentService);
		
		btnUpdateUiUseHandler.setOnClickListener(this);
		btnStartService.setOnClickListener(this);
		btnStopService.setOnClickListener(this);

		btnBindTimeService.setOnClickListener(this);
		btnUnbindTimeService.setOnClickListener(this);
		btnGetTimeFromBinder.setOnClickListener(this);
		
		btnStartIntentService.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int vid = v.getId();

		if (vid == R.id.btnUpdateUiUseHandler) {
			Thread t = new HelloMsgThread();
			t.start();
			return;
		}

		if (vid == R.id.btnStartService) {
			Intent intent = new Intent(this, My01Service.class);
			startService(intent);
			return;
		}

		if (vid == R.id.btnStopService) {
			Intent intent = new Intent(this, My01Service.class);
			stopService(intent);
			return;
		}

		if (vid == R.id.btnBindTimeService) {
			
			Intent intent = new Intent(this, MyTimeService.class);
			bindService(intent, connection, BIND_AUTO_CREATE);
			

			return;
		}

		if (vid == R.id.btnUnbindTimeService) {
			
			unbindService(connection);
			//connection.onServiceDisconnected(name);
			binder = null;
			
			return;
		}

		if (vid == R.id.btnGetTimeFromBinder) {
			LogUtil.log(TAG,"btnGetTimeFromBinder click,binder="+binder);
			if(binder==null){
				textViewMsg.setText("binder is null,please bind");
				return ;	
			}
			textViewMsg.setText("now time is "+binder.getTime());

			return;
		}
		
		if(vid==R.id.btnStartIntentService){
			
			LogUtil.log(TAG, "main thread id "+Thread.currentThread().getId());
			Intent intentService = new Intent(this, MyIntentService.class);
			startService(intentService);

			return;
		}

	}

	class HelloMsgThread extends Thread {
		@Override
		public void run() {

			Message message = new Message();
			message.what = HELLO;
			handler.sendMessage(message);
		}
	}

}
