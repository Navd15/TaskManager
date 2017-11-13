package brewcrew.com.taskmanager;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.sql.SQLData;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
LinearLayoutManager linear;
static List li;
static taskRecycler taskRec ;
 static  RecyclerView recycler;
    TextView notice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        li=new ArrayList<MyTasks>();

        // Text view

        notice=(TextView) findViewById(R.id.notice) ;


    //recyler view intialization
      taskRec=new taskRecycler(li);
   linear=new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,true);
   recycler=(RecyclerView) findViewById(R.id.recycler);
  recycler.setLayoutManager(linear);
        recycler.setAdapter(taskRec);

        recycler.invalidate();




        // item click events



        //FloatingButton

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add=new Intent(getApplicationContext(),Editor.class);
                add.putExtra("default date", DateFormat.FULL);
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
        recycler.setAdapter(taskRec);
        super.onRestart();
    }

    //to handle db tasks
    class Async extends AsyncTask<Cursor,Void,String[]>{


        @Override
        protected String[] doInBackground(Cursor... cursors) {
            return new String[0];
        }
    }

}
