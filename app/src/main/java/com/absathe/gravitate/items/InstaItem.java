package com.absathe.gravitate.items;

/**
 * Created by ABSathe on 19-04-2018.
 */

public class InstaItem {
    private String imageURL;
    private String caption;
    public InstaItem() {
        this.imageURL="something else";
    }

    public InstaItem(String string) {
        this.imageURL = string;
    }

    public void setImageURL(String string) {
        this.imageURL = string;
    }

    public String getImageURL() {
        return this.imageURL;
    }

    public void setCaption(String string) {
        this.caption = string;
    }

    public String getCaption() {
        return this.caption;
    }
}
