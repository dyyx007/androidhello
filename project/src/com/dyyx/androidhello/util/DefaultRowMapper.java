package com.dyyx.androidhello.util;

import java.util.HashMap;
import java.util.Map;

import android.database.Cursor;


public class DefaultRowMapper implements RowMapper<Map<String,String>>{
	
	private String[]columnNames = null;
	private int columnCount = 0;
	private String[]rawColumnNames = null;
	
	
	public Map<String,String> getObject(Cursor cursor){
		if(cursor==null){
			return null;
		}
		buildColumnInfo(cursor);
		
		Map<String,String> m = new HashMap<String,String>();
		//  columnIndex the zero-based index of the target column
		for(int i=0;i<columnCount;i++){
			m.put(columnNames[i], cursor.getString(i));
		}
		
		return m;
	}
	
	private void buildColumnInfo(Cursor cursor){
		if(columnNames!=null){
			return;
		}
		
		int columnCountTmp = cursor.getColumnCount();
		if(columnCountTmp<=0){
			throw new RuntimeException("columnCount error,"+columnCountTmp);
		}
		String[]columnNamesTmp = new String[columnCountTmp];
		String[]rawColumnNamesTmp =  new String[columnCountTmp];
		
		
		String columnName = null;
		for(int i =0;i<columnCountTmp;i++){
			columnName =  cursor.getColumnName(i);
			rawColumnNamesTmp[i] = columnName;
			
			if(DyyxCommUtil.isBlank(columnName)){
				columnName = HelloConst.DEFAULT_COLUMN_NAME_PREFIX+i;
			}
			columnName = columnName.toLowerCase();
			columnNamesTmp[i] = columnName;
		}
		
		this.columnCount = columnCountTmp;
		this.columnNames = columnNamesTmp;
		this.rawColumnNames = rawColumnNamesTmp;
		
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public String[] getRawColumnNames() {
		return rawColumnNames;
	}
	
	
}