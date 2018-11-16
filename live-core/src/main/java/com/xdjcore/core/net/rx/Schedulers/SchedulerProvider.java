package com.xdjcore.core.net.rx.Schedulers;

import android.support.annotation.NonNull;

import io.reactivex.ObservableTransformer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SchedulerProvider implements BaseSchedulerProvider {

    private static class Holder {
        static SchedulerProvider provider = new SchedulerProvider();
    }

    public static SchedulerProvider getInstance() {
        return Holder.provider;
    }


    @NonNull
    @Override
    public Scheduler computation() {
        return Schedulers.computation();
    }

    @NonNull
    @Override
    public Scheduler io() {
        return Schedulers.io();
    }

    @NonNull
    @Override
    public Scheduler ui() {
        return AndroidSchedulers.mainThread();
    }

    @NonNull
    @Override
    public <T> ObservableTransformer<T, T> applySchedulers() {
        return observable->observable.subscribeOn(io()).observeOn(ui());

    }
    //new ObservableTransformer<T, T>() {
    //            @Override
    //            public ObservableSource<T> apply(Observable<T> observable) {
    //                return observable.subscribeOn(io()).observeOn(ui());
    //            }
    //        };

}
