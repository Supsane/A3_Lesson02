package ru.geekbrains.gviewer.model;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;

public class InfoModelImpl implements InfoModel {

    private static final String FUBAR = "FUBAR";
    private static final String SUSFU = "SUSFU";
    private static final String BOHICA = "BOHICA";

    @Override
    public Observable<String> retrieveInfo() {
//        return Observable.timer(2L, TimeUnit.SECONDS).flatMap(new Func1<Long, Observable<String>>() {
//            @Override
//            public Observable<String> call(Long aLong) {
//                Observable<String> result;
//                double random = Math.random();
//                if (random > 0.5 && random < 0.75) {
//                    result = Observable.just(FUBAR);
//                } else if (random > 0.75) {
//                    result = Observable.just(SUSFU);
//                } else {
//                    result = Observable.error(new IllegalStateException(BOHICA));
//                }
//                return result;
//            }
//        });

        Observable<String> stringObservable = Observable.timer(2L, TimeUnit.SECONDS).flatMap(new Func1<Long, Observable<String>>() {
            @Override
            public Observable<String> call(Long aLong) {
                return Observable.just(FUBAR, SUSFU, FUBAR, SUSFU, FUBAR, SUSFU, FUBAR, SUSFU, FUBAR, SUSFU);
            }
        });

        Observable<Integer> integerObservable = Observable.range(1, 10);

        return Observable.combineLatest(stringObservable, integerObservable, new Func2<String, Integer, String>() {
            @Override
            public String call(String s, Integer integer) {
                return s + integer;
            }
        });
    }
}