package com.dyyx.androidhello;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.dyyx.androidhello.base.BaseActivity;


public class SystemInfoActivity extends BaseActivity {
	
	private TextView textview ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.system_info);
		textview = (TextView) findViewById(R.id.textview01);
		
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		int width = metric.widthPixels;     // ��Ļ��ȣ����أ�
		int height = metric.heightPixels;   // ��Ļ�߶ȣ����أ�
	
		
		float density = metric.density;      // ��Ļ�ܶȣ�0.75 / 1.0 / 1.5��
		int densityDpi = metric.densityDpi;  // ��Ļ�ܶ�DPI��120 / 160 / 240��
		
		StringBuilder sb = new StringBuilder("width="+width+"px");
		sb.append(",height="+height+"px");
		sb.append(",density="+density);
		sb.append(",densityDpi="+densityDpi);
		
		// 
		double widthinch = width /  densityDpi;
		double heightinch = height /  densityDpi;
		
		sb.append(",widthinch="+widthinch);
		sb.append(",heightinch="+heightinch);
		
		double tmp =   widthinch*widthinch + heightinch*heightinch;
		double x = Math.pow(tmp,  0.5);
		
		sb.append(",x="+x);
		
		textview.setText(sb.toString());
		
	}

	
}
