package com.example.myapplication;

import com.google.gson.Gson;

public class User {
    public String firstname; // имя
    public String lastname; // фамилия
    public int school; // номер школы

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getSchool() {
        return school;
    }

    public void setSchool(int school) {
        this.school = school;
    }

    public User(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public User() {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public static void main(String[] args) {
        User user = new User();
        user.firstname = "Yuriy";
        user.lastname = "Mironov";
        user.school = 6;
        Gson gson = new Gson();
        String jsonString = gson.toJson(user);
        System.out.println(jsonString);
        User userOnClient = gson.fromJson(jsonString, User.class);
        System.out.println(userOnClient.firstname + " - " + userOnClient.lastname);
    }
}
