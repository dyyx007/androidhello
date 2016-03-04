package com.dyyx.androidhello;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnLongClickListener;

import com.dyyx.androidhello.game.GamePintuLayout;

public class GamePintuActivity extends Activity implements OnLongClickListener{

	GamePintuLayout mGameView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game_pintu);

		mGameView = (GamePintuLayout) findViewById(R.id.id_gameview);
		
		mGameView.setOnLongClickListener(this);

	}
	
	@Override
	public boolean onLongClick(View v) {
		// 长按事件貌似没触发
		showImgDialog();
		
		return true;
	}
	
	public void clickHandle(View view) {
		int vid = view.getId();

		if (vid == R.id.btnShowImg) {
			showImgDialog();	
		}
		
	}
	
	private void showImgDialog(){
		
		LayoutInflater lf = LayoutInflater.from(this);
		View viewtmp = lf.inflate(R.layout.game_pintu_pic, null);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		//builder.setTitle("image");
		//builder.setMessage("");
		builder.setCancelable(true);
		builder.setView(viewtmp);
		// Caused by: java.lang.NoSuchMethodError: android.app.AlertDialog$Builder.setView
		//builder.setView(R.layout.game_pintu_pic);
		builder.create().show();
		
	}


}
