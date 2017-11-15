package brewcrew.com.taskmanager;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    LinearLayoutManager linear;
    static List li;
    taskRecycler taskRec;
    RecyclerView recycler;
    Calendar calendar=Calendar.getInstance();
    TextView notice;

String date_today=calendar.getInstance().get(Calendar.DATE)+" "+picker.give_month_in_string(calendar.get(Calendar.MONTH))+" "+calendar.get(Calendar.YEAR);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        li = new ArrayList<MyTasks>();
        // Text view


        recycler = (RecyclerView) findViewById(R.id.recycler);
        taskRec = new taskRecycler(li);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        //recyler view intialization


        recycler.setAdapter(taskRec);
        recycler.setLayoutManager(layoutManager);


        // item click events

        //FloatingButtonDateFormat.getInstance().toString()

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(getApplicationContext(), Editor.class);

                add.putExtra("default date",date_today);
                startActivity(add);


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //to handle db tasks
    class Async extends AsyncTask<Cursor, Void, String[]> {


        @Override
        protected String[] doInBackground(Cursor... cursors) {
            return new String[0];
        }
    }

}
