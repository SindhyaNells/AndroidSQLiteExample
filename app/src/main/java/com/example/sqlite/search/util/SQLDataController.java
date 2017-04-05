package com.example.sqlite.search.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sindhya on 3/31/17.
 */
public class SQLDataController {

    Context context;
    ProductsDBHelper dbHelper;
    SQLiteDatabase sqlDb;

    private static final String createTableQuery="create table "+ DbConstants.TABLE_NAME+" ("
            +DbConstants.COLUMN_NAME+" text not null,"
            +DbConstants.COLUMN_DESC+" text,"
            +DbConstants.COLUMN_PRICE+" text,"
            +DbConstants.COLUMN_REVIEW+" text);";


    public SQLDataController(Context context){
        this.context=context;
        dbHelper=new ProductsDBHelper(context);
    }

    public SQLDataController open(){

        sqlDb=dbHelper.getWritableDatabase();
        return this;
    }

    public void close(){

        dbHelper.close();
    }

    public long insert(String item_name,String item_desc,String item_price,String item_review){
        ContentValues values = new ContentValues();
        values.put(DbConstants.COLUMN_NAME, item_name);
        values.put(DbConstants.COLUMN_DESC, item_desc);
        values.put(DbConstants.COLUMN_PRICE,item_price);
        values.put(DbConstants.COLUMN_REVIEW,item_review);

        return sqlDb.insert(DbConstants.TABLE_NAME,null,values);
    }

    public Cursor getData(String searchVal){

        return sqlDb.query(DbConstants.TABLE_NAME, new String[]{DbConstants.COLUMN_NAME, DbConstants.COLUMN_DESC, DbConstants.COLUMN_PRICE, DbConstants.COLUMN_REVIEW }, DbConstants.COLUMN_NAME+" like " + "'%"+ searchVal+"%'", null, null, null, null);

    }

    public class ProductsDBHelper extends SQLiteOpenHelper {

        public static final int DB_VERSION=1;

        public ProductsDBHelper(Context context) {
            super(context, DbConstants.DATABASE_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(createTableQuery);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+DbConstants.TABLE_NAME);
            onCreate(sqlDb);
        }
    }
}

