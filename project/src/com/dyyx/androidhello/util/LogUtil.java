package com.dyyx.androidhello.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import android.util.Log;

public class LogUtil{
	
	
	private static List<String> logs = Collections.synchronizedList(new ArrayList<String>());
	
	public static void log(String tag,String msg){
		
		Log.i(tag, msg);
	
		Date now = new Date();
		String nowstr = DyyxCommUtil.getDateString(now, DyyxCommUtil.TIME_FORMAT);
        logs.add(nowstr+"."+tag+"."+msg);	
	
	}
	
	public static void log(String tag,String msg,Throwable t){
		Log.e(tag, msg,t);
		Date now = new Date();
		String nowstr = DyyxCommUtil.getDateString(now, DyyxCommUtil.TIME_FORMAT);
        logs.add(nowstr+"."+tag+"."+msg+"."+t);	
	}
	
	public static void clear(){		
		logs.clear();
	}
	
	public static List<String> getLogs(int max){
		if(max<=0){			
			max = 10;
		}
		List<String> list = new ArrayList<String>();
		int num = logs.size();
		if(max >= num){
			list.addAll(logs);
		}else{
		   list = logs.subList(num-max, num-1);	
		}
		return list;	
	}	
	
	public static List<String> getLogs(){
		return getLogs(200);
	}
	
	
}