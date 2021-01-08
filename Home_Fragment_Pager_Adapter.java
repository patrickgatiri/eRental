package com.patrickgatirigmail.finale;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class Home_Fragment_Pager_Adapter extends FragmentPagerAdapter {
    private static final String TAG = "Home_Fragment_Pager_Adapter";

    public Home_Fragment_Pager_Adapter(FragmentManager fm) {
        super(fm);
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Fragment fragment = Fragment_Tenants.newInstance();
                Log.d(TAG, "getItem: Inflating the tenant details fragment");
                return fragment;
            case 1:
                Fragment fragment2 = Fragment_Rent.newInstance();
                Log.d(TAG, "getItem: Inflating the fragment_rent details fragment");
                return fragment2;
            case 2:
                Fragment fragment3 = Fragment_Water.newInstance();
                Log.d(TAG, "getItem: Inflating the water details fragment");
                return fragment3;
            default:
                return null;
        }
    }

    public int getCount() {
        return 3;
    }
}
