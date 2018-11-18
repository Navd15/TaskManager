package brewcrew.com.taskmanager.helperClasses;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import brewcrew.com.taskmanager.R;
import brewcrew.com.taskmanager.activities.Editor;
import brewcrew.com.taskmanager.activities.MainActivity;
import brewcrew.com.taskmanager.db.database;
import brewcrew.com.taskmanager.db.databaseEntries;

/**
 * Created by navdeep on 09/11/2017.
 */


public class taskRecycler extends RecyclerView.Adapter<taskRecycler.taskHolders>{
    private static final String TAG = "taskRecycler";
    private static int[] colors = {Color.rgb(255, 104, 151), Color.rgb(255, 104, 151), Color.rgb(42, 69, 109), Color.BLUE, Color.rgb(113, 48, 173), Color.rgb(48, 163, 136)
            , Color.RED, Color.rgb(104, 161, 255), Color.rgb(255, 232, 104)};


    public interface touchListener {
        void onCleck(int clickedIndex);
    }



    final private touchListener listener;

    public class taskHolders extends RecyclerView.ViewHolder implements GestureDetector.OnGestureListener,View.OnClickListener,PopupMenu.OnMenuItemClickListener {


        private static final String TAG = "taskHolders";
        GestureDetectorCompat gestureDetector;
        TextView date, task_title, task_des, label_view;
        PopupMenu pop;

        public taskHolders(View itemView) {
            super(itemView);

gestureDetector=new GestureDetectorCompat(itemView.getContext(),this);
            //Intialize views from recycler_task.xml

            pop=new PopupMenu(itemView.getContext(),itemView);

            MenuInflater menuInflater=pop.getMenuInflater();
            menuInflater.inflate(R.menu.popup_menu,pop.getMenu());
            pop.setGravity(Gravity.TOP);

            itemView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    return gestureDetector.onTouchEvent(motionEvent);
                }
            });
//            frame = itemView.findViewById(R.id.container);
//frame.setOnTouchListener(new View.OnTouchListener() {
//    @Override
//    public boolean onTouch(View view, MotionEvent motionEvent) {
//
//        return gestureDetector.onTouchEvent(motionEvent);
//    }
//});
            label_view = (TextView) itemView.findViewById(R.id.label_view);
            //date field
            date = (TextView) itemView.findViewById(R.id.date_view);
//            title field
            task_title = (TextView) itemView.findViewById(R.id.title_view);
//           description field
            task_des = (TextView) itemView.findViewById(R.id.des_view);

            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            listener.onCleck(position);
        }

        @Override
        public boolean onDown(MotionEvent motionEvent) {
//            itemView.animate().translationX(motionEvent.getX());
            Log.i(TAG, "onDown: "+motionEvent.getActionMasked());
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {
            itemView.animate().translationX(motionEvent.getX()).translationX(motionEvent.getX());
            Log.i(TAG, "onShowPress: ");
        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {

            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            Log.i(TAG, "onScroll: "+motionEvent.getX());
            itemView.animate().translationX(motionEvent.getX());


            return true;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {
            pop.setOnMenuItemClickListener(this);
            pop.show();
        }



        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {


            Log.i(TAG, "onFling: "+motionEvent.getAction());
            return false;
        }

        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            String query=String.format(databaseEntries.deleteById,databaseEntries.tableName,"ID",String.valueOf(this.getAdapterPosition()+1));
            Log.i(TAG, "onMenuItemClick: "+query);


            Log.i(TAG, "onMenuItemClick: "+database.delete(this.itemView.getContext(),"ID",String.valueOf(tasks.get(getAdapterPosition()).getID())));
            return true;
        }
    }


    private ArrayList<MyTasks> tasks ;

    public taskRecycler(ArrayList tasks, touchListener tcl) {
        this.tasks = tasks;
        listener = tcl;
        Log.i(TAG, "taskRecycler: runned");
    }



    @Override
    public taskHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_task, null, false);
        view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new taskHolders(view);
    }

    @Override
    public void onBindViewHolder(taskHolders holder, int position) {
        MyTasks myTasks=tasks.get(position);
        String lab = myTasks.getDesc().substring(0, 1).toUpperCase();
        holder.label_view.setText(lab);
        holder.label_view.setTextColor(colors[new Random().nextInt(colors.length)]);
        if(myTasks.getDate().equals("null")){
            holder.date.setVisibility(View.INVISIBLE);
        }
        holder.date.setText(myTasks.getDate());
        holder.task_title.setText(myTasks.getTitle());
        holder.task_des.setText(myTasks.getDesc());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
