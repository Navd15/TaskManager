package brewcrew.com.taskmanager;

import java.util.Date;
import java.util.StringTokenizer;

/**
 * Created by navdeep on 09/11/2017.
 */

public class MyTasks {
    String title;
    String desc;
    String date;
    boolean isCompleted;




    public MyTasks(){};



    public MyTasks(String d1,String t1,String d,boolean completed) {

        date=d1;
        title=t1;

        desc=d;
        isCompleted=completed;
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    private void setDesc(String desc) {
        desc = desc;
    }

    public String getDate() {
        return date;
    }

    private void setDate(String date) {
        this.date = date;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    private void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
