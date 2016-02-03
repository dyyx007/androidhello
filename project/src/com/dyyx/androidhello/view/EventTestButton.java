package com.dyyx.androidhello.view; 

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Button;

import com.dyyx.androidhello.util.EventUtil;


public class EventTestButton extends Button {
	public EventTestButton(Context context, AttributeSet attrs) {
		super(context, attrs);
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
		EventUtil.add("EventTestButton.super.dispatchTouchEvent="+result);
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
		boolean result = super.onTouchEvent(event);
		EventUtil.add("EventTestButton.super.onTouchEvent="+result);
		return result;
	}
}