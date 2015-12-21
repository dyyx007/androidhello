package com.dyyx.androidhello;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.service.My01Service;
import com.dyyx.androidhello.util.DyyxCommUtil;

public class ServiceActivity extends BaseActivity implements OnClickListener {

	Button btnUpdateUiUseHandler;
	TextView textViewMsg;
	
	Button btnStartService;
	Button btnStopService;

	static final int HELLO = 1;

	Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case HELLO:
					// 在这里可以进行UI操作
					String msgstr = "HELLO,Nice to meet you,"+DyyxCommUtil.getNowDateString();
					textViewMsg.setText(msgstr);
					break;
				default:
					break;
			}
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

		
		
		btnUpdateUiUseHandler.setOnClickListener(this);
		btnStartService.setOnClickListener(this);
		btnStopService.setOnClickListener(this);
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
		
		

	}
	
	class HelloMsgThread extends Thread{
		@Override
		public void run(){
			
			Message message = new Message();
			message.what = HELLO;
			handler.sendMessage(message); 
		}
	}

}
