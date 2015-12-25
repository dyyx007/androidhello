package com.dyyx.androidhello;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.util.DyyxCommUtil;
import com.dyyx.androidhello.util.LogUtil;

public class SensorActivity extends BaseActivity {

	TextView textviewLight;
	TextView textviewM;
	TextView textviewA;
	TextView textviewMa;

	SensorManager sensorManager;
	SensorEventListener lightListener = null;
	SensorEventListener magneticEventListener = null;
	SensorEventListener accelerometerEventListener = null;

	float[] mvs = new float[3];
	float[] avs = new float[3];

	//private static long lastLogTime = 0;
	static final long SLEEP_TIME = 10L * 1000L;
	Handler handler = new Handler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sensor);
		textviewLight = (TextView) findViewById(R.id.textviewLight);

		textviewM = (TextView) findViewById(R.id.textviewM);
		textviewA = (TextView) findViewById(R.id.textviewA);
		textviewMa = (TextView) findViewById(R.id.textviewMa);

		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

		Sensor lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
		lightListener = new LightEventListener();
		sensorManager.registerListener(lightListener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);

		Sensor magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
		magneticEventListener = new MagneticEventListener();
		sensorManager.registerListener(magneticEventListener, magneticSensor, SensorManager.SENSOR_DELAY_GAME);

		Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		accelerometerEventListener = new AccelerometerEventListener();
		sensorManager
				.registerListener(accelerometerEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_GAME);

		//SensorManager.getRotationMatrix(R, I, gravity, geomagnetic)

		Thread t = new LogThread();

		t.start();

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (sensorManager != null) {
			sensorManager.unregisterListener(lightListener);

			sensorManager.unregisterListener(magneticEventListener);
			sensorManager.unregisterListener(accelerometerEventListener);
		}
	}

	private class LightEventListener implements SensorEventListener {
		@Override
		public void onSensorChanged(SensorEvent event) {
			// values数组中第一个下标的值就是当前的光照强度
			float value = event.values[0];
			textviewLight.setText("Current light level is " + value + " lx");
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			//
		}
	}

	private class MagneticEventListener implements SensorEventListener {
		@Override
		public void onSensorChanged(SensorEvent event) {
			float[] vs = event.values;

			float[] tmp = vs.clone();
			mvs = tmp;

			textviewM.setText("m="+getInfo(mvs));
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			//
		}
	}

	private class AccelerometerEventListener implements SensorEventListener {
		@Override
		public void onSensorChanged(SensorEvent event) {
			float[] vs = event.values;
			float[] tmp = vs.clone();
			avs = tmp;

			textviewA.setText("a="+getInfo(avs));
		}

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {
			//
		}
	}

	private String getInfo(float[] a) {
		if (a == null) {
			return "";
		}
		return a[0] + ":" + a[1] + ":" + a[2];
	}

	class LogThread extends Thread {

		@Override
		public void run() {

			while (true) {

				try {

					String s = "a="+getInfo(avs) + ",m=" + getInfo(mvs) + "," + DyyxCommUtil.getNowDateString();

					Runnable r = new UpdateRunner(s);
					handler.post(r);

					LogUtil.log(TAG, s);

					Thread.sleep(SLEEP_TIME);
				} catch (Throwable e) {

				}

			}

		}
	}

	class UpdateRunner implements Runnable {
		String s = null;

		public UpdateRunner(String s) {
			this.s = s;
		}

		@Override
		public void run() {
			textviewMa.setText(s);
		}
	}

}
