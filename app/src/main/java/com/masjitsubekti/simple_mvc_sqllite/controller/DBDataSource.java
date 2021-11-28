package com.masjitsubekti.simple_mvc_sqllite.controller;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.masjitsubekti.simple_mvc_sqllite.model.Book;
import com.masjitsubekti.simple_mvc_sqllite.model.DBHelper;
import java.util.ArrayList;

public class DBDataSource {
    private SQLiteDatabase database;
    private final DBHelper dbHelper;
    private final String[] allColumns = {
            DBHelper.columnId,
            DBHelper.columnName,
            DBHelper.columnAuthor
    };
    private Cursor cursor;

    public DBDataSource(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    private Book cursorToBook(Cursor cursor) {
        Book book = new Book();

        book.setBookId(cursor.getLong(0));
        book.setBookName(cursor.getString(1));
        book.setBookAuthor(cursor.getString(2));

        return book;
    }

    public void createData(String bookName, String bookAuthor) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.columnName, bookName);
        values.put(DBHelper.columnAuthor, bookAuthor);

        long insertId = database.insert(DBHelper.tableName, null, values);

        cursor = database.query(DBHelper.tableName, allColumns, DBHelper.columnId + "=" +
                insertId, null, null, null, null
        );
        cursor.moveToFirst();
        cursor.close();
    }

    public ArrayList<Book> readData() {
        ArrayList<Book> listBook = new ArrayList<>();

        cursor = database.query(DBHelper.tableName, allColumns,
                null, null, null, null, null
        );
        cursor.moveToFirst();
        while(!cursor.isAfterLast()) {
            Book book = cursorToBook(cursor);
            listBook.add(book);
            cursor.moveToNext();
        }
        cursor.close();

        return listBook;
    }

    public void updateData(long bookId, String bookName, String bookAuthor) {
        ContentValues values = new ContentValues();
        values.put(DBHelper.columnName, bookName);
        values.put(DBHelper.columnAuthor, bookAuthor);

        String[] args = {"" + bookId};

        database.update(DBHelper.tableName, values, DBHelper.columnId + "=?", args);
    }

    public void deleteData(long bookId) {
        String[] args = {"" + bookId};
        database.delete(DBHelper.tableName, DBHelper.columnId + "=?", args);
    }
}
