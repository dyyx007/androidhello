package com.dyyx.androidhello.util;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
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

}
