package com.example.dbcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button addNew;
    ListView listViewStudent;
    TextView head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addNew = findViewById(R.id.button4);
        head = findViewById(R.id.textView4);
        listViewStudent = findViewById(R.id.listViewStudent);

        DBHelper db = new DBHelper(MainActivity.this);
        ArrayList<StudentModel> arraylist = db.getAllStudents();
        studentListConfig std = new studentListConfig(this, 0, arraylist);
        listViewStudent.setAdapter(std);

        listViewStudent.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,updateRecord.class);
                intent.putExtra("action","update");
                StudentModel s = (StudentModel)listViewStudent.getItemAtPosition(i);
                intent.putExtra("name",s.getName());
                intent.putExtra("rollno",s.getRoll());
                intent.putExtra("enrolled",s.isEnrolled());
                String id = Integer.toString(s.getId());
                Log.i("id",id);
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });

        addNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,AddRecord.class);
                startActivity(i);
            }
        });
    }
}