package com.example.apptodolistbyson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.apptodolistbyson.adapter.ToDoAdapter;
import com.example.apptodolistbyson.data.ToDoDatabase;
import com.example.apptodolistbyson.model.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listViewTask;
    ToDoAdapter adapter;
    List<Task> arrTask;
    ToDoDatabase db;
    Cursor cursor;
    EditText edtAdd, edtEdit;
    Button btnAdd, btnCancelAdd, btnEdit, btnCancelEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = ToDoDatabase.getInstance(this, "databaseToDo", 1);
        initWidgets();
        arrTask = new ArrayList<>();
        getData();
        adapter = new ToDoAdapter(this, R.layout.item_listview_todolist, arrTask);
        listViewTask.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        // create table
        String sqlQuery = "CREATE TABLE IF NOT EXISTS DBToDo( id INTEGER PRIMARY KEY AUTOINCREMENT, task NVARCHAR(200) )";
        db.QueryData(sqlQuery);

        // add task
//        String sqlTask = "" ;
//        String sqlAddTask = "INSERT INTO DBToDo VALUES (NULL,  '" + sqlTask + "' )";
//        db.QueryData("INSERT INTO DBToDo VALUES (NULL,  'đi làm ruông' )");

        // select task

        adapter.notifyDataSetChanged();

    }


    // create menu add
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_menu_todolist, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // click item menu add
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addMenu){
            addDialog();
            Toast.makeText(this, "oke add", Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }


    // init widgets
    public void initWidgets(){
        listViewTask = findViewById(R.id.listViewTask);
    }

    // remove task
    public void removeTask(int id){
        db.QueryData("DELETE FROM DBToDo WHERE id = '"+ id +"'");
        getData();
        adapter.notifyDataSetChanged();
    }


    // get data task
    public void getData(){
        arrTask.clear();
        String sqlSelectTask = "SELECT * FROM DBToDo";
        cursor = db.GetData(sqlSelectTask);
        while (cursor.moveToNext()){
            arrTask.add(new Task(cursor.getInt(0), cursor.getString(1)));
        }
    }


    // dialog add task
    public void addDialog(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.add_dialog_todolist);

        edtAdd = dialog.findViewById(R.id.edtAdd);
        btnAdd = dialog.findViewById(R.id.btnAdd);
        btnCancelAdd = dialog.findViewById(R.id.btnCancelAdd);


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(edtAdd.getText())){

                    String sqlAddTask = "INSERT INTO DBToDo VALUES (NULL,  '" + edtAdd.getText() + "' )";
                    db.QueryData(sqlAddTask);
                    getData();
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "add successfuly", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else {
                    Toast.makeText(MainActivity.this, "enter task", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancelAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    //dialog edit task

    public void editTaskDialog(final int id){

        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_edit_task);


        edtEdit = dialog.findViewById(R.id.edtEdit);
        btnEdit = dialog.findViewById(R.id.btnEdit);
        btnCancelEdit = dialog.findViewById(R.id.btnCancelEdit);


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(edtEdit.getText())){

                    String sqlAddTask = "UPDATE DBToDo SET task = '" + edtEdit.getText() + "' WHERE id = '" + id + "' ";
                    db.QueryData(sqlAddTask);
                    getData();
                    adapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "edit successfuly", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else {
                    Toast.makeText(MainActivity.this, "enter task", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancelEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }


}
