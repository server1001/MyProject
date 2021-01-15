package com.rxjava;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class MainActivity extends AppCompatActivity {
    Observable observable;
    Observer observer;


    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test();
        retrofit.create()
        Call call=null;

        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });

    }

    void test() {
        //观察者
        observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.e("===========  ", "onNext" + "s");
            }

            @Override
            public void onNext(String s) {
                Log.e("===========onNext  ", "onNext" + s);
            }


            @Override
            public void onError(Throwable e) {
                Log.e("===========onNext  ", "s");

            }

            @Override
            public void onComplete() {
                Log.e("===========  ", "onComplete");

            }
        };
        //被观察者
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter emitter) throws Exception {
                emitter.onNext("1111111");
                emitter.onNext("22222222222");
                emitter.onNext("3333333333");
                emitter.onComplete();

            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())

                .subscribe(observer);


    }

    public static <T> T requireNonNull(T object, String message) {
        if (object == null) {
            throw new NullPointerException(message);
        }
        return object;
    }
}
