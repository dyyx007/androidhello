package com.dyyx.androidhello.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.dyyx.androidhello.util.LogUtil;

public class BaseFragment extends Fragment {

	protected String TAG = this.getClass().getSimpleName();

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		LogUtil.log(TAG, "Fragment.onAttach,"+this);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogUtil.log(TAG, "Fragment.onCreate,"+this);
	}

	/*
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		Log.d(TAG, "onCreateView");
		return super.onCreateView(inflater, container, savedInstanceState);
	}
	*/

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		LogUtil.log(TAG, "Fragment.onActivityCreated,"+this);
	}

	@Override
	public void onStart() {
		super.onStart();
		LogUtil.log(TAG, "Fragment.onStart,"+this);
	}

	@Override
	public void onResume() {
		super.onResume();
		LogUtil.log(TAG, "Fragment.onResume,"+this);
	}

	@Override
	public void onPause() {
		super.onPause();
		LogUtil.log(TAG, "Fragment.onPause,"+this);
	}

	@Override
	public void onStop() {
		super.onStop();
		LogUtil.log(TAG, "Fragment.onStop,"+this);
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		LogUtil.log(TAG, "Fragment.onDestroyView,"+this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		LogUtil.log(TAG, "Fragment.onDestroy,"+this);
	}

	@Override
	public void onDetach() {
		super.onDetach();
		LogUtil.log(TAG, "Fragment.onDetach,"+this);
	}

}
