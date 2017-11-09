package brewcrew.com.taskmanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by navdeep on 09/11/2017.
 */

public class taskRecycler extends RecyclerView.Adapter<taskRecycler.taskHolders> {

    List<MyTasks> tasks= new ArrayList<MyTasks>();
    //constructor will intialize the List of tasks
    public taskRecycler(List mts) {
        mts=tasks;
    }

    @Override
    public taskHolders onCreateViewHolder(ViewGroup parent, int viewType) {
       taskHolders tsk= new taskHolders(View.inflate(parent.getContext(),R.layout.recycler_task,null));
        return tsk;
    }

    @Override
    public void onBindViewHolder(taskHolders holder, int position) {
holder.date.setText(tasks.get(position).getDate().toString());
        holder.task_des.setText(tasks.get(position).getTitle());

    }





    @Override
    public int getItemCount() {
        return tasks.size();
    }

  public class taskHolders extends RecyclerView.ViewHolder{
       TextView date;
       TextView task_title;
       TextView task_des;

       public taskHolders(View itemView) {
           super(itemView);

           //Intialize views frrom recycler_task.html
           //date field
           date=(TextView) itemView.findViewById(R.id.date_view);
           // title field
           task_title=(TextView) itemView.findViewById(R.id.title_view);
           //description field
           task_des=(TextView) itemView.findViewById(R.id.date_view);

       }


   }

}
