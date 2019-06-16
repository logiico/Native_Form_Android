package ir.afkar.sundatepicker.year;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/*
 * Created by Alireza Afkar - 24/10/14
 */

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