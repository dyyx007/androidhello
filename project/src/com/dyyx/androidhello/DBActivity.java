package com.dyyx.androidhello;

import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.util.DBUtil;
import com.dyyx.androidhello.util.DyyxCommUtil;

public class DBActivity extends BaseActivity {

	EditText textEditSql = null;
	//Button btnRead = null;
	EditText textEditResult = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.db);

		textEditSql = (EditText) findViewById(R.id.textEditSql);
		textEditResult = (EditText) findViewById(R.id.textEditResult);

	}

	public void clickHandle(View view) {
		int vid = view.getId();
		if (vid == R.id.btnQuery) {

			String sql = textEditSql.getText().toString();
			if (DyyxCommUtil.isBlank(sql)) {
				String msg = "sql is blank";
				Toast.makeText(DBActivity.this, msg, Toast.LENGTH_SHORT).show();
				return;
			}
			try {
				List list = DBUtil.query(sql, null, null);
				String result = DyyxCommUtil.join(list, "\n");
				textEditResult.setText(result);
			} catch (Throwable e) {
				textEditResult.setText("error," + e + "," + DyyxCommUtil.getTraceInfo(e));
			}

			return;
		}
		// run update sql
		if (vid == R.id.btnRun) {

			String sql = textEditSql.getText().toString();
			if (DyyxCommUtil.isBlank(sql)) {
				String msg = "sql is blank";
				Toast.makeText(DBActivity.this, msg, Toast.LENGTH_SHORT).show();
				return;
			}
			try {
				DBUtil.executeSql(sql, null);

				String msg = "executeSql done," + DyyxCommUtil.getNowDateString();
				textEditResult.setText(msg);
			} catch (Throwable e) {
				textEditResult.setText("error," + e + "," + DyyxCommUtil.getTraceInfo(e));
			}

			return;
		}
		// get all tables
		if (vid == R.id.btnGetTables) {
			String sql = "SELECT name FROM sqlite_master WHERE type='table' ORDER BY name";
			textEditSql.setText(sql);

			try {

				List list = DBUtil.query(sql, null, null);
				String result = DyyxCommUtil.join(list, "\n");
				textEditResult.setText(result);
			} catch (Throwable e) {
				textEditResult.setText("error," + e + "," + DyyxCommUtil.getTraceInfo(e));
			}

			return;
		}

		if (vid == R.id.btnViewColumnInfo) {

			String sql = textEditSql.getText().toString();
			if (DyyxCommUtil.isBlank(sql)) {
				String msg = "sql is blank";
				Toast.makeText(DBActivity.this, msg, Toast.LENGTH_SHORT).show();
				return;
			}
			try {
				DBUtil.ColumnInfo ci = DBUtil.getColumnInfo(sql, null, 0);

				textEditResult.setText(ci.toString());

			} catch (Throwable e) {
				textEditResult.setText("error," + e + "," + DyyxCommUtil.getTraceInfo(e));
			}

			return;
		}

	}

}
