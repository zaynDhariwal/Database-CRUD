package com.example.dbcrud;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class studentListConfig extends ArrayAdapter<StudentModel> {

    public studentListConfig(@NonNull Context context,int resource,
                             @NonNull List<StudentModel> objects)
    {
        super(context,resource,objects);
    }
    @NonNull @Override
    public View getView(int position, @NonNull View convertView, @NonNull ViewGroup parent)
    {
        StudentModel ctg =  getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.student_item,parent,
                false);
        TextView name = convertView.findViewById(R.id.textView5);
        TextView enrolled = convertView.findViewById(R.id.textView6);
        TextView rollNo = convertView.findViewById(R.id.textView7);
        TextView edit = convertView.findViewById(R.id.textView8);
        name.setText(ctg.getName());
        rollNo.setText(ctg.getRoll());
        enrolled.setText(ctg.isEnrolled());
        edit.setText("Edit");
        return convertView;
    }

}
