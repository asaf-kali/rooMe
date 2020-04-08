package com.example.roome.user_classes;

/**
 * A class representing a User in the app.
 */
public class User {

    /* constants */
    public static final int NAME_MAXIMUM_LENGTH = 18;
    public static final int MAXIMUM_AGE_LENGTH = 2;
    public static final int MAXIMUM_AGE = 70;
    public static final int MINIMUM_AGE = 14;
    public static final int PHONE_NUMBER_LENGTH = 10;
    public static final int DEFAULT_MAXIMUM_AGE = 99;
    public static final int DEFAULT_MINIMUM_AGE = 0;


    /* profile info */
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String phoneNumber;
    private String info;

    /* filters */
    private boolean kosherImportance;
    private int minAgeRequired;
    private int maxAgeRequired;
    private boolean hasProfilePic = false;

    /**
     * a constructor for User.
     *
     * @param firstName - users first name.
     * @param lastName  - users last name.
     */
    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = 0;
    }

    /**
     * a constructor for User.
     */
    public User() {
    }

    //------------------------------------------Getters---------------------------------------------

    /**
     * getter for users firstName
     *
     * @return users first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * getter for users lastName
     *
     * @return users last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * getter for users age
     *
     * @return users age
     */
    public int getAge() {
        return age;
    }

    /**
     * getter for users phoneNumber
     *
     * @return users phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * getter for users gender
     *
     * @return users gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * getter for users kosherImportance
     *
     * @return users kosher importance
     */
    public boolean getKosherImportance() {
        return kosherImportance;
    }

    /**
     * getter for users minAgeRequired.
     *
     * @return users preference for minimum roommate age required.
     */
    public int getMinAgeRequired() {
        return minAgeRequired;
    }

    /**
     * getter for users maxAgeRequired.
     *
     * @return users preference for maximum roommate age required.
     */
    public int getMaxAgeRequired() {
        if (maxAgeRequired == 0) {
            return DEFAULT_MAXIMUM_AGE;
        }
        return maxAgeRequired;
    }

    /**
     * getter for users hasProfilePic
     *
     * @return true if user has profile pic, otherwise false.
     */
    public boolean getHasProfilePic() {
        return hasProfilePic;
    }

    /**
     * getter for users info.
     *
     * @return users info.
     */
    public String getInfo() {
        return info;
    }

    //------------------------------------------Setters---------------------------------------------

    /**
     * setter for user first name.
     *
     * @param firstName user first name.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * setter for user last name.
     *
     * @param lastName user first name.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * setter for user age.
     *
     * @param age - user age.
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * setter for user phoneNumber.
     *
     * @param phoneNumber - user phone number.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * setter for user gender.
     *
     * @param gender - user gender.
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * setter for user kosherImportance.
     *
     * @param kosherImportance - user kosher importance.
     */
    public void setKosherImportance(boolean kosherImportance) {
        this.kosherImportance = kosherImportance;
    }

    /**
     * setter for user minAgeRequired.
     *
     * @param minAgeRequired - user preference for minimum roommate age.
     */
    public void setMinAgeRequired(int minAgeRequired) {
        this.minAgeRequired = minAgeRequired;
    }

    /**
     * setter for user maxAgeRequired.
     *
     * @param maxAgeRequired - user preference for maximum roommate age.
     */
    public void setMaxAgeRequired(int maxAgeRequired) {
        this.maxAgeRequired = maxAgeRequired;
    }

    /**
     * setter for user hasProfilePic.
     *
     * @param hasProfilePic - true if user has profile pic, otherwise false.
     */
    public void setHasProfilePic(boolean hasProfilePic) {
        this.hasProfilePic = hasProfilePic;
    }

    /**
     * setter for users info.
     *
     * @param info - users info.
     */
    public void setInfo(String info) {
        this.info = info;
    }
}
