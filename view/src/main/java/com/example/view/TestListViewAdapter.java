package com.example.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * description ：
 * author : mr.zhao
 * email : zqc0520@aliyun.com
 * date : 2020/10/15 10:36
 */
public class TestListViewAdapter extends BaseAdapter {
    Context context;
    List<String > dataList;

    public TestListViewAdapter(Context context) {
        this.context = context;
        dataList=new ArrayList<>();
    }

    public void setDataList(List<String> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.layout_lv_item,parent,false);
            viewHolder=new ViewHolder(convertView);
            viewHolder.init();
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(dataList.get(position));

        return convertView;
    }

    public class ViewHolder{
        View view;
        TextView textView;

        public ViewHolder(View view) {
            this.view = view;
        }

        public void init(){
            textView=view.findViewById(R.id.tv_show);
        }
    }
}
