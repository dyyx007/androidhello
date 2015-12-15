package com.dyyx.androidhello.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dyyx.androidhello.R;
import com.dyyx.androidhello.base.BaseFragment;

public class RightFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_right, container, false);
		return view;
	}
}
