package com.example.roome.user_classes; //todo rename?

public class User {

    private String id;
    private String firstName;
    private String lastName;
    private int age;

    private Boolean isApartmentSearcher;
    private Boolean isRoome;
    private Boolean lovesAnimals; //todo rename

    public User(String firstName, String lastName, String id, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.age = age;
    }

    //------------------------------------------Geters---------------------------------------------
    public String getId() {
        return id;
    }

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
