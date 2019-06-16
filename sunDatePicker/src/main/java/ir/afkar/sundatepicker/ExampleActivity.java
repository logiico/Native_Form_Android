package ir.afkar.sundatepicker;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;

import com.afkar.sundatepicker.R;

import java.util.Calendar;

import ir.afkar.sundatepicker.DatePickerDialog.OnDateSetListener;
import ir.afkar.sundatepicker.tool.JDF;

/*
 * Created by Alireza Afkar - 24/10/14
 */

public class ExampleActivity extends FragmentActivity implements
		OnClickListener, OnDateSetListener {

	Button dateOneBTN;
	Button dateTwoBTN;

	date dateOne;
	date dateTwo;

	@Override
	protected void onCreate(Bundle arg0) {
		setContentView(R.layout.example_layout);

		dateOneBTN = (Button) findViewById(R.id.date1);
		dateTwoBTN = (Button) findViewById(R.id.date2);

		dateOneBTN.setOnClickListener(this);
		dateTwoBTN.setOnClickListener(this);

		JDF jdf = new JDF();

		dateOne = new date(jdf.getIranianYear(), jdf.getIranianMonth(),
				jdf.getIranianDay());
		dateTwo = new date(jdf.getIranianYear(), jdf.getIranianMonth(),
				jdf.getIranianDay());

		super.onCreate(arg0);
	}

	@Override
	public void onClick(View v) {
		int id = 1;

		if (v.getId() == R.id.date2)
			id = 2;

		boolean darkTheme = ((CheckBox) findViewById(R.id.darkCheckBox))
				.isChecked();

		DatePickerDialog dp = DatePickerDialog.newInstance(
				ExampleActivity.this, id, darkTheme);

		if (!((CheckBox) findViewById(R.id.fontCheckBox)).isChecked())
			dp.setTypeFace(Typeface.createFromAsset(getAssets(), "pFont.ttf"));

		if (v.getId() == R.id.date1)
			dp.setInitialDate(dateOne.year, dateOne.month, dateOne.day);
		else
			dp.setInitialDate(dateTwo.calendar);

		if (((CheckBox) findViewById(R.id.redCheckBox)).isChecked())
			dp.setMainColor(-2949011);

		dp.setFutureDisabled(((CheckBox) findViewById(R.id.futureCheckBox))
				.isChecked());

		dp.show(getSupportFragmentManager(), "");
	}

	@Override
	public void onDateSet(int id, Calendar calendar, int year, int month,
			int day) {

		if (id == 1) {
			dateOne.setDate(year, month, day, calendar);
			dateOneBTN.setText(dateOne.getDate());
		} else {
			dateTwo.setDate(year, month, day, calendar);
			dateTwoBTN.setText(dateTwo.getDate());
		}

	}

	class date {
		int year;
		int month;
		int day;
		Calendar calendar;

		date(int year, int month, int day) {
			this.year = year;
			this.month = month;
			this.day = day;
			calendar = Calendar.getInstance();
		}

		void setDate(int year, int month, int day, Calendar calendar) {
			this.year = year;
			this.month = month;
			this.day = day;
			this.calendar = calendar;
		}

		String getDate() {
			return year + "/" + month + "/" + day + "  ("
					+ calendar.get(Calendar.YEAR) + "/"
					+ calendar.get(Calendar.MONTH) + "/"
					+ calendar.get(Calendar.DAY_OF_MONTH) + ")";
		}
	}
}
