package ru.geekbrains.gviewer.presenter;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.util.ArrayList;
import java.util.List;

import ru.geekbrains.gviewer.model.InfoModel;
import ru.geekbrains.gviewer.view.InfoView;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class InfoPresenterImpl extends MvpBasePresenter<InfoView> implements InfoPresenter {

    private final InfoModel model;
    private Subscription subscription;

    public InfoPresenterImpl(InfoModel model) {
        this.model = model;
    }

    @Override
    public void detachView(boolean retainInstance) {
        if (!retainInstance && subscription != null && !subscription.isUnsubscribed()) {
            subscription.unsubscribe();
            subscription = null;
        }
        super.detachView(retainInstance);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void loadInformation() {
        getView().showLoading(false);
        subscription = model.retrieveInfo().observeOn(Schedulers.immediate()).subscribe(strings -> {
            InfoView view =getView();
            if (isViewAttached()) {
                view.setData(strings);
                view.showContent();
            }
        }, throwable -> {
            if (isViewAttached()) {
                List<String> list = new ArrayList<>();
                list.add(throwable.getMessage());
                getView().setData(list);
                getView().showContent();
            }
        });

//        subscription = model.retrieveInfo().observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<String[]>() {
//            @Override
//            public void call(String[] strings) {
//                String result = "";
//                for (int i = 0; i < strings.length; i++) {
//                    result += strings[i];
//                }
//                InfoView view = getView();
//                if (isViewAttached()) {
//                    view.showContent();
//                }
//
//            }
//        });
    }

}