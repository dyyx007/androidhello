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
	Button btnReport = null;
	Button btnSave = null;
	Button btnQuery = null;
	
	
	EditText textEditHs300 = null;
	EditText textEditSab = null;
	EditText textEditMfb = null;
	EditText textEditSmv = null;
	EditText textEditCv = null;
	EditText textEditBv = null;
	EditText textEditSv = null;
	EditText textEditReport = null;
	EditText textEditBuys = null;
	EditText textEditSells = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fund);

		
		btnRead = (Button) findViewById(R.id.btnRead);
		
		
		textEditDate = (EditText) findViewById(R.id.textEditDate);
		
		textEditHs300 = (EditText) findViewById(R.id.textEditHs300);
		textEditSab = (EditText) findViewById(R.id.textEditSab);
		textEditMfb = (EditText) findViewById(R.id.textEditMfb);
		textEditSmv = (EditText) findViewById(R.id.textEditSmv);
		textEditCv = (EditText) findViewById(R.id.textEditCv);
		textEditBv = (EditText) findViewById(R.id.textEditBv);
		textEditSv = (EditText) findViewById(R.id.textEditSv);
		textEditReport = (EditText) findViewById(R.id.textEditReport);
		
		textEditBuys = (EditText) findViewById(R.id.textEditBuys);
		textEditSells = (EditText) findViewById(R.id.textEditSells);
		
		
		
		Date now = new Date();
		String fundDateNow = DyyxCommUtil.getDateString(now, DyyxCommUtil.FUND_DATE_FORMAT);
		
		textEditDate.setText(fundDateNow);
		
		
		
		
	}

	public void clickHandle(View view) {
		int vid = view.getId();
		if (vid == R.id.btnRead) {

			

			return;
		}

		if (vid == R.id.btnReport) {

		
			return;
		}
		
		if (vid == R.id.btnSave) {

			
			return;
		}
		
		if (vid == R.id.btnQuery) {

			
			return;
		}

	}

}
