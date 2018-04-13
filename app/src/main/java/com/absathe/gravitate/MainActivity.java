package com.absathe.gravitate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.absathe.gravitate.adapters.ViewPagerFragmentAdapter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;

public class MainActivity extends AppCompatActivity implements FBFragment.OnFragmentInteractionListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBottomNavigationBar();
    }

    protected void setBottomNavigationBar() {
        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);
        final AHBottomNavigationViewPager viewPager = (AHBottomNavigationViewPager) findViewById(R.id.view_pager);
        ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);

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

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                viewPager.setCurrentItem(position);
                return true;
            }
        });
    }

    public void onFacebookFragmentInteraction(String string){
        Intent viewPost = new Intent(MainActivity.this, FBPostView.class);
        viewPost.putExtra("webViewURL", string);
        MainActivity.this.startActivity(viewPost);
    }

}
