package ir.afkar.sundatepicker.year;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.afkar.sundatepicker.R;
import ir.afkar.sundatepicker.tool.Date;

/*
 * Created by Alireza Afkar - 24/10/14
 */

@SuppressLint("ValidFragment")
public class YearMainFragement extends Fragment {

	private YearPageAdapter mAdapter;
	private static ViewPager mPager;

	public static int yearNumber = 0;
	int minYear;
	int maxYear;
	int[] years;

	@SuppressLint("ValidFragment")
	public YearMainFragement(int minYear, int maxYear) {
		setRetainInstance(true);
		this.minYear = minYear;
		this.maxYear = maxYear;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		return inflater.inflate(R.layout.frame_layout, null);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		years = new int[(maxYear - minYear) + 1];
		int counter = 0;
		for (int i = minYear; i <= maxYear; i++) {
			years[counter++] = i;
		}

		if (mAdapter == null)
			mAdapter = new YearPageAdapter(getChildFragmentManager(), years);

		mPager = (ViewPager) view.findViewById(R.id.pager);
		mPager.setAdapter(mAdapter);
		((TextView) view.findViewById(R.id.title))
				.setVisibility(View.INVISIBLE);

		mPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int year) {
				Date.setYear(mAdapter.getYear(year));
				Date.updateUI();
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});

		super.onViewCreated(view, savedInstanceState);
	}

}
