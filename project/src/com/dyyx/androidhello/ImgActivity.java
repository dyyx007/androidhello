package com.dyyx.androidhello;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.util.DyyxCommUtil;
import com.dyyx.androidhello.util.ImageLoader;

public class ImgActivity extends BaseActivity {

	EditText textEditUrl = null;
	ImageView imageView = null;
	
	private static final String URL = "http://img.alicdn.com/bao/uploaded/i3/T1FcYZFpNcXXXXXXXX_!!0-item_pic.jpg_200x200q90.jpg";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.img);

		textEditUrl = (EditText) findViewById(R.id.textEditUrl);
		imageView = (ImageView) findViewById(R.id.img01);

	}

	public void clickHandle(View view) {
		int vid = view.getId();
		
		
		if (vid == R.id.btnLoad) {

			String url = textEditUrl.getText().toString();
			if(DyyxCommUtil.isBlank(url)){
				url = URL;
				textEditUrl.setText(url);
			}
			ImageLoader.loadImage(imageView, url);
			

			return;
		}

		
	}
}
