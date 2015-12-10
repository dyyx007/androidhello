package com.dyyx.androidhello;

import java.util.Date;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.util.DyyxCommUtil;

public class NetworkActivity extends BaseActivity implements OnClickListener,Runnable {


	Button btnRunOnUiThread;
	Button btnRunUsePost;
	EditText  editTextUrl;
	EditText editTextResult;

	boolean runOnUiThread = true;
	Handler handler = null;
	
	String httpResult = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.network);

		btnRunOnUiThread =  (Button) findViewById(R.id.btnRunOnUiThread);
		btnRunUsePost =  (Button) findViewById(R.id.btnRunUsePost);
		editTextUrl =  (EditText) findViewById(R.id.editTextUrl);
		editTextResult =(EditText) findViewById(R.id.editTextResult);
		
		btnRunUsePost.setOnClickListener(NetworkActivity.this);
		btnRunOnUiThread.setOnClickListener(NetworkActivity.this);
		
		handler = new Handler();
		
	}


	@Override
	public void onClick(View v) {
		
		editTextResult.setText(" ");
		
		if(v.getId() == R.id.btnRunOnUiThread){
			runOnUiThread = true;
		}
		
        if(v.getId() == R.id.btnRunUsePost){
        	runOnUiThread = false;
		}
        Thread t = new HttpThread();
        t.start();
		
	}
	
	@Override
	public void run(){
		editTextResult.setText(httpResult);
	}
	
	private class HttpThread extends Thread{
		
		   public void run() {
			   // 非ui线程不能更新ui
			   // 可以读取ui中的内容 这个确认下
			   //
			   String url = editTextUrl.getText().toString();
			   StringBuilder sb = new StringBuilder();
			   sb.append("runOnUiThread="+runOnUiThread);
			   sb.append(",url="+url);
			   sb.append(",time="+new Date());
			   String result = null;
			   try{
			     result = DyyxCommUtil.doHttpGet(url);
			   }catch(Throwable e){
				 result = e+"";   
			   }
			   sb.append(",result="+result);
			   httpResult = sb.toString();
			   
			   if(runOnUiThread){
				   NetworkActivity.this.runOnUiThread(NetworkActivity.this);
			   }else{
				   handler.post(NetworkActivity.this);
			   }
			   
		    }
	}
	
	
	

}
