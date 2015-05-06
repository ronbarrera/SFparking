package com.example.ronald.sfparking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Helper for creating and upgrading local SQLite database "Parking_Information.db". Uses string
 * constructors from SqliteSchema to create and upgrade (clear and recreate) the database.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Parking_Information.db";

    private static MySQLiteHelper instanceDB;
    //constructor

    /**
     * getter for the instance of this class, to provide a singleton class to maintain a single
     * database for the app.
     * @param context current context of program
     * @return the MySQLiteHelper object that manages the database.
     */
    public static synchronized MySQLiteHelper getInstance(Context context)
    {
        if(instanceDB==null)
            instanceDB=new MySQLiteHelper(context);

        return instanceDB;
    }

    private MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(SqliteSchema.CREATE_TABLE);
    }

    /**
     * if the app is upgraded, the location history table in the database will be deleted and re-created.
     * @param database
     * @param oldVer
     * @param newVer
     */
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVer, int newVer){
        Log.d(MySQLiteHelper.class.getName(), "Upgrading database from version " + oldVer + " to " +
                newVer + ", which will destroy all old data");
        database.execSQL(SqliteSchema.DELETE_TABLE);
        onCreate(database);
    }

}
