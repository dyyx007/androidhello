package com.dyyx.androidhello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.dyyx.androidhello.adaptor.MyListViewAdaptor;

public class ListViewActivity extends Activity {
	
	ListView lv = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.list_view);
		
		lv = (ListView) findViewById(R.id.listview01);
		
		MyListViewAdaptor d = new MyListViewAdaptor(this,getData());
		
		lv.setAdapter(d);
		
		
	}
	
	private List<Map> getData(){
		List<Map> list = new ArrayList<Map>();
		
		Map m = new HashMap();
		m.put("title","美女001");
		m.put("img",R.drawable.mm01);
		list.add(m);
		
		m = new HashMap();
		m.put("title","美女002");
		m.put("img",R.drawable.mm02);
		list.add(m);
		
		m = new HashMap();
		m.put("title","美女003");
		m.put("img",R.drawable.mm03);
		list.add(m);
		
		m = new HashMap();
		m.put("title","美女004");
		m.put("img",R.drawable.mm04);
		list.add(m);
		
		m = new HashMap();
		m.put("title","美女005");
		m.put("img",R.drawable.mm05);
		list.add(m);
		
		
		m = new HashMap();
		m.put("title","美女006");
		m.put("img",R.drawable.mm06);
		list.add(m);
		
		
		m = new HashMap();
		m.put("title","美女007");
		m.put("img",R.drawable.mm07);
		list.add(m);
		
		return list;	
	}
	

}
