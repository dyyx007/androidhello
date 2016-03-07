package com.dyyx.androidhello;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.util.DyyxCommUtil;

public class ConfigActivity extends BaseActivity {

	Button btnChangeScreen;
	Button btnGetConfigInfo;
	TextView tvInfo;

	StringBuilder sb = new StringBuilder();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.config);

		btnChangeScreen = (Button) findViewById(R.id.btnChangeScreen);
		btnGetConfigInfo = (Button) findViewById(R.id.btnGetConfigInfo);
		tvInfo = (TextView) findViewById(R.id.tvInfo);

		sb.append(",onCreate at " + DyyxCommUtil.getNowDateString());
		sb.append("********\n");
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);

		sb.append(",onConfigurationChanged at " + DyyxCommUtil.getNowDateString());
		sb.append(",config="+getConfigInfo());
		sb.append("///////////\n");
		
	}

	public void clickHandle(View view) {
		int vid = view.getId();

		if (vid == R.id.btnChangeScreen) {
			
			//int orientation = this.getRequestedOrientation();
			
			Configuration config = getResources().getConfiguration(); 
			int orientation = config.orientation;
			
			sb.append(", change screen at "+DyyxCommUtil.getNowDateString());
			
			if(orientation == Configuration.ORIENTATION_PORTRAIT){
				this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
				
				sb.append(" to landscape ");
			}
			
			if(orientation == Configuration.ORIENTATION_LANDSCAPE){
				this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
				sb.append(" to portrait ");

			}
			
			sb.append("----------\n");
			
			tvInfo.setText(sb.toString()+"\n\n"+getConfigInfo() );

			return;
		}

		if (vid == R.id.btnGetConfigInfo) {

			tvInfo.setText(sb.toString()+"\n\n"+getConfigInfo() );
			
			return;
		}

	}

	private String getConfigInfo() {
		
		Configuration config = getResources().getConfiguration(); 
		// -1
		//int orientation = this.getRequestedOrientation();
		// 
		int orientation = config.orientation;
		
		String s = "orientation=" + this.getRequestedOrientation();
		s = s + "," + getOrientation(orientation);
		return s;
	}

	private String getOrientation(int i) {
		if (i == Configuration.ORIENTATION_PORTRAIT) {
			return " ˙∆¡";
		}
		if (i == Configuration.ORIENTATION_LANDSCAPE) {
			return "∫·∆¡";
		}
		return "UK";
	}

}
