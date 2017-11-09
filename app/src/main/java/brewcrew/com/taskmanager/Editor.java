package brewcrew.com.taskmanager;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Editor extends AppCompatActivity {
public static TextView textView;
  public static  EditText ET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        textView=(TextView) findViewById(R.id.date);
        ET=(EditText) findViewById(R.id.ET);



    }
public void datepicker(View v ){
    DialogFragment  dialogFragment=new DialogFragment();
    dialogFragment.show(getSupportFragmentManager(),"date_picker");

}

}
