package com.yushilei.observable.async.observables;

/**
 * Created by Administrator on 2017/9/10.
 */

public class Event<T> {
    public static final int CODE_SUCCESS = 0x01;
    public static final int CODE_FAIL = 0x02;
    public static final int CODE_COMPLETE = 0x03;
    final int code;
    final T t;
    final Throwable trx;

    public Event(int code) {
        this.code = code;
        t = null;
        trx = null;
    }

    public Event(int code, T t) {
        this.t = t;
        this.code = code;
        trx = null;
    }

    public Event(int code, Throwable trx) {
        this.code = code;
        this.trx = trx;
        t = null;
    }
}
