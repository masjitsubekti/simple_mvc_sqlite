package com.masjitsubekti.simple_mvc_sqllite.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.masjitsubekti.simple_mvc_sqllite.R;
import com.masjitsubekti.simple_mvc_sqllite.controller.DBDataSource;

public class UpdateData extends Activity implements OnClickListener {
    private EditText edtBookName, edtBookAuthor;
    private Button btnUpdate;
    private DBDataSource dataSource;
    private long bookId;
    private String bookName, bookAuthor;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedinstanceState) {
        super.onCreate(savedinstanceState);
        setContentView(R.layout.update_data);

        bookId = getIntent().getExtras().getLong("id");
        bookName = getIntent().getExtras().getString("name");
        bookAuthor = getIntent().getExtras().getString("author");

        edtBookName = (EditText) findViewById(R.id.edtBookName);
        edtBookName.setText(bookName);

        edtBookAuthor = (EditText) findViewById(R.id.edtBookAuthor);
        edtBookAuthor.setText(bookAuthor);

        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);

        dataSource = new DBDataSource(this);
        dataSource.open();
    }

    @SuppressLint("NonConstantResourceId")
    public void onClick(View v) {
        try {
            bookName = edtBookName.getText().toString();
            bookAuthor = edtBookAuthor.getText().toString();

            if(bookName.length()==0) {
                edtBookName.setError("Book Name is required !");
            }else if (bookAuthor.length()==0){
                edtBookAuthor.setError("Book Author is required !");
            }else{
                if (v.getId() == R.id.btnUpdate) {
                    dataSource.updateData(bookId, bookName, bookAuthor);

                    Toast.makeText(this, "Data updated!", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(this, ListUpdate.class);
                    startActivity(intent);

                    finish();
                }
            }
        } catch (Exception e) {
            Toast.makeText(this, "Please fill in the data!", Toast.LENGTH_SHORT).show();
        }
    }
}
