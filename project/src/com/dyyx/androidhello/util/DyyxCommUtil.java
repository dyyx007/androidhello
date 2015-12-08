package com.dyyx.androidhello.util;


public class DyyxCommUtil{
	
	public static int getInt(String s,int def){		
		try{
			return Integer.parseInt(s);
		}catch(Throwable e){
			return def;
		}
	}
	
}