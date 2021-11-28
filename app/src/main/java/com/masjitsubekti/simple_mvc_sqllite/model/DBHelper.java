package com.masjitsubekti.simple_mvc_sqllite.model;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{
    public static final String tableName = "books";
    public static final String columnId = "book_id";
    public static final String columnName = "book_name";
    public static final String columnAuthor = "book_author";
    private static final String db_name = "books.db";
    private static final String db_create = "CREATE TABLE " + tableName + "("
            + columnId + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + columnName + " VARCHAR(100) NOT NULL, "
            + columnAuthor + " VARCHAR(100) NOT NULL"
            + ")";

    public DBHelper(Context context) {
        super(context, db_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(db_create);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
    }
}
