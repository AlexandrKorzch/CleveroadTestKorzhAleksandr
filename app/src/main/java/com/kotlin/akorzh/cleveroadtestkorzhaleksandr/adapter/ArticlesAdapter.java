package com.kotlin.akorzh.cleveroadtestkorzhaleksandr.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.kotlin.akorzh.cleveroadtestkorzhaleksandr.R;
import com.kotlin.akorzh.cleveroadtestkorzhaleksandr.databinding.ArticleLayoutBinding;
import com.kotlin.akorzh.cleveroadtestkorzhaleksandr.model.Articles;
import com.kotlin.akorzh.cleveroadtestkorzhaleksandr.util.DateUtil;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;;

/**
 * Created by akorzh on 07.03.18.
 */

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ViewHolder> {

    private List<Articles> mArticles = new ArrayList<>();
    private ItemClick clickCalBack;

    @Override
    public ArticlesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ArticleLayoutBinding binding
                = DataBindingUtil.inflate(layoutInflater, R.layout.article_layout, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Articles article = mArticles.get(position);
        ArticleLayoutBinding binding = holder.getBinding();

        binding.tvTitle.setText(article.getTitle());
        binding.tvAuthor.setText(article.getAuthor());
        binding.tvName.setText(article.getSource().getName());
        binding.tvDescription.setText(article.getDescription());
        binding.tvDate.setText(article.getPublishedAt());
        binding.tvDate.setText(DateUtil.formatDate(article.getPublishedAt()));

        Picasso.with(binding.getRoot().getContext())
                .load(article.getUrlToImage())
                .into(binding.ivPicture);

        binding.ivPicture.setOnClickListener(view -> clickCalBack.openUrl(article.getUrl()));
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    public void setArticles(List<Articles> articles) {
        mArticles = articles;
        notifyDataSetChanged();
    }

    public void setClickCalBack(ItemClick clickCalBack) {
        this.clickCalBack = clickCalBack;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ArticleLayoutBinding binding;

        ViewHolder(ArticleLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        ArticleLayoutBinding getBinding() {
            return binding;
        }
    }

    public interface ItemClick{
        void openUrl(String url);
    }
}


