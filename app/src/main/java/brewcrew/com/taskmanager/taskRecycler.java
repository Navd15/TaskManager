package brewcrew.com.taskmanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by navdeep on 09/11/2017.
 */

public class taskRecycler extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//      LayoutInflater lf=LayoutInflater.from();
        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


    }


    @Override
    public int getItemCount() {
        return 0;
    }

   class tasHolders extends RecyclerView.ViewHolder{
       TextView date;
       TextView task_title;
       TextView task_des;

       public tasHolders(View itemView) {
           super(itemView);

           //Intialize views frrom recycler_task.html
           date=(TextView) itemView.findViewById(R.id.date_view);
           task_title=(TextView) itemView.findViewById(R.id.title_view);


       }
   }

}
