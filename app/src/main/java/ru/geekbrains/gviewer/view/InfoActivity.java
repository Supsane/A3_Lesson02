package ru.geekbrains.gviewer.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hannesdorfmann.mosby.mvp.viewstate.lce.LceViewState;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.MvpLceViewStateActivity;
import com.hannesdorfmann.mosby.mvp.viewstate.lce.data.RetainingLceViewState;

import java.util.List;

import ru.geekbrains.gviewer.R;
import ru.geekbrains.gviewer.model.InfoModelImpl;
import ru.geekbrains.gviewer.presenter.InfoPresenter;
import ru.geekbrains.gviewer.presenter.InfoPresenterImpl;

public class InfoActivity extends MvpLceViewStateActivity<RecyclerView, List<String>, InfoView, InfoPresenter> implements InfoView, SwipeRefreshLayout.OnRefreshListener {

    private static final String UNKNOWN_ERROR_MESSAGE = "Unknown error";

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setContentView(R.layout.screen_info);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        contentView.setLayoutManager(layoutManager);
        contentView.setHasFixedSize(true);
        adapter = new RecyclerAdapter();
        contentView.setAdapter(adapter);
    }

    @NonNull
    @Override
    public InfoPresenter createPresenter() {
        return new InfoPresenterImpl(new InfoModelImpl());
    }

    @Override
    protected String getErrorMessage(Throwable e, boolean pullToRefresh) {
        String errorMessage = e.getMessage();
        return errorMessage == null ? UNKNOWN_ERROR_MESSAGE : errorMessage;
    }

    @Override
    public void setData(List<String> data) {
        adapter.addItems(data);
    }

    @Override
    public void loadData(boolean pullToRefresh) {
        getPresenter().loadInformation();
    }

    @NonNull
    @Override
    public LceViewState<List<String>, InfoView> createViewState() {
        return new RetainingLceViewState<>();
    }

    @Override
    public List<String> getData() {
        return adapter.getList();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        loadData(true);
    }
}
