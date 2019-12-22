package com.example.roome.user_classes;

import java.util.ArrayList;

public class ApartmentSearcherUser extends User {
    private ArrayList<String> optionalNeighborhoods;

    public ApartmentSearcherUser(String firstName, String lastName, int age) {
        super(firstName, lastName, age);
    }


    //------------------------------------------Seters---------------------------------------------
    private void setOptionalNeighborhoods(ArrayList<String> optionalNeighborhoods) {
        this.optionalNeighborhoods = optionalNeighborhoods;
    }
}
