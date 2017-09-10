package com.yushilei.observable.async.observables;

import com.yushilei.observable.async.Subscriber;

/**
 * Created by Administrator on 2017/9/10.
 */

public abstract class ProxySubscriber<T> extends Subscriber<T> {
    final Subscriber<T> mSubscriber;

    public ProxySubscriber(Subscriber<T> mSubscriber) {
        this.mSubscriber = mSubscriber;
    }

}
