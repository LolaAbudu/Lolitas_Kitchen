package org.pursuit.mealprep.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import org.pursuit.mealprep.fragments.ChooseOptionFragment;
import org.pursuit.mealprep.fragments.TypeInFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    private int numberOfTabs;

    public ViewPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TypeInFragment.newInstance();
            case 1:
                return ChooseOptionFragment.newInstance();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
