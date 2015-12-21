package com.dyyx.androidhello.service;

import android.os.Binder;

import com.dyyx.androidhello.util.DyyxCommUtil;

public class MyTimeServiceBinder extends Binder {

	//private static final String TAG = "MyTimeServiceBinder";

	
	public String getTime(){
		return DyyxCommUtil.getNowDateString();
	}
	
}
