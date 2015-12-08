package com.dyyx.androidhello.base;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.KeyEvent;

import com.dyyx.androidhello.util.LogUtil;

public class BaseActivity extends Activity {

	String TAG = this.getClass().getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogUtil.log(TAG, "onCreate," + this.getClass() + ',' + this + ",taskId=" + this.getTaskId());
	}

	@Override
	protected void onStart() {
		super.onStart();
		LogUtil.log(TAG, "onStart," + this.getClass() + ',' + this + ",taskId=" + this.getTaskId());
	}

	@Override
	protected void onResume() {
		super.onResume();
		LogUtil.log(TAG, "onResume," + this.getClass() + ',' + this + ",taskId=" + this.getTaskId());
	}

	@Override
	protected void onPause() {
		super.onPause();
		LogUtil.log(TAG, "onPause," + this.getClass() + ',' + this + ",taskId=" + this.getTaskId());
	}

	@Override
	protected void onStop() {
		super.onStop();
		LogUtil.log(TAG, "onStop," + this.getClass() + ',' + this + ",taskId=" + this.getTaskId());
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		LogUtil.log(TAG, "onDestroy," + this.getClass() + ',' + this + ",taskId=" + this.getTaskId());
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		String str = "onKeyDown," + this.getClass() + ',' + this + ",taskId=" + this.getTaskId();
		str = str + ",keyCode=" + keyCode + ",event=" + event;
		LogUtil.log(TAG, str);
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onBackPressed() {
		LogUtil.log(TAG, "onBackPressed," + this.getClass() + ',' + this + ",taskId=" + this.getTaskId());
	
		super.onBackPressed();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		LogUtil.log(TAG, "onSaveInstanceState," + this.getClass() + ',' + this + ",taskId=" + this.getTaskId());
		super.onSaveInstanceState(outState);

	}
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState,PersistableBundle persistentState){
		LogUtil.log(TAG, "onRestoreInstanceState," + this.getClass() + ',' + this + ",taskId=" + this.getTaskId());
		super.onRestoreInstanceState(savedInstanceState, persistentState);
	}

}
