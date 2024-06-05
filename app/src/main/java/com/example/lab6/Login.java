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

public class Login extends AppCompatActivity {
    private EditText et_email, et_password;
    private Button bt_login, bt_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(getSupportActionBar()).hide();
        setContentView(R.layout.activity_login);
        Mapping();

        bt_login.setOnClickListener(v -> {
            String email = et_email.getText().toString().trim();
            String password = et_password.getText().toString().trim();

            String hashPassword;
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                byte[] bytes = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
                hashPassword = ByteToHexConverter(bytes);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
            
            if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                if (!password.isEmpty()) {
                    DBHelper dbHelper = new DBHelper(Login.this);
                    boolean check = dbHelper.VerifyUser(email, hashPassword);
                    if (check) {
                        Intent intent = new Intent(Login.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(Login.this, "Wrong email or password", Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    et_password.setError("Password can't be empty");
                }
            }
            else {
                et_email.setError("Your email is empty or have an incorrect format");
            }
        });
        bt_signup.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, SignUp.class);
            startActivity(intent);
        });
    }
    void Mapping()
    {
        bt_login= findViewById(R.id.bt_login);
        bt_signup = findViewById(R.id.bt_signup);
        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
    }
    String ByteToHexConverter(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte aByte : bytes) {
            sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}