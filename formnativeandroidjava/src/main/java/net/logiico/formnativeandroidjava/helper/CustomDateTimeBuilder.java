package net.logiico.formnativeandroidjava.helper;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.StyleRes;

public class CustomDateTimeBuilder implements Parcelable {
    public static final Parcelable.Creator<CustomDateTimeBuilder> CREATOR = new Parcelable.Creator<CustomDateTimeBuilder>() {
        @Override
        public CustomDateTimeBuilder createFromParcel(Parcel source) {
            return new CustomDateTimeBuilder(source);
        }

        @Override
        public CustomDateTimeBuilder[] newArray(int size) {
            return new CustomDateTimeBuilder[size];
        }
    };
    private long minDate;
    private long maxDate;
    private boolean withTime;
    private boolean twentyFourTimeFormat;
    private long selectedDate;
    private int currentHour;
    private int currentMinute;
    private int themeResId;

    CustomDateTimeBuilder() {
    }

    protected CustomDateTimeBuilder(Parcel in) {
        this.minDate = in.readLong();
        this.maxDate = in.readLong();
        this.withTime = in.readByte() != 0;
        this.twentyFourTimeFormat = in.readByte() != 0;
        this.selectedDate = in.readLong();
        this.currentHour = in.readInt();
        this.currentMinute = in.readInt();
        this.themeResId = in.readInt();
    }

    /**
     * Use newInstance instead for naming convention
     * return new Instance
     */
    @Deprecated
    public static CustomDateTimeBuilder get() {
        return newInstance();
    }

    public static CustomDateTimeBuilder newInstance() {
        return new CustomDateTimeBuilder().withCurrentHour(-1).withCurrentMinute(-1);
    }

    public CustomDateTimeBuilder withMinDate(long minDate) {
        this.minDate = minDate;
        return this;
    }

    public CustomDateTimeBuilder withMaxDate(long maxDate) {
        this.maxDate = maxDate;
        return this;
    }

    public CustomDateTimeBuilder withTime(boolean withTime) {
        this.withTime = withTime;
        return this;
    }

    public CustomDateTimeBuilder with24Hours(boolean is24Hours) {
        this.twentyFourTimeFormat = is24Hours;
        return this;
    }

    public CustomDateTimeBuilder withSelectedDate(long selectedDate) {
        this.selectedDate = selectedDate;
        return this;
    }

    public CustomDateTimeBuilder withCurrentHour(int setCurrentHour) {
        this.currentHour = setCurrentHour;
        return this;
    }

    public CustomDateTimeBuilder withCurrentMinute(int setCurrentMinute) {
        this.currentMinute = setCurrentMinute;
        return this;
    }

    public CustomDateTimeBuilder withTheme(@StyleRes int themeResId) {
        this.themeResId = themeResId;
        return this;
    }

    public int getCurrentHour() {
        return currentHour;
    }

    public int getCurrentMinute() {
        return currentMinute;
    }

    long getMinDate() {
        return minDate;
    }

    long getMaxDate() {
        return maxDate;
    }

    boolean isWithTime() {
        return withTime;
    }

    public boolean is24Hours() {
        return twentyFourTimeFormat;
    }

    long getSelectedDate() {
        return selectedDate;
    }

    public int getThemeResId() {
        return themeResId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.minDate);
        dest.writeLong(this.maxDate);
        dest.writeByte(this.withTime ? (byte) 1 : (byte) 0);
        dest.writeByte(this.twentyFourTimeFormat ? (byte) 1 : (byte) 0);
        dest.writeLong(this.selectedDate);
        dest.writeInt(this.currentHour);
        dest.writeInt(this.currentMinute);
        dest.writeInt(this.themeResId);
    }
}
