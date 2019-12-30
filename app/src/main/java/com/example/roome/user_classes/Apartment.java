package com.example.roome.user_classes;

import android.media.Image;

import java.util.ArrayList;

public class Apartment {


    //--------------------apartment info---------------------

    private ArrayList<Image> apartmentImages;
    private String neighborhood;
    private int entryDate; //todo: make sure to represent it as MM/YYYY
    private int numberOfRoommates;
    private double rent;


    //------------------------------------------Getters---------------------------------------------


    public ArrayList<Image> getApartmentImages() {
        return apartmentImages;
    }


    public String getNeighborhood() {
        return neighborhood;
    }


    public int getEntryDate() {
        return entryDate;
    }

    public int getNumberOfRoommates() {
        return numberOfRoommates;
    }


    public double getRent() {
        return rent;
    }


    //------------------------------------------Setters---------------------------------------------


    public void setApartmentImages(ArrayList<Image> apartmentImages) {
        this.apartmentImages = apartmentImages;
    }


    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }


    public void setEntryDate(int entryDate) {
        this.entryDate = entryDate;
    }


    public void setNumberOfRoommates(int numberOfRoommates) {
        this.numberOfRoommates = numberOfRoommates;
    }


    public void setRent(double rent) {
        this.rent = rent;
    }


}
