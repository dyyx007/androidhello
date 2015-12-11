package com.dyyx.androidhello.chat;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dyyx.androidhello.R;

public class MsgAdaptor extends BaseAdapter {
	LayoutInflater mInflater;
	List<Msg> data;

	public MsgAdaptor(Context context, List<Msg> data) {
		this.mInflater = LayoutInflater.from(context);
		this.data = data;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		Msg msg = data.get(position);
		View view;
		MsgViewHolder viewHolder;
		if (convertView == null) {
			view = mInflater.inflate(R.layout.msg_item, null);
			viewHolder = new MsgViewHolder();
			viewHolder.leftLayout = (LinearLayout) view.findViewById(R.id.left_layout);
			viewHolder.rightLayout = (LinearLayout) view.findViewById(R.id.right_layout);
			viewHolder.leftMsg = (TextView) view.findViewById(R.id.left_msg);
			viewHolder.rightMsg = (TextView) view.findViewById(R.id.right_msg);
			view.setTag(viewHolder);
		} else {
			view = convertView;
			viewHolder = (MsgViewHolder) view.getTag();
		}

		if (msg.type == Msg.TYPE_RECEIVED) {
			// 如果是收到的消息，则显示左边的消息布局，将右边的消息布局隐藏
			viewHolder.leftLayout.setVisibility(View.VISIBLE);

			viewHolder.rightLayout.setVisibility(View.GONE);
			viewHolder.leftMsg.setText(msg.content);
		} else if (msg.type == Msg.TYPE_SENT) {
			// 如果是发出的消息，则显示右边的消息布局，将左边的消息布局隐藏
			viewHolder.rightLayout.setVisibility(View.VISIBLE);
			viewHolder.leftLayout.setVisibility(View.GONE);
			viewHolder.rightMsg.setText(msg.content);
		}
		return view;
	}

	@Override
	public int getCount() {

		return data.size();
	}

	@Override
	public Object getItem(int arg0) {

		return null;
	}

	@Override
	public long getItemId(int arg0) {

		return 0;
	}

}
