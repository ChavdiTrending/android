package com.absathe.gravitate;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.absathe.gravitate.adapters.ViewPagerFragmentAdapter;
import com.absathe.gravitate.items.NetworkHandling;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;

public class MainActivity extends AppCompatActivity
        implements
        HomeFragment.OnFragmentInteractionListener,
        FBFragment.OnFragmentInteractionListener,
        InstaFragment.OnFragmentInteractionListener,
        YTFragment.OnFragmentInteractionListener{

    public static String httpResponse = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBottomNavigationBar();
        getDataFromServer();
    }

    protected void setBottomNavigationBar() {
        AHBottomNavigation bottomNavigation = findViewById(R.id.bottom_navigation);
        final AHBottomNavigationViewPager viewPager = findViewById(R.id.view_pager);
        ViewPagerFragmentAdapter adapter = new ViewPagerFragmentAdapter(getSupportFragmentManager());

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0, true);
        AHBottomNavigationItem item0 = new AHBottomNavigationItem(R.string.tab_home, R.drawable.ic_home, R.color.colorPrimary);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_fb, R.drawable.ic_facebook, R.color.color_fb);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_insta, R.drawable.ic_instagram, R.color.color_insta);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_yt, R.drawable.ic_youtube, R.color.color_yt);

        bottomNavigation.addItem(item0);
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);

        bottomNavigation.setForceTint(true);
        bottomNavigation.setColored(true);
        bottomNavigation.setTranslucentNavigationEnabled(true);

        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                viewPager.setCurrentItem(position, true);
                return true;
            }
        });
    }

    public void getDataFromServer() {
        NetworkHandling nh = new NetworkHandling(MainActivity.this);
        String stuff = nh.getFromServer("http://192.168.2.4:8000/trending/");

    }

    @Override
    public void onFacebookFragmentInteraction(String string){
        Intent viewPost = new Intent(MainActivity.this, FBPostView.class);
        viewPost.putExtra("webViewURL", string);
        MainActivity.this.startActivity(viewPost);
    }

    @Override
    public void onInstagramFragmentInteraction(String string) {
        Intent viewPost = new Intent(MainActivity.this, InstagramView.class);
        viewPost.putExtra("postURL", string);
        MainActivity.this.startActivity(viewPost);
    }

    @Override
    public void onYouTubeFragmentInteraction(String string) {
        try {
            Intent openInBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(string));
            startActivity(openInBrowser);
        } catch(ActivityNotFoundException e) {
            Toast.makeText(MainActivity.this, "Please install a web browser for this", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        } catch(Exception e) {
            Toast.makeText(MainActivity.this, "Unhandled exception " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
