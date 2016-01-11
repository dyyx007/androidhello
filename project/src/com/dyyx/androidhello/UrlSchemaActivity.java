package com.dyyx.androidhello;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;

import com.dyyx.androidhello.base.BaseActivity;

public class UrlSchemaActivity extends BaseActivity {

	EditText textEditResult = null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.url_schema);

		textEditResult = (EditText) findViewById(R.id.textEditResult);
		
		StringBuilder sb = new StringBuilder();
		Intent intent = getIntent();  
		sb.append("intent="+intent);
		if(intent!=null){
		    Uri uri = intent.getData();  
		    sb.append(",uri="+uri);
		    if(uri!=null){
		    	String name = uri.getQueryParameter("name");
		    	sb.append(",name="+name);
		    }
		}
		textEditResult.setText(sb.toString());
	
	}

	
}
