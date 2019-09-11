package ir.afkar.sundatepicker;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateHelper {
    public static String GetCurrentDate(String format) {
        return String.valueOf(android.text.format.DateFormat.format(format, new java.util.Date()));
    }

    public static String getDate(Date date, String format) {
        try {
            return String.valueOf(android.text.format.DateFormat.format(format, date));
        } catch (Exception e) {
            return null;
        }
    }

    public static Date getDate(String date, String format) {

        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);

            return dateFormat.parse(date);
        } catch (Exception e) {
            return null;
        }
    }

    public static Date getDate(int year, int month, int day) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Date getDate(int year, int month, int day, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
