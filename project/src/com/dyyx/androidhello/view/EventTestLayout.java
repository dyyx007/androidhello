package com.dyyx.androidhello.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import com.dyyx.androidhello.util.EventUtil;

public class EventTestLayout extends LinearLayout {
	public EventTestLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				EventUtil.add("EventTestLayout---dispatchTouchEvent---DOWN");
				break;
			case MotionEvent.ACTION_MOVE:
				EventUtil.add("EventTestLayout---dispatchTouchEvent---MOVE");
				break;
			case MotionEvent.ACTION_UP:
				EventUtil.add("EventTestLayout---dispatchTouchEvent---UP");
				break;
			default:
				break;
		}
		boolean result = super.dispatchTouchEvent(event);
		EventUtil.add("EventTestLayout.super.dispatchTouchEvent="+result);
		return result;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				EventUtil.add("EventTestLayout---onInterceptTouchEvent---DOWN");
				break;
			case MotionEvent.ACTION_MOVE:
				EventUtil.add("EventTestLayout---onInterceptTouchEvent---MOVE");
				break;
			case MotionEvent.ACTION_UP:
				EventUtil.add("EventTestLayout---onInterceptTouchEvent---UP");
				break;
			default:
				break;
		}
		boolean result = super.onInterceptTouchEvent(event);
		EventUtil.add("EventTestLayout.super.onInterceptTouchEvent="+result);
		return result;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				System.out.println("EventTestLayout---onTouchEvent---DOWN");
				break;
			case MotionEvent.ACTION_MOVE:
				System.out.println("EventTestLayout---onTouchEvent---MOVE");
				break;
			case MotionEvent.ACTION_UP:
				System.out.println("EventTestLayout---onTouchEvent---UP");
				break;
			default:
				break;
		}
		boolean result = super.onTouchEvent(event);
		EventUtil.add("EventTestLayout.super.onTouchEvent="+result);
		return result;
	}
}
