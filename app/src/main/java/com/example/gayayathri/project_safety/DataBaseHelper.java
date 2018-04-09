package com.example.gayayathri.project_safety;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="Routes";
    public static final String TABLE_NAME="routes";
    public static final String COL_1="ID";
    public static final String COL_2="FROM_PLACE";
    public static final String COL_3="TO_PLACE";



    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT ,FROM_PLACE TEXT,TO_PLACE TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);

    }
    public boolean insertData(String from,String to )
    {
        SQLiteDatabase sqLiteDataBase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,from);
        contentValues.put(COL_3,to);

        long result = sqLiteDataBase.insert(TABLE_NAME,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;

    }
    public Cursor getAllData()
    {
        SQLiteDatabase sqLiteDataBase = this.getWritableDatabase();
        Cursor res = sqLiteDataBase.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
    public boolean updateData(String id,String from,String to){
        SQLiteDatabase sqLiteDataBase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,id);
        contentValues.put(COL_2,from);
        contentValues.put(COL_3,to);

        sqLiteDataBase.update(TABLE_NAME, contentValues,"ID = ?",new String[] { id } );
        return true;

    }

    public Integer deleteData(String id)
    {
        SQLiteDatabase sqLiteDataBase = this.getWritableDatabase();
        return sqLiteDataBase.delete(TABLE_NAME,"ID = ?", new String[] {id});


    }

    public String getWidgetData()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select count(*) from  "+TABLE_NAME,null);
        StringBuffer stringBuffer = new StringBuffer();
        while (cursor.moveToNext()) {

            stringBuffer.append(cursor.getString(0));


        }
        return stringBuffer.toString();

    }
    public String getdata(){
        SQLiteDatabase m = this.getWritableDatabase();
        Cursor c = m.rawQuery("select count(ID) from "+TABLE_NAME,null);
        StringBuffer s = new StringBuffer();
        while(c.moveToNext()){
            s.append(c.getString(0));
        }

        return s.toString() ;
    }

}
