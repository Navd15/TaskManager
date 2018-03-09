package brewcrew.com.taskmanager.activities;
import android.app.FragmentManager;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import brewcrew.com.taskmanager.R;
import brewcrew.com.taskmanager.UIComponents.UserGuide;
import brewcrew.com.taskmanager.db.database;
import brewcrew.com.taskmanager.db.databaseEntries;
import brewcrew.com.taskmanager.helperClasses.MyTasks;
import brewcrew.com.taskmanager.pickers.datePicker;
import brewcrew.com.taskmanager.pickers.timePicker;
public class Editor extends AppCompatActivity {

    private final int ON = R.drawable.noti_on;
    private final int OFF = R.drawable.noti_off;
    private static final String TAG = "Editor";
    public static TextView date, timeView;
    public  EditText desc, titl;
    public  ImageView notifiy;
    private MyTasks tasks;
    static boolean first_run = true;
    private database db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        getSupportActionBar().setTitle(null);
        date = (TextView) findViewById(R.id.date);
        titl = (EditText) findViewById(R.id.titl);
        desc = (EditText) findViewById(R.id.desc);
        notifiy = (ImageView) findViewById(R.id.notify);
        timeView = (TextView) findViewById(R.id.time_view);
        notifiy.setTag(ON);
        notifiy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onOffTogle((ImageView) view);
            }
        });
//
      if (first_run) {
        showDialog();
       first_run = false;

     }
        if (getIntent().hasExtra("from_onCleck")) {
            tasks = MainActivity.li.get(getIntent().getIntExtra("from_onCleck", 0));
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
                db = new database(this);
                if (!tasks.getDate().equals(date.getText().toString())
                        | !tasks.getDesc().equals(desc.getText().toString())
                        | !tasks.getTitle().equals(titl.getText().toString())
                        | !tasks.getTime().equals(timeView.getText().toString()) | tasks.isNotifyUser() != notifyUser(notifiy.getDrawable())) {
                    ContentValues values = new ContentValues();
                    values.put(databaseEntries.description, desc.getText().toString());
                    values.put(databaseEntries.date, date.getText().toString());
                    values.put(databaseEntries.time, timeView.getText().toString());
                    values.put(databaseEntries.title, titl.getText().toString());
                    values.put(databaseEntries.notifyUser, notifyUser(notifiy.getDrawable()));
                    String[] args = {String.valueOf(getIntent().getIntExtra("from_onCleck", 0))};
                    db.getReadableDatabase().update(databaseEntries.tableName, values, "ID", args);
// tasks.setDesc(desc.getText().toString());//    DialogFragment  dialogFragment=new DialogFragment();
//    dialogFragment.show(getSupportFragmentManager(),"datePicker.class");
//                    tasks.setDate(Date_formatter(date.getText()));
//                    tasks.setTime(timeView.getText().toString());
//                    tasks.setTitle(titl.getText().toString());, notifyUser(notifiy.getDrawable())
                    super.onKeyDown(keyCode, event);
                }

            } else if (desc.getText().toString().length() != 0) {
                if (date.getText().toString().length() != 0 & notifyUser(notifiy.getDrawable())) {
                    String tym;
                    if (timeView.getText().toString().length() != 0) {
                        tym = timeView.getText().toString();
                    } else
                        tym = "00:00";
                    MyTasks m = new MyTasks(desc.getText().toString(), titl.getText().toString(), tym, date.getText().toString(), notifyUser(notifiy.getDrawable()), false);
                    MainActivity.li.add(m);
                } else {
                    notifiy.setImageDrawable(getDrawable(R.drawable.noti_on));
                    AlertDialog.Builder ad = new AlertDialog.Builder(this, R.style.ThemeOverlay_AppCompat_Dialog);
                    ad.setMessage("Please select date or toggle \n the notification icon");
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
    /*
    * Helper methods
    *
    * */
    public void showDatePicker(View v) {
        datePicker pic = new datePicker();
        pic.show(getSupportFragmentManager(), "datepicker");

    }
    public void showDatePicker() {
        datePicker pic = new datePicker();
        pic.show(getSupportFragmentManager(), "datepicker");
    }
    public void showTimePicker(View v) {
        new timePicker().show(getFragmentManager(), "time_picker");

    }
    public void showDialog() {
        FragmentManager fm = getFragmentManager();
        UserGuide userGuide = new UserGuide();
        userGuide.show(fm, "dialog");
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
    private boolean notifyUser(Drawable drawable) {
        return drawable == getDrawable(ON) ? true : false;
    }
    private void onOffTogle(ImageView imgView) {
        int tag = Integer.valueOf(imgView.getTag().toString());
        if (tag == ON) {
            imgView.setImageResource(0);
            Log.i(TAG, "onOffTogle: FIRST IF");
            imgView.setImageResource(R.drawable.noti_off);
            imgView.setTag(OFF);
        } else
            if (tag == OFF) {
            imgView.setTag(ON);
            Log.i(TAG, "onOffTogle: SECND IF");
            imgView.setImageResource(0);
            imgView.setImageResource(R.drawable.noti_on);
        }
        Log.i(TAG, "onOffTogle:Clicked ");
    }

}