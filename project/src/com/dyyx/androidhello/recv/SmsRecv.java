package com.dyyx.androidhello.recv;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 *  �Զ���㲥
 * 
 *
 * @author  gang.dug 
 * @version	1.0.0   2015-12-10 ����4:17:10 
 * @since   JDK1.6
 */
public class SmsRecv extends BroadcastReceiver {
	
	 static final String TAG = "SmsRecv";
	
	 @Override   
     public void onReceive( Context context, Intent intent ) {   

			Toast.makeText(context, "SmsReceiver.onReceive,"+intent,Toast.LENGTH_SHORT ).show(); 
			
			Bundle bundle = intent.getExtras();
			// ��ȡ������Ϣ
			Object[] pdus = (Object[]) bundle.get("pdus");
			SmsMessage[] messages = new SmsMessage[pdus.length];
			for (int i = 0; i < messages.length; i++) {
				messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
			}
			// ��ȡ���ͷ�����
			String address = messages[0].getOriginatingAddress();
			String fullMessage = "";
			for (SmsMessage message : messages) {
				// ��ȡ��������
				fullMessage += message.getMessageBody();
			}
			
			Toast.makeText(context, "recv sms from "+address+","+fullMessage,Toast.LENGTH_SHORT ).show(); 
       }   
}