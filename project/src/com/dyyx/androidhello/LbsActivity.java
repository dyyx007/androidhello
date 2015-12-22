package com.dyyx.androidhello;

import java.util.List;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.util.DyyxCommUtil;
import com.dyyx.androidhello.util.LogUtil;

public class LbsActivity extends BaseActivity {

	Spinner spinnerLocationProvider;
	EditText textEditLbsResult;
	Button btnGetLbsProviders;
	Button btnGetLocation;
	Button btnSetLocationListener;
	LocationListener locationListener = null;
	LocationManager locationManager =null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lbs);

		textEditLbsResult = (EditText) findViewById(R.id.textEditLbsResult);
		btnGetLbsProviders = (Button) findViewById(R.id.btnGetLbsProviders);
		btnGetLocation = (Button) findViewById(R.id.btnGetLocation);
		btnSetLocationListener = (Button) findViewById(R.id.btnSetLocationListener);
		

		spinnerLocationProvider = (Spinner) findViewById(R.id.spinnerLocationProvider);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
		adapter.add(LocationManager.NETWORK_PROVIDER);
		adapter.add(LocationManager.GPS_PROVIDER);
		adapter.add(LocationManager.PASSIVE_PROVIDER);
		spinnerLocationProvider.setAdapter(adapter);
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		String defaultProvider = getDefaultProvider();

		String str = "defaultProvider=" + defaultProvider;
		textEditLbsResult.setText(str);
		if (!DyyxCommUtil.isBlank(defaultProvider)) {
			locationListener = new MyLocationListener();
		
			locationManager.requestLocationUpdates(defaultProvider, 5000, 1, locationListener);
		}

	}

	protected void onDestroy() {
		super.onDestroy();
		if (locationManager != null && locationListener!=null) {

			locationManager.removeUpdates(locationListener);
		}
	}

	public void clickHandle(View view) {
		int vid = view.getId();
		if (vid == R.id.btnGetLocation) {

			String provider = getSelectedProvider();
			Location location = null;
			String str = "provider=" + provider;
			try {
				location = locationManager.getLastKnownLocation(provider);
			} catch (Throwable e) {
				str = str + ",exception=" + e;
			}

			str = str + ",location=" + getLocationInfo(location);

			textEditLbsResult.setText(str);

			return;
		}

		if (vid == R.id.btnGetLbsProviders) {

			List<String> providerList = locationManager.getProviders(true);
			String str = "providerList=" + DyyxCommUtil.join(providerList, ",");
			textEditLbsResult.setText(str);

			return;
		}
		
		if(vid==R.id.btnSetLocationListener){
			
			String provider = getSelectedProvider();
			if(locationListener==null){
				
				locationListener = new MyLocationListener();
			}
			locationManager.removeUpdates(locationListener);
			locationManager.requestLocationUpdates(provider, 5000, 1, locationListener);
			
			String str = "setLocationListener done,provider="+provider;
			textEditLbsResult.setText(str);
			return;
		}

	}

	String getSelectedProvider() {
		return spinnerLocationProvider.getSelectedItem().toString();
	}

	String getDefaultProvider() {

		List<String> providerList = locationManager.getProviders(true);
		if (providerList == null || providerList.isEmpty()) {
			return null;
		}
		return providerList.get(0);
	}

	String getLocationInfo(Location location) {
		if (location == null) {
			return "location is null";
		}
		String s = "latitude=" + location.getLatitude() + ",longitude=" + location.getLongitude();
		s = s + ",location=" + location;
		return s;
		//return location.toString();
	}

	public class LocationResult {

		public String provider;
		public Location location;

		@Override
		public String toString() {
			String str = "provider=" + provider + ",location=" + location;
			return str;
		}
	}

	class MyLocationListener implements LocationListener {

		static final String TAG = "MyLocationListener";

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {

			LogUtil.log(TAG, "onStatusChanged,provider=" + provider + ",status=" + status + ",extras=" + extras);
		}

		@Override
		public void onProviderEnabled(String provider) {

			LogUtil.log(TAG, "onProviderEnabled,provider=" + provider);
		}

		@Override
		public void onProviderDisabled(String provider) {

			LogUtil.log(TAG, "onProviderDisabled,provider=" + provider);
		}

		@Override
		public void onLocationChanged(Location location) {
			// 更新当前设备的位置信息
			//showLocation(location);
			textEditLbsResult.setText(getLocationInfo(location)+",time="+DyyxCommUtil.getNowDateString());
		}
	}
}
