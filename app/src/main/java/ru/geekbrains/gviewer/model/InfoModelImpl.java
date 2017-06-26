package ru.geekbrains.gviewer.model;

import java.util.List;

import rx.Observable;
import rx.functions.Func2;

public class InfoModelImpl implements InfoModel {

//    private static final String FUBAR = "FUBAR";
//    private static final String SUSFU = "SUSFU";
//    private static final String BOHICA = "BOHICA";

    private static final String ERROR_MSG = " ";

    @Override
    public Observable<List<String>> retrieveInfo() {
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
//
//        Observable<String[]> stringObservable = Observable.timer(1L, TimeUnit.SECONDS).flatMap(new Func1<Long, Observable<String[]>>() {
//            @Override
//            public Observable<String[]> call(Long aLong) {
//                String[] str = {FUBAR, SUSFU, FUBAR, SUSFU, FUBAR, SUSFU, FUBAR, SUSFU, FUBAR, SUSFU};
//                return Observable.just(str);
//            }
//        });
//
//        Observable<Integer[]> integerObservable = Observable.timer(1L, TimeUnit.SECONDS).flatMap(new Func1<Long, Observable<Integer[]>>() {
//            @Override
//            public Observable<Integer[]> call(Long aLong) {
//                Integer[] integers = new Integer[10];
//                for (int i = 0; i < 10; i++) {
//                    integers[i] = i;
//                }
//                return Observable.just(integers);
//            }
//        });
//
//        return Observable.combineLatest(stringObservable, integerObservable, new Func2<String[], Integer[], String[]>() {
//            @Override
//            public String[] call(String[] strings, Integer[] integers) {
//                String[] result = new String[strings.length];
//                for (int i = 0; i < 10; i++) {
//                    result[i] = strings[i] + integers[i];
//                }
//                return result;
//            }
//        });
        Observable<String> stringObservable = Observable.from(new String[]{"A", "B", "C", "D", "E"});

        return Observable.range(1, 5).zipWith(stringObservable, (integer, s) -> {
            double random = Math.random();
            if (random > 0.95) {
                throw new IllegalStateException(ERROR_MSG);
            }
            return integer + s;
        }).buffer(3);
    }
}