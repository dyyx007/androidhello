package com.dyyx.androidhello;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.dyyx.androidhello.base.BaseActivity;
import com.dyyx.androidhello.chat.Msg;
import com.dyyx.androidhello.chat.MsgAdaptor;
import com.dyyx.androidhello.util.DyyxCommUtil;

public class ChatActivity extends BaseActivity implements OnClickListener {

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
		initData();
		adaptor = new MsgAdaptor(this,data);
		
		
		msg = (EditText) findViewById(R.id.editTextMsg);
		send = (Button) findViewById(R.id.btnSend);
		msgs = (ListView) findViewById(R.id.listViewMsgs);
		
		msgs.setAdapter(adaptor);
		
		send.setOnClickListener(this);
		


	}
	
	@Override
	public void onClick(View v) {
		
		String content = msg.getText().toString();
		if (!DyyxCommUtil.isBlank(content)) {
		Msg m = new Msg(content, Msg.TYPE_SENT);
		data.add(m);
		// 当有新消息时，刷新ListView中的显示
		adaptor.notifyDataSetChanged(); 
		// 将ListView定位到最后一行
		msgs.setSelection(data.size()); 
		// 清空输入框中的内容
		msg.setText(""); 
	 
		}
	}

	private void initData(){
		
		Msg msg1 = new Msg("Hello guy.", Msg.TYPE_RECEIVED);
	
		data.add(msg1);
		Msg msg2 = new Msg("Hello. Who is that?", Msg.TYPE_SENT);
		data.add(msg2);
		Msg msg3 = new Msg("This is Tom. Nice talking to you. ", Msg.TYPE_RECEIVED);
		data.add(msg3);
		
		
	}

}
