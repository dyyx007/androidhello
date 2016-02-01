package com.dyyx.androidhello.util;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
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
		return sb.toString();
	}

}
