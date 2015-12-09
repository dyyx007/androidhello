package com.dyyx.androidhello;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.dyyx.androidhello.base.BaseActivity;

public class NetworkActivity extends BaseActivity implements OnClickListener {


	Button btnRunOnUiThread;
	Button btnRunUsePost;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.network);

		btnRunOnUiThread =  (Button) findViewById(R.id.btnRunOnUiThread);
		btnRunUsePost =  (Button) findViewById(R.id.btnRunUsePost);
		
		btnRunUsePost.setOnClickListener(this);
		btnRunOnUiThread.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.btnRunOnUiThread){
			
			return;
		}
		
        if(v.getId() == R.id.btnRunUsePost){
			
			return;
		}
		
	}

}
