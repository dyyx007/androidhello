package com.dyyx.androidhello;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.util.DyyxCommUtil;
import com.dyyx.androidhello.util.LogUtil;

public class LogActivity extends BaseActivity {

	EditText textEditMaxLines = null;
	Button btnShowLog = null;
	Button btnClear = null;
	EditText textEditLogs = null;

	int maxLines = MAX_LINES;
	static final int MAX_LINES = 200;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.log);

		textEditMaxLines = (EditText) findViewById(R.id.textEditMaxLines);
		textEditLogs = (EditText) findViewById(R.id.textEditLogs);
		btnShowLog = (Button) findViewById(R.id.btnShowLogs);
		btnClear = (Button) findViewById(R.id.btnClear);

		maxLines = getMaxLines();

		textEditMaxLines.setText(maxLines + "");

	}

	public void showLogs(View view) {
		maxLines = getMaxLines();
		textEditMaxLines.setText(maxLines + "");
		List<String> logs = LogUtil.getLogs(maxLines);
		if (logs == null) {
			logs = new ArrayList<String>();
		}
		StringBuilder sb = new StringBuilder();
		int num = logs.size();
		sb.append("logs.size="+num).append("\n");
		if (num > 0) {
			int start = num - 1;
			for (int i = start; i >= 0; i--)
				sb.append(logs.get(i)).append("\n");
		}
		textEditLogs.setText(sb.toString());
	}

	public void clearLogs(View view) {

		LogUtil.clear();
		textEditLogs.setText("logs clear done");
	}

	private int getMaxLines() {
		String s = textEditMaxLines.toString();
		int tmp = DyyxCommUtil.getInt(s, 0);
		if (tmp <= 0) {
			tmp = MAX_LINES;
		}
		return tmp;
	}

}
