package brewcrew.com.taskmanager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Editor extends AppCompatActivity {
    public static TextView date;
    public static EditText desc;
    String tag = "Editor";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        String tareek=getIntent().getStringExtra("default date");
        date = (TextView) findViewById(R.id.date);
        date.setText(tareek);
        desc = (EditText) findViewById(R.id.desc);
        desc.setText(desc.getText());


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && desc.getText().toString().length() != 0) {
//          new MyTasks(textView.getText().toString(),ET.getText().subSequence(0,11).toString(),ET.getText().toString());
            MyTasks m = new MyTasks(date.getText().toString(), desc.getText().subSequence(0, desc.getText().length() / 2).toString(), desc.getText().toString(), false);


            MainActivity.li.add(m);

        }


        return super.onKeyDown(keyCode, event);
    }

    public void datePicker(View v) {
        picker pic = new picker();
        pic.show(getSupportFragmentManager(), "picker");
//    DialogFragment  dialogFragment=new DialogFragment();
//    dialogFragment.show(getSupportFragmentManager(),"picker.class");

    }


}
