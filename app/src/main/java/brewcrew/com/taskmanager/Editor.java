      package brewcrew.com.taskmanager;

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
public static TextView textView;
  public static  EditText ET;
    String tag="Editor";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        textView=(TextView) findViewById(R.id.date);
        ET=(EditText) findViewById(R.id.ET);
        ET.setText(ET.getText());


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==KeyEvent.KEYCODE_BACK&& ET.getText().toString().length()!=0){
          //  new MyTasks(textView.getText().toString(),ET.getText().subSequence(0,11).toString(),ET.getText().toString());
MyTasks m=new MyTasks();
            m.setDesc(ET.getText().toString());

MainActivity.li.add(m);

MainActivity.recycler.setAdapter(new taskRecycler(MainActivity.li));
            MainActivity.taskRec.notifyDataSetChanged();


        }


        return super.onKeyUp(keyCode, event);
    }

    public void datePicker(View v ){
    DialogFragment  dialogFragment=new DialogFragment();
    dialogFragment.show(getSupportFragmentManager(),"picker");

}



}
