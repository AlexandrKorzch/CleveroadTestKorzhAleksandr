package com.kotlin.akorzh.cleveroadtestkorzhaleksandr;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;

import com.kotlin.akorzh.cleveroadtestkorzhaleksandr.adapter.ArticlesAdapter;
import com.kotlin.akorzh.cleveroadtestkorzhaleksandr.databinding.ActivityMainBinding;
import com.kotlin.akorzh.cleveroadtestkorzhaleksandr.model.Articles;
import com.kotlin.akorzh.cleveroadtestkorzhaleksandr.viewmodel.ArticlesViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements ArticlesAdapter.ItemClick, LifecycleOwner {

    private static final String TAG = "LOG";

    private ActivityMainBinding mBinding;
    private ArticlesAdapter mAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private ArticlesViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initRecyclerView();
        subscribeArticles();
    }

    private void initRecyclerView() {
        mBinding.rvArticles.setItemAnimator(new DefaultItemAnimator());
        mLinearLayoutManager = new LinearLayoutManager(this);
        mBinding.rvArticles.setLayoutManager(mLinearLayoutManager);
        mAdapter = new ArticlesAdapter();
        mBinding.rvArticles.setAdapter(mAdapter);
    }

    private void subscribeArticles() {
        mViewModel = ViewModelProviders.of(this).get(ArticlesViewModel.class);
        mViewModel.setLifeCycle(getLifecycle());
        mViewModel.getUsers().observe(this, this::showArticles);
    }

    private void showArticles(List<Articles> articles) {
        mAdapter.setClickCalBack(this);
        mAdapter.setArticles(articles);
        if (mViewModel.getSelected() != null) {
            new Handler().postDelayed(
                    () -> mLinearLayoutManager.scrollToPosition(mViewModel.getSelected())
                    , 300);
        }
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
        mViewModel.select(mLinearLayoutManager.findFirstVisibleItemPosition());
    }
}
