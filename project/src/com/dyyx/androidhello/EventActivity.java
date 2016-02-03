package com.dyyx.androidhello;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.util.DyyxCommUtil;
import com.dyyx.androidhello.util.EventUtil;
import com.dyyx.androidhello.view.EventTestButton;

/**
 * @author gang.dug
 * @version 1.0.0 2016-2-3 15:56:08
 * @since JDK1.6
 */
public class EventActivity extends BaseActivity {

	EventTestButton eventTestBtn;
	
	TextView textViewInfo;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event);
		textViewInfo = (TextView)this.findViewById(R.id.textViewInfo);
		eventTestBtn = (EventTestButton)this.findViewById(R.id.eventTestBtn);
		
		eventTestBtn.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					EventUtil.add("EventTestButton---onTouch---DOWN");
					break;
				case MotionEvent.ACTION_MOVE:
					EventUtil.add("EventTestButton---onTouch---MOVE");
					break;
				case MotionEvent.ACTION_UP:
					EventUtil.add("EventTestButton---onTouch---UP");
					break;
				default:
					break;
				}
				// True if the listener has consumed the event, false otherwise
				// if true,onClick do not run !!!
				return false;
			}
		});
	}

	public void clickHandle(View view) {
		int vid = view.getId();
		
		textViewInfo.setText("");
		
        String now = DyyxCommUtil.getNowDateString();
		if (vid == R.id.eventTestBtn) {
			String msg = "eventTestBtn click,"+now;
			EventUtil.add(msg);
			textViewInfo.setText(msg);

			return;
		}

		if (vid == R.id.btn001) {
			
			String msg = "btn001 (in event test layout) click,"+now;
			EventUtil.add(msg);
			textViewInfo.setText(msg);

			return;
		}

		if (vid == R.id.btn002) {
			
			String msg = "btn002 click,"+now;
			EventUtil.add(msg);
			textViewInfo.setText(msg);

			return;
		}
		
		if (vid == R.id.btn003) {
			
			String msg = "btn003 click,"+now;
			EventUtil.add(msg);
			textViewInfo.setText(msg);

			return;
		}

	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				EventUtil.add("EventActivity---dispatchTouchEvent---DOWN");
				break;
			case MotionEvent.ACTION_MOVE:
				EventUtil.add("EventActivity---dispatchTouchEvent---MOVE");
				break;
			case MotionEvent.ACTION_UP:
				EventUtil.add("EventActivity---dispatchTouchEvent---UP");
				break;
			default:
				break;
		}
		boolean result = super.dispatchTouchEvent(event);
		EventUtil.add("EventActivity.super.dispatchTouchEvent="+result);
		return result;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				EventUtil.add("EventActivity---onTouchEvent---DOWN");
				break;
			case MotionEvent.ACTION_MOVE:
				EventUtil.add("EventActivity---onTouchEvent---MOVE");
				break;
			case MotionEvent.ACTION_UP:
				EventUtil.add("EventActivity---onTouchEvent---UP");
				break;
			default:
				break;
		}
		boolean result =  super.onTouchEvent(event);
		
		EventUtil.add("EventActivity.super.onTouchEvent="+result);
		
		return result;
	}

}
