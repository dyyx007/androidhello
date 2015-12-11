package com.dyyx.androidhello;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.chat.Msg;
import com.dyyx.androidhello.chat.MsgAdaptor;

public class ChatActivity extends BaseActivity {

	ListView msgs;
	EditText msg;
	Button send;
	MsgAdaptor adaptor;
	
	List<Msg> data = new ArrayList<Msg>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.chat);
		
		
		
		


	}

	private void initData(){
		
		Msg msg1 = new Msg("Hello guy.", Msg.TYPE_RECEIVED);
		msgList.add(msg1);
		Msg msg2 = new Msg("Hello. Who is that?", Msg.TYPE_SENT);
		msgList.add(msg2);
		Msg msg3 = new Msg("This is Tom. Nice talking to you. ", Msg.TYPE_RECEIVED);
		msgList.add(msg3);
		
		
	}

}
