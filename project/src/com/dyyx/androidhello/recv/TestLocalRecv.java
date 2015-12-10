package com.dyyx.androidhello.recv;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.dyyx.androidhello.util.LogUtil;

/**
 * �Զ��屾�ع㲥
 * 
 *
 * @author  gang.dug 
 * @version	1.0.0   2015-12-10 ����4:17:36 
 * @since   JDK1.6
 */
public class TestLocalRecv extends BroadcastReceiver {
	
	 static final String TAG = "TestLocalRecv";
	
	 @Override   
     public void onReceive( Context context, Intent intent ) {   
		 StringBuilder sb = new StringBuilder();
		 
		 sb.append("onReceive,intent="+intent);
		 
		 LogUtil.log(TAG, sb.toString());
		 
		 Toast.makeText( context, TAG+","+sb,Toast.LENGTH_SHORT ).show(); 
        
       }   
}