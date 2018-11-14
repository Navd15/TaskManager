package brewcrew.com.taskmanager.helperClasses;

import android.graphics.Color;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

import brewcrew.com.taskmanager.R;
import brewcrew.com.taskmanager.activities.Editor;
import brewcrew.com.taskmanager.activities.MainActivity;

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

    public class taskHolders extends RecyclerView.ViewHolder implements GestureDetector.OnGestureListener ,View.OnClickListener {


        private static final String TAG = "taskHolders";
        GestureDetectorCompat gestureDetector;
        FrameLayout frame;
        TextView date, task_title, task_des, label_view;


        public taskHolders(View itemView) {
            super(itemView);
gestureDetector=new GestureDetectorCompat(itemView.getContext(),this);
            //Intialize views from recycler_task.xml

            frame = itemView.findViewById(R.id.container);
frame.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        return gestureDetector.onTouchEvent(motionEvent);
    }
});
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
            Log.i(TAG, "onDown: "+motionEvent.getActionMasked());
            return false;
        }

        @Override
        public void onShowPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onSingleTapUp(MotionEvent motionEvent) {
            return false;
        }

        @Override
        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {

            if (v<v1) {
                this.frame.animate().translationX(200);
            }
            else
                this.frame.animate().translationX(0);
            return false;
        }

        @Override
        public void onLongPress(MotionEvent motionEvent) {

        }

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
//            System.out.println(motionEvent.getAction());

            Log.i(TAG, "onFling: "+motionEvent.getAction());
            return false;
        }
    }


    private ArrayList<MyTasks> tasks = new ArrayList<MyTasks>();

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
        String lab = tasks.get(position).getDesc().substring(0, 1).toUpperCase();
        holder.label_view.setText(lab);
        holder.label_view.setTextColor(colors[new Random().nextInt(colors.length)]);
        holder.date.setText(tasks.get(position).getDate());
        holder.task_title.setText(tasks.get(position).getTitle());
        holder.task_des.setText(tasks.get(position).getDesc());


    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount:" + tasks.size());
        return tasks.size();
    }


}
