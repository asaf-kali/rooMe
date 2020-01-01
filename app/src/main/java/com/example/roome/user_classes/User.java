package com.example.roome.user_classes; //todo rename?

import android.media.Image;

public class User {

    public static final int NAME_MAXIMUM_LENGTH = 18;
    public static final int MAXIMUM_LENGTH = 4;
    public static final int MAXIMUM_AGE = 120;
    public static final int MINIMUM_AGE = 6;
    public static final int PHONE_NUMBER_LENGTH = 10 ;


    //--------------------profile info---------------------
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private Image profilePic;
    private String phoneNumber;

    //--------------------filters---------------------
    private boolean kosherImportance;
    private int minAgeRequired;
    private int maxAgeRequired;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = 0;
    }

    //------------------------------------------Getters---------------------------------------------
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public Image getProfilePic() {
        return profilePic;
    }

    public boolean getKosherImportance() {
        return kosherImportance;
    }

    public int getMinAgeRequired() {
        return minAgeRequired;
    }

    public int getMaxAgeRequired() {
        return maxAgeRequired;
    }


    //------------------------------------------Setters---------------------------------------------
    private void setUserName(String firstName) {
        this.firstName = firstName;
    }

    private void setUserAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setProfilePic(Image profilePic) {
        this.profilePic = profilePic;
    }

    public void setKosherImportance(boolean kosherImportance) {
        this.kosherImportance = kosherImportance;
    }

    public void setMinAgeRequired(int minAgeRequired) {
        this.minAgeRequired = minAgeRequired;
    }


    public void setMaxAgeRequired(int maxAgeRequired) {
        this.maxAgeRequired = maxAgeRequired;
    }
}
