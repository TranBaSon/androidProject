package com.example.sqlbysontran;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqlbysontran.adapter.LvStudentAdapter;
import com.example.sqlbysontran.data.DBManager;
import com.example.sqlbysontran.model.Student;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    LinearLayout showId;
    EditText edtName, edtPhone, edtAddress, edtEmail;
    TextView textViewId;
    Button btnSave, btnUpdate;
    private LvStudentAdapter lvStudentAdapter;
    private List<Student> studentList;
    ListView listView;
    DBManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = DBManager.getInstance(MainActivity.this);
        maping();
        btnUpdate.setVisibility(View.INVISIBLE);
        showId.setVisibility(View.INVISIBLE);
        studentList = db.getAllStudent();

        initAdapterStudent();

        setBtnSave();

        setBtnUpdate();

        removeStudent();

        handlerItemLVStudent();


    }


    public void maping() {
        edtAddress = findViewById(R.id.edtAddress);
        edtEmail = findViewById(R.id.edtEmail);
        edtName = findViewById(R.id.edtName);
        edtPhone = findViewById(R.id.edtPhone);
        btnSave = findViewById(R.id.btnSave);
        btnUpdate = findViewById(R.id.btnUpdate);
        listView = findViewById(R.id.lv_student);
        textViewId = findViewById(R.id.textViewId);
        showId = findViewById(R.id.showId);
    }

    public Student newStudent() {
        String name = edtName.getText().toString();
        String phone = edtPhone.getText().toString();
        String email = edtPhone.getText().toString();
        String address = edtAddress.getText().toString();

        Student student = new Student(name, phone, address, email);

        return student;
    }

    public void initAdapterStudent() {
        if (lvStudentAdapter == null) {
            lvStudentAdapter = new LvStudentAdapter(this, R.layout.item_list_student, studentList);
            listView.setAdapter(lvStudentAdapter);
        } else {
            lvStudentAdapter.notifyDataSetChanged();
            listView.setSelection(lvStudentAdapter.getCount() - 1);
        }
    }

    public void handlerItemLVStudent() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                edtName.setText(studentList.get(i).getmName());
                edtAddress.setText(studentList.get(i).getmAddress());
                edtPhone.setText(studentList.get(i).getmPhone());
                edtEmail.setText(studentList.get(i).getmEmail());
                textViewId.setText(studentList.get(i).getmId() + "");


                showId.setVisibility(View.VISIBLE);
                btnSave.setVisibility(View.INVISIBLE);
                btnUpdate.setVisibility(View.VISIBLE);
            }
        });
    }

    public void setBtnSave() {

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String phone = edtPhone.getText().toString();
                String address = edtAddress.getText().toString();
                String email = edtEmail.getText().toString();
                if (!name.isEmpty() && name != null && !phone.isEmpty() && phone != null && !address.isEmpty() && address != null && !email.isEmpty() && email != null) {
                    Student student = new Student(name, phone, address, email);
                    db.addStudent(student);
                    studentList.clear();
                    studentList.addAll(db.getAllStudent());
                    initAdapterStudent();
                    formatEdt();
                } else {
                    Toast.makeText(MainActivity.this, "enter all field", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void setBtnUpdate() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int id = Integer.parseInt(textViewId.getText().toString());
                String name = edtName.getText().toString();
                String phone = edtPhone.getText().toString();
                String address = edtAddress.getText().toString();
                String email = edtEmail.getText().toString();
                if (!name.isEmpty() && name != null && !phone.isEmpty() && phone != null && !address.isEmpty() && address != null && !email.isEmpty() && email != null) {
                    Student student = new Student(id, name, phone, address, email);
                    int result = db.updateStudent(student);
                    if (result > 0) {
                        Toast.makeText(MainActivity.this, "update successfuly", Toast.LENGTH_SHORT).show();
                        btnUpdate.setVisibility(View.INVISIBLE);
                        showId.setVisibility(View.INVISIBLE);
                        btnSave.setVisibility(View.VISIBLE);
                        formatEdt();

                        studentList.clear();
                        studentList.addAll(db.getAllStudent());
                        lvStudentAdapter.notifyDataSetChanged();
                    } else {
                        Toast.makeText(MainActivity.this, "update don't successfuly", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "enter all field", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void removeStudent() {
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                db.deleteStudent(studentList.get(i).getmId());
                studentList.clear();
                studentList.addAll(db.getAllStudent());
                lvStudentAdapter.notifyDataSetChanged();
                if (studentList.isEmpty()) {
                    btnUpdate.setVisibility(View.INVISIBLE);
                    showId.setVisibility(View.INVISIBLE);
                    btnSave.setVisibility(View.VISIBLE);
                    formatEdt();
                }
                return false;
            }
        });
    }

    public void formatEdt() {
        edtName.setText("");
        edtPhone.setText("");
        edtAddress.setText("");
        edtEmail.setText("");
    }
}
