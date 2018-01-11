package brewcrew.com.taskmanager.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import brewcrew.com.taskmanager.Db.database;
import brewcrew.com.taskmanager.Db.databaseEntries;
import brewcrew.com.taskmanager.Pickers.datePicker;
import brewcrew.com.taskmanager.R;
import brewcrew.com.taskmanager.helperClasses.MyTasks;
import brewcrew.com.taskmanager.helperClasses.taskRecycler;


public class MainActivity extends AppCompatActivity implements taskRecycler.touchListener {
    private static final String TAG = "MainActivity";
    static List li = new ArrayList<MyTasks>();
    LinearLayoutManager linear;
    taskRecycler taskRec;
    RecyclerView recycler;
    Calendar calendar = Calendar.getInstance();
    TextView notice;
    private Intent add_edit;
    private int date_int = calendar.get(Calendar.DATE);
    String date_tommo = date_int + "/"
            + datePicker.give_month_in_string(calendar.get(Calendar.MONTH))
            + "/" + calendar.get(Calendar.YEAR);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notice = (TextView) findViewById(R.id.notice_view);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        visibilitySetter();
        taskRec = new taskRecycler(li, this);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        //recyler view intialization


        recycler.setAdapter(taskRec);
        recycler.setLayoutManager(layoutManager);


       /* if (savedInstanceState != null) {


        }*/

        database db = new database(this);
        Cursor cursor=db.getReadableDatabase().
        Log.i(TAG, "onCreate: " );
        String sele[]= {databaseEntries.title};
//        Cursor cursor=db.getReadableDatabase().query(databaseEntries.tableName,sele,null,null,null,null,null);



        /*
        * fab event handling
        * */
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_edit = new Intent(getApplicationContext(), Editor.class);

                add_edit.putExtra("default date", date_tommo);


                startActivity(add_edit);


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

    @Override
    protected void onRestart() {
        super.onRestart();
        visibilitySetter();
        recycler.setAdapter(taskRec);

    }

    @Override
    protected void onResume() {
        super.onResume();
        visibilitySetter();
        recycler.setAdapter(taskRec);
    }


   /*
  Helper methods
    */


    // item click events
    @Override
    public void onCleck(int clickedIndex) {
        add_edit = new Intent(getApplicationContext(), Editor.class);
        add_edit.putExtra("from_onCleck", clickedIndex);
        startActivity(add_edit);
        Log.i(TAG, "onCleck clicked:" + clickedIndex);
    }

    /*
    * Show message if no event in List is present.
    *
    * */
    void visibilitySetter() {

        if (li.size() < 1) {
            notice.setVisibility(View.VISIBLE);
            recycler.setVisibility(View.INVISIBLE);
        } else {
            recycler.setVisibility(View.VISIBLE);
            notice.setVisibility(View.INVISIBLE);

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {


        super.onSaveInstanceState(outState);
    }

    /*
        Fetch data from database asynchronously*/
    class Async extends AsyncTask<Cursor, Void, String[]> {


        @Override
        protected String[] doInBackground(Cursor... cursors) {
            return new String[0];
        }
    }


}
