package com.absathe.gravitate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBottomNavigationBar();
    }

    protected void setBottomNavigationBar() {
        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        AHBottomNavigationItem item0 = new AHBottomNavigationItem(R.string.tab_home, R.drawable.ic_home, R.color.colorPrimary);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_fb, R.drawable.ic_facebook, R.color.color_fb);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_insta, R.drawable.ic_instagram, R.color.color_insta);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_yt, R.drawable.ic_youtube, R.color.color_yt);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.tab_settings, R.drawable.ic_settings, R.color.colorPrimaryDark);

        bottomNavigation.addItem(item0);
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);

        bottomNavigation.setForceTint(true);
        bottomNavigation.setColored(true);
        bottomNavigation.setTranslucentNavigationEnabled(true);
    }
}
