package com.dyyx.androidhello;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.dyyx.androidhello.base.BaseActivity;

public class PhotoActivity extends BaseActivity implements OnClickListener {

	public static final int TAKE_PHOTO = 1;
	public static final int CROP_PHOTO = 2;
	private Button btnTakePhoto;
	private ImageView imageViewPhoto;
	private Uri imageUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sms);

		imageViewPhoto = (ImageView) findViewById(R.id.imageViewPhoto);
		btnTakePhoto = (Button) findViewById(R.id.btnTakePhoto);

		btnTakePhoto.setOnClickListener(this);

	

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnSendSms:

	
				break;

			default:
				break;
		}
	}

	


}
