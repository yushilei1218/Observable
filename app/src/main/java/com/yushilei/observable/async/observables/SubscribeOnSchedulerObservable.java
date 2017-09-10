package com.yushilei.observable.async.observables;

import com.yushilei.observable.async.Observable;
import com.yushilei.observable.async.Subscriber;
import com.yushilei.observable.async.scheduler.Scheduler;

/**
 * Created by Administrator on 2017/9/10.
 */

public class SubscribeOnSchedulerObservable<T> extends Observable<T> {
    private final Scheduler scheduler;
    private final Observable<T> parent;

    public SubscribeOnSchedulerObservable(Observable<T> parent, Scheduler scheduler) {
        super(new OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {

            }
        });
        this.parent = parent;
        this.scheduler = scheduler;
    }

    @Override
    public void subscribe(final Subscriber<T> subscriber) {
        scheduler.execute(new Runnable() {
            @Override
            public void run() {
                parent.subscribe(subscriber);
            }
        });

    }
    public class SchedulerOnSubscribe<T> implements OnSubscribe<T>{
        @Override
        public void call(Subscriber<? super T> subscriber) {

        }
    }
}
