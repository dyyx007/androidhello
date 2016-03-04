package com.dyyx.androidhello.layout;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

import com.dyyx.androidhello.R;
import com.dyyx.androidhello.util.DialogUtil;

public class ToolBarLayout extends LinearLayout implements OnClickListener {

	Activity activity = null;

	public ToolBarLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.tool_bar, this);

		Button btnBack = (Button) findViewById(R.id.btnBack);
		Button btnInfo = (Button) findViewById(R.id.btnInfo);

		activity = (Activity) context;

		btnBack.setOnClickListener(this);
		btnInfo.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();

		if (id == R.id.btnBack) {
			activity.finish();
			return;
		}

		if (id == R.id.btnInfo) {
			String title = "tooll bar info";
			String msg = "this is tool bar";
			int icon = 0;
			String btnTitle = "";
			DialogUtil.show(activity, title, msg, icon, btnTitle);

			return;
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);  
	}
	
	

}
