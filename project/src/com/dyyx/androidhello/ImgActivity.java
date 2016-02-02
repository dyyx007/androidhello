package com.dyyx.androidhello;

import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.alibaba.fastjson.JSON;
import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.dto.HttpResult;
import com.dyyx.androidhello.dto.search.Item;
import com.dyyx.androidhello.dto.search.ItemResult;
import com.dyyx.androidhello.dto.search.ItemResultData;
import com.dyyx.androidhello.dto.search.SearchData;
import com.dyyx.androidhello.dto.search.SearchResult;
import com.dyyx.androidhello.util.DyyxCommUtil;
import com.dyyx.androidhello.util.HttpCallback;
import com.dyyx.androidhello.util.HttpUtil;
import com.dyyx.androidhello.util.ImageLoader;
import com.dyyx.androidhello.util.NetworkUtil;

public class ImgActivity extends BaseActivity {

	EditText textEditUrl = null;
	ImageView imageView = null;
	EditText textEditInfo = null;

	private static final String URL = "http://img.alicdn.com/bao/uploaded/i3/T1FcYZFpNcXXXXXXXX_!!0-item_pic.jpg_200x200q90.jpg";

	private static final String SEARCH_URL = "https://s.taobao.com/search?ajax=true&q=";

	private static final String Q = "bijini";

	//private static String result = null;
	private static String url = null;
	private static HttpResult httpResult = null;

	private static int showIndex = 0;
	private static int itemNum = 0;
	
	private static Map<Integer,String> netTypeMap = new HashMap<Integer,String>();
	
	private static final int NET_TYPE_WIFI = 1;
	
	static{
		
		netTypeMap.put(0,"no network");
		netTypeMap.put(1,"WIFI");
		netTypeMap.put(2,"3G");
		netTypeMap.put(3,"2G");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.img);

		textEditUrl = (EditText) findViewById(R.id.textEditUrl);
		textEditInfo = (EditText) findViewById(R.id.textEditInfo);
		imageView = (ImageView) findViewById(R.id.img01);

	}

	public void clickHandle(View view) {
		int vid = view.getId();

		if (vid == R.id.btnLoad) {
			
			if(!wifiCheck()){				
				return;
			}

			String url = textEditUrl.getText().toString();
			if (DyyxCommUtil.isBlank(url)) {
				url = URL;
				textEditUrl.setText(url);
			}
			ImageLoader.loadImage(imageView, url);

			return;
		}

		if (vid == R.id.btnSearch) {
			
			if(!wifiCheck()){				
				return;
			}

			String q = textEditUrl.getText().toString();
			if (DyyxCommUtil.isBlank(q)) {
				q = Q;
				textEditUrl.setText(q);
			}

			String qencode = q;
			try {
				qencode = URLEncoder.encode(q, "utf-8");
			} catch (Throwable e) {

			}

			url = SEARCH_URL + qencode;

			HttpCallback callback = new SearchCallback();
			HttpUtil.run(this, url, null, callback);

			return;
		}

		if (vid == R.id.btnShow) {
			
			if(!wifiCheck()){				
				return;
			}

			if (httpResult == null || DyyxCommUtil.isBlank(httpResult.result)) {
				String msg = "no data,please search";
				textEditInfo.setText(msg);
				return;
			}

			SearchResult searchResult = null;

			searchResult = JSON.parseObject(httpResult.result, SearchResult.class);
			//SearchData searchData = searchResult.mods;
			//ItemResult itemlist
			List<Item> items = getItems(searchResult);
			if (items == null || items.isEmpty()) {
				String msg = "search result is empty";
				textEditInfo.setText(msg);
			}

			int num = items.size();
			itemNum = num;
			if (showIndex >= num) {
				showIndex = 0;
			}

			Item item = items.get(showIndex);
			showIndex++;
			String pic_url = item.pic_url;

			if (pic_url.startsWith("//")) {
				pic_url = "http:" + pic_url;
			}

			ImageLoader.loadImage(imageView, pic_url);

			return;
		}

		if (vid == R.id.btnInfo) {

			String s = "showIndex=" + showIndex;
			s = s + ",itemNum=" + itemNum;
			s = s + "," + ImageLoader.getInfo();
			
			
			//应用程序最大可用内存 
	        long maxMemory = Runtime.getRuntime().maxMemory()/1024/1024; 
	        //应用程序已获得内存 
	        long totalMemory = Runtime.getRuntime().totalMemory()/1024/1024; 
	        //应用程序已获得内存中未使用内存 
	        long freeMemory = Runtime.getRuntime().freeMemory()/1024/1024; 

	        s = s + ",maxMemory=" + maxMemory;
	        s = s + ",totalMemory=" + totalMemory;
	        s = s + ",freeMemory=" + freeMemory;
	        
	        
			textEditInfo.setText(s);

			return;
		}
		
		if (vid == R.id.btnClearCache) {
			
			ImageLoader.clearCache();
			String msg = "clear cache done,"+DyyxCommUtil.getNowDateString();
			textEditInfo.setText(msg);
			return;
		}
		
	    if (vid == R.id.btnResetIndex) {
	    	
	    	showIndex = 0;
			String msg = "shwo index done,"+DyyxCommUtil.getNowDateString();
			textEditInfo.setText(msg);
			
			return;
		}
	    
	    if (vid == R.id.btnLogs) {
	    	
	    	String s = ImageLoader.getLogs();
	    	textEditInfo.setText(s);
			
			return;
		}
	    
       if (vid == R.id.btnNetType) {
	    	

           int netType = NetworkUtil.getAPNType(this);
           String type = netTypeMap.get(netType);
           if(DyyxCommUtil.isBlank(type)){
        	   type = "NK";
           }
           
           textEditInfo.setText("network type:"+type);
			
			return;
		}
	
	

	}
	
	private boolean wifiCheck(){
		 int netType = NetworkUtil.getAPNType(this);
		 if(NET_TYPE_WIFI==netType){
			 return true;
		 }
		 
		 textEditInfo.setText("only for wifi");
		 
		 return false;
	}

	private List<Item> getItems(SearchResult searchResult) {
		if (searchResult == null) {
			return null;
		}
		SearchData searchData = searchResult.mods;
		if (searchData == null) {
			return null;
		}
		ItemResult itemResult = searchData.itemlist;
		if (itemResult == null) {
			return null;
		}
		ItemResultData itemResultData = itemResult.data;
		if (itemResultData == null) {
			return null;
		}
		return itemResultData.auctions;
	}

	private class SearchCallback extends HttpCallback {

		public void run() {

			httpResult = this.result;
			//ImgActivity.this.result = httpResult.result;

			String str = "url=" + url + "\n";
			str = str + ",exception=" + result.exception + "\n";
			str = str + ",result=" + result.result + "\n";

			textEditInfo.setText(str);

		}
	}
	
}
