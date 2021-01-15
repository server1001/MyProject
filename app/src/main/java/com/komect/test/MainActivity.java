package com.komect.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.komect.test.photoview.PhotoView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RcvAppDetailImgAdapter rcvAppDetailImgAdapter;
    String[] url = new String[]{
//            "https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=198338726,3753792958&fm=26&gp=0.jpg",
//            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606904360813&di=6c6de6be31de43298c76b4f277f55b3d&imgtype=0&src=http%3A%2F%2Fimage.biaobaiju.com%2Fuploads%2F20180801%2F23%2F1533137915-IrGnpWgoJN.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606904360813&di=7ee59bcf570def47a124d8f50e7c0b25&imgtype=0&src=http%3A%2F%2Fimage.biaobaiju.com%2Fuploads%2F20190508%2F18%2F1557309954-eqIcisDRMS.jpeg",
            "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=3782644332,3207850232&fm=26&gp=0.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606904360813&di=5f5e6f7995a943e4dc4e5316559acaf3&imgtype=0&src=http%3A%2F%2F2c.zol-img.com.cn%2Fproduct%2F124_500x2000%2F748%2FceZOdKgDAFsq2.jpg",
            "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1606904360812&di=4eeaca4247f97b69780dd146f487a1ba&imgtype=0&src=http%3A%2F%2Fimage.biaobaiju.com%2Fuploads%2F20190705%2F22%2F1562338783-rnglzLTeSQ.jpg",
            "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2271071396,2251292987&fm=26&gp=0.jpg"
    };
    ArrayList urlList;
    PhotoView photoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView=findViewById(R.id.rcv_test);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        setData();
        rcvAppDetailImgAdapter = new RcvAppDetailImgAdapter( this);
        //  rcvAppDetailImgAdapter.setPictureClickCallback(this);
        rcvAppDetailImgAdapter.setList(urlList);
        recyclerView.setAdapter(rcvAppDetailImgAdapter);
    }
    void setData() {
        urlList = new ArrayList();
        urlList.addAll(Arrays.asList(url));
    }
}