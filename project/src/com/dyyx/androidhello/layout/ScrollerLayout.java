package com.dyyx.androidhello.layout;

import android.content.Context;
import android.support.v4.view.ViewConfigurationCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

public class ScrollerLayout extends ViewGroup {
	
	/**
     * ������ɹ���������ʵ��
     */
    private Scroller mScroller;

    /**
     * �ж�Ϊ�϶�����С�ƶ�������
     */
    private int mTouchSlop;

    /**
     * �ֻ�����ʱ����Ļ����
     */
    private float mXDown;

    /**
     * �ֻ���ʱ��������Ļ����
     */
    private float mXMove;

    /**
     * �ϴδ���ACTION_MOVE�¼�ʱ����Ļ����
     */
    private float mXLastMove;

    /**
     * ����ɹ�������߽�
     */
    private int leftBorder;

    /**
     * ����ɹ������ұ߽�
     */
    private int rightBorder;

    public ScrollerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        // ��һ��������Scroller��ʵ��
        mScroller = new Scroller(context);
        ViewConfiguration configuration = ViewConfiguration.get(context);
        // ��ȡTouchSlopֵ
        mTouchSlop = ViewConfigurationCompat.getScaledPagingTouchSlop(configuration);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            // ΪScrollerLayout�е�ÿһ���ӿؼ�������С
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if (changed) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childView = getChildAt(i);
                // ΪScrollerLayout�е�ÿһ���ӿؼ���ˮƽ�����Ͻ��в���
                //  void layout(int l, int t, int r, int b)
                // letf top  right bottom
                childView.layout(i * childView.getMeasuredWidth(), 0, (i + 1) * childView.getMeasuredWidth(), childView.getMeasuredHeight());
            }
            // ��ʼ�����ұ߽�ֵ
            leftBorder = getChildAt(0).getLeft();
            rightBorder = getChildAt(getChildCount() - 1).getRight();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mXDown = ev.getRawX();
                mXLastMove = mXDown;
                break;
            case MotionEvent.ACTION_MOVE:
                mXMove = ev.getRawX();
                float diff = Math.abs(mXMove - mXDown);
                mXLastMove = mXMove;
                // ����ָ�϶�ֵ����TouchSlopֵʱ����ΪӦ�ý��й����������ӿؼ����¼�
                if (diff > mTouchSlop) {
                    return true;
                }
                break;
        }
        // Return true to steal motion events from the children and have
        // them dispatched to this ViewGroup through onTouchEvent()
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mXMove = event.getRawX();
                int scrolledX = (int) (mXLastMove - mXMove);
                if (getScrollX() + scrolledX < leftBorder) {
                    scrollTo(leftBorder, 0);
                    return true;
                } else if (getScrollX() + getWidth() + scrolledX > rightBorder) {
                    scrollTo(rightBorder - getWidth(), 0);
                    return true;
                }
                scrollBy(scrolledX, 0);
                mXLastMove = mXMove;
                break;
            case MotionEvent.ACTION_UP:
                // ����ָ̧��ʱ�����ݵ�ǰ�Ĺ���ֵ���ж�Ӧ�ù������ĸ��ӿؼ��Ľ���
                int targetIndex = (getScrollX() + getWidth() / 2) / getWidth();
                int dx = targetIndex * getWidth() - getScrollX();
                // �ڶ���������startScroll()��������ʼ���������ݲ�ˢ�½���
                mScroller.startScroll(getScrollX(), 0, dx, 0);
                invalidate();
                break;
        }
        // True if the event was handled, false otherwise
        return super.onTouchEvent(event);
    }
    
    // View.OnClickListener
    
    //  View.OnTouchListener
    
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
    	return super.dispatchTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        // ����������дcomputeScroll()�������������ڲ����ƽ���������߼�
        if (mScroller.computeScrollOffset()) {
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
            invalidate();
        }
    }
}