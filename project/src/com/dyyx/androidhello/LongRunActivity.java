package com.dyyx.androidhello;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.service.LongRunService;

public class LongRunActivity extends BaseActivity {

	Button btnStartLongRunService = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.longrun);

		btnStartLongRunService = (Button) findViewById(R.id.btnStartLongRunService);

	}

	public void startLongRunService(View view) {

		Intent intent = new Intent(this, LongRunService.class);
		startService(intent);

		Toast.makeText(this, "startLongRunService done", Toast.LENGTH_SHORT).show();

	}

}
