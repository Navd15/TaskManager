package brewcrew.com.taskmanager.helperClasses;

/**
 * Created by navdeep on 09/11/2017.
 */


public class MyTasks {
    int ID;
    String title;
    String desc;
    String date;
    boolean isCompleted;
    boolean notifyUser;
    String time;
    private static final String TAG = "MyTasks";


    public MyTasks() {
    }

    ;

    public String getID() {
        return String.valueOf(ID);
    }

    public void setID(int ID) {
        this.ID = ID;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public boolean isNotifyUser() {
        return notifyUser;
    }

    public void setNotifyUser(boolean notifyUser) {
        this.notifyUser = notifyUser;
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
        this.desc = desc;
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
