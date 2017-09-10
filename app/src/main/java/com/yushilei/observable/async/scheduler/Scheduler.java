package com.yushilei.observable.async.scheduler;

/**
 * Created by Administrator on 2017/9/10.
 */

public interface Scheduler {
    void execute(Runnable run);
}
