package brewcrew.com.taskmanager.Pickers;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

import brewcrew.com.taskmanager.activities.Editor;

/**
 * Created by navdeep on 09/11/2017.
 */


public class datePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {
   public static String give_month_in_string(int month_of_year) {
        switch (month_of_year) {
            case 0:
                return give_month.January.toString();
            case 1:
                return give_month.Febuary.toString();
            case 2:
                return give_month.March.toString();
            case 3:
                return give_month.April.toString();
            case 4:
                return give_month.May.toString();
            case 5:
                return give_month.June.toString();
            case 6:
                return give_month.July.toString();
            case 7:
                return give_month.August.toString();
            case 8:
                return give_month.September.toString();
            case 9:
                return give_month.October.toString();
            case 10:
                return give_month.November.toString();
            case 11:
                return give_month.December.toString();
            default:
                return null;
        }


    }

    public enum give_month {
        January,
        Febuary,
        March,
        April,
        May,
        June,
        July,
        August,
        September,
        October,
        November,
        December,

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);


        return new DatePickerDialog(getActivity(), this, year, month, day);

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {


        Editor.date.setText(datePicker.getDayOfMonth() + "/" + give_month_in_string(datePicker.getMonth())+ "/" + datePicker.getYear());
    }



}

