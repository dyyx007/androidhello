package com.dyyx.androidhello.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class ImageLoader {

	private static final String TAG = ImageLoader.class.getSimpleName();
	
	//private static Map<String,Bitmap> cache = new ConcurrentHashMap<String,Bitmap>();
	private static Map<String,Bitmap> cache = Collections.synchronizedMap(new HashMap<String,Bitmap>());
	
	private static final AtomicLong hit = new AtomicLong(0);
	private static final AtomicLong miss = new AtomicLong(0);
	private static final AtomicLong error = new AtomicLong(0);
	
	private static List<String> logs = Collections.synchronizedList(new ArrayList<String>());
	
	private static final int MAX_NUM = 200;
	
	//private static final List<String> urls = Collections.synchronizedList(new ArrayList<String>()) ;
	
	
	// cache  memory  disk 
	// bitmap size in memory
	// 
	
	// default image
	// load error image
	//

	public static void loadImage(ImageView imageView, String url) {

		if (imageView == null) {
			return;
		}

		if (DyyxCommUtil.isBlank(url)) {
			return;
		}
		url = url.trim();
		
		Thread t = new ImageLoadThread(imageView,url);
		t.start();		

	}

	private static class ImageLoadThread extends Thread {

		ImageView imageView = null;
		String url = null;

		public ImageLoadThread(ImageView imageView, String url) {
			this.imageView = imageView;
			this.url = url;
		}

		public void run() {
			try {
				Bitmap bitmap = null;
				bitmap = cache.get(url);
				if(bitmap==null){
				   miss.getAndIncrement();
				   bitmap = getImageBitmap(url);
				   if(bitmap!=null){
					   cache.put(url, bitmap);
				   }
				}else{
				   hit.getAndIncrement();
				}
		        //imageView.post(action)
				//Handler h = imageView.getHandler();
				//if (h == null) {
				//	LogUtil.log(TAG, "imageView handler is null");
				//	return;
				//}
				ImageShowRunner r = new ImageShowRunner(imageView, bitmap);
				//h.post(r);
				imageView.post(r);
				
			} catch (Throwable e) {
				LogUtil.log(TAG, "ImageLoadThread run error,"+e,e);
			}

		}

	}

	
	private static void addLog(String msg,Throwable e){
		int num = logs.size();
		if(num>MAX_NUM){
			logs.clear();
		}
		String s = msg+","+e+":::"+DyyxCommUtil.getTraceInfo(e);
		logs.add(s);
	}
	
	private static class ImageShowRunner implements Runnable {

		ImageView imageView = null;
		Bitmap bitmap = null;

		public ImageShowRunner(ImageView imageView, Bitmap bitmap) {
			this.imageView = imageView;
			this.bitmap = bitmap;
		}

		public void run() {
			if (imageView == null || bitmap == null) {
				return;
			}
			try {
				imageView.setImageBitmap(bitmap);
			} catch (Throwable e) {
                // ignore
				LogUtil.log(TAG, "imageView.setImageBitmap error,"+e,e);
			}
		}
	}

	private static Bitmap getImageBitmap(String url) {
		URL imgUrl = null;
		Bitmap bitmap = null;
		InputStream is = null;
		try {
			imgUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection) imgUrl.openConnection();
			conn.setDoInput(true);
			conn.connect();
			is = conn.getInputStream();
			bitmap = BitmapFactory.decodeStream(is);
		} catch (Throwable e) {
			error.getAndIncrement();
			addLog("getImageBitmap error",e);
			throw new RuntimeException("getImageBitmap error,url=" + url, e);
		} finally {
			DyyxCommUtil.close(is);
		}
		return bitmap;
	}
	
	public static String getInfo(){
		StringBuilder sb = new StringBuilder();
		sb.append("hit="+hit);
		sb.append(",miss="+miss);
		sb.append(",error="+error);
		sb.append(",cacheSize="+cache.size());
		long tmp = getCachedBitmapSize();
		sb.append(",cachedBitmapSize="+tmp);
		sb.append(",cachedBitmapSize(MB)="+(tmp / (1024*1024)));
		
		
		return sb.toString();
	}
	
	private static long getCachedBitmapSize(){
		Set<String> keys = cache.keySet();
		Bitmap bitmap = null;
		long sum = 0;
		for(String key:keys){
			bitmap = cache.get(key);
			if(bitmap==null){
				continue;
			}
			sum=sum+bitmap.getByteCount();
		}
		return sum;
	}
	

	public static void clearCache(){
		if(cache==null){
			return;
		}
		cache.clear();
	}
	
	public static String getLogs(){
		return DyyxCommUtil.join(logs, "\n\n\n");
	}

}
