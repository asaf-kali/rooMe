package com.example.roome.user_classes; //todo rename?

import android.media.Image;

public class User {

    private String firstName;
    private String lastName;
    private int age;
    private Image profilePic;
    private Boolean lovesAnimals; //todo rename

    public User(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = 0;
    }

    //------------------------------------------Geters---------------------------------------------
     public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    //------------------------------------------Seters---------------------------------------------
    private void setUserName(String firstName) {
        this.firstName = firstName;
    }

    private void setUserAge(int age) {
        this.age = age;
    }
}
