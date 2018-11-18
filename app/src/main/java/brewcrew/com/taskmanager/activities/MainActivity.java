package brewcrew.com.taskmanager.activities;

import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import brewcrew.com.taskmanager.R;
import brewcrew.com.taskmanager.db.database;
import brewcrew.com.taskmanager.db.databaseEntries;
import brewcrew.com.taskmanager.helperClasses.MyTasks;
import brewcrew.com.taskmanager.helperClasses.taskRecycler;
import brewcrew.com.taskmanager.pickers.datePicker;

public class MainActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private static final String TAG = "MainActivity";
    static ArrayList<MyTasks> li;
    taskRecycler taskRec;
    LinearLayoutManager linearLayoutManager;
    StaggeredGridLayoutManager staggeredGrid;
    RecyclerView recycler;
    Cursor cursor;
    Calendar calendar = Calendar.getInstance();
    TextView notice;
    int Linear_Layout = 1;
    int Staggerd_Layout = 2;
    private Intent add_edit;
    taskRecycler.touchListener tt = new taskRecycler.touchListener() {
        @Override
        public void onCleck(int clickedIndex) {
            add_edit = new Intent(getApplicationContext(), Editor.class);
            add_edit.putExtra("from_onClick", clickedIndex);
            startActivity(add_edit);
            Log.i(TAG, "onCleck clicked:" + clickedIndex);
        }
    };
    private int date_int = calendar.get(Calendar.DATE);
    String date_tommo = date_int + "/"
            + datePicker.give_month_in_string(calendar.get(Calendar.MONTH))
            + "/" + calendar.get(Calendar.YEAR);

    private static Boolean trueFalser(int status) {
        return (status == 0) ? false : true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate: ");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        li = new ArrayList<>();
        notice = (TextView) findViewById(R.id.notice_view);
        DividerItemDecoration did = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.addItemDecoration(did);
        setLayoutManagers();
        selectLayoutManager(Linear_Layout);
        //get cursor from databse class
        cursor = database.getCursor(databaseEntries.selectAllQuery, this);
        if (li.size() == 0) {
            loadData(cursor);

        }
        taskRec = new taskRecycler(li, tt);
        recycler.setAdapter(taskRec);
        visibilitySetter();

        /*
         * fab event handling
         *
         * */

        Button fab = (Button) findViewById(R.id.fab);
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
        //loadData(cursor);
        loadData(database.getCursor(databaseEntries.selectAllQuery, this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData(database.getCursor(databaseEntries.selectAllQuery, this));
    }

    /*
   Helper methods
     */
    // item click events
    /*
     * Show notice if no event is present in list
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

    void toggle(MenuItem item) {
        if (recycler.getLayoutManager() == linearLayoutManager) {
            recycler.scrollToPosition(0);
            selectLayoutManager(Staggerd_Layout);
            item.setIcon(getDrawable(R.drawable.ic_align));
            recycler.getAdapter().notifyDataSetChanged();

        } else if (recycler.getLayoutManager() == staggeredGrid) {
            item.setIcon(getDrawable(R.drawable.ic_menu));
            selectLayoutManager(Linear_Layout);
            recycler.getAdapter().notifyDataSetChanged();
        }
    }

    //load data method envokes async execute
    private void loadData(Cursor cursor) {
        new Async().execute(cursor);
    }

    private void selectLayoutManager(int manager) {
        switch (manager) {
            case 1:
                recycler.setLayoutManager(linearLayoutManager);
                break;
            case 2:
                recycler.setLayoutManager(staggeredGrid);
                break;
            default:
                recycler.setLayoutManager(linearLayoutManager);
        }

    }

    private void setLayoutManagers() {
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        staggeredGrid = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.delete:

                break;


        }

        return false;
    }

    /*
     *Asynchronous fetch from db
     * */
    private class Async extends AsyncTask<Cursor, Void, ArrayList<MyTasks>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            li.clear();

        }

        @Override
        protected ArrayList<MyTasks> doInBackground(Cursor... cursores) {
            Cursor curs = cursores[0];
            //ArrayList<MyTasks> updatedList = new ArrayList<>();
            int descColumn = curs.getColumnIndexOrThrow(databaseEntries.description);
            int titleColumn = curs.getColumnIndexOrThrow(databaseEntries.title);
            int timeColumn = curs.getColumnIndexOrThrow(databaseEntries.time);
            int dateColumn = curs.getColumnIndexOrThrow(databaseEntries.date);
            int statusColumn = curs.getColumnIndexOrThrow(databaseEntries.status);
            int notifyColumn = curs.getColumnIndex(databaseEntries.notifyUser);
            int idColumn=curs.getColumnIndexOrThrow("ID");
            MyTasks myTasks;


            while (curs.moveToNext()) {
                myTasks=new MyTasks();
                myTasks.setDate(curs.getString(dateColumn));

                myTasks.setID(curs.getInt(idColumn));
                myTasks.setDesc(curs.getString(descColumn));
                myTasks.setNotifyUser(trueFalser(notifyColumn));
                myTasks.setTitle(curs.getString(titleColumn));
                myTasks.setTime(curs.getString(timeColumn));
                myTasks.setCompleted(trueFalser(statusColumn));
                li.add(myTasks);
            }

            curs.close();
            return li;
        }



        @Override
        protected void onPostExecute(ArrayList<MyTasks> list) {
            super.onPostExecute(list);
            recycler.getAdapter().notifyDataSetChanged();
            visibilitySetter();
            Log.i(TAG, "onPostExecute: " + li.size());

        }
    }

}
