package com.example.sqlbysontran.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqlbysontran.R;
import com.example.sqlbysontran.model.Student;

import java.util.List;

public class LvStudentAdapter extends ArrayAdapter {
    private Context context;
    private int resource;
    private List<Student> studentList;

    public LvStudentAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.studentList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_student, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tvAddress = convertView.findViewById(R.id.tvAddress);
            viewHolder.tvId = convertView.findViewById(R.id.tvId);
            viewHolder.tvName = convertView.findViewById(R.id.tvName);
            viewHolder.tvEmail = convertView.findViewById(R.id.tvEmail);
            viewHolder.tvPhone = convertView.findViewById(R.id.tvPhone);
            Log.d("id", (studentList.get(position).getmId() + 1) + "" );
            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Student student = studentList.get(position);
        viewHolder.tvId.setText(student.getmId()+ " ");
        viewHolder.tvAddress.setText(student.getmAddress());
        viewHolder.tvEmail.setText(student.getmEmail());
        viewHolder.tvName.setText(student.getmName());
        viewHolder.tvPhone.setText(student.getmPhone());


        return convertView;
    }


    public class ViewHolder{

        TextView tvId, tvName, tvEmail, tvPhone, tvAddress;

    }

}
