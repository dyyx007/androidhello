package com.dyyx.androidhello;

import java.util.Date;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.util.DyyxCommUtil;

public class NetworkActivity extends BaseActivity implements OnClickListener, Runnable {

	Button btnRunOnUiThread;
	Button btnRunUsePost;
	Button btnSendMsg;
	EditText editTextUrl;
	EditText editTextResult;

	//boolean runOnUiThread = true;
	Handler handler = null;

	String httpResult = null;

	static final int RUN_ON_UI_THREAD = 1;
	static final int POST = 2;
	static final int SEND_MSG = 3;
	static int type = RUN_ON_UI_THREAD;

	static final int MSG_0 = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.network);

		btnRunOnUiThread = (Button) findViewById(R.id.btnRunOnUiThread);
		btnRunUsePost = (Button) findViewById(R.id.btnRunUsePost);
		btnSendMsg = (Button) findViewById(R.id.btnSendMsg);

		editTextUrl = (EditText) findViewById(R.id.editTextUrl);
		editTextResult = (EditText) findViewById(R.id.editTextResult);

		btnRunUsePost.setOnClickListener(NetworkActivity.this);
		btnRunOnUiThread.setOnClickListener(NetworkActivity.this);
		btnSendMsg.setOnClickListener(NetworkActivity.this);
		//handler = new Handler();
		handler = new MyHandler();

	}

	@Override
	public void onClick(View v) {

		editTextResult.setText(" ");

		if (v.getId() == R.id.btnRunOnUiThread) {
			//runOnUiThread = true;
			type = RUN_ON_UI_THREAD;
		}

		if (v.getId() == R.id.btnRunUsePost) {
			//runOnUiThread = false;
			type = POST;
		}

		if (v.getId() == R.id.btnSendMsg) {
			//runOnUiThread = false;
			type = SEND_MSG;
		}

		Thread t = new HttpThread();
		t.start();

	}

	@Override
	public void run() {
		editTextResult.setText(httpResult);
	}

	private class HttpThread extends Thread {

		public void run() {
			// 非ui线程不能更新ui
			// 可以读取ui中的内容 这个确认下
			//
			String url = editTextUrl.getText().toString();
			StringBuilder sb = new StringBuilder();
			sb.append("type=" + type);
			sb.append(",url=" + url);
			sb.append(",time=" + new Date());
			String result = null;
			try {
				result = DyyxCommUtil.doHttpGet(url);
			} catch (Throwable e) {
				result = e + "";
			}
			sb.append(",result=" + result);
			httpResult = sb.toString();

			if (type == RUN_ON_UI_THREAD) {
				NetworkActivity.this.runOnUiThread(NetworkActivity.this);
				return;
			}
			if (type == POST) {
				handler.post(NetworkActivity.this);
				return;
			}
			if (type == SEND_MSG) {
				//handler.post(NetworkActivity.this);
				Message msg = new Message();
				msg.what = MSG_0;
				msg.obj = httpResult;
				handler.sendMessage(msg);
				return;
			}

		}
	}

	class MyHandler extends Handler {
		@Override
		public void handleMessage(Message msg) {

			if (msg.what == MSG_0) {
				String result = (String) msg.obj;
				editTextResult.setText(result);
			}

		}
	}

}
