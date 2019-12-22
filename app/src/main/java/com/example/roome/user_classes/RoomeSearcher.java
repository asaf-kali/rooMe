package com.example.roome.user_classes;

import android.media.Image;

import java.util.ArrayList;

public class RoomeSearcher extends User {

    private ArrayList<Image> apartmentImages;
    private int numberOfRoomes;

    public RoomeSearcher(String firstName, String lastName, String id, int age) {
        super(firstName, lastName, id, age);
    }
}
