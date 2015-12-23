package com.dyyx.androidhello;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.util.DyyxCommUtil;

public class StoreActivity extends BaseActivity {

	Spinner spinnerMode;
	EditText textEditResult;
	Button btnRead;
	Button btnWrite;
	Button btnGetAppDirInfo;
	EditText textEditFile;

	static final String ASSETS_PREFIX = "assets:";
	static final String FILE_PREFIX = "file:";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.store);

		textEditResult = (EditText) findViewById(R.id.textEditResult);
		textEditFile = (EditText) findViewById(R.id.textEditFile);

		btnRead = (Button) findViewById(R.id.btnRead);
		btnWrite = (Button) findViewById(R.id.btnWrite);
		btnGetAppDirInfo = (Button) findViewById(R.id.btnGetAppDirInfo);

		spinnerMode = (Spinner) findViewById(R.id.spinnerMode);

		//this.MODE_PRIVATE
		//int id =R.raw.data ;

	}

	public void clickHandle(View view) {
		int vid = view.getId();
		if (vid == R.id.btnGetAppDirInfo) {

			Context context = HelloApp.getContext();

			StringBuilder sb = new StringBuilder();
			sb.append("cacheDir=" + context.getCacheDir());
			sb.append(",dbpath=" + context.getDatabasePath("dyyx"));
			sb.append(",filesDir=" + context.getFilesDir());
			sb.append(",filesDirFullPath=" + context.getFilesDir().getAbsolutePath());

			sb.append(",packageCodePath=" + context.getPackageCodePath());
			sb.append(",packageResourcePath=" + context.getPackageResourcePath());
			
			
			sb.append(",Environment.getDataDirectory()=" + Environment.getDataDirectory());
			
			sb.append(",Environment.getDownloadCacheDirectory()=" + Environment.getDownloadCacheDirectory());
			sb.append(",Environment.getExternalStorageDirectory()=" + Environment.getExternalStorageDirectory());
			sb.append(",Environment.getRootDirectory()=" + Environment.getRootDirectory());
			
			sb.append(",Environment.getExternalStorageState()=" + Environment.getExternalStorageState());
			sb.append(",Environment.getExternalStorageDirectory().getAbsolutePath()()=" +Environment.getExternalStorageDirectory().getAbsolutePath());

			
			//Environment.getExternalStoragePublicDirectory(type)
			textEditResult.setText(sb.toString());

			return;
		}

		if (vid == R.id.btnRead) {

			String file = textEditFile.getText().toString();
			if (DyyxCommUtil.isBlank(file)) {
				//return;
				file = "R.raw.data";
				textEditFile.setText(file);
			}

			InputStream is = null;

			String result = null;
			try {
				is = getInputStream(file);
				result = getString(is);
			} catch (Throwable e) {
				result = "read error," + e + ",file=" + file;
			} finally {
				DyyxCommUtil.close(is);
			}
			if (result == null) {
				result = "";
			}
			textEditResult.setText(result);

			return;
		}

		if (vid == R.id.btnWrite) {
			
			String result = textEditResult.getText().toString();
			if(DyyxCommUtil.isBlank(result)){
				//textEditResult.setText("result is blank");
				
				return;
			}
			String file = textEditFile.getText().toString();
			if (DyyxCommUtil.isBlank(file)) {
				//return;
				file = "data";
				textEditFile.setText(file);
			}
		    String msg = "";
			OutputStream os = null;
			try{
			os =  getOutputStream(file);
			os.write(result.getBytes(DyyxCommUtil.CHARSET));
			msg = msg+"write done";
			}catch(Throwable e){
				msg=msg+",write error,"+e;
			}finally{
				DyyxCommUtil.close(os);
			}
			Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();

			return;
		}

	}

	private InputStream getInputStream(String file) throws Exception {

		if (file.equals("R.raw.data")) {
			return getResources().openRawResource(R.raw.data);
		}
		if (file.startsWith(ASSETS_PREFIX)) {

			file = file.substring(ASSETS_PREFIX.length());
			
			Toast.makeText(this, "assets file,"+file, Toast.LENGTH_SHORT).show();

			return getAssets().open(file);
		}
		if (file.startsWith(FILE_PREFIX)) {
			file = file.substring(FILE_PREFIX.length());
			
			Toast.makeText(this, "file,"+file, Toast.LENGTH_SHORT).show();
			
			
			return new FileInputStream(file);
		}
		return openFileInput(file);

	}

	private String getString(InputStream is) throws Exception {
		if (is == null) {
			return null;
		}

		int num = is.available();
		if (num <= 0) {
			return null;
		}
		byte[] buf = new byte[num];
		is.read(buf);
		return new String(buf, DyyxCommUtil.CHARSET);

	}

	private OutputStream getOutputStream(String file) throws Exception {
		if (file.equals("R.raw.data")) {
			throw new Exception("not support,file=" + file);
		}
		if (file.startsWith(ASSETS_PREFIX)) {
			throw new Exception("not support,file=" + file);
		}
		if (file.startsWith(FILE_PREFIX)) {
			file = file.substring(FILE_PREFIX.length());
			return new FileOutputStream(file);
		}

		return openFileOutput(file, getMode());
	}

	private int getMode() {

		return Context.MODE_PRIVATE;
	}

}
