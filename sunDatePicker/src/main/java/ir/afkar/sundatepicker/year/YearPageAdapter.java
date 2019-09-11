package ir.afkar.sundatepicker.year;


/*
 * Created by Alireza Afkar - 24/10/14
 */

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class YearPageAdapter extends FragmentPagerAdapter {

    int[] years;

    public YearPageAdapter(FragmentManager fm, int[] years) {
        super(fm);
        this.years = years;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "" + years[position];
    }

    public int getYear(int position) {
        return years[position];
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Fragment getItem(int year) {
        return new YearFragement(years);
    }
}