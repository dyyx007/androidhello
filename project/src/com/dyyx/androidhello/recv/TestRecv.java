package com.dyyx.androidhello.recv;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.dyyx.androidhello.util.LogUtil;

/**
 *  �Զ���㲥
 * 
 *
 * @author  gang.dug 
 * @version	1.0.0   2015-12-10 ����4:17:10 
 * @since   JDK1.6
 */
public class TestRecv extends BroadcastReceiver {
	
	 static final String TAG = "TestRecv";
	
	 @Override   
     public void onReceive( Context context, Intent intent ) {   
		 StringBuilder sb = new StringBuilder();
		 
		 sb.append("onReceive,intent="+intent);
		 LogUtil.log(TAG, sb.toString());
		 Toast.makeText( context, TAG+","+sb,Toast.LENGTH_SHORT ).show(); 
       }   
}