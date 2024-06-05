package com.example.lab6;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Objects;

public class Info extends AppCompatActivity {
    Integer id;
    private TextView tv_username_info, tv_email_info, tv_phone_number_info, tv_password_info, tv_school_info, tv_class_info;
    Button bt_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_info);
        Mapping();
        ShowInfo();
        bt_back.setOnClickListener(v -> finish());
    }
    void Mapping()
    {
        bt_back = findViewById(R.id.bt_back);
        tv_username_info = findViewById(R.id.tv_username_info);
        tv_email_info = findViewById(R.id.tv_email_info);
        tv_school_info = findViewById(R.id.tv_school_info);
        tv_class_info = findViewById(R.id.tv_class_info);
        tv_phone_number_info = findViewById(R.id.tv_phone_number_info);
        tv_password_info = findViewById(R.id.tv_password_info);
    }
    void ShowInfo()
    {
        Intent intent = getIntent();
        id = intent.getIntExtra("ID", 0);
        DBHelper dbHelper = new DBHelper(Info.this);
        UserModel userModel = dbHelper.SelectUserById(id);
        tv_username_info.setText(userModel.getUsername());
        tv_email_info.setText(userModel.getEmail());
        tv_school_info.setText(userModel.getSchool());
        tv_class_info.setText(userModel.getClass_room());
        tv_phone_number_info.setText(userModel.getPhone_number());
        tv_password_info.setText(userModel.getPassword());
    }
}