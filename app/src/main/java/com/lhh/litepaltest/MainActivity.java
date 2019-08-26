package com.lhh.litepaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createDatabase = findViewById(R.id.create_database);
        Button addData = findViewById(R.id.add_data);
        Button updateData = findViewById(R.id.update_data);
        Button deleteData = findViewById(R.id.delete_data);
        Button queryButton = findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           List<Book> books = DataSupport.findAll(Book.class);
                for (Book book : books) {
                    Log.d("lhh", book.toString());
                }
            }
        });
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //删除页数少于500的
                DataSupport.deleteAll(Book.class, "price>?", "16");
            }
        });
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //存在更新，不存在就保存
                Book book = new Book();
                book.setPages(500);
                book.setPrice(30.2);
                book.saveOrUpdate("name=?","水浒传");
               /* Book book = new Book();
                book.setPages(999);
                book.setPress("Ancho999");
                //更新以上数据，条件是下面这两条
                book.updateAll("name= ? and author = ?", "The Lost Synbol", "Dan Brown");
                Log.d("TAG", "更新成功");*/

            }
        });
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Book book = new Book();
             book.setId(001);
             book.setName("水浒传");
             book.setPrice(15.9);
             book.setPress("新华社");
             book.setPages(400);
             book.setAuthor("施耐庵");
             book.save();
            }
        });
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Connector.getDatabase();
            }
        });
    }
}
