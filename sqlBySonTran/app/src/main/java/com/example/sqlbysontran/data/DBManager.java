package com.example.sqlbysontran.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.sqlbysontran.model.Student;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "student_list";
    private static final String TABLE_NAME = "student";
    private static final String NAME = "name";
    private static final String ADDRESS = "address";
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";
    private static final String ID = "id";
    private Context context;

    private static DBManager instance;


    private DBManager(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
        Log.d("DBManager", "DBManager: ");
    }

    public static DBManager getInstance(Context context){
        if (instance == null){
            instance = new DBManager(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlQuery = "create table " + TABLE_NAME + " ( " +
                ID + " integer primary key, " +
                NAME + " text , " +
                PHONE + " text , " +
                EMAIL + " text , " +
                ADDRESS + " text  )";
        sqLiteDatabase.execSQL(sqlQuery);

        Toast.makeText(context, "create table successfuly", Toast.LENGTH_LONG).show();
        Log.d("DBManager", "onCreate ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d("DBManager", "onUpgrade: ");
    }


    public void addStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(NAME, student.getmName());
        values.put(PHONE, student.getmPhone());
        values.put(ADDRESS, student.getmAddress());
        values.put(EMAIL, student.getmEmail());
        db.insert(TABLE_NAME, null, values);
        db.close();
        Log.d("DBManager", "AddStudent ");
    }

    public List<Student> getAllStudent(){
        List<Student> studentList = new ArrayList<>();
        String sqlSelect = "select * from " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(sqlSelect, null);

        if (cursor.moveToFirst()){
            do {
                studentList.add(new Student(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(4), cursor.getString(3)));
            }while (cursor.moveToNext());
        }
        db.close();

        return studentList;
    }


    public int updateStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, student.getmName());
        contentValues.put(PHONE, student.getmPhone());
        contentValues.put(ADDRESS, student.getmAddress());
        contentValues.put(EMAIL, student.getmEmail());

        int result = db.update(TABLE_NAME, contentValues, ID + " = ?", new String[] {String.valueOf(student.getmId())});
        return result ;
    }


    public int deleteStudent(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME, ID + " = ?", new String[] {String.valueOf(id)});
    }

}
