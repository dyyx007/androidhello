package com.dyyx.androidhello;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.util.DyyxCommUtil;

public class SpActivity extends BaseActivity {

	Button btnRead;
	Button btnWrite;

	EditText textEditResult;

	EditText textEditString;
	EditText textEditInt;
	EditText textEditBool;

	static final String KEY_STRING_VALUE = "stringValue";
	static final String KEY_INT_VALUE = "intValue";
	static final String KEY_BOOL_VALUE = "boolValue";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sp);

		btnRead = (Button) findViewById(R.id.btnRead);
		btnWrite = (Button) findViewById(R.id.btnWrite);

		textEditResult = (EditText) findViewById(R.id.textEditResult);

		textEditString = (EditText) findViewById(R.id.textEditString);
		textEditInt = (EditText) findViewById(R.id.textEditInt);
		textEditBool = (EditText) findViewById(R.id.textEditBool);
	}

	public void clickHandle(View view) {
		int vid = view.getId();
		if (vid == R.id.btnRead) {

			SharedPreferences pref = getSharedPreferences("data", MODE_PRIVATE);
			String stringValue = pref.getString(KEY_STRING_VALUE, "");
			int intValue = pref.getInt(KEY_INT_VALUE, 0);
			boolean boolValue = pref.getBoolean(KEY_BOOL_VALUE, false);

			String s = "stringValue=" + stringValue;
			s = s + ",intValue=" + intValue;
			s = s + ",boolValue=" + boolValue;

			textEditResult.setText(s);

			return;
		}

		if (vid == R.id.btnWrite) {

			String stringValue = textEditString.getText().toString();
			String intValueStr = textEditInt.getText().toString();
			String boolValueStr = textEditBool.getText().toString();

			if (DyyxCommUtil.isBlank(stringValue)) {
				Toast.makeText(this, "string value is blank", Toast.LENGTH_SHORT).show();
				return;
			}
			if (DyyxCommUtil.isBlank(intValueStr)) {
				Toast.makeText(this, "int value is blank", Toast.LENGTH_SHORT).show();
				return;
			}
			if (DyyxCommUtil.isBlank(boolValueStr)) {
				Toast.makeText(this, "bool value is blank", Toast.LENGTH_SHORT).show();
				return;
			}

			int intValue = DyyxCommUtil.getInt(intValueStr, 0);
			boolean boolValue = false;
			if ("true".equalsIgnoreCase(boolValueStr)) {
				boolValue = true;
			}

			SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
			editor.putString(KEY_STRING_VALUE, stringValue);
			editor.putInt(KEY_INT_VALUE, intValue);
			editor.putBoolean(KEY_BOOL_VALUE, boolValue);
			editor.commit();

			Toast.makeText(this, "write done", Toast.LENGTH_SHORT).show();
			return;
		}

	}

}
