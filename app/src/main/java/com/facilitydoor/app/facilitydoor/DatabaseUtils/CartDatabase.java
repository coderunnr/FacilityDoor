package com.facilitydoor.app.facilitydoor.DatabaseUtils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.facilitydoor.app.facilitydoor.Models.DatabaseModel;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by my hp on 1/16/2016.
 */
public class CartDatabase {

    public static final String KEY_ROWID="_id";
    public static final String KEY_SUBID="sub_id";
    public static final String KEY_NAME="sub_name";
    public static final String KEY_UNITS="sub_units";
    public static final String KEY_PRICE="price";
    public static final String KEY_DATE="date";
    public static final String KEY_TIME="time";

    private static final String DATABASE_NAME="FacilityDoordb";
    private static final String DATABASE_TABLE="carttable";
    private static final int DATABASE_VERSION=1;

    private Dbhelper ourhelper;
    private final Context ourcontext;
    private SQLiteDatabase ourdatabase;

    public void createentry(String sub_id, String sub_name,String sub_units,String price,String date,String time) {
        ContentValues cv=new ContentValues();
        cv.put(KEY_SUBID,sub_id);
        cv.put(KEY_NAME,sub_name);
        cv.put(KEY_PRICE,price);
        cv.put(KEY_UNITS,sub_units);
        cv.put(KEY_DATE,date);
        cv.put(KEY_TIME,time);
        ourdatabase.insert(DATABASE_TABLE,null,cv);

    }

    public ArrayList<DatabaseModel> getData() {
        String[] columns=new String[] {KEY_ROWID,KEY_NAME,KEY_SUBID,KEY_PRICE,KEY_UNITS,KEY_DATE,KEY_TIME};

        Cursor c=ourdatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
       int sub_id=c.getColumnIndex(KEY_SUBID);
        int sub_name=c.getColumnIndex(KEY_NAME);
        int price=c.getColumnIndex(KEY_PRICE);
        int row_id=c.getColumnIndex(KEY_ROWID);
        int units=c.getColumnIndex(KEY_UNITS);
        int date=c.getColumnIndex(KEY_DATE);
        int time=c.getColumnIndex(KEY_TIME);

        ArrayList<DatabaseModel> databaseModels=new ArrayList<>();
        for(c.moveToFirst();!c.isAfterLast();c.moveToNext()){

            DatabaseModel databaseModel=new DatabaseModel();
            databaseModel.setSub_id(c.getString(sub_id));
            databaseModel.setSub_name(c.getString(sub_name));
            databaseModel.setPrice(c.getString(price));
            databaseModel.setUnits(c.getString(units));
            databaseModel.setRow_id(c.getString(row_id));
            databaseModel.setDate(c.getString(date));
            databaseModel.setTime(c.getString(time));

            databaseModels.add(databaseModel);

        }


        return databaseModels;
    }
    /*

    public String getname(long l) {

        String[] columns=new String[] {KEY_ROWID,KEY_NAME,KEY_HOTNESS};

        Cursor c=ourdatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+l,null,null,null,null);
        if(c!=null){
            c.moveToFirst();
            String name=c.getString(1);
            return name;
        }
        return null;
    }

    public String gethotness(long l) {
        String[] columns=new String[] {KEY_ROWID,KEY_NAME,KEY_HOTNESS};

        Cursor c=ourdatabase.query(DATABASE_TABLE,columns,KEY_ROWID+"="+l,null,null,null,null);
        if(c!=null){
            c.moveToFirst();
            String hotness=c.getString(2);
            return hotness;
        }
        return null;
    }

    public void updateEntry(long l, String name, String hotness) {

        ContentValues cv=new ContentValues();
        cv.put(KEY_NAME,name);
        cv.put(KEY_HOTNESS,hotness);
        ourdatabase.update(DATABASE_TABLE,cv,KEY_ROWID+"="+l,null);
    }*/

    public void deleteEntry(int l) {
        ourdatabase.delete(DATABASE_TABLE,KEY_ROWID+"="+l,null);
    }


    private static class Dbhelper extends SQLiteOpenHelper {
        public Dbhelper(Context context) {
            super(context,DATABASE_NAME, null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+DATABASE_TABLE+" ("+KEY_ROWID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
        KEY_NAME+" TEXT NOT NULL, "+KEY_SUBID+" TEXT NOT NULL, "+KEY_UNITS+" TEXT, "+KEY_PRICE+" TEXT NOT NULL, "+KEY_DATE+" TEXT NOT NULL, "+KEY_TIME+" TEXT NOT NULL);");




        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            db.execSQL("DROP TABLE IF EXISTS "+DATABASE_NAME);
            onCreate(db);
        }
    }

    public CartDatabase(Context c){
    ourcontext=c;
    }

    public CartDatabase open()throws SQLException{
        ourhelper=new Dbhelper(ourcontext);
        ourdatabase=ourhelper.getWritableDatabase();
        return this;
    }

    public void close(){
        ourhelper.close();
    }
}
