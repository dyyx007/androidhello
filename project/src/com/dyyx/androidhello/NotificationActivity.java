package com.dyyx.androidhello;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import com.dyyx.androidhello.base.BaseActivity;

public class NotificationActivity extends BaseActivity implements OnClickListener {

	Button sendBtn = null;
	Button btnSendPenddingIntent = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.notification);
		
		NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
				manager.cancel(2);

		sendBtn = (Button) findViewById(R.id.btnSend);
		btnSendPenddingIntent = (Button) findViewById(R.id.btnSendPenddingIntent);
	
		
		sendBtn.setOnClickListener(this);
		btnSendPenddingIntent.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnSend:
				
				 Toast.makeText( this, "send notification button click",Toast.LENGTH_SHORT ).show(); 
				 
				NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
				Notification notification = new Notification(R.drawable.ic_launcher, "This is ticker text",
						System.currentTimeMillis());
				notification.setLatestEventInfo(this, "This is content title", "This is content text", null);
				manager.notify(1, notification);
				break;
				
			case R.id.btnSendPenddingIntent:
				 Toast.makeText( this, "btnSendPenddingIntent notification click",Toast.LENGTH_SHORT ).show(); 
				NotificationManager manager2 = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
				Notification notification2 = new Notification(R.drawable. ic_launcher, "This is ticker text", System.currentTimeMillis());
				Intent intent = new Intent(this, NotificationActivity.class);
				PendingIntent pi = PendingIntent.getActivity(this, 0, intent,
				PendingIntent.FLAG_CANCEL_CURRENT);
				notification2.setLatestEventInfo(this, "This is content title", "This is content text", pi);
				manager2.notify(2, notification2);
				
				break;
			default:
				break;
		}
	}
}
