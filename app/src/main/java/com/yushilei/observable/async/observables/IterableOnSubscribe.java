package com.yushilei.observable.async.observables;


import android.support.annotation.NonNull;

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
                Event<T> event = new Event<T>(Event.CODE_SUCCESS, t);
                onResult(event);
            }
            Event<T> event = new Event<T>(Event.CODE_COMPLETE);
            onResult(event);
        } catch (Throwable trx) {
            Event<T> event = new Event<T>(Event.CODE_FAIL, trx);
            onResult(event);
        }
    }

    public void onResult(Event<T> event) {
        switch (event.code) {
            case Event.CODE_FAIL:
                break;
            case Event.CODE_SUCCESS:
                break;
            case Event.CODE_COMPLETE:
                break;
        }
    }
}
