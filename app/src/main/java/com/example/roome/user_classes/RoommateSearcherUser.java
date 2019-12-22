package com.example.roome.user_classes;

import android.media.Image;

import java.util.ArrayList;

public class RoommateSearcherUser extends User {

    private ArrayList<Image> apartmentImages;
    private ArrayList<Image> roommatesImages;
    private String neighborhood;
    private String entryDate;
    private double rent;
    private String additionalInfo;
    private int numberOfRoommates;

    public RoommateSearcherUser(String firstName, String lastName, int age) {
        super(firstName, lastName,  age);
    }
}
