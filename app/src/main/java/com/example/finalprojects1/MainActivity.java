package com.example.finalprojects1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView Lv;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        Lv = findViewById(R.id.listView);

        updateListView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                AlertDialog.Builder todoTaskBuilder = new AlertDialog.Builder(this);
                todoTaskBuilder.setTitle("Add Todo Task Item");
                todoTaskBuilder.setMessage("Describe The Todo task...");
                final EditText todoET = new EditText(this);
                todoTaskBuilder.setView(todoET);
                todoTaskBuilder.setPositiveButton("Add Task", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String todoTaskInput = todoET.getText().toString();
                        ContentValues values = new ContentValues();
                        values.put(DBHelper.row_todo, todoTaskInput);
                        if(todoTaskInput.equals("")){
                            Toast.makeText(MainActivity.this,"Isi Data Dengan Lengkap",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            dbHelper.addData(values);
                            updateListView();
                        }
                    }
                });
                todoTaskBuilder.setNegativeButton("Cancel", null);
                todoTaskBuilder.create().show();
                return true;

            default:
                return false;
        }
    }


    private void updateListView() {
        Cursor cursor = dbHelper.allData();
        CustomAdapter customCursorAdapter = new CustomAdapter(this,cursor,0);
        Lv.setAdapter(customCursorAdapter);
    }

    public void hapusTodo(View view) {
        View v = (View) view.getParent();
        TextView getId = v.findViewById(R.id.tv_id);
        final long id = Long.parseLong(getId.getText().toString());

        dbHelper.deleteData(id);
        updateListView();
    }

    public void updateTodo(View view) {
        View v = (View) view.getParent();
        TextView getId = v.findViewById(R.id.tv_id);
        TextView getTask =  v.findViewById(R.id.tv_todo);
        final long id = Long.parseLong(getId.getText().toString());
        String todoTaskItem = getTask.getText().toString();
        AlertDialog.Builder todoTaskBuilder = new AlertDialog.Builder(this);
        todoTaskBuilder.setTitle("Update Todo");
        todoTaskBuilder.setMessage("Update The Todo Task...");
        final EditText todoET = new EditText(this);
        todoET.setText(todoTaskItem);
        todoTaskBuilder.setView(todoET);
        todoTaskBuilder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String todoTaskInput = todoET.getText().toString();
                ContentValues values = new ContentValues();
                values.put(DBHelper.row_todo, todoTaskInput);
                if(todoTaskInput.equals("")){
                    Toast.makeText(MainActivity.this,"Isi Todo dengan lengkap", Toast.LENGTH_SHORT).show();
                }else{
                    dbHelper.updateData(values,id);
                    updateListView();
                }
            }
        });
        todoTaskBuilder.setNegativeButton("Cancel", null);
        todoTaskBuilder.create().show();
    }

}