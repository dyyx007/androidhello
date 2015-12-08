package com.dyyx.androidhello;

import android.os.Bundle;
import android.widget.GridView;

import com.dyyx.androidhello.adaptor.MyGridViewAdaptor;
import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.util.DataUtil;

public class GridViewActivity extends BaseActivity {
	
	GridView gv = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.grid_view);
		
		gv = (GridView) findViewById(R.id.gridview01);
		
		MyGridViewAdaptor d = new MyGridViewAdaptor(this,DataUtil.getData());
		
		gv.setAdapter(d);
		
		
	}
	
	

}
