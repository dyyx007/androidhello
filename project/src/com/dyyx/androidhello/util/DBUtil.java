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
		// java.lang.IllegalArgumentException:Empty bindArgs
		if(params==null || params.length<=0){
			db.execSQL(sql);
		}else{
			db.execSQL(sql, params);			
		}
	
	}

	public static Cursor query(String sql, String[] params) {	
		return db.rawQuery(sql, params);
	}
	
	public static ColumnInfo getColumnInfo(String sql, String[] params) {
		return getColumnInfo(sql,params,0);	
	}
	
	public static ColumnInfo getColumnInfo(String sql, String[] params,int limit) {
		Cursor cursor = null;
		try {
			if(limit<0){
				limit = 0;
			}
			String sqltmp = sql.toLowerCase();
			
			if(sqltmp.indexOf(" limit ")<=0 && limit>0){
				sql = sql + " limit "+limit;
			}
			
			cursor = db.rawQuery(sql, params);
			if(cursor==null){
				return null;
			}
			boolean hasNext = cursor.moveToFirst();
			
			return getColumnInfo(cursor);
		}finally{
			close(cursor);
		}
	}

	public static List query(String sql, String[] params, RowMapper rowMapper) {
		Cursor cursor = null;

		try {
			cursor = query(sql, params);
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
	
	public static ColumnInfo getColumnInfo(Cursor cursor){
		return getColumnInfo(cursor,true);
	}
	
	public static ColumnInfo getColumnInfo(Cursor cursor,boolean getType){
		if(cursor==null){
			return null;
		}
		ColumnInfo info = new ColumnInfo();
		
		int columnCountTmp = cursor.getColumnCount();
		if(columnCountTmp<=0){
			throw new RuntimeException("columnCount error,"+columnCountTmp);
		}
		String[]columnNamesTmp = new String[columnCountTmp];
		String[]rawColumnNamesTmp =  new String[columnCountTmp];
		int[] typesTmp = new int[columnCountTmp];
		
		String columnName = null;
		for(int i =0;i<columnCountTmp;i++){
			
			if (getType) {
				try {
					typesTmp[i] = cursor.getType(i);
				} catch (Throwable e) {
					// 结果集为空时会报错 android.database.CursorIndexOutOfBoundsException
					typesTmp[i] = -1;
				}
			}
			
			
			columnName =  cursor.getColumnName(i);
			rawColumnNamesTmp[i] = columnName;
			
			if(DyyxCommUtil.isBlank(columnName)){
				columnName = HelloConst.DEFAULT_COLUMN_NAME_PREFIX+i;
			}
			columnName = columnName.toLowerCase();
			columnNamesTmp[i] = columnName;
			
		}
		
		info.columnCount = columnCountTmp;
		info.columnNames = columnNamesTmp;
		info.rawColumnNames = rawColumnNamesTmp;
		info.types = typesTmp;
		
		return info;
		
	}
	
	public static class ColumnInfo{
		
		public String[]columnNames = null;
		public int columnCount = 0;
		public String[]rawColumnNames = null;
		public int[] types = null;
		
		@Override
		public String toString(){
			StringBuilder sb = new StringBuilder();
			sb.append("columnCount="+columnCount);
			sb.append(";\n rawColumnNames="+DyyxCommUtil.join(rawColumnNames, ","));
			sb.append(";\n columnNames="+DyyxCommUtil.join(columnNames, ","));
			sb.append(";\n types="+DyyxCommUtil.join(types, ","));
			return sb.toString();
		}
	}
	
	public static String getInsertSql(String table,List<String> columns){
		if(DyyxCommUtil.isBlank(table)){
			throw new RuntimeException("table is blank");
		}
		if(columns==null || columns.isEmpty()){
			throw new RuntimeException("columns is empty");
		}
		int num = columns.size();
		StringBuilder sb = new StringBuilder();
		
		sb.append("insert into ").append(table);
		sb.append("(").append(DyyxCommUtil.join(columns, ","));
		sb.append(")");
		sb.append(" values(");
		sb.append(DyyxCommUtil.repeat("?", num, ","));
		sb.append(")");
		
		return sb.toString();
	}
	
	public static String getUpdateSql(String table,List<String> columns,List<String> selectColumns){
		if(DyyxCommUtil.isBlank(table)){
			throw new RuntimeException("table is blank");
		}
		if(columns==null || columns.isEmpty()){
			throw new RuntimeException("columns is empty");
		}
		if(selectColumns==null || selectColumns.isEmpty()){
			throw new RuntimeException("selectColumns is empty");
		}
		
		
	
		StringBuilder sb = new StringBuilder();
		
		sb.append("update ").append(table);
		sb.append("set ").append(getColumnUpdateSql(columns));
		sb.append(" where ");
		sb.append(getColumnUpdateSql(selectColumns));
		
		
		return sb.toString();
	}
	
	private static String getColumnUpdateSql(List<String> columns){
		int num = columns.size();
		boolean isfirst = true;
		StringBuilder sb = new StringBuilder();
		
		for(String item:columns){
			if(isfirst){
				isfirst=false;
			}else{
				sb.append(",");
			}
			sb.append(item).append("=?");			
		}
		return sb.toString();
	}

}
