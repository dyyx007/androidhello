package com.dyyx.androidhello;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.util.DyyxCommUtil;
import com.dyyx.androidhello.util.LogUtil;

public class BroadcastActivity extends BaseActivity implements OnClickListener{

	
	
	
	Button btnSend;
	Button btnSendLocal;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.broadcast);

		btnSend =  (Button) findViewById(R.id.btnSend);
		btnSendLocal =  (Button) findViewById(R.id.btnSendLocal);

		btnSend.setOnClickListener(BroadcastActivity.this);
		btnSendLocal.setOnClickListener(BroadcastActivity.this);
		
	}


	@Override
	public void onClick(View v) {
		
		
		if(v.getId() == R.id.btnSend){
			Intent intent=new Intent(DyyxCommUtil.BROADCAST_TEST); 
			intent.putExtra("data", "broadcast test,"+DyyxCommUtil.getNowDateString()); 
			this.sendBroadcast(intent); 
			return;
		}
		
        if(v.getId() == R.id.btnSendLocal){
        	LogUtil.log(TAG, "btnSendLocal click");
        	
        	//LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        	LocalBroadcastManager lbm = HelloApp.lbm;
        	
        	Intent intent=new Intent(DyyxCommUtil.BROADCAST_TEST_LOCAL);
        	intent.putExtra("data", "local broadcast test,"+DyyxCommUtil.getNowDateString()); 
        	
        	LogUtil.log(TAG, "local broadcast send "+intent);
        	
        	lbm.sendBroadcast(intent);

     
        	return;
		}
      
		
	}
	
	
	

}
