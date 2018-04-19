package com.absathe.gravitate.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.absathe.gravitate.FBFragment;
import com.absathe.gravitate.HomeFragment;
import com.absathe.gravitate.InstaFragment;
import com.absathe.gravitate.SettingsFragment;
import com.absathe.gravitate.YTFragment;

/**
 * Created by ABSathe on 13-04-2018.
 */

public class ViewPagerFragmentAdapter extends android.support.v4.app.FragmentPagerAdapter {

    public ViewPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new HomeFragment();
            case 1: return new FBFragment();
            case 2: return new InstaFragment();
            case 3: return new YTFragment();
            case 4: return new SettingsFragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 5;
    }
}
