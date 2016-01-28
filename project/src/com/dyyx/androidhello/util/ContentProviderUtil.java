package com.dyyx.androidhello.util;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import com.dyyx.androidhello.HelloApp;

public class ContentProviderUtil {

	// uris
	// content://com.android.contacts/contacts
	public static final String URI_CONTACTS = "content://com.android.contacts/contacts";

	public static List query(String uri, RowMapper rowMapper) {
	
		if(DyyxCommUtil.isBlank(uri)){
			throw new RuntimeException("uri is blank");			
		}
		uri = uri.trim();
		
		if (rowMapper == null) {
			rowMapper = new DefaultRowMapper();
		}
		Cursor cursor = null;

		Uri urio = Uri.parse(uri);
		
		try {
			
			ContentResolver resolver = HelloApp.getContext().getContentResolver();
			cursor = resolver.query(urio, null, null, null, null);

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
			DBUtil.close(cursor);
		}
	}

}
