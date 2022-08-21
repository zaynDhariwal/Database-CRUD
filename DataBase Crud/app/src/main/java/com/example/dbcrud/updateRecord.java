package com.example.dbcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class updateRecord extends AppCompatActivity {

    Button edit,delete;
    String name,rollno,enrolled,id;
    EditText nametxt,rolltxt;
    Switch enrolledSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_record);
        Intent i = getIntent();
        edit = findViewById(R.id.button2);
        delete = findViewById(R.id.button3);
        nametxt = findViewById(R.id.name);
        rolltxt = findViewById(R.id.className);
        enrolledSwitch = findViewById(R.id.switch1);
        if(i.getExtras() != null)
        {
            edit.setVisibility(View.VISIBLE);
            delete.setVisibility(View.VISIBLE);
            name = i.getStringExtra("name");
            rollno = i.getStringExtra("rollno");
            enrolled = i.getStringExtra("enrolled");
            id = i.getStringExtra("id");
            nametxt.setText(name);
            rolltxt.setText(rollno);
            if(enrolled == "Not Enrolled")
            {
                enrolledSwitch.setChecked(false);
            }
            else
                enrolledSwitch.setChecked(true);
        }

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(updateRecord.this);
                String en = enrolledSwitch.isChecked() ? "Enrolled" : "Not Enrolled";
                StudentModel s = new StudentModel(nametxt.getText().toString(),
                        rolltxt.getText().toString()
                        ,en);
                s.setId(Integer.parseInt(id));
                db.updateStudent(s);
                Toast t = Toast.makeText(updateRecord.this,"Record updated",Toast.LENGTH_SHORT);
                Intent back = new Intent(updateRecord.this, MainActivity.class);
                t.show();
                startActivity(back);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(updateRecord.this);
                db.deleteStudent(Integer.parseInt(id));
                Toast t = Toast.makeText(updateRecord.this,"Record Deleted",Toast.LENGTH_SHORT);
                Intent back = new Intent(updateRecord.this, MainActivity.class);
                t.show();
                startActivity(back);
            }
        }
        );
    }
}