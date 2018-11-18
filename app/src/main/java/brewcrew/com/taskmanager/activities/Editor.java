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
    private static final String TAG = "Editor";
    public static TextView date, timeView;
    static boolean first_run = true;
    private final int ON = R.drawable.noti_on;
    private final int OFF = R.drawable.noti_off;
    public EditText desc, titl;
    private ContentValues contentValues;
    public ImageView notifiy;
    private MyTasks tasks;
    private database db;
    private int idNo;

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
            showHelpMessage();
            first_run = false;

        }

        if (getIntent().hasExtra("from_onClick")) {
            idNo = getIntent().getIntExtra("from_onClick", 0);
            tasks = MainActivity.li.get(idNo);
            date.setText(tasks.getDate());
            titl.setText(tasks.getTitle());
            desc.setText(tasks.getDesc());
            timeView.setText(tasks.getTime());
            if (tasks.isNotifyUser()) {
                notifiy.setTag(OFF);
            }

        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        super.onKeyDown(keyCode, event);
        db = new database(this);
        Log.i(TAG, "onKeyDown: "+ String.valueOf(date.getText().toString()==""));
        if (keyCode == KeyEvent.KEYCODE_BACK)
            if (tasks != null) {
                if (!tasks.getDate().equals(date.getText().toString())
                        | !tasks.getDesc().equals(desc.getText().toString())
                        | !tasks.getTitle().equals(titl.getText().toString())
                        | !tasks.getTime().equals(timeView.getText().toString())
                        | tasks.isNotifyUser() != notifyUser(notifiy.getDrawable())) {
                    contentValues = new ContentValues();
                    contentValues.put(databaseEntries.description, desc.getText().toString());
                    contentValues.put(databaseEntries.date, date.getText().toString());
                    contentValues.put(databaseEntries.time, timeView.getText().toString());
                    contentValues.put(databaseEntries.title, titl.getText().toString());
                    contentValues.put(databaseEntries.notifyUser, notifyUser(notifiy.getDrawable()));
                    int rows = db.getWritableDatabase().update(databaseEntries.tableName, contentValues, String.format("ID = %s",tasks.getID() ),null);
                    Log.i(TAG, "rowsUpdated:" + rows);
                }

            } else if (desc.getText().toString().length() != 0) {
//                if (date.getText().toString()!=" " &&  notifyUser(notifiy.getDrawable())) {
                    contentValues = new ContentValues();
                    contentValues.put(databaseEntries.description, desc.getText().toString());
                    contentValues.put(databaseEntries.title, returnText(titl));
                    contentValues.put(databaseEntries.notifyUser, notifyUser(notifiy.getDrawable()));
                    contentValues.put(databaseEntries.status, 0);
                    contentValues.put(databaseEntries.date, returnText(date));
                    contentValues.put(databaseEntries.time,returnText(timeView));
                    Log.i(TAG, "onKeyDown: " + db.getWritableDatabase().insert(databaseEntries.tableName, null, contentValues));
//                }
//                else {
//                    notifiy.setImageDrawable(getDrawable(R.drawable.noti_on));
//                    showDialog();
//                }
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

    public void showHelpMessage() {
        FragmentManager fm = getFragmentManager();
        UserGuide userGuide = new UserGuide();
        userGuide.show(fm, "dialog");
    }

    public void showDialog() {
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
        } else if (tag == OFF) {
            imgView.setTag(ON);
            Log.i(TAG, "onOffTogle: SECND IF");
            imgView.setImageResource(0);
            imgView.setImageResource(R.drawable.noti_on);
        }
        Log.i(TAG, "onOffTogle:Clicked ");
    }

    private String returnText(EditText t) {

        return t.getText().toString() != "" ? t.getText().toString() : "";

    }

    private String returnText(TextView t) {
        return t.getText().toString() !="" ? t.getText().toString() : "";
    }


}