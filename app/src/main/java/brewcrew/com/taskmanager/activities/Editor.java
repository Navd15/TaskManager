package brewcrew.com.taskmanager.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    public static TextView date,timeView;
    public static EditText desc;
    String tag = "Editor";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        getSupportActionBar().setTitle(null);
        String tareek = getIntent().getStringExtra("default date");
        date = (TextView) findViewById(R.id.date);
        date.setText(tareek);
        desc = (EditText) findViewById(R.id.desc);
        desc.setText(desc.getText());
        timeView=(TextView) findViewById(R.id.time_view);


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && desc.getText().toString().length() != 0) {
//          new MyTasks(textView.getText().toString(),ET.getText().subSequence(0,11).toString(),ET.getText().toString());
            MyTasks m = new MyTasks(date.getText().toString(), desc.getText().subSequence(0,

                    desc.getText().length() / 2).toString(),
                    desc.getText().toString(),
"12:00 Am"
                    ,false);


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

public void showTimePicker(View v){
    new timePicker().show(getFragmentManager(),"time_picker");


}
}
