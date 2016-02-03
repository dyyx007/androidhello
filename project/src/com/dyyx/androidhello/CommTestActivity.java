package com.dyyx.androidhello;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.util.ContentProviderUtil;
import com.dyyx.androidhello.util.DyyxCommUtil;
import com.dyyx.androidhello.util.EventUtil;

public class CommTestActivity extends BaseActivity {

	EditText textEditInput = null;
	//Button btnRead = null;
	EditText textEditResult = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comm_test);

		textEditInput = (EditText) findViewById(R.id.textEditInput);
		textEditResult = (EditText) findViewById(R.id.textEditResult);

	}

	public void clickHandle(View view) {
		int vid = view.getId();
		
		if (vid == R.id.btnRun) {

			String msg = "todo..."+DyyxCommUtil.getNowDateString();
			textEditResult.setText(msg);

			return;
		}

		if (vid == R.id.btnReadContacts) {
			
			String input = textEditInput.getText().toString();
			String uri = ContentProviderUtil.URI_CONTACTS;
			if(DyyxCommUtil.isBlank(input)){
				textEditInput.setText(uri);
			}
			
			List list = ContentProviderUtil.query(uri, null);
			
            
            String msg = DyyxCommUtil.join(list, "\n");
			textEditResult.setText(msg);
			

			return;
		}
		if (vid == R.id.btnEventLogs) {
			
			textEditResult.setText(EventUtil.getLogs());
			return;
			
		}
		
		if(vid == R.id.btnClearEventLogs){
			EventUtil.clear();
			textEditResult.setText("event logs clear done,"+DyyxCommUtil.getNowDateString());
			return ;
		}
		
	}
}
