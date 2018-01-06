package brewcrew.com.taskmanager.helperClasses;

/**
 * Created by navdeep on 09/11/2017.
 */


public class MyTasks {
    String title;
    String desc;
    String date;
    boolean isCompleted;
    String time;
    private static final String TAG = "MyTasks";



    public MyTasks(){};

    public MyTasks(String desc) {
        this.desc = desc;
        
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public MyTasks(String d1, String t1, String d, String tym, boolean completed) {

        date=d1;
        title=t1;

        desc=d;

        isCompleted=completed;
        time=tym;
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
