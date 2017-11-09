package brewcrew.com.taskmanager;

import java.util.Date;

/**
 * Created by navdeep on 09/11/2017.
 */

public class MyTasks {
    String title;
    String Desc;
    Date date;
    public MyTasks() {


    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return Desc;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
