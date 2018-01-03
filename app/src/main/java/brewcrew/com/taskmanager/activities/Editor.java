package brewcrew.com.taskmanager.activities;

import android.content.ComponentName;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import brewcrew.com.taskmanager.Pickers.datePicker;
import brewcrew.com.taskmanager.Pickers.timePicker;
import brewcrew.com.taskmanager.R;
import brewcrew.com.taskmanager.helperClasses.MyTasks;

public class Editor extends AppCompatActivity {
    Calendar calendar = Calendar.getInstance();
    public static TextView date, timeView;
    public static EditText desc, titl;
    private static final String TAG = "Editor";
    private String sender;
   private MyTasks tasks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        getSupportActionBar().setTitle(null);
        String tareek = getIntent().getStringExtra("default date");
        date = (TextView) findViewById(R.id.date);
        date.setText(tareek);
        titl = (EditText) findViewById(R.id.titl);
        desc = (EditText) findViewById(R.id.desc);
        desc.setText(desc.getText());
        timeView = (TextView) findViewById(R.id.time_view);

if(getIntent().hasExtra("from_onCleck")){
    tasks= (MyTasks) MainActivity.li.get(getIntent().getIntExtra("from_onCleck",0));
    date.setText(tasks.getDate());
    titl.setText(tasks.getTitle());
    desc.setText(tasks.getDesc());
    timeView.setText(tasks.getTime());

}


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(tasks!=null){
            if(tasks.getDate()==date.getText().toString()){}

        }
        else
       if (keyCode == KeyEvent.KEYCODE_BACK && desc.getText().toString().length() != 0) {

       //  new MyTasks(textView.getText().toString(),ET.getText().subSequence(0,11).toString(),ET.getText().toString());
            MyTasks m = new MyTasks(date.getText().toString(),titl.getText().toString(),
                    desc.getText().toString(),
                    timeView.getText().toString()
                    , false);


            MainActivity.li.add(m);


        }


        return super.onKeyDown(keyCode, event);
    }

    public void showDatePicker(View v) {
        datePicker pic = new datePicker();
        pic.show(getSupportFragmentManager(), "datePicker");
//    DialogFragment  dialogFragment=new DialogFragment();
//    dialogFragment.show(getSupportFragmentManager(),"datePicker.class");

    }

    public void showTimePicker(View v) {
        new timePicker().show(getFragmentManager(), "time_picker");


    }
}
