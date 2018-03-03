package brewcrew.com.taskmanager.helperClasses;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import brewcrew.com.taskmanager.R;

/**
 * Created by navdeep on 09/11/2017.
 */


public class taskRecycler extends RecyclerView.Adapter<taskRecycler.taskHolders> {
    private static final String TAG = "taskRecycler";


    public interface touchListener {
        void onCleck(int clickedIndex);

    }

    final private touchListener listener;

    public class taskHolders extends RecyclerView.ViewHolder implements View.OnClickListener {

        private static final String TAG = "taskHolders";

        TextView date;
        TextView task_title;
        TextView task_des;


        public taskHolders(View itemView) {
            super(itemView);
            Log.i(TAG, "taskHolders: runned");

            //Intialize views from recycler_task.xml

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
    }


  private  ArrayList<MyTasks> tasks = new ArrayList<MyTasks>();
    //constructor will intialize the List of tasks

    public taskRecycler(ArrayList tasks, touchListener tcl) {
        this.tasks = tasks;
        listener = tcl;
        Log.i(TAG, "taskRecycler: runned");
    }

    @Override
    public taskHolders onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_task, null, false);

        return new taskHolders(view);
    }

    @Override
    public void onBindViewHolder(taskHolders holder, int position) {

        Log.i(TAG, "onBindViewHolder:" + tasks.get(position).getDate());
        holder.date.setText(tasks.get(position).getDate());
        holder.task_title.setText(tasks.get(position).getTitle());
        holder.task_des.setText(tasks.get(position).getDesc());
        Log.i(TAG, "onBindViewHolder: runned");

    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount:" + tasks.size());
        return tasks.size();
    }


}
