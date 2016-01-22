package com.dyyx.androidhello.util;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DyyxCommUtil {
	

	
	public  static final String BROADCAST_TEST = "com.dyyx.androidhello.broadcast.test";
	public  static final String BROADCAST_TEST_LOCAL = "com.dyyx.androidhello.broadcast.test_local";

	
	public  static final String NO_STOP_MSG = "stop之后无法继续play,暂时不实现该功能";

	public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static final String TIME_FORMAT = "HH:mm:ss";
	public static final String FUND_DATE_FORMAT = "yyyyMMdd";
	
	
	public static final String EMPTY = "";
	
	public static final String CHARSET = "utf-8";
	// application/x-javascript; charset=GBK
	private static final String  CHARSET_KEY = "charset=";
	private static final int  CHARSET_KEY_LEN = CHARSET_KEY.length();

	public static boolean isBlank(String s) {
		if (s == null || EMPTY.equals(s)) {
			return true;
		}
		String tmp = s.trim();
		return tmp.isEmpty();
	}

	public static int getInt(String s, int def) {
		try {
			return Integer.parseInt(s);
		} catch (Throwable e) {
			return def;
		}
	}
	
	public static Integer getIntObj(String s, Integer def) {
		try {
			return Integer.parseInt(s);
		} catch (Throwable e) {
			return null;
		}
	}
	
	public static double getDouble(String s, double def) {
		try {
			return Double.parseDouble(s);
		} catch (Throwable e) {
			return def;
		}
	}
	
	public static long getLong(String s, long def) {
		try {
			return Long.parseLong(s);
		} catch (Throwable e) {
			return def;
		}
	}
	
	public static Date getDate(String str, String format,Date def) {
		if(isBlank(str)){
			return null;
		}
		if(isBlank(format)){
			format = DATE_TIME_FORMAT;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.parse(str);
		} catch (Throwable e) {
			return def;
		}
	}

	public static String getDateString(Date date, String format) {
		if (date == null) {
			return "";
		}
		if (isBlank(format)) {
			format = DATE_TIME_FORMAT;
		}
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			return sdf.format(date);
		} catch (Throwable e) {
			return date.toString();
		}
	}
	
	public static String getNowDateString() {
		return getDateString(new Date(), null);
	}
	
    public static String doHttpGet(String url)throws Exception{

		URL urlo = new URL(url);

		URLConnection conn = urlo.openConnection();
		HttpURLConnection huc = (HttpURLConnection) conn;
		

		String contentType = huc.getContentType();
        String charset = getCharset(contentType);
        if(isBlank(charset)){
        	charset = CHARSET;
        }
		
		
		InputStream is = null;
		ByteArrayOutputStream baos= new ByteArrayOutputStream();
		int ch = 0;
		try{
		is = huc.getInputStream();
		while((ch=is.read())!=-1){
			baos.write(ch);
		}
		byte[] bytes = baos.toByteArray();
		

		return new String(bytes,charset);
		}finally{
			close(is);
		}
	}
    
    public static String join(Collection c,String sep){
    	if(c==null){
    		return null;
    	}
    	boolean isfirst = true;
    	StringBuilder sb = new StringBuilder();
    	
    	for(Object item:c){
    		
    	    if(isfirst){
    	    	isfirst = false;
    	    }else{
    	    	if(sep!=null){
    	    	     sb.append(sep);
    	    	}
    	    }
    		
    		
    		if(item==null){
    			
    		}else{
    			sb.append(item);
    		}
    	
    	}
    	
    	return sb.toString();
    }
    
    public static String repeat(String str,int num,String sep){
    	if(num<=0){
    		return null;
    	}
    	boolean isfirst = true;
    	StringBuilder sb = new StringBuilder();
    	for(int i=0;i<num;i++){
    		
    		 if(isfirst){
     	    	isfirst = false;
     	    }else{
     	    	if(sep!=null){
     	    	     sb.append(sep);
     	    	}
     	    }
     			
     		if(str!=null){
     			sb.append(str);
     		}
    	}
    	
    	return sb.toString();
    	
    }
    
    public static String join(Object[]objs,String sep){
    	if(objs==null){
    		return null;
    	}
    	boolean isfirst = true;
    	StringBuilder sb = new StringBuilder();
    	
    	for(Object item:objs){
    		
    	    if(isfirst){
    	    	isfirst = false;
    	    }else{
    	    	if(sep!=null){
    	    	     sb.append(sep);
    	    	}
    	    }

    		if(item==null){
    			
    		}else{
    			sb.append(item);
    		}
    	
    	}
    	
    	return sb.toString();
    }
    
    
    public static String join(int[]objs,String sep){
    	if(objs==null){
    		return null;
    	}
    	boolean isfirst = true;
    	StringBuilder sb = new StringBuilder();
    	
    	for(int item:objs){
    		
    	    if(isfirst){
    	    	isfirst = false;
    	    }else{
    	    	if(sep!=null){
    	    	     sb.append(sep);
    	    	}
    	    }
    		sb.append(item);
    		  	
    	}
    	
    	return sb.toString();
    }
    
    
    public static List<String> split(String str, String sep) {
		if (str == null) {
			return null;
		}
		if(sep==null || sep==""){
			sep = ",";
		}
		//if (sep == null || sep.length() < 1) {
		//	return null;
		//}
		List<String> list = new ArrayList<String>();
		int fromIndex = 0;
		//int len = str.length();
		int sepLen = sep.length();
		int pos = 0;
		String tmp = null;
		// a,b,c
		while (true) {
			pos = str.indexOf(sep, fromIndex);
			if (pos < 0) {
				list.add(str.substring(fromIndex));
				break;
			}
			tmp = str.substring(fromIndex, pos);		
			list.add(tmp);
			fromIndex = fromIndex + tmp.length() + sepLen;
		}
		return list;
	}
    
    public static String replaceAll(String str,String findStr,String replaceStr){
    	return null;
    }
    

    private static String getCharset(String str) {
		// application/x-javascript; charset=GBK
		if(isBlank(str)){
			return null;
		}
		int pos = str.indexOf(CHARSET_KEY);
		if(pos >= 0){
			return str.substring(pos+CHARSET_KEY_LEN);
		}

		return null;
	}
    
    
	public static void runHttpCall(String url,Map<String,String> params,Runnable callback){
		
	}
	
	public static void close(Closeable obj) {
		if (obj == null) {
			return;
		}
		try {
			obj.close();
		} catch (Throwable e) {

		}
	}
	
	public static void x(){
		
		
	}
	
	public static String getTraceInfo(Throwable e){
		if(e==null){
			return null;
		}
		ByteArrayOutputStream buf = null;
		try{
		buf = new java.io.ByteArrayOutputStream();
		e.printStackTrace(new java.io.PrintWriter(buf, true));
		String s = buf.toString();

		return s;
		}catch(Throwable e2){
			return e+"";
		}finally{
			close(buf);
		}
		
	}

}
