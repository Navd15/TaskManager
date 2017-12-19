package brewcrew.com.taskmanager.Pickers;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.TimePicker;

import java.util.Calendar;

import brewcrew.com.taskmanager.activities.Editor;

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
        timePicker.setIs24HourView(false);
        Log.i("TAG", "onTimeSet:"+i+","+i1);
        Editor.timeView.setText(timePicker.getHour()+":"+timePicker.getMinute());
    }
}
