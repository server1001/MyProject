package com.komect.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * description ：
 * author : mr.zhao
 * email : zqc0520@aliyun.com
 * date : 2020/12/2 15:18
 */
public class RcvAppDetailImgAdapter extends RecyclerView.Adapter<RcvAppDetailImgAdapter.ImgViewHolder> {
    ArrayList<String > urlList=new ArrayList<>();
    Context mContext;

    public RcvAppDetailImgAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setList(ArrayList<String> list) {
        this.urlList=list;
//        urlList.clear();
//        for (int i = 0; i < list.size(); i++) {
//            urlList.add(Uri.parse(list.get(i)));
//        }
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ImgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_app_detail_img_show, parent, false);
        return new ImgViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ImgViewHolder holder, final int position) {
          Glide.with(mContext).load(urlList.get(position)).into(holder.imageView);
          holder.imageView.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  ImagesLook.getInstance().show(mContext, holder.imageView, urlList,position);

              }
          });


    }

    @Override
    public int getItemCount() {
        return urlList.size();
    }

    class ImgViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;

        public ImgViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_detail_show);
        }


    }


}
