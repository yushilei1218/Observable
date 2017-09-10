package com.yushilei.observable.async.observables;

import com.yushilei.observable.async.Observable;
import com.yushilei.observable.async.Subscriber;
import com.yushilei.observable.async.scheduler.Scheduler;

/**
 * Created by Administrator on 2017/9/10.
 */

public class ObserverOnSchedulerObservable<T> extends Observable<T> {
    private final Scheduler scheduler;
    private Observable<T> observable;

    public ObserverOnSchedulerObservable(Observable<T> observable, Scheduler scheduler) {
        super(null);
        this.scheduler = scheduler;
        this.observable = observable;
    }

    @Override
    public void subscribe(Subscriber<T> subscriber) {
        ProxySubscriber<T> proxy = new ProxySubscriber<T>(subscriber) {
            @Override
            public void onNext(final T t) {
                scheduler.execute(new Runnable() {
                    @Override
                    public void run() {
                        mSubscriber.onNext(t);
                    }
                });
            }

            @Override
            public void onComplete() {
                scheduler.execute(new Runnable() {
                    @Override
                    public void run() {
                        mSubscriber.onComplete();
                    }
                });
            }

            @Override
            public void onError(final Throwable trx) {
                scheduler.execute(new Runnable() {
                    @Override
                    public void run() {
                        mSubscriber.onError(trx);
                    }
                });
            }
        };
        observable.subscribe(proxy);
    }
}

