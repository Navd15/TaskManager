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

    private final String insert="("+databaseEntries.title+","+databaseEntries.description+","

            +databaseEntries.date+","+databaseEntries.time+","+databaseEntries.status+")";

    public database(Context context) {

        super(context, databaseName, null, version);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE IF NOT EXISTS " + databaseEntries.tableName + "("
                + "ID INTEGER PRIMARY KEY AUTOINCREMENT, "
                + databaseEntries.title + " text, "
                + databaseEntries.description + "  text NOT NULL, "
                + databaseEntries.date + " text NOT NULL, "
                + databaseEntries.time + " text NOT NULL, "
                + databaseEntries.status + " INTEGER  NOT NULL )"+";";

        db.execSQL(createTable);
        db.execSQL("INSERT INTO " + databaseEntries.tableName +" "+insert+ " VALUES('TITLE','DESC','DATE','TIME',0);");

        Log.i(TAG, "onCreate: runned");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


/* public static Cursor getCursor(String query){

        return this.getReadableDatabase().rawQuery(query,null);

    };
* Helper methods
* */

public static Cursor getCursor(String query,Context context){

    return new database(context).getReadableDatabase().rawQuery(query,null);

};

}