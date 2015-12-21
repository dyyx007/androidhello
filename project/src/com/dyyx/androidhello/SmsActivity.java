package com.dyyx.androidhello;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dyyx.androidhello.base.BaseActivity;

public class SmsActivity extends BaseActivity implements OnClickListener {

	EditText editTextSmsFrom = null;
	EditText editTextSmsTo = null;
	EditText editTextSmsContent = null;

	Button btnSendSms = null;

	IntentFilter smsRecvFilter;
	SmsReceiver smsReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sms);

		editTextSmsFrom = (EditText) findViewById(R.id.editTextSmsFrom);
		editTextSmsTo = (EditText) findViewById(R.id.editTextSmsTo);
		editTextSmsContent = (EditText) findViewById(R.id.editTextSmsContent);

		btnSendSms = (Button) findViewById(R.id.btnSendSms);

		btnSendSms.setOnClickListener(this);

		smsRecvFilter = new IntentFilter();
		smsRecvFilter.addAction("android.provider.Telephony.SMS_RECEIVED");
		smsRecvFilter.setPriority(888);
		smsReceiver = new SmsReceiver();
		registerReceiver(smsReceiver, smsRecvFilter);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(smsReceiver);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnSendSms:

				SmsManager smsManager = SmsManager.getDefault();
				String to = editTextSmsTo.getText().toString();
				to = "10086";
				String content = editTextSmsContent.getText().toString();
				smsManager.sendTextMessage(to, null,content, null, null);
				
				
				break;

			default:
				break;
		}
	}

	class SmsReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {

			
			Toast.makeText(context, "SmsReceiver.onReceive,"+intent,Toast.LENGTH_SHORT ).show(); 
			
			Bundle bundle = intent.getExtras();
			// 提取短信消息
			Object[] pdus = (Object[]) bundle.get("pdus");
			SmsMessage[] messages = new SmsMessage[pdus.length];
			for (int i = 0; i < messages.length; i++) {
				messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
			}
			// 获取发送方号码
			String address = messages[0].getOriginatingAddress();
			String fullMessage = "";
			for (SmsMessage message : messages) {
				// 获取短信内容
				fullMessage += message.getMessageBody();
			}
			editTextSmsFrom.setText(address);
			editTextSmsContent.setText(fullMessage);
			
			

		}

	}

}
