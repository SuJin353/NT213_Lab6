package com.example.lab6;

public class UserModel {
    String username, email, school, class_room, phone_number, password;
    Integer id;

    public UserModel() {
    }

    public UserModel(Integer id, String username,  String email, String school, String class_room, String phone_number, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.school = school;
        this.class_room = class_room;
        this.phone_number = phone_number;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getClass_room() {
        return class_room;
    }

    public void setClass_room(String class_room) {
        this.class_room = class_room;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
