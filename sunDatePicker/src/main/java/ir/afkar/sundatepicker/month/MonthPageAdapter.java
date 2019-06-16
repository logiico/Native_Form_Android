package ir.afkar.sundatepicker.month;

import ir.afkar.sundatepicker.DatePickerDialog;
import ir.afkar.sundatepicker.tool.Date;
import ir.afkar.sundatepicker.tool.JDF;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/*
 * Created by Alireza Afkar - 24/10/14
 */

public class MonthPageAdapter extends FragmentPagerAdapter {
	private String[] monthNames = { "فروردین", "اردیبهشت", "خرداد", "تیر",
			"مرداد", "شهریور", "مهر", "آبان", "آذر", "دی", "بهمن", "اسفند" };

	public MonthPageAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override
	public CharSequence getPageTitle(int position) {
		return monthNames[position];
	}

	@Override
	public int getCount() {
		if (DatePickerDialog.maxMonth > 0
				&& new JDF().getIranianYear() == Date.getYear())
			return DatePickerDialog.maxMonth;
		else
			return monthNames.length;
	}

	@Override
	public Fragment getItem(int month) {
		return  MonthFragement.newInstance(month);
	}
}