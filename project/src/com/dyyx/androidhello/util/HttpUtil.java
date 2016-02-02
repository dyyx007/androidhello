package com.dyyx.androidhello.util;

import java.util.Map;

import android.app.Activity;

import com.dyyx.androidhello.dto.HttpResult;

public class HttpUtil {

	
	public static void run(Activity activity,String url,Map<String,String> params,HttpCallback callback){
		if(activity==null){
			return;
		}
		if(DyyxCommUtil.isBlank(url)){
			return;
		}
		if(callback==null){
			return;
		}
		url = url.trim();
		
		Thread t = new HttpRunThread(url,params,activity,callback);
		t.start();
		
	}
	
	private static class HttpRunThread extends Thread{
		
		String url = null;
		Map<String,String> params = null;
		Activity activity = null;

        HttpCallback callback = null;
		
		public HttpRunThread(String url,Map<String,String> params,Activity activity,HttpCallback callback){
			this.url = url;
			this.params = params;
			this.activity = activity;
			this.callback = callback;
		}
		
		public void run(){
			
			HttpResult result = DyyxCommUtil.getHttpResult(url);
			callback.setResult(result);
			activity.runOnUiThread(callback);
		}
	}
	
}
