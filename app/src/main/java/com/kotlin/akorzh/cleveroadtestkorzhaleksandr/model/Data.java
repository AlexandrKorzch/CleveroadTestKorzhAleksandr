package com.kotlin.akorzh.cleveroadtestkorzhaleksandr.model;

import java.util.List;

/**
 * Created by akorzh on 07.03.18.
 */

public class Data {

    private List<Articles> articles;
    private String totalResults;
    private String status;

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Data{" +
                "articles=" + articles +
                ", totalResults='" + totalResults + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}