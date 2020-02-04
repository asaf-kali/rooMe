package com.example.roome.user_classes;

import android.media.Image;

import java.util.ArrayList;

public class RoommateSearcherUser extends User {

    //--------------------profile info---------------------
    private ArrayList<Image> roommatesImages; //todo: maybe delete
    private String bio; //such as bio
    private Apartment apartment;

    //--------------------filters---------------------
    private String genderPreference;


    public RoommateSearcherUser(String firstName, String lastName, int age) {
        super(firstName, lastName);
    }

    public RoommateSearcherUser() {
        super();
    }

    //------------------------------------------Getters---------------------------------------------


    public ArrayList<Image> getRoommatesImages() {
        return roommatesImages;
    }


    public String getBio() {
        return bio;
    }


    public String getGenderPreference() {
        return genderPreference;
    }


    public Apartment getApartment() {
        return apartment;
    }


    //------------------------------------------Setters---------------------------------------------


    public void setRoommatesImages(ArrayList<Image> roommatesImages) {
        this.roommatesImages = roommatesImages;
    }


    public void setBio(String bio) {
        this.bio = bio;
    }


    public void setGenderPreference(String genderPreference) {
        this.genderPreference = genderPreference;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }
}
