package com.example.roome.user_classes;

import android.media.Image;

import java.util.ArrayList;
import java.util.Date;

public class RoommateSearcherUser extends User {

    //--------------------profile info---------------------
    private ArrayList<Image> roommatesImages; //todo: maybe delete
    private String additionalInfo; //such as bio
    private Apartment apartment;

    //--------------------filters---------------------
    private String genderPreference;


    public RoommateSearcherUser(String firstName, String lastName, int age) {
        super(firstName, lastName);
    }

    //------------------------------------------Getters---------------------------------------------


    public ArrayList<Image> getRoommatesImages() {
        return roommatesImages;
    }


    public String getAdditionalInfo() {
        return additionalInfo;
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


    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }


    public void setGenderPreference(String genderPreference) {
        this.genderPreference = genderPreference;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }


}
