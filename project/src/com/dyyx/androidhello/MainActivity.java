package com.dyyx.androidhello;

import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
//import android.support.v7.app.ActionBarActivity;

//public class MainActivity extends ActionBarActivity {
public class MainActivity extends Activity {
	
	private TextView textview ;
    private Spinner spinner;

	private Button btn;
	
	private static Map<String,Class> activityMap = new HashMap<String,Class>();
	
	static{
		
		activityMap.put("listview", ListViewActivity.class);
		activityMap.put("gridview", GridViewActivity.class);
		activityMap.put("systeminfo", SystemInfoActivity.class);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//setContentView(R.layout.table_test);
		
	    //
		textview = (TextView) findViewById(R.id.textview01);
		spinner = (Spinner) findViewById(R.id.spinner01);
		btn = (Button)findViewById(R.id.button01);
		
		//spinner.g
		
		btn.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
            	String tmp = spinner.getSelectedItem().toString();
            	if(tmp==null){           		
            		tmp = "";
            	}
            	textview.setText(tmp);
            	String key = tmp.toLowerCase();
            	Class activityClass = activityMap.get(key);
            	
            	if(activityClass!=null){
            		Intent i = new Intent(MainActivity.this,activityClass); 
            		 MainActivity.this.startActivity(i); 
            		return;
            	}
            	
            	//Toast.makeText(MainActivity.this, tmp, Toast.LENGTH_SHORT);
            	// 
            	HelloApp.selectedLayout = tmp;
            	
            	 Intent i = new Intent(MainActivity.this,com.dyyx.androidhello.LayoutTestActivity.class); 
            	 MainActivity.this.startActivity(i); 
                   
            }    
        });   
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
