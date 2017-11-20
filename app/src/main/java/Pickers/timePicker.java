package Pickers;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TimePicker;

import java.util.Calendar;

import brewcrew.com.taskmanager.Editor;

/**
 * Created by navdeep on 20/11/17.
 */

public class timePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int next_day_hour= Calendar.getInstance().get(Calendar.HOUR);
        int next_day_min=Calendar.getInstance().get(Calendar.MINUTE);

        return new TimePickerDialog(getActivity(),this,next_day_hour,next_day_min,false);

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        Editor.timeView.setText(timePicker.getHour()+":"+timePicker.getMinute()+timePicker.getBaseline());
    }
}
