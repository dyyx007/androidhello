package com.dyyx.androidhello;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.dyyx.androidhello.fragment.RightFragment2;
/**
 * 动态添加碎片
 * 
 *
 * @author  gang.dug 
 * @version	1.0.0   2015-12-15 下午3:54:07 
 * @since   JDK1.6
 */
public class FragmentAddActivity extends  FragmentActivity implements OnClickListener{

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_add_test);
		
		Button button = (Button) findViewById(R.id.btn01);
		button.setOnClickListener(this);
		
	

	}
	
	@Override
	public void onClick(View v) {

		RightFragment2 fragment = new RightFragment2();
		
		FragmentManager fragmentManager = getSupportFragmentManager();

		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.replace(R.id.layoutRight, fragment);
		
		// 添加到返回栈
		
		transaction.addToBackStack(null);
		
	
		
		transaction.commit();
	}

	
}
