package ru.geekbrains.gviewer.model;

import java.util.List;
import rx.Observable;

public interface InfoModel {
    Observable<List<String>> retrieveInfo();
}