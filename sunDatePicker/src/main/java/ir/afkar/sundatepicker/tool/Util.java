package ir.afkar.sundatepicker.tool;

import android.content.Context;
import android.os.Vibrator;

import ir.afkar.sundatepicker.DatePickerDialog;

/*
 * Created by Alireza Afkar - 24/10/14
 */

public class Util {

	private static Vibrator vibrator = null;

	public static void tryVibrate(Context context) {
		if (vibrator == null) {
			vibrator = (Vibrator) context
					.getSystemService(Context.VIBRATOR_SERVICE);
		}

		if (DatePickerDialog.checkVibrate() && vibrator != null)
			vibrator.vibrate(20);
	}
}
