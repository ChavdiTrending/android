package com.absathe.gravitate.items;

/**
 * Created by ABSathe on 13-04-2018.
 */

public class FBItem {
    private String webViewURL;
    public FBItem() {
        this.webViewURL = "www.google.com";
    }
    public FBItem(String string) {
        this.webViewURL = string;
    }

    public String getWebViewURL() {
        return this.webViewURL;
    }

    public FBItem setWebViewURL(String url) {
        this.webViewURL = url;
        return this;
    }
}
