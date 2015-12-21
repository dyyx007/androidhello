package com.dyyx.androidhello.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.KeyEvent;

import com.dyyx.androidhello.util.LogUtil;

public class BaseActivity extends Activity {

	protected String TAG = this.getClass().getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogUtil.log(TAG, "onCreate," + this + ",taskId=" + this.getTaskId());
	}

	@Override
	protected void onStart() {
		super.onStart();
		LogUtil.log(TAG, "onStart," + this + ",taskId=" + this.getTaskId());
	}

	@Override
	protected void onResume() {
		super.onResume();
		LogUtil.log(TAG, "onResume," + this + ",taskId=" + this.getTaskId());
	}

	@Override
	protected void onPause() {
		super.onPause();
		LogUtil.log(TAG, "onPause," + this + ",taskId=" + this.getTaskId());
	}

	@Override
	protected void onStop() {
		super.onStop();
		LogUtil.log(TAG, "onStop," + this + ",taskId=" + this.getTaskId());
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		LogUtil.log(TAG, "onDestroy," + this + ",taskId=" + this.getTaskId());
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		String str = "onKeyDown," + this + ",taskId=" + this.getTaskId();
		str = str + ",keyCode=" + keyCode + ",event=" + event;
		LogUtil.log(TAG, str);
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onBackPressed() {
		LogUtil.log(TAG, "onBackPressed," + this + ",taskId=" + this.getTaskId());
	
		super.onBackPressed();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		LogUtil.log(TAG, "onSaveInstanceState," + this + ",taskId=" + this.getTaskId());
		super.onSaveInstanceState(outState);

	}
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState,PersistableBundle persistentState){
		LogUtil.log(TAG, "onRestoreInstanceState," + this + ",taskId=" + this.getTaskId());
		super.onRestoreInstanceState(savedInstanceState, persistentState);
	}

}
