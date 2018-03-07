package com.kotlin.akorzh.cleveroadtestkorzhaleksandr.viewmodel;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.OnLifecycleEvent;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.kotlin.akorzh.cleveroadtestkorzhaleksandr.manager.ApiManager;
import com.kotlin.akorzh.cleveroadtestkorzhaleksandr.model.Articles;

import java.util.List;

import io.reactivex.disposables.Disposable;

import static com.kotlin.akorzh.cleveroadtestkorzhaleksandr.global.Const.BUSINES;
import static com.kotlin.akorzh.cleveroadtestkorzhaleksandr.global.Const.COUNTRY;

/**
 * Created by akorzh on 07.03.18.
 */

public class ArticlesViewModel extends ViewModel implements LifecycleObserver {

    private static final String TAG = "ArticlesViewModel";

    private Disposable mDisposable;

    private MutableLiveData<List<Articles>> mLiveDataArticles;
    private final MutableLiveData<Integer> selected = new MutableLiveData<>();


    public LiveData<List<Articles>> getUsers() {
        if (mLiveDataArticles == null) {
            mLiveDataArticles = new MutableLiveData<>();
            loadUsers();
        }
        return mLiveDataArticles;
    }

    public void select(Integer item) {
        selected.setValue(item);
    }

    public Integer getSelected() {
        return selected.getValue();
    }


    private void loadUsers() {
        mDisposable = new ApiManager().getNews(COUNTRY, BUSINES)
                .subscribe(articles -> mLiveDataArticles.setValue(articles)
                        , throwable -> Log.e(TAG, "throwable: ", throwable));
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void stop() {
        mDisposable.dispose();
    }

    public void setLifeCycle(Lifecycle lifeCycle) {
        lifeCycle.addObserver(this);
    }
}