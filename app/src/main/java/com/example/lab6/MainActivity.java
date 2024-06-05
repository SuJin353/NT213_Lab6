package com.example.lab6;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    ListView lv_user;
    ArrayList<UserModel> allUser;
    UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_main);
        Mapping();
        SelectUser(allUser);
    }
    void Mapping()
    {
        lv_user = findViewById(R.id.lv_user);
        DBHelper dbHelper = new DBHelper(MainActivity.this);
        allUser = dbHelper.SelectAllUser();
        userAdapter = new UserAdapter(allUser, MainActivity.this);
        lv_user.setAdapter(userAdapter);
    }
    void SelectUser(ArrayList<UserModel> userArrayList)
    {
        lv_user.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(MainActivity.this, Info.class);
            intent.putExtra("ID", userArrayList.get(i).getId());
            startActivity(intent);
        });
    }
}