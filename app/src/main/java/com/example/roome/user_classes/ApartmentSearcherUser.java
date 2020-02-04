package com.example.roome.user_classes;

import java.util.ArrayList;

public class ApartmentSearcherUser extends User {

    //--------------------profile info---------------------
    private String bio; //where the user can write about himself

    //---------------------------------------------filters------------------------------------------
    private ArrayList<Integer> optionalNeighborhoods;
    private int minRent;
    private int maxRent;
    private String earliestEntryDate;
    private String latestEntryDate;
    private int maxNumDesiredRoommates;
    private boolean isSmokingFree;
    private boolean hasNoPets;
    private boolean hasAC;

    private static final int MAX_RENT_VALUE = 4000;
    private static final int DEFAULT_MAX_ROOMMATES = 4;


    public ApartmentSearcherUser(String firstName, String lastName, int age) {
        super(firstName, lastName);
    }

    public ApartmentSearcherUser() {
        super();
    }

    public ApartmentSearcherUser(ApartmentSearcherUser aUser) {
        super(aUser.getFirstName(), aUser.getLastName());
        this.optionalNeighborhoods = aUser.optionalNeighborhoods;
        this.minRent = aUser.minRent;
        this.maxRent = aUser.maxRent;
        this.earliestEntryDate = aUser.earliestEntryDate;
        this.latestEntryDate = aUser.latestEntryDate;
        this.maxNumDesiredRoommates = aUser.maxNumDesiredRoommates;
        this.bio = aUser.bio;
    }

    //------------------------------------------Getters---------------------------------------------

    public String getBio() {
        return bio;
    }

    public ArrayList<Integer> getOptionalNeighborhoods() {
        return optionalNeighborhoods;
    }

    public int getMinRent() {
        return minRent;
    }

    public int getMaxRent() {
        if (maxRent == 0) {
            return MAX_RENT_VALUE;
        }
        return maxRent;
    }

    public String getEarliestEntryDate() {
        return earliestEntryDate;
    }

    public int getMaxNumDesiredRoommates() {
        if (maxNumDesiredRoommates == 0){
            return DEFAULT_MAX_ROOMMATES;
        }
        return maxNumDesiredRoommates;
    }

    public boolean isSmokingFree() { return isSmokingFree;  }

    public boolean isHasNoPets() { return hasNoPets; }

    public boolean isHasAC() { return hasAC; }

    //------------------------------------------Seters---------------------------------------------
    public void setOptionalNeighborhoods(ArrayList<Integer> optionalNeighborhoods) {
        this.optionalNeighborhoods = optionalNeighborhoods;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setMinRent(int minRent) {
        this.minRent = minRent;
    }

    public void setMaxRent(int maxRent) {
        this.maxRent = maxRent;
    }

    public void setEarliestEntryDate(String earliestEntryDate) {
        this.earliestEntryDate = earliestEntryDate;
    }

    public void setMaxNumDesiredRoommates(int maxNumDesiredRoommates) {
        this.maxNumDesiredRoommates = maxNumDesiredRoommates;
    }

    public boolean matches(RoommateSearcherUser roommateSearcherUser) {
        //todo check if there is a match
        return true;   //todo delete
    }

    public void setSmokingFree(boolean smokingFree) { isSmokingFree = smokingFree; }

    public void setHasNoPets(boolean hasNoPets) { this.hasNoPets = hasNoPets; }

    public void setHasAC(boolean hasAC) { this.hasAC = hasAC; }

}
