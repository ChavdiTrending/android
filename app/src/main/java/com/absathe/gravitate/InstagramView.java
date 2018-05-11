package com.absathe.gravitate;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class InstagramView extends AppCompatActivity {

    private String postURL = null;
    private ImageView view = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram_view);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        postURL = getIntent().getStringExtra("postURL");
        if(postURL == null)
            return;

        view = findViewById(R.id.instaview_image);
        Glide.with(this)
                .load(postURL)
                .into(view);
    }

}
