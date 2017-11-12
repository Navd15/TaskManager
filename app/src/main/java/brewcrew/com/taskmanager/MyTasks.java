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

        d1=date;
        t1=title;
        d=desc;
        completed=isCompleted;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        desc = desc;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}
