package com.example.nativeformmakerandroid.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import com.example.nativeformmakerandroid.fragment.FN_FormNative_Fragment;
import com.example.nativeformmakerandroid.model.NativeForm.NF_B_Tab;

/**
 * Created by imac on 2/13/17.
 */


public class FN_FormNative_ViewPagerAdaptor extends FragmentStatePagerAdapter {
    int PAGE_COUNT;

    private String tabTitles[];
    private Context context;
    public ArrayList<NF_B_Tab> Tabs;
    boolean editable;

    public FN_FormNative_ViewPagerAdaptor(ArrayList<NF_B_Tab> Tabs, FragmentManager fm, int pagecount, boolean editable, Context context) {
        super(fm);
        this.context = context;
        this.PAGE_COUNT = pagecount;
        this.Tabs = Tabs;
        this.editable = editable;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        FN_FormNative_Fragment f = new FN_FormNative_Fragment();
        f.setObjectFunction(Tabs, position, editable);
        return f;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        tabTitles = new String[Tabs.size()];
        for (int i = 0; i < Tabs.size(); i++) {
            tabTitles[i] = Tabs.get(i).Name;
        }
        return tabTitles[position];
    }


}