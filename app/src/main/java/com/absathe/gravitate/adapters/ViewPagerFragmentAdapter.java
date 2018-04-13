package com.absathe.gravitate.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.absathe.gravitate.FBFragment;

/**
 * Created by ABSathe on 13-04-2018.
 */

public class ViewPagerFragmentAdapter extends android.support.v4.app.FragmentPagerAdapter {

    public ViewPagerFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return new FBFragment();
    }

    @Override
    public int getCount() {
        return 5;
    }
}
