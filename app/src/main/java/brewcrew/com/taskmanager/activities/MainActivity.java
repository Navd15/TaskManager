package brewcrew.com.taskmanager.activities;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

import brewcrew.com.taskmanager.db.database;
import brewcrew.com.taskmanager.db.databaseEntries;
import brewcrew.com.taskmanager.pickers.datePicker;
import brewcrew.com.taskmanager.R;
import brewcrew.com.taskmanager.helperClasses.MyTasks;
import brewcrew.com.taskmanager.helperClasses.taskRecycler;
public class MainActivity extends AppCompatActivity implements taskRecycler.touchListener {

    private static final String TAG = "MainActivity";
    static ArrayList<MyTasks> li;
    taskRecycler taskRec;
    LinearLayoutManager linearLayoutManager;
    StaggeredGridLayoutManager staggeredGrid;
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
        Cursor cursor;
        cursor = database.getCursor(databaseEntries.selectAllQuery, this);
        Async async = new Async();
        async.execute(cursor);
        try {
            taskRec = new taskRecycler(async.get(), this);
        } catch (InterruptedException IE) {
            Log.e(TAG, "onCreate:" + IE.getMessage());
        } catch (ExecutionException EE) {
            Log.e(TAG, "onCreate:" + EE.getMessage());
        }
        visibilitySetter();
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        staggeredGrid = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recycler.setLayoutManager(linearLayoutManager);
        recycler.setAdapter(taskRec);




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
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        // getMenuInflater().inflate();
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return super.onContextItemSelected(item);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.toggle_layout:
                toggle(item);
                break;

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
        add_edit.putExtra("from_onClick", clickedIndex);
        startActivity(add_edit);
        Log.i(TAG, "onCleck clicked:" + clickedIndex);
    }
    /*
    * Show banner if no event is present in list
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
    void toggle(MenuItem item) {
        if (recycler.getLayoutManager() == linearLayoutManager) {
            recycler.setLayoutManager(staggeredGrid);
            item.setIcon(getDrawable(R.drawable.ic_menu));

        } else if (recycler.getLayoutManager() == staggeredGrid) {
            item.setIcon(getDrawable(R.drawable.ic_align));
            recycler.setLayoutManager(linearLayoutManager);
        }
    }
    private static Boolean trueFalser(int status) {
        return (status == 0) ? false : true;
    }
    /*
    *Asynchronous Handling of data
    * */
    private class Async extends AsyncTask<Cursor, Void, ArrayList> {

        @Override
        protected void onPreExecute() {
            li = new ArrayList<>();
            super.onPreExecute();

        }
        @Override
        protected ArrayList doInBackground(Cursor... cursores) {
            Cursor curs = cursores[0];
            int descColumn = curs.getColumnIndexOrThrow(databaseEntries.description);
            int titleColumn = curs.getColumnIndexOrThrow(databaseEntries.title);
            int timeColumn = curs.getColumnIndexOrThrow(databaseEntries.time);
            int dateColumn = curs.getColumnIndexOrThrow(databaseEntries.date);
            int statusColumn = curs.getColumnIndexOrThrow(databaseEntries.status);
            int notifyColumn = curs.getColumnIndex(databaseEntries.notifyUser);
            while (curs.moveToNext()) {
                li.add(new MyTasks(curs.getString(descColumn),
                        curs.getString(titleColumn),
                        curs.getString(timeColumn),
                        curs.getString(dateColumn),
                        trueFalser(curs.getInt(statusColumn)), trueFalser(curs.getInt(notifyColumn))));
            }
            curs.close();
            return li;
        }
        @Override
        protected void onPostExecute(ArrayList list) {
            super.onPostExecute(list);

        }
    }

}
