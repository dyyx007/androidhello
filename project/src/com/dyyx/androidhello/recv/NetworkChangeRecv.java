package com.dyyx.androidhello.recv;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.dyyx.androidhello.util.LogUtil;

public class NetworkChangeRecv extends BroadcastReceiver {
	
	 static final String TAG = "NetworkChangeRecv";
	
	 @Override   
     public void onReceive( Context context, Intent intent ) {   
		 StringBuilder sb = new StringBuilder();
		 
		 sb.append("NetworkChangeRecv.onReceive,intent="+intent);
		 
		 try{
         ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);   
         NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();   
         NetworkInfo mobNetInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE); 
         
         sb.append(",activeNetInfo="+activeNetInfo);
         sb.append(",mobNetInfo="+mobNetInfo);
         
		 }catch(Throwable e){			 
			 sb.append(",getNetworkInfo error,"+e);
		 }
		 
		 Toast.makeText( context, sb.toString(),Toast.LENGTH_SHORT ).show(); 
         LogUtil.log(TAG, sb.toString());
         
        
       }   
}