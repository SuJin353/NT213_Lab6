package com.example.lab6;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class SignUp extends AppCompatActivity {
    private EditText et_username, et_email, et_phone_number, et_password, et_confirm_password, et_school, et_class;
    private Button bt_back, bt_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_sign_up);
        Mapping();

        bt_back.setOnClickListener(v -> finish());
        bt_signup.setOnClickListener(v -> CreateUser());
    }

    void Mapping() {
        et_username = findViewById(R.id.et_username);
        et_school = findViewById(R.id.et_school);
        et_class = findViewById(R.id.et_class);
        et_email = findViewById(R.id.et_email);
        et_phone_number = findViewById(R.id.et_phone_number);
        et_password = findViewById(R.id.et_password);
        et_confirm_password = findViewById(R.id.et_confirm_password);
        bt_back = findViewById(R.id.bt_back);
        bt_signup = findViewById(R.id.bt_signup);
    }

    void CreateUser() {
        UserModel userModel;
        String username = et_username.getText().toString().trim();
        String school = et_school.getText().toString();
        String class_room = et_class.getText().toString();
        String email = et_email.getText().toString().trim();
        String phone_number = et_phone_number.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        String confirm_password = et_confirm_password.getText().toString().trim();

        if (username.isEmpty() || school.isEmpty() || class_room.isEmpty() || email.isEmpty() || password.isEmpty() || phone_number.isEmpty()) {
            Toast.makeText(SignUp.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            et_email.setError("Incorrect email format");
        } else if (password.length() < 6) {
            et_password.setError("Password must be at least 6 character");
        } else if (!password.equals(confirm_password)) {
            et_confirm_password.setError("Password are not matching");
        } else {
            String hashPassword;
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                byte[] bytes = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
                hashPassword = ByteToHexConverter(bytes);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            DBHelper dbHelper = new DBHelper(SignUp.this);
            userModel = new UserModel(1, username, email, school, class_room, phone_number, hashPassword);
            boolean success = dbHelper.AddUser(userModel);
            Toast.makeText(SignUp.this, "Success=" + success, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignUp.this, Login.class);
            startActivity(intent);
            finish();
        }
    }

    String ByteToHexConverter(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}