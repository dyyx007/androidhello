package com.dyyx.androidhello.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * SQLiteDatabase db = dbHelper.getWritableDatabase();
 * 
 *
 * @author  gang.dug 
 * @version	1.0.0   2016-1-12 ÉÏÎç11:50:16 
 * @since   JDK1.6
 */
public class HelloDBHelper extends SQLiteOpenHelper {
	
	private static final String TAG = HelloDBHelper.class.getSimpleName();

	Context context;

	public HelloDBHelper(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = getFundCreateSql();
		try {
			
			db.execSQL(sql);
			LogUtil.log(TAG, "onCreate done,sql="+sql);
			
		} catch (Throwable e) {
			LogUtil.log(TAG, "onCreate error,sql="+sql,e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		LogUtil.log(TAG, "onUpgrade,oldVersion=oldVersion"+oldVersion+",newVersion="+newVersion);
	}
	
	private static final String getFundCreateSql(){
		StringBuilder sb = new StringBuilder();
		sb.append("create table fund(");
		sb.append("id integer primary key autoincrement,");
		sb.append("report_date varchar(8) not null,");
		sb.append("hs300index real not null,");
		sb.append("sab integer not null,");
		sb.append("mfb integer not null,");
		sb.append("smv integer not null,");
		sb.append("cv  integer not null,");
		sb.append("bv  integer not null,");
		sb.append("sv  integer not null,");
		sb.append("result varchar(128) not null,");
		sb.append("buys text,");
		sb.append("sells text");
		sb.append(")");
		
		return sb.toString();
		
	}

}
