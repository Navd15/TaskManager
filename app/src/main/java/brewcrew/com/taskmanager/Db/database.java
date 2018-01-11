package brewcrew.com.taskmanager.Db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by navdeep on 12/11/17.
 */

public class database extends SQLiteOpenHelper {
    private static final String TAG = "database";
    public static final String databaseName = "taskDb";
    private static int version = 1;

    public database(Context context) {
        super(context, databaseName, null, version);


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable  = "CREATE TABLE IF NOT EXISTS" + databaseEntries.tableName + "("
                + databaseEntries.ID + "INTEGER PRIMARY KEY AUTOINCREMENT,"
                + databaseEntries.title + "TEXT,"
                + databaseEntries.description + "TEXT NOT NULL,"
                + databaseEntries.date + "TEXT NOT NULL,"
                + databaseEntries.time + "TEXT NOT NULL );";

        db.execSQL(createTable);
        db.execSQL("INSERT INTO " +databaseEntries.tableName+" VALUES (1,'TITLE','DESC','DATE','TIME');");

        Log.i(TAG, "onCreate: runned");

    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {


    }

}