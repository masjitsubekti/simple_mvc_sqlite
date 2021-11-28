package com.masjitsubekti.simple_mvc_sqllite.view;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.masjitsubekti.simple_mvc_sqllite.R;
import com.masjitsubekti.simple_mvc_sqllite.controller.DBDataSource;
import com.masjitsubekti.simple_mvc_sqllite.model.Book;
import java.util.ArrayList;

public class DeleteData extends Activity {
    private DBDataSource dataSource;
    private ArrayList<Book> values;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_data);

        dataSource = new DBDataSource(this);
        dataSource.open();

        values = dataSource.readData();
        ArrayAdapter<Book> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, values
        );

        listView = findViewById(R.id.listBook);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(
                (parent, v, position, id) -> {
                    Book book = values.get(position);

                    AlertDialog.Builder dialog = new AlertDialog.Builder(DeleteData.this);
                    dialog.setMessage("Do you want to delete this data?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", (dialog1, which) -> {
                                dataSource.deleteData(book.getBookId());
                                Toast.makeText(DeleteData.this, "Data deleted successfully!", Toast.LENGTH_SHORT).show();
                                DeleteData.this.recreate();
                            }).setNegativeButton("No", (dialog12, which) -> dialog12.cancel());

                    AlertDialog alertDialog = dialog.create();
                    alertDialog.show();
                }
        );
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.onCreate(null);
    }
}