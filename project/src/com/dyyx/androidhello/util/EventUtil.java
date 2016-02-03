package com.dyyx.androidhello.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class EventUtil{
	
	private static List<String> logs = Collections.synchronizedList(new ArrayList<String>());
	
	
	
	public static void add(String str){
		if(DyyxCommUtil.isBlank(str)){
			return;
		}
		int num = logs.size();
		if(num>=200){
			logs.clear();
		}
		String now = DyyxCommUtil.getNowDateString();
		logs.add(now+","+str);
	}
	
	public static void clear(){
		logs.clear();
	}
	
	public static String getLogs(){
		List<String> tmp = new ArrayList<String>();
		tmp.addAll(logs);
		Collections.reverse(tmp);
		return DyyxCommUtil.join(tmp, "\n\n");
	}
	
}