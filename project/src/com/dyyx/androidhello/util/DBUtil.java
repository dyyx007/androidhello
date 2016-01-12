package com.dyyx.androidhello.util;

import java.io.Closeable;
import java.util.ArrayList;
import java.util.List;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.dyyx.androidhello.HelloApp;

public class DBUtil {
	
	private static final int VERSION = 1;
	private static final String NAME = "hellodb";

	private static SQLiteOpenHelper dbHelper = null;

	private static SQLiteDatabase db = null;

	static {
		dbHelper = new HelloDBHelper(HelloApp.getContext(), NAME, null, VERSION);
		db = dbHelper.getWritableDatabase();
	}

	public static SQLiteDatabase getDatabase() {
		return db;
	}

	/*
	 * public static SQLiteDatabase getReadableDatabase(){ SQLiteDatabase db =
	 * dbHelper.getReadableDatabase(); //db.close(); return db; }
	 */

	public static void executeSql(String sql, Object[] params) {
		db.execSQL(sql, params);
	}

	public static Cursor query(String sql, String[] params) {
		Cursor cursor = db.rawQuery(sql, params);
	
		return cursor;
	}

	public static List query(String sql, String[] params, RowMapper rowMapper) {
		Cursor cursor = null;

		try {
			cursor = db.rawQuery(sql, params);
			if (cursor == null) {
				return null;
			}

			boolean hasNext = cursor.moveToFirst();
			if (!hasNext) {
				return null;
			}
			if (rowMapper == null) {
				rowMapper = new DefaultRowMapper();
			}
			List list = new ArrayList();
			Object o = null;
			while (hasNext) {
				o = rowMapper.getObject(cursor);
				list.add(o);
				hasNext = cursor.moveToNext();
			}

			return list;

		} finally {
			close(cursor);
		}
	}

	public static void close(Closeable o) {
		if (o == null) {
			return;
		}
		try {
			o.close();
		} catch (Throwable e) {
			//
		}
	}
	
	

}
