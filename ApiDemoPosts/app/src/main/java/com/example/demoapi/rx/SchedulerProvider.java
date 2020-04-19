package com.example.demoapi.rx;

import rx.Scheduler;

public interface SchedulerProvider {

    Scheduler io();

    Scheduler computation();

    Scheduler main();
}
