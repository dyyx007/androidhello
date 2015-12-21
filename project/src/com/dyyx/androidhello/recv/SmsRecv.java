package com.dyyx.androidhello.recv;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 *  自定义广播
 * 
 *
 * @author  gang.dug 
 * @version	1.0.0   2015-12-10 下午4:17:10 
 * @since   JDK1.6
 */
public class SmsRecv extends BroadcastReceiver {
	
	 static final String TAG = "SmsRecv";
	
	 @Override   
     public void onReceive( Context context, Intent intent ) {   

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
			
			Toast.makeText(context, "recv sms from "+address+","+fullMessage,Toast.LENGTH_SHORT ).show(); 
       }   
}