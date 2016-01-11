package com.dyyx.androidhello;

import java.util.Date;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.util.DyyxCommUtil;

public class FundActivity extends BaseActivity {

	
	EditText textEditDate = null;
	Button btnRead = null;
	Button btnSave = null;
	//TextView textView ;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fund);

		textEditDate = (EditText) findViewById(R.id.textEditDate);
		
		Date now = new Date();
		String fundDateNow = DyyxCommUtil.getDateString(now, DyyxCommUtil.FUND_DATE_FORMAT);
		
		textEditDate.setText(fundDateNow);
		
		
	}

	public void clickHandle(View view) {
		int vid = view.getId();
		if (vid == R.id.btnRead) {

			

			return;
		}

		if (vid == R.id.btnWrite) {

		
			return;
		}

	}

}
