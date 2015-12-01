package com.dyyx.androidhello.adaptor;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dyyx.androidhello.R;


public class MyListViewAdaptor extends BaseAdapter{
	 
    private LayoutInflater mInflater;
    private List<Map> data;
    
    public MyListViewAdaptor(Context context,List<Map>  data){
        this.mInflater = LayoutInflater.from(context);
        this.data = data;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      
        ViewHolder holder = null;
        if (convertView == null) {
             
            holder=new ViewHolder();  
             
            convertView = mInflater.inflate(R.layout.list_view_item, null);
            holder.img = (ImageView)convertView.findViewById(R.id.img);
            holder.title = (TextView)convertView.findViewById(R.id.title);
            convertView.setTag(holder);
             
        }else {
             
            holder = (ViewHolder)convertView.getTag();
        }
         
         
        holder.img.setBackgroundResource((Integer)data.get(position).get("img"));
        holder.title.setText((String)data.get(position).get("title"));
       
       
         
        return convertView;
    }
     
    
    
}
     