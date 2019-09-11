package ir.afkar.sundatepicker;

import java.util.Calendar;

/**
 * Created by tenthwindow on 2/24/15.
 */
public class persianDate {
    public int year;
    public int month;
    public int day;
    Calendar calendar;

    public persianDate(int year, int month, int day) {
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
