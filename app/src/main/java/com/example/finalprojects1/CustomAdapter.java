package com.example.finalprojects1;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.os.Build;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class CustomAdapter  extends CursorAdapter  {

    private LayoutInflater ly;
    private SparseBooleanArray mSelectedItems;

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)

    public CustomAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        ly = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mSelectedItems=new SparseBooleanArray();
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        View v = ly.inflate(R.layout.activity_item,viewGroup,false);
        MyHolder holder = new MyHolder();
        holder.TvID = v.findViewById(R.id.tv_id);
        holder.TvTodo =v.findViewById(R.id.tv_todo);
        v.setTag(holder);

        return v;
    }

    @SuppressLint("Range")
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        MyHolder holder = (MyHolder)view.getTag();

        holder.TvID.setText(cursor.getString(cursor.getColumnIndex(DBHelper.row_id)));

        holder.TvTodo.setText(cursor.getString(cursor.getColumnIndex(DBHelper.row_todo)));
    }

    class MyHolder{
        TextView TvID;
        TextView TvTodo;
    }

}

