package com.example.dbcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class AddRecord extends AppCompatActivity {

    Button add;
    String name, rollno, enrolled, id;
    EditText nametxt, rolltxt;
    Switch enrolledSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);
        Intent i = getIntent();
        nametxt = findViewById(R.id.name);
        rolltxt = findViewById(R.id.className);
        add = findViewById(R.id.button2);
        enrolledSwitch = findViewById(R.id.switch1);
        if (i.getExtras() != null) {
            name = i.getStringExtra("name");
            rollno = i.getStringExtra("rollno");
            enrolled = i.getStringExtra("enrolled");
            id = i.getStringExtra("id");
            nametxt.setText(name);
            rolltxt.setText(rollno);
            if (enrolled == "Not Enrolled") {
                enrolledSwitch.setChecked(false);
            } else
                enrolledSwitch.setChecked(true);
        }

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(AddRecord.this);
                String en = enrolledSwitch.isChecked() ? "Enrolled" : "Not Enrolled";
                StudentModel s = new StudentModel(nametxt.getText().toString(),
                        rolltxt.getText().toString()
                        ,en);
                db.addStudent(s);
                Toast t = Toast.makeText(AddRecord.this,"Record Added",Toast.LENGTH_SHORT);
                Intent back = new Intent(AddRecord.this, MainActivity.class);
                t.show();
                startActivity(back);
            }
        });
    }
}