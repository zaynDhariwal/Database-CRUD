package com.example.dbcrud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;


public class DBHelper extends SQLiteOpenHelper {

    public static final String STUDENT_ID = "StudentID";
    public static final String STUDENT_NAME = "StudentName";
    public static final String STUDENT_RollNo = "StudentRollNo";
    public static final String STUDENT_ENROLL = "IsEnrolled";
    public static final String STUDENT_TABLE = "StudentTable";

    public DBHelper(@Nullable Context context)
    {
        super(context, "MyDB.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqdb) {
        String createTableStatement = "CREATE TABLE " + STUDENT_TABLE + "(" +
                STUDENT_ID + " Integer PRIMARY KEY AUTOINCREMENT, " + STUDENT_NAME + " Text, "
                + STUDENT_RollNo + " String, " + STUDENT_ENROLL + " String) ";
        sqdb.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqdb, int i, int i1) {
        sqdb.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);
        onCreate(sqdb);
    }

    public void  addStudent(StudentModel STUDENTModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(STUDENT_NAME, STUDENTModel.getName());
        cv.put(STUDENT_RollNo, STUDENTModel.getRoll());
        cv.put(STUDENT_ENROLL, STUDENTModel.isEnrolled());
        db.insert(STUDENT_TABLE, null, cv);
        db.close();
    }

    public ArrayList<StudentModel> getAllStudents() {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorCourses = db.rawQuery("SELECT * FROM " + STUDENT_TABLE, null);
        ArrayList<StudentModel> studentArrayList = new ArrayList<>();
        if (cursorCourses.moveToFirst()) {
            do {
                StudentModel stu =
                        new StudentModel(cursorCourses.getInt(0), cursorCourses.getString(1),
                        cursorCourses.getString(2),
                        cursorCourses.getString(3));
                studentArrayList.add(stu);
            } while (cursorCourses.moveToNext());

        }
        cursorCourses.close();
        return studentArrayList;
    }

    public int updateStudent(StudentModel studentModel)
    {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(STUDENT_NAME, studentModel.getName());
        values.put(STUDENT_RollNo, studentModel.getRoll());
        values.put(STUDENT_ENROLL, studentModel.isEnrolled());

        return sqdb.update(STUDENT_TABLE,values,STUDENT_ID +"=?",
                new String[]{String.valueOf(studentModel.getId())});
    }

    public void deleteStudent(int id)
    {
        SQLiteDatabase sqdb = this.getWritableDatabase();
        sqdb.delete(STUDENT_TABLE,STUDENT_ID+"=?",new String[]{String.valueOf(id)});
        sqdb.close();
    }
}
