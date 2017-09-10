package com.yushilei.observable.async;

import com.yushilei.observable.async.observables.IterableOnSubscribe;
import com.yushilei.observable.async.observables.ObserverOnSchedulerObservable;
import com.yushilei.observable.async.observables.SubscribeOnSchedulerObservable;
import com.yushilei.observable.async.scheduler.Scheduler;

/**
 * Created by Administrator on 2017/9/10.
 */

public class Observable<T> {

    final OnSubscribe<T> onSubscribe;

    public Observable(OnSubscribe<T> onSubscribe) {
        this.onSubscribe = onSubscribe;
    }

    public static <T> Observable<T> from(Iterable<? extends T> iterable) {
        return new Observable<T>(new IterableOnSubscribe<T>(iterable));
    }

    public void subscribe(Subscriber<T> subscriber) {
        onSubscribe.call(subscriber);
    }

    public Observable<T> observerOn(Scheduler scheduler) {
        return new ObserverOnSchedulerObservable<>(this, scheduler);
    }

    public Observable<T> subscribeOn(Scheduler scheduler) {
        return new SubscribeOnSchedulerObservable<T>(this, scheduler);
    }


    public interface OnSubscribe<T> extends Action1<Subscriber<? super T>> {

    }
}
