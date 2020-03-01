package com.example.roome.user_classes;

import java.util.ArrayList;

/**
 * A class representing a ApartmentSearcherUser - a user who's looking for an apartment with roommates.
 */
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

    private static final int DEFAULT_MAX_RENT_VALUE = 6000;
    private static final int DEFAULT_MAX_ROOMMATES = 4;


    public ApartmentSearcherUser(String firstName, String lastName, int age) {
        super(firstName, lastName);
    }

    /**
     * a constructor for ApartmentSearcherUser.
     */
    public ApartmentSearcherUser() {
        super();
    }

    /**
     * a constructor for ApartmentSearcherUser.
     * @param aUser - an ApartmentSearcherUser.
     */
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

    /**
     * geter for the users bio
     *
     * @return the users bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * geter for the users optionalNeighborhoods
     *
     * @return the users optional neighborhoods list.
     */
    public ArrayList<Integer> getOptionalNeighborhoods() {
        return optionalNeighborhoods;
    }

    /**
     * geter for the users minRent
     *
     * @return the users preference for the minimum rent.
     */
    public int getMinRent() {
        return minRent;
    }

    /**
     * geter for the users maxRent
     *
     * @return the users preference for the maximum rent.
     */
    public int getMaxRent() {
        if (maxRent == 0) {
            return DEFAULT_MAX_RENT_VALUE;
        }
        return maxRent;
    }

    /**
     * geter for the users earliestEntryDate
     *
     * @return the users preference for the earliest entry date (to the apartment).
     */
    public String getEarliestEntryDate() {
        return earliestEntryDate;
    }

    /**
     * geter for the users maxNumDesiredRoommates
     *
     * @return the users preference for the maximum number of desired roommates.
     */
    public int getMaxNumDesiredRoommates() {
        if (maxNumDesiredRoommates == 0) {
            return DEFAULT_MAX_ROOMMATES;
        }
        return maxNumDesiredRoommates;
    }

    /**
     * geter for isSmokingFree
     *
     * @return the users preference for the smoking free apartment.
     */
    public boolean isSmokingFree() {
        return isSmokingFree;
    }

    /**
     * geter for hasNoPets
     *
     * @return the users preference for pets in the apartment.
     */
    public boolean isHasNoPets() {
        return hasNoPets;
    }

    /**
     * geter for isHasAC
     *
     * @return the users preference for air conditioner.
     */
    public boolean isHasAC() {
        return hasAC;
    }

    //------------------------------------------Seters---------------------------------------------

    /**
     * seter for the users optionalNeighborhoods
     *
     * @param optionalNeighborhoods users optional neighborhoods
     */
    public void setOptionalNeighborhoods(ArrayList<Integer> optionalNeighborhoods) {
        this.optionalNeighborhoods = optionalNeighborhoods;
    }

    /**
     * seter for users bio
     *
     * @param bio users bio
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * seter for the users minRent
     *
     * @param minRent - users preference for the minimum rent.
     */
    public void setMinRent(int minRent) {
        this.minRent = minRent;
    }

    /**
     * seter for the users maxRent
     *
     * @param maxRent - users preference for the maximum rent.
     */
    public void setMaxRent(int maxRent) {
        this.maxRent = maxRent;
    }

    /**
     * seter for the users earliestEntryDate
     *
     * @param earliestEntryDate - users preference for the earliest entry date (to the apartment).
     */
    public void setEarliestEntryDate(String earliestEntryDate) {
        this.earliestEntryDate = earliestEntryDate;
    }

    /**
     * seter for the users maxNumDesiredRoommates
     *
     * @param maxNumDesiredRoommates - users preference for the maximum number of desired roommates.
     */
    public void setMaxNumDesiredRoommates(int maxNumDesiredRoommates) {
        this.maxNumDesiredRoommates = maxNumDesiredRoommates;
    }

    /**
     * seter for isSmokingFree
     *
     * @param smokingFree - users preference for the smoking free apartment.
     */
    public void setSmokingFree(boolean smokingFree) {
        isSmokingFree = smokingFree;
    }

    /**
     * seter for hasNoPets
     *
     * @param hasNoPets - users preference for pets in the apartment.
     */
    public void setHasNoPets(boolean hasNoPets) {
        this.hasNoPets = hasNoPets;
    }

    /**
     * seter for isHasAC
     *
     * @param hasAC - users preference for air conditioner.
     */
    public void setHasAC(boolean hasAC) {
        this.hasAC = hasAC;
    }

}
