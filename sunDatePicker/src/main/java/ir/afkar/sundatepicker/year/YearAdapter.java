package ir.afkar.sundatepicker.year;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.afkar.sundatepicker.R;
import ir.afkar.sundatepicker.DatePickerDialog;
import ir.afkar.sundatepicker.tool.Date;
import ir.afkar.sundatepicker.tool.Util;

/*
 * Created by Alireza Afkar - 24/10/14
 */

public class YearAdapter extends BaseAdapter {
	private Context context;
	int[] years;
	Typeface typeface;

	public YearAdapter(Context context, int[] years) {
		this.context = context;
		this.years = years;
		typeface = DatePickerDialog.getTypeFace();
	}

	@Override
	public int getCount() {
		return years.length;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@SuppressWarnings("deprecation")
	@Override
	public View getView(final int position, View v, ViewGroup parent) {

		if (v == null) {
			v = LayoutInflater.from(parent.getContext()).inflate(
					R.layout.years_text_layout, null);
		}

		final TextView tv = (TextView) v.findViewById(R.id.year);

		if (typeface != null)
			tv.setTypeface(typeface);

		tv.setText(String.valueOf(years[position]));

		tv.setBackgroundColor(context.getResources().getColor(
				android.R.color.transparent));
		if (years[position] == Date.getYear()) {
			Date.setYearText(tv);
			tv.setBackgroundDrawable(DatePickerDialog.getCircle());
		}

		tv.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View arg0) {

				if (Date.getYearText() != null) {
					Date.getYearText().setBackgroundColor(
							context.getResources().getColor(
									android.R.color.transparent));
				}

				Date.setYear(years[position]);
				Date.setYearText(tv);
				Date.updateUI();

				tv.setBackgroundDrawable(DatePickerDialog.getCircle());
				Util.tryVibrate(context);
				DatePickerDialog.dayMonth.performClick();
			}
		});

		return v;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

}