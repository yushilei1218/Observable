package com.yushilei.observable.async;

/**
 * Created by Administrator on 2017/9/10.
 */

public interface Observer<T> {
    public void onNext(T t);

    public void onComplete();

    public void onError(Throwable trx);
}
