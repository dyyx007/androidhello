package com.dyyx.androidhello;

import java.util.HashMap;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.util.LogUtil;
//import android.support.v7.app.ActionBarActivity;

//public class MainActivity extends ActionBarActivity {
public class MainActivity extends BaseActivity {
	
	private TextView textViewVersion;
	private TextView textview ;
    private Spinner spinner;

	private Button btn;
	
	private static Map<String,Class> activityMap = new HashMap<String,Class>();
	
	private static final String VERSION = "20160307-1025";
	
	static{
		
		activityMap.put("listview", ListViewActivity.class);
		activityMap.put("gridview", GridViewActivity.class);
		activityMap.put("systeminfo", SystemInfoActivity.class);
		activityMap.put("log", LogActivity.class);
		activityMap.put("intenttest", IntentTestActivity.class);
		activityMap.put("style", StyleActivity.class);
		activityMap.put("musicplayer", MusicPlayerActivity.class);
		activityMap.put("musicplayer2", MusicPlayer2Activity.class);
		activityMap.put("network", NetworkActivity.class);
		activityMap.put("broadcast", BroadcastActivity.class);
		activityMap.put("chat", ChatActivity.class);
		
		activityMap.put("fragmenttest", FragmentTestActivity.class);
		activityMap.put("fragmentaddtest", FragmentAddActivity.class);
		
		activityMap.put("store",StoreActivity.class);
		activityMap.put("sp",SpActivity.class);
		
		activityMap.put("notification",NotificationActivity.class);
		activityMap.put("sms",SmsActivity.class);
		activityMap.put("photo",PhotoActivity.class);
		activityMap.put("service",ServiceActivity.class);
		activityMap.put("longrun",LongRunActivity.class);
		
		activityMap.put("lbs",LbsActivity.class);
		activityMap.put("sensor",SensorActivity.class);
		activityMap.put("urlschema",UrlSchemaActivity.class);
		activityMap.put("fund",FundActivity.class);
		activityMap.put("db",DBActivity.class);
		activityMap.put("commtest",CommTestActivity.class);
		activityMap.put("img",ImgActivity.class);
		activityMap.put("event",EventActivity.class);
		activityMap.put("scroll",ScrollActivity.class);
		activityMap.put("scrolllayout",ScrollLayoutActivity.class);
		
		
		activityMap.put("gamepintu",GamePintuActivity.class);
		activityMap.put("toolbar",ToolBarActivity.class);
		activityMap.put("myview",MyViewActivity.class);
		activityMap.put("config",ConfigActivity.class);
		
		
		
		
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//setContentView(R.layout.table_test);
		
	    //
		textViewVersion = (TextView) findViewById(R.id.textViewVersion);
		textview = (TextView) findViewById(R.id.textview01);
		spinner = (Spinner) findViewById(R.id.spinner01);
		btn = (Button)findViewById(R.id.button01);

		textViewVersion.setText(VERSION);
		
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
	    LogUtil.log(TAG, "onCreateOptionsMenu run");
	    
		return true;
	}
	
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
		LogUtil.log(TAG, "onPrepareOptionsMenu run");
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		LogUtil.log(TAG, "onOptionsItemSelected run,id="+item.getItemId());
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			
			LogUtil.log(TAG, "option menu,action_settings run");
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
