package com.dyyx.androidhello;

import android.os.Bundle;
import android.widget.ListView;

import com.dyyx.androidhello.adaptor.MyListViewAdaptor;
import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.util.DataUtil;

public class ListViewActivity extends BaseActivity {
	
	ListView lv = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.list_view);
		
		lv = (ListView) findViewById(R.id.listview01);
		
		MyListViewAdaptor d = new MyListViewAdaptor(this,DataUtil.getData());
		
		lv.setAdapter(d);
		
		
	}
	
	

}
