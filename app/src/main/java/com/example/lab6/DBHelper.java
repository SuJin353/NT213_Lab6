package com.example.lab6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE = "USER_TABLE";
    public static final String ID = "ID";
    public static final String USERNAME = "USERNAME";
    public static final String EMAIL = "EMAIL";
    public static final String SCHOOL = "SCHOOL";
    public static final String CLASS = "CLASS";
    public static final String PHONENUMBER = "PHONENUMBER";
    public static final String PASSWORD = "PASSWORD";

    public DBHelper(@Nullable Context context) {
        super(context, "user.dg", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String createTableStatement = "CREATE TABLE " + USER_TABLE + " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + USERNAME + " TEXT, " + EMAIL + " TEXT, " + SCHOOL + " TEXT, " + CLASS + " TEXT, " + PHONENUMBER + " TEXT, " + PASSWORD + " TEXT)";

        db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean AddUser(UserModel userModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USERNAME, userModel.getUsername());
        cv.put(EMAIL, userModel.getEmail());
        cv.put(SCHOOL, userModel.getSchool());
        cv.put(CLASS, userModel.getClass_room());
        cv.put(PHONENUMBER, userModel.getPhone_number());
        cv.put(PASSWORD, userModel.getPassword());

        long insert = db.insert(USER_TABLE, null, cv);
        return insert != -1;
    }
    public boolean VerifyUser(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + USER_TABLE  + " WHERE " + EMAIL + " ='" + email + "' AND " + PASSWORD + " ='" + password + "'";
        Cursor cursor = db.rawQuery(queryString, null);
        return cursor.getCount() > 0;
    }
    public UserModel SelectUserById(Integer id) {
        UserModel user = new UserModel();
        String queryString = "SELECT * FROM " + USER_TABLE  + " WHERE " + ID + " ='" + id + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            id = cursor.getInt(0);
            String username = cursor.getString(1);
            String email = cursor.getString(2);
            String school = cursor.getString(3);
            String class_room = cursor.getString(4);
            String phonenumber = cursor.getString(5);
            String password = cursor.getString(6);
            user = new UserModel(id, username, email, school, class_room, phonenumber, password);
        }
        cursor.close();
        db.close();
        return user;
    }
    public ArrayList<UserModel> SelectAllUser() {
        ArrayList<UserModel> returnList = new ArrayList<>();
        String queryString = "SELECT * FROM " + USER_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String username = cursor.getString(1);
                String email = cursor.getString(2);
                String school = cursor.getString(3);
                String class_room = cursor.getString(4);
                String phonenumber = cursor.getString(5);
                String password = cursor.getString(6);
                UserModel user = new UserModel(id, username, email, school, class_room, phonenumber, password);
                returnList.add(user);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return returnList;
    }
    public void Delete (UserModel userModel)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + USER_TABLE + " WHERE " + ID + " = " + userModel.getId();
        Cursor cursor = db.rawQuery(queryString, null);
        cursor.moveToFirst();
    }
}
