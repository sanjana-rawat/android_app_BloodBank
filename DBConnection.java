package com.example.bloodbank.Activities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBConnection extends SQLiteOpenHelper {
    public DBConnection(Context context) {
        super(context, "bloodBank.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USERS (uid INTEGER  PRIMARY KEY AUTOINCREMENT,name TEXT,city TEXT,phNo NUMBER(10),bldGrp TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS USERS");
    }

    public boolean  register(String name,String city, String phNo,String bldGrp){
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv =new ContentValues();
        cv.put("name",name);
        cv.put("city",city);
        cv.put("phNo",phNo);
        cv.put("bldGrp",bldGrp);
//        cv.put("pwd",pwd);
        long result= db.insert("USERS",null,cv);
        if(result==-1)
        {
            return false;
        }
        else {
            return true;
        }
    }
    public Cursor getData(){
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor c= db.rawQuery("SELECT * FROM USERS",null);
        return c;
    }

}
