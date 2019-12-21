package com.example.roome.user_classes;

import java.util.ArrayList;

public class ApartmentSearcherUser extends User {
    private ArrayList<String> optionalNeighborhoods;

    public ApartmentSearcherUser(String firstName, String lastName, String id, int age) {
        super(firstName, lastName, id, age);
    }


    //------------------------------------------Seters---------------------------------------------
    private void setOptionalNeighborhoods(ArrayList<String> optionalNeighborhoods) {
        this.optionalNeighborhoods = optionalNeighborhoods;
    }
}
