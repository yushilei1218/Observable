package com.yushilei.observable;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RxJavaActivity extends AppCompatActivity {
    private static final String TAG = "RxJavaActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
    }

    public void testRxJava(View view) {
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ids.add(i);
        }

        Observable.from(ids)
                .map(new Func1<Integer, String>() {
                    @Override
                    public String call(Integer integer) {
                        SystemClock.sleep(30);
                        Log.d(TAG, "call " + integer + getThreadName());
                        return integer.toString();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onNext(String integer) {
                        Log.d(TAG, "onNext " + integer + getThreadName());
                    }

                    @Override
                    public void onCompleted() {
                        Log.d(TAG, "onComplete " + getThreadName());
                    }

                    @Override
                    public void onError(Throwable trx) {
                        Log.d(TAG, "onError " + trx.getClass().getName() + " " + getThreadName());
                    }
                });

        Observable.just(1).lift(new Observable.Operator<String, Integer>() {
            @Override
            public Subscriber<? super Integer> call(final Subscriber<? super String> subscriber) {
                return new Subscriber<Integer>() {
                    @Override
                    public void onCompleted() {
                        subscriber.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        subscriber.onError(e);
                    }

                    @Override
                    public void onNext(Integer integer) {
                        subscriber.onNext(integer.toString());
                    }
                };
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {

            }
        });
    }

    private String getThreadName() {
        return " Thread Name:" + Thread.currentThread().getName();
    }
}
