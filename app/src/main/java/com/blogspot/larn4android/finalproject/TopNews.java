package com.blogspot.larn4android.finalproject;

/**
 * Created by Minto on 8/29/2016.
 */
public class TopNews {

    private String newsImage,newsTitle;

    public TopNews() {
    }

    public TopNews(String newsTitle, String newsImage) {
        this.newsTitle = newsTitle;
        this.newsImage = newsImage;
    }

    public String getNewsImage() {
        return newsImage;
    }

    public void setNewsImage(String newsImage) {
        this.newsImage = newsImage;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }
}
