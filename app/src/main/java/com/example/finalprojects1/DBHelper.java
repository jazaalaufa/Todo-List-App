package com.example.finalprojects1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper  {

    public static final String database_name ="db_todo";
    public static final String table_name = "table_todo";

    public static final String row_id = "_id";
    public static final String row_todo = "Todo";

    private SQLiteDatabase db;


    public DBHelper(Context context) {
        super(context, database_name, null, 2);
        db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + table_name + "(" + row_id + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + row_todo + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
    }

    public Cursor allData(){
        Cursor cur = db.rawQuery("SELECT * FROM " + table_name + " ORDER BY " + row_id + " ASC ",null);
        return cur;
    }

    public void addData(ContentValues values){
        db.insert(table_name,null,values);
    }

    public void updateData(ContentValues values,long id){
        db.update(table_name,values, row_id + "=" + id,null);
    }

    public void deleteData(long id){
        db.delete(table_name, row_id + "=" + id,null);
    }
}
