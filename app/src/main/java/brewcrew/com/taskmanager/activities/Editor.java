package brewcrew.com.taskmanager.activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import brewcrew.com.taskmanager.Pickers.datePicker;
import brewcrew.com.taskmanager.Pickers.timePicker;
import brewcrew.com.taskmanager.R;
import brewcrew.com.taskmanager.helperClasses.MyTasks;

public class Editor extends AppCompatActivity {
    private static final String TAG = "Editor";
    public static TextView date, timeView;
    public static EditText desc, titl;
    private MyTasks tasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        getSupportActionBar().setTitle(null);
        date = (TextView) findViewById(R.id.date);
        titl = (EditText) findViewById(R.id.titl);
        desc = (EditText) findViewById(R.id.desc);
        timeView = (TextView) findViewById(R.id.time_view);

        if (getIntent().hasExtra("from_onCleck")) {
            tasks = (MyTasks) MainActivity.li.get(getIntent().getIntExtra("from_onCleck", 0));
            date.setText(tasks.getDate());
            titl.setText(tasks.getTitle());
            desc.setText(tasks.getDesc());
            timeView.setText(tasks.getTime());


        }


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            if (tasks != null) {
                if (!tasks.getDate().toString().equals(date.getText().toString()) | !tasks.getDesc().toString().equals(desc.getText().toString()) | !
                        tasks.getTitle().equals(titl.getText().toString().toString())
                        | !tasks.getTime().toString().equals(timeView.getText().toString())) {

                    tasks.setDesc(desc.getText().toString());
                    tasks.setDate(date.getText().toString());
                    tasks.setTime(timeView.getText().toString());
                    tasks.setTitle(titl.getText().toString());
                    int temp_pos = getIntent().getIntExtra("from_onCleck", 0);
//                    MainActivity.li.add(temp_pos, tasks);
//                    MainActivity.li.remove(temp_pos);
                    super.onKeyDown(keyCode, event);
                }

            } else if (desc.getText().toString().length() != 0) {

                if (date.getText().toString().length() != 0) {
                    String tym = null;
                    if (timeView.getText().toString().length() != 0) {
                        tym = timeView.getText().toString();
                    } else
                        tym = "00:00";
                    MyTasks m = new MyTasks(date.getText().toString(), titl.getText().toString(),
                            desc.getText().toString(),
                            tym
                            , false);


                    MainActivity.li.add(m);
                } else {

                    AlertDialog.Builder ad = new AlertDialog.Builder(this, R.style.ThemeOverlay_AppCompat_Dialog);
                    ad.setMessage("Please select date for the task");
                    ad.setPositiveButton("Set date", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            showDatePicker();
                        }
                    });

                    ad.setCancelable(true);

                    AlertDialog adg = ad.create();
                    adg.show();


                }
            }

        return super.onKeyDown(keyCode, event);
    }


    public void showDatePicker(View v) {
        datePicker pic = new datePicker();
        pic.show(getSupportFragmentManager(), "datepicker");

//    DialogFragment  dialogFragment=new DialogFragment();
//    dialogFragment.show(getSupportFragmentManager(),"datePicker.class");

    }

    public void showDatePicker() {
        datePicker pic = new datePicker();
        pic.show(getSupportFragmentManager(), "datepicker");

//    DialogFragment  dialogFragment=new DialogFragment();
//    dialogFragment.show(getSupportFragmentManager(),"datePicker.class");

    }

    public void showTimePicker(View v) {
        new timePicker().show(getFragmentManager(), "time_picker");


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }
}
