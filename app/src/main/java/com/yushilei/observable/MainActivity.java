package com.yushilei.observable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.yushilei.observable.async.Observable;
import com.yushilei.observable.async.Observer;
import com.yushilei.observable.async.Subscriber;
import com.yushilei.observable.async.scheduler.Schedulers;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void rxJava(View view) {
        List<Integer> ids = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ids.add(i);
        }
        ids.add(null);
        Observable.from(ids)
                .subscribeOn(Schedulers.io()).
                subscribe(new Subscriber<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        Log.d(TAG, "onNext " + integer.toString() + getThreadName());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete " + getThreadName());
                    }

                    @Override
                    public void onError(Throwable trx) {
                        Log.d(TAG, "onError " + trx.getMessage() + " " + getThreadName());
                    }
                });
    }

    private String getThreadName() {
        return " Thread Name:" + Thread.currentThread().getName();
    }
}
