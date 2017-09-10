package com.yushilei.observable.async.observables;


import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.util.Log;

import com.yushilei.observable.async.Observable;
import com.yushilei.observable.async.Subscriber;

import java.util.Iterator;

/**
 * Created by Administrator on 2017/9/10.
 */

public class IterableOnSubscribe<T> implements Observable.OnSubscribe<T> {
    private final Iterable<? extends T> ts;

    public IterableOnSubscribe(Iterable<? extends T> ts) {
        this.ts = ts;
    }

    @Override
    public void call(Subscriber<? super T> subscriber) {
        try {
            for (T t : ts) {
                SystemClock.sleep(2);
                Log.d("IterableOnSubscribe", " subscribe " + Thread.currentThread().getName());
                subscriber.onNext(t);
            }
            subscriber.onComplete();
        } catch (Throwable trx) {
            subscriber.onError(trx);
        }
    }
}
