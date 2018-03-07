package com.kotlin.akorzh.cleveroadtestkorzhaleksandr;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.kotlin.akorzh.cleveroadtestkorzhaleksandr.adapter.ArticlesAdapter;
import com.kotlin.akorzh.cleveroadtestkorzhaleksandr.databinding.ActivityMainBinding;
import com.kotlin.akorzh.cleveroadtestkorzhaleksandr.manager.ApiManager;
import com.kotlin.akorzh.cleveroadtestkorzhaleksandr.model.Articles;

import java.util.List;

import io.reactivex.disposables.Disposable;

import static com.kotlin.akorzh.cleveroadtestkorzhaleksandr.global.Const.BUSINES;
import static com.kotlin.akorzh.cleveroadtestkorzhaleksandr.global.Const.COUNTRY;

public class MainActivity extends AppCompatActivity implements ArticlesAdapter.ItemClick {

    private static final String TAG = "LOG";

    private ActivityMainBinding mBinding;
    private Disposable mDisposable;
    private ArticlesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initRecyclerView();
        requestArticles();
    }

    private void initRecyclerView() {
        mBinding.rvArticles.setItemAnimator(new DefaultItemAnimator());
        mBinding.rvArticles.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ArticlesAdapter();
        mBinding.rvArticles.setAdapter(mAdapter);
    }

    private void requestArticles() {
        mDisposable = new ApiManager().getNews(COUNTRY, BUSINES)
                .subscribe(this::showArticles
                        , throwable -> Log.e(TAG, "throwable: ", throwable));
    }

    private void showArticles(List<Articles> articles) {
        mAdapter.setArticles(articles);
        mAdapter.setClickCalBack(this);
    }

    @Override
    public void openUrl(String url) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDisposable.dispose();
    }
}
