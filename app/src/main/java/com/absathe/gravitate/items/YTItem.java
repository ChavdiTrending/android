package com.absathe.gravitate.items;

/**
 * Created by ABSathe on 19-04-2018.
 */

public class YTItem {
    private String thumnailURL;
    private String videoURL;
    private String videoTitle;

    public YTItem() {
        this.thumnailURL = null;
        this.videoURL = null;
        this.videoTitle = null;
    }

    public String getThumnailURL() {
        return thumnailURL;
    }

    public void setThumnailURL(String thumnailURL) {
        this.thumnailURL = thumnailURL;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String title) {
        this.videoTitle = title;
    }
}
