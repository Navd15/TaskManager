package brewcrew.com.taskmanager;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Pickers.datePicker;

public class MainActivity extends AppCompatActivity {
    LinearLayoutManager linear;
    static List li = new ArrayList<MyTasks>();
    taskRecycler taskRec;
    RecyclerView recycler;
    Calendar calendar = Calendar.getInstance();
    TextView notice;

    String date_today = calendar.getInstance().get(Calendar.DATE) + " " + datePicker.give_month_in_string(calendar.get(Calendar.MONTH)) + " " + calendar.get(Calendar.YEAR);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notice = (TextView) findViewById(R.id.notice_view);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        recycler.setVisibility(View.INVISIBLE);
        visibilitySetter();
        taskRec = new taskRecycler(li);

        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        layoutManager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
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

                add.putExtra("default date", date_today);
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

    //to handle db tasks
    class Async extends AsyncTask<Cursor, Void, String[]> {


        @Override
        protected String[] doInBackground(Cursor... cursors) {
            return new String[0];
        }
    }

    //helper methods
    void visibilitySetter() {

        if (li.size()==0) {
            notice.setVisibility(View.VISIBLE);
            recycler.setVisibility(View.INVISIBLE);
        } else
            recycler.setVisibility(View.VISIBLE);
        notice.setVisibility(View.INVISIBLE);


    }
}
