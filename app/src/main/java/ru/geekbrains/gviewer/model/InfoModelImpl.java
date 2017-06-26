package ru.geekbrains.gviewer.model;

import java.util.List;

import rx.Observable;

public class InfoModelImpl implements InfoModel {

    private static final String ERROR_MSG = " ";

    @Override
    public Observable<List<String>> retrieveInfo() {
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