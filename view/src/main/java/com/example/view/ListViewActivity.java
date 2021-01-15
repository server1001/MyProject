package com.example.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {
    ListView listView;
    List<String >dataList;
    TestListViewAdapter testListViewAdapter;
    String [] data=new String[]{"show01","show02","show03","show04","show05","show06","show07","show08","show09","show10","show11","show12"};

    public static final int num=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        listView=findViewById(R.id.listView);
        dataList=new ArrayList<>();
        testListViewAdapter=new TestListViewAdapter(this);
        listView.setAdapter(testListViewAdapter);
        initData();
    }



    public void initData(){
        dataList.addAll(Arrays.asList(data));
        testListViewAdapter.setDataList(dataList);
    }
}