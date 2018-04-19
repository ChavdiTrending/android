package com.absathe.gravitate.items;

/**
 * Created by ABSathe on 19-04-2018.
 */

public class InstaItem {
    private String imageURL;

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
}
