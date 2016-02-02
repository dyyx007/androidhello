package com.dyyx.androidhello.util;

import com.dyyx.androidhello.dto.HttpResult;


public abstract class HttpCallback implements Runnable{
	protected HttpResult result;
	
	

	public HttpResult getResult() {
		return result;
	}

	public void setResult(HttpResult result) {
		this.result = result;
	}
	
	

}