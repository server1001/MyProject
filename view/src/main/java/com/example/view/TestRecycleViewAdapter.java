package com.example.view;

import android.nfc.Tag;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * description ：
 * author : 赵青春
 * email : 1458174550@qq.com
 * date : 2020/10/15 10:11
 */
public class TestRecycleViewAdapter extends RecyclerView.Adapter<TestViewHolder> {

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_test, parent, false);
        return new TestViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 0;
    }
}
