package com.dyyx.androidhello;

import java.util.List;

import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.util.DBUtil;
import com.dyyx.androidhello.util.DyyxCommUtil;

public class DBActivity extends BaseActivity {

	EditText textEditSql = null;
	//Button btnRead = null;
	EditText textEditResult = null;

	private static String CREATE_PET_SQL = null;

	private static final String PET_DATA = "1,tiger,1,9.99";
	private static final String TABLE_PET = "pet";
	static {

		StringBuilder sb = new StringBuilder();
		sb.append("create  table  pet( \n");
		sb.append("id INTEGER primary key autoincrement, \n");
	
		// unqiue  unique!!!
		sb.append("name text not null unique, \n");
		sb.append("age  INTEGER not null, \n");
		sb.append("weight real \n");
		sb.append(")");

		CREATE_PET_SQL = sb.toString();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.db);

		textEditSql = (EditText) findViewById(R.id.textEditSql);
		textEditResult = (EditText) findViewById(R.id.textEditResult);

	}

	public void clickHandle(View view) {
		int vid = view.getId();
		if (vid == R.id.btnQuery) {

			String sql = textEditSql.getText().toString();
			if (DyyxCommUtil.isBlank(sql)) {
				String msg = "sql is blank";
				Toast.makeText(DBActivity.this, msg, Toast.LENGTH_SHORT).show();
				return;
			}
			try {
				List list = DBUtil.query(sql, null, null);
				String result = DyyxCommUtil.join(list, "\n");
				textEditResult.setText(result);
			} catch (Throwable e) {
				textEditResult.setText("error," + e + "," + DyyxCommUtil.getTraceInfo(e));
			}

			return;
		}
		// run update sql
		if (vid == R.id.btnRun) {

			String sql = textEditSql.getText().toString();
			if (DyyxCommUtil.isBlank(sql)) {
				String msg = "sql is blank";
				Toast.makeText(DBActivity.this, msg, Toast.LENGTH_SHORT).show();
				return;
			}
			try {
				DBUtil.executeSql(sql, null);

				String msg = "executeSql done," + DyyxCommUtil.getNowDateString();
				textEditResult.setText(msg);
			} catch (Throwable e) {
				textEditResult.setText("error," + e + "," + DyyxCommUtil.getTraceInfo(e));
			}

			return;
		}
		// get all tables
		if (vid == R.id.btnGetTables) {
			String sql = "SELECT name FROM sqlite_master WHERE type='table' ORDER BY name";
			textEditSql.setText(sql);

			try {

				List list = DBUtil.query(sql, null, null);
				String result = DyyxCommUtil.join(list, "\n");
				textEditResult.setText(result);
			} catch (Throwable e) {
				textEditResult.setText("error," + e + "," + DyyxCommUtil.getTraceInfo(e));
			}

			return;
		}

		if (vid == R.id.btnViewColumnInfo) {

			String sql = textEditSql.getText().toString();
			if (DyyxCommUtil.isBlank(sql)) {
				String msg = "sql is blank";
				Toast.makeText(DBActivity.this, msg, Toast.LENGTH_SHORT).show();
				return;
			}
			try {
				DBUtil.ColumnInfo ci = DBUtil.getColumnInfo(sql, null, 0);

				textEditResult.setText(ci.toString());

			} catch (Throwable e) {
				textEditResult.setText("error," + e + "," + DyyxCommUtil.getTraceInfo(e));
			}

			return;
		}

		if (vid == R.id.btnCreatePetTable) {
			String sql = CREATE_PET_SQL;
			textEditSql.setText(sql);

			try {

				DBUtil.executeSql(sql, null);

				String msg = "create pet table done," + DyyxCommUtil.getNowDateString();
				textEditResult.setText(msg);
			} catch (Throwable e) {
				textEditResult.setText("error," + e + "," + DyyxCommUtil.getTraceInfo(e));
			}

			return;
		}

		if (vid == R.id.btnInsertPet) {

			ContentValues values = getPetValues();
			if (values == null) {
				return;
			}
			values.put("id", (String) null);

			String msg = null;
			try {
				// TABLE_PET
				long id = DBUtil.getDatabase().insert(TABLE_PET, null, values);

				msg = "pet insert done,id=" + id + ",values=" + values;

			} catch (Throwable e) {
				msg = "pet insert error,"+e+",values=" + values + "," + DyyxCommUtil.getTraceInfo(e);
			}

			msg = msg + "," + DyyxCommUtil.getNowDateString();

			textEditResult.setText(msg);

			return;
		}

		if (vid == R.id.btnUpdatePet) {
				

			ContentValues values = getPetValues();
			if (values == null) {
				return;
			}
			
            String id = values.getAsString("id");
			String msg = null;
			try {
				// TABLE_PET
				int result = DBUtil.getDatabase().update(TABLE_PET, values,"id=?",new String[]{id});

				msg = "pet update done,result=" + result + ",values=" + values;

			} catch (Throwable e) {
				msg = "pet update error,"+e+",values=" + values + "," + DyyxCommUtil.getTraceInfo(e);
			}
        
			msg = msg + "," + DyyxCommUtil.getNowDateString();

			textEditResult.setText(msg);


			return;
		}

	}

	private ContentValues getPetValues() {

		//PET_DATA
		String str = textEditSql.getText().toString();
		if (DyyxCommUtil.isBlank(str)) {
			str = PET_DATA;
			textEditSql.setText(str);
		}
		str = str.trim();

		List<String> list = DyyxCommUtil.split(str, ",");
		if (list == null || list.size() != 4) {
			String msg = "pet data error," + str;
			textEditResult.setText(msg);

			str = PET_DATA;
			textEditSql.setText(str);

			return null;
		}
        
		int id = DyyxCommUtil.getInt(list.get(0), -1);
		int age = DyyxCommUtil.getInt(list.get(2), -1);
		String weightStr = list.get(3);
		
		double weight = DyyxCommUtil.getDouble(weightStr, -1);
		
		if(id<=0 || age<0 || weight<0){
			
			String msg = "pet data error," + str;
			textEditResult.setText(msg);

			str = PET_DATA;
			textEditSql.setText(str);

			return null;
		}
		
		ContentValues vs = new ContentValues();
		if(id>0){
		    vs.put("id", id+"");
		}
		vs.put("name", list.get(1));
		if(age>0){
		   vs.put("age", age+"");
		}
		if(weight>0){
		    vs.put("weight", weight+"");
		}

		return vs;
	}

}
