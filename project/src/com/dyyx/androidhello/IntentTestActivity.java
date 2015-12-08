package com.dyyx.androidhello;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.dyyx.androidhello.base.BaseActivity;

public class IntentTestActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.intent_test);


	}

	
	public void btnClick(View view) {
		if(view.getId()==R.id.btnCall){
			Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:10086"));
            startActivity(i);		
			return;
		}
		
		if(view.getId()==R.id.btnDial){
			Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:10086"));
            startActivity(i);		
			return;
		}
		
		if(view.getId()==R.id.btnWeb){
			Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.baidu.com"));
            startActivity(i);		
			return;
		}
		
		

	}

	

}
