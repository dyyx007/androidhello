package com.dyyx.androidhello.util;

import android.database.Cursor;


public interface RowMapper<T>{
	
	public T getObject(Cursor cursor);
}