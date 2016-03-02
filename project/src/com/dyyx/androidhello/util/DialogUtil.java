package com.dyyx.androidhello.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class DialogUtil {

	public static void show(Activity activity, String title, String msg, int icon, String btnTitle) {
		if (activity == null) {
			return;
		}
		if (DyyxCommUtil.isBlank(title)) {
			title = "title";
		}
		if (DyyxCommUtil.isBlank(msg)) {
			msg = "msg";
		}
		if (DyyxCommUtil.isBlank(btnTitle)) {
			btnTitle = "OK";
		}

		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle(title);
		builder.setMessage(msg);
		if (icon >= 0) {
			builder.setIcon(icon);
		}
		builder.setCancelable(false);
		builder.setPositiveButton(btnTitle, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		builder.create().show();

	}

}
