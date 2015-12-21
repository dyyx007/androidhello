package com.dyyx.androidhello;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.util.LogUtil;

public class PhotoActivity extends BaseActivity implements OnClickListener {

	public static final int TAKE_PHOTO = 1;
	public static final int CROP_PHOTO = 2;
	private Button btnTakePhoto;
	private Button btnGetPhotoFromAlbum;
	private ImageView imageViewPhoto;
	private Uri imageUri;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo);

		imageViewPhoto = (ImageView) findViewById(R.id.imageViewPhoto);
		btnTakePhoto = (Button) findViewById(R.id.btnTakePhoto);
		
		btnGetPhotoFromAlbum = (Button) findViewById(R.id.btnGetPhotoFromAlbum);

		btnTakePhoto.setOnClickListener(this);
		btnGetPhotoFromAlbum.setOnClickListener(this);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.btnTakePhoto:

				String imageFile = "tempImage.jpg";
				File esd = Environment.getExternalStorageDirectory();
				File outputImage = new File(esd, imageFile);
				
				String str = "externalStorageDirectory="+esd.getAbsolutePath();
				str=str+",outputImage="+outputImage.getAbsolutePath();
				
				LogUtil.log(TAG, str);

				try {
					if (outputImage.exists()) {
						outputImage.delete();
					}
					outputImage.createNewFile();

				} catch (IOException e) {
					e.printStackTrace();
				}
				imageUri = Uri.fromFile(outputImage);
				Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				// 启动相机程序
				startActivityForResult(intent, TAKE_PHOTO);

				break;
				
			case R.id.btnGetPhotoFromAlbum:
				
				// 创建File对象，用于存储选择的照片
				File outputImage2 = new File(Environment. getExternalStorageDirectory(), "output_image.jpg");
				try {
				if (outputImage2.exists()) {
				outputImage2.delete();
				}
				outputImage2.createNewFile();
				} catch (IOException e) {
				e.printStackTrace();
				
				}
				imageUri = Uri.fromFile(outputImage2);
				Intent intent2 = new Intent("android.intent.action.GET_CONTENT");
				intent2.setType("image/*");
				intent2.putExtra("crop", true);
				intent2.putExtra("scale", true);
				intent2.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				startActivityForResult(intent2, CROP_PHOTO);
		
				
				break;
			default:
				break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
			case TAKE_PHOTO:
				if (resultCode == RESULT_OK) {
					Intent intent = new Intent("com.android.camera.action.CROP");
					intent.setDataAndType(imageUri, "image/*");
					intent.putExtra("scale", true);
					intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
					// 启动裁剪程序
					startActivityForResult(intent, CROP_PHOTO); 
				}
				break;

			case CROP_PHOTO:
				if (resultCode == RESULT_OK) {
					try {
						Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
						// 将裁剪后的照片显示出来
						imageViewPhoto.setImageBitmap(bitmap); 
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					}
				}
				break;

			default:
				break;
		}

	}

}
