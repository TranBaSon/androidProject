package com.example.apptodolistbyson.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptodolistbyson.MainActivity;
import com.example.apptodolistbyson.R;
import com.example.apptodolistbyson.model.Task;

import java.util.List;

public class ToDoAdapter extends ArrayAdapter {
    private MainActivity context;
    private int resource;
    List<Task> listTask;

    public ToDoAdapter(MainActivity context, int resource, List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listTask = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;


        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_listview_todolist, parent, false);
            holder = new ViewHolder();
            holder.tvTask = convertView.findViewById(R.id.tvTask);
            holder.ivEdit = convertView.findViewById(R.id.ivEdit);
            holder.ivTrash = convertView.findViewById(R.id.ivTrash);

            convertView.setTag(holder);

        }else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Task task = listTask.get(position);

        holder.tvTask.setText(task.getTask());

        holder.ivTrash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.removeTask(task.getId());
                Toast.makeText(context, "remove successfuly" , Toast.LENGTH_SHORT).show();
            }
        });


        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.editTaskDialog(task.getId());
            }
        });



        return convertView;
    }

    class ViewHolder{
        TextView tvTask;
        ImageView ivTrash;
        ImageView ivEdit;
    }

}
