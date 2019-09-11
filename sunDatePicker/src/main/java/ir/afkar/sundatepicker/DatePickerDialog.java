package ir.afkar.sundatepicker;

import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.afkar.sundatepicker.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import ir.afkar.sundatepicker.month.MonthMainFragement;
import ir.afkar.sundatepicker.tool.Date;
import ir.afkar.sundatepicker.tool.JDF;
import ir.afkar.sundatepicker.tool.Util;
import ir.afkar.sundatepicker.year.YearMainFragement;

/*
 * Created by Alireza Afkar - 24/10/14
 */

public class DatePickerDialog extends DialogFragment implements OnClickListener {

    public static LinearLayout dayMonth;
    public static int maxMonth;
    static boolean mDarkTheme;
    static int minYear;
    static int maxYear;
    static int id;
    private static TextView dayTV;
    private static TextView monthTV;
    private static TextView yearTV;
    private static TextView dayNameTV;
    private static int mBlue = 0;
    private static int mGry = 0;
    private static int primary = 0;
    private static int primaryDark = 0;
    private static int white = 0;
    private static GradientDrawable circle;
    private static Boolean mVibrate;
    private static Typeface mTypeFace = null;
    private static OnDateSetListener mCallBack;
    public boolean lastDateAllowed = true;
    TextView doneTV;
    FrameLayout frameLayout;
    FragmentManager fragmentManager;

    public DatePickerDialog() {

    }

    public static DatePickerDialog newInstance(
            OnDateSetListener onDateSetListener, int requestID,
            boolean darkTheme) {

        JDF jdf = new JDF();
        return newInstance(onDateSetListener, requestID, jdf.getIranianYear(),
                jdf.getIranianMonth(), jdf.getIranianDay(), darkTheme);
    }

    public static DatePickerDialog newInstance(
            OnDateSetListener onDateSetListener, boolean darkTheme) {
        return newInstance(onDateSetListener, 0, darkTheme);
    }

    public static DatePickerDialog newInstance(
            OnDateSetListener onDateSetListener, int requestID, int year,
            int month, int day, boolean darkTheme) {

        DatePickerDialog datePickerDialog = new DatePickerDialog();

        mDarkTheme = darkTheme;
        if (mDarkTheme)
            datePickerDialog.setStyle(
                    DialogFragment.STYLE_NO_TITLE,
                    DialogFragment.STYLE_NO_TITLE);
        else
            datePickerDialog
                    .setStyle(
                            DialogFragment.STYLE_NO_TITLE,
                            DialogFragment.STYLE_NO_TITLE);

        mCallBack = onDateSetListener;
        Date.setDate(year, month, day, false);
        minYear = new JDF().getIranianYear();
        maxYear = minYear + 2;
        mVibrate = true;
        id = requestID;
        mBlue = 0;
        mGry = 0;
        primary = 0;
        primaryDark = 0;
        white = 0;
        mTypeFace = null;
        maxMonth = 0;

        return datePickerDialog;
    }

    /*
     * Getters
     */
    public static GradientDrawable getCircle() {
        return circle;
    }

    public static int getBlueColor() {
        return mBlue;
    }

    public static int getGrayColor() {
        return mGry;
    }

    public static int getPrimaryDark() {
        return primaryDark;
    }

    public static boolean checkVibrate() {
        return mVibrate;
    }

    public static Typeface getTypeFace() {
        return mTypeFace;
    }

    public void setTypeFace(Typeface typeface) {
        mTypeFace = typeface;
    }

    public static int getRequestID() {
        return id;
    }

    public void setRequestID(int requestID) {
        id = requestID;
    }

    public static void updateDisplay(int year, int month, int day) {
        try {
            DatePickerDialog.dayTV.setText("" + day);
            DatePickerDialog.monthTV.setText(JDF.monthNames[month - 1]);
            DatePickerDialog.yearTV.setText("" + year);
            DatePickerDialog.dayNameTV.setText(new JDF().getIranianDayName(
                    year, month, day));
        } catch (Exception e) {
        }
    }

    @Override
    public void onStart() {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int screenWidth = (int) (metrics.widthPixels * 0.75);
        int screenHeight = (int) (metrics.heightPixels * 0.90);
        getDialog().getWindow().setLayout(screenWidth, screenHeight);
        setRetainInstance(true);
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater layoutInflater,
                             ViewGroup container, Bundle savedInstanceState) {

        final View view = layoutInflater.inflate(R.layout.main_layout, null);

        if (mBlue == 0)
            mBlue = getResources().getColor(R.color.blue);

        if (mGry == 0)
            mGry = getResources().getColor(R.color.gray);

        if (primary == 0)
            primary = getResources().getColor(R.color.primary);

        if (primaryDark == 0)
            primaryDark = getResources().getColor(R.color.primary_dark);

        if (white == 0)
            white = getResources().getColor(R.color.white);

        circle = new GradientDrawable();
        circle.setCornerRadius(getResources().getDimension(
                R.dimen.circle_radius));
        circle.setColor(mBlue);
        circle.setAlpha(80);

        frameLayout = (FrameLayout) view.findViewById(R.id.frame_container);
        fragmentManager = getChildFragmentManager();

        dayTV = (TextView) view.findViewById(R.id.day);
        monthTV = (TextView) view.findViewById(R.id.month);
        yearTV = (TextView) view.findViewById(R.id.year);
        dayNameTV = (TextView) view.findViewById(R.id.dayName);
        doneTV = (TextView) view.findViewById(R.id.done);
        dayMonth = (LinearLayout) view.findViewById(R.id.dayMonthBack);

        if (mTypeFace != null) {
            dayTV.setTypeface(mTypeFace);
            monthTV.setTypeface(mTypeFace);
            yearTV.setTypeface(mTypeFace);
            dayNameTV.setTypeface(mTypeFace);
            doneTV.setTypeface(mTypeFace);
        }

//        if (mDarkTheme) {
//            dayNameTV.setTextColor(getResources().getColor(R.color.white));
//            dayNameTV.setBackgroundColor(getResources().getColor(
//                    R.color.primary_dark));
//            dayNameTV.setBackgroundColor(getResources().getColor(
//                    R.color.transparent_white));
//        } else {
//            dayNameTV.setTextColor(getResources().getColor(R.color.white));
//            dayNameTV.setBackgroundColor(getResources().getColor(R.color.primary));
//            dayNameTV.setTextColor(getResources().getColor(R.color.black));
//            dayNameTV.setBackgroundColor(getResources().getColor(R.color.gray));
//        }

        dayMonth.setOnClickListener(this);
        yearTV.setOnClickListener(this);
        doneTV.setOnClickListener(this);

        updateDisplay(Date.getYear(), Date.getMonth(), Date.getDay());

        ((LinearLayout) view.findViewById(R.id.dayMonthBack)).performClick();

        return view;
    }

    @Override
    public void onClick(View v) {


        if (v.getId() == R.id.year) {
            yearTV.setTextColor(white);
            dayTV.setTextColor(mGry);
            monthTV.setTextColor(mGry);
            switchFragment(new YearMainFragement(minYear, maxYear));
        } else if (v.getId() == R.id.dayMonthBack) {
            yearTV.setTextColor(mGry);
            dayTV.setTextColor(white);
            monthTV.setTextColor(white);
            switchFragment(new MonthMainFragement());
        } else if (v.getId() == R.id.done) {
            if (mCallBack != null) {
                Calendar calendar = Calendar.getInstance();
                JDF j = new JDF();
                j.setIranianDate(Date.getYear(), Date.getMonth(), Date.getDay());
                calendar.set(j.getGregorianYear(), j.getGregorianMonth(),
                        j.getGregorianDay());

                if (!lastDateAllowed) {

                    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                    String current_date = sdf.format(new java.util.Date());
                    String selected_date = sdf.format(DateHelper.getDate(j.getGregorianYear(), j.getGregorianMonth() - 1,
                            j.getGregorianDay()));

                    if (selected_date.compareTo(current_date) < 0) {
                        Toast.makeText(v.getContext(), "شما مجاز به انتخاب تاریخ گذشته نمی باشید.", Toast.LENGTH_LONG).show();
                        return;
                    }
                }

                mCallBack.onDateSet(id, calendar, Date.getYear(),
                        Date.getMonth(), Date.getDay());
                Util.tryVibrate(getActivity());
            }
            dismiss();
        }
    }

    /*
     * End of Setters
     */

    void switchFragment(final Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(android.R.anim.fade_in,
                android.R.anim.fade_out);
        transaction.replace(R.id.frame_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /*
     * Setters
     */
    public void setMainColor(int color) {
        mBlue = color;
    }

    public void setSecondColor(int color) {
        mGry = color;
    }

    public void setYearRange(int _minYear, int _maxYear) {
        minYear = _minYear;
        maxYear = _maxYear;
    }

    public void setInitialDate(int year, int month, int day) {
        Date.setDate(year, month, day, false);
    }

    public void setInitialDate(Calendar calendar) {
        JDF jdf = new JDF();
        jdf.setGregorianDate(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH) + 1,
                calendar.get(Calendar.DAY_OF_MONTH));
        Date.setDate(jdf.getIranianYear(), jdf.getIranianMonth(),
                jdf.getIranianDay(), false);
    }

    public void setVibrate(boolean vibrate) {
        mVibrate = vibrate;
    }

    /*
     * End of Getters
     */

    public void setFutureDisabled(Boolean disabled) {
        if (disabled) {
            JDF jdf = new JDF();
            maxMonth = jdf.getIranianMonth();
            maxYear = jdf.getIranianYear();

            if (minYear > maxYear)
                minYear = maxYear - 1;

            if (Date.getMonth() > jdf.getIranianMonth())
                Date.setMonth(jdf.getIranianMonth());
            if (Date.getDay() > jdf.getIranianDay())
                Date.setDay(jdf.getIranianDay());
            if (Date.getYear() > jdf.getIranianYear())
                Date.setYear(jdf.getIranianYear());
        } else
            maxMonth = 0;
    }

    public static abstract interface OnDateSetListener {
        public abstract void onDateSet(int id, Calendar calendar, int year,
                                       int month, int day);
    }

}
