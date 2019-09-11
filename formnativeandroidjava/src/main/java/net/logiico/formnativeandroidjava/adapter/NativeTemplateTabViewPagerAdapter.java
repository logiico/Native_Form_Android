package net.logiico.formnativeandroidjava.adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import net.logiico.formnativeandroidjava.fragment.NativeTemplateFragment;
import net.logiico.formnativeandroidjava.model.NativeTemplateTab;

import java.util.List;

public class NativeTemplateTabViewPagerAdapter extends FragmentStatePagerAdapter {
    public List<NativeTemplateTab> Tabs;
    int PAGE_COUNT;
    //    public ArrayList<NF_B_Tab> Tabs;
    boolean editable;
    private String tabTitles[];
    private Context context;

    //    public FN_FormNative_ViewPagerAdaptor(ArrayList<NF_B_Tab> Tabs, FragmentManager fm, int pagecount, boolean editable, Context context) {
    public NativeTemplateTabViewPagerAdapter(List<NativeTemplateTab> Tabs, FragmentManager fm, int pageCount, boolean editable, Context context) {
        super(fm);
        this.context = context;
        this.PAGE_COUNT = pageCount;
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
        NativeTemplateFragment f = new NativeTemplateFragment();
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