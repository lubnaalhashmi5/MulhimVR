package com.example.mulhimvr;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MulhimVR_Database extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME="MulhimVR.db";//database name
    public static final String TABLE_NAME="ServicesDemos";//table name

    //Delclare all table columns
    public static final String Column_1="ID", Column_2="Service_Demo", Column_3="Day_Number", Column_4="CleintName";

    //set the database
    public MulhimVR_Database(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }

    // Create the table
    public void onCreate(SQLiteDatabase VR)
    {
        VR.execSQL("create table " +TABLE_NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Service_Demo TEXT,Day_Number INTEGER,CleintName TEXT)");
    }
    @Override

    //Check the database if updated or not
    public void onUpgrade(SQLiteDatabase VR, int oldVersion, int newVersion)
    {
        VR.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(VR);
    }

    //The function will add data to the table
    public boolean AddData(String SD, int DN, String Name)
    {
        //Create the database object
        SQLiteDatabase VR=this.getReadableDatabase();
        ContentValues CV=new ContentValues();

        //add data to the table
        CV.put(Column_2,SD);
        CV.put(Column_3,DN);
        CV.put(Column_4,Name);
        // Checked if data entered or not
        long result=VR.insert(TABLE_NAME,null,CV);
        if(result==-1)
            return false;
        else
            return true;
    }

    //The function will update the data according to id
    public boolean updateData(String id, String SD, int DN, String Name)
    {
        SQLiteDatabase VR=this.getReadableDatabase();
        ContentValues CV=new ContentValues();
        CV.put(Column_1,id);
        CV.put(Column_2,SD);
        CV.put(Column_3,DN);
        CV.put(Column_4,Name);
        VR.update(TABLE_NAME,CV,"ID=?",new String[]{id});
        return true;
    }

    // The function will delete the data according to id
    public Integer deleteData(String id)
    {
        SQLiteDatabase VR=this.getWritableDatabase();
        return VR.delete(TABLE_NAME,"ID=?",new String[]{id});
    }

    // The function will show the database
    public Cursor getAllData()
    {
        SQLiteDatabase VR=this.getWritableDatabase();
        Cursor cursor=VR.rawQuery("select * from " +TABLE_NAME,null);
        return cursor;
    }
}
