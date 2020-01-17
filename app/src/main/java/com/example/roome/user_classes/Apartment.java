package com.example.roome.user_classes;

public class Apartment {


    private static final int MAIN_IMAGE_INDEX = 0;


    //--------------------apartment info---------------------

    private Boolean hasMainImage;
    private String neighborhood;
    private int entryDate; //todo: make sure to represent it as MM/YYYY
    private int numberOfRoommates;
    private double rent;

    public Apartment(){}

    public Apartment(Boolean hasMainImage, String neighborhood, int entryDate, int numberOfRoommates, double rent) {
        this.hasMainImage = hasMainImage;
        this.neighborhood = neighborhood;
        this.entryDate = entryDate;
        this.numberOfRoommates = numberOfRoommates;
        this.rent = rent;
    }


    //------------------------------------------Getters---------------------------------------------

    public boolean getHasMainImage() {return hasMainImage;}

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


    public void setHasMainImage(Boolean flag) { this.hasMainImage = flag; }

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
