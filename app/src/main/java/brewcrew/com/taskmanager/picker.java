package brewcrew.com.taskmanager;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

/**
 * Created by navdeep on 09/11/2017.
 */


public  class picker extends DialogFragment implements DatePickerDialog.OnDateSetListener{
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c= Calendar.getInstance();
        int year =c.get(Calendar.YEAR);
        int day=c.get(Calendar.DAY_OF_MONTH);
        int month=c.get(Calendar.MONTH);
        int date= c.get(Calendar.DATE);


        return new DatePickerDialog(getActivity(),this,year,month,day);

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
Editor.textView.setText(datePicker.getDayOfMonth()+"-"+datePicker.getMonth()+"-"+datePicker.getYear());
    }
}
