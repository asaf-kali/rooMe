package com.example.roome;

import com.example.roome.user_classes.ApartmentSearcherUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseMediate {
//
//    private static FirebaseDatabase mFirebaseDatabase;
//    private static DatabaseReference mFirebaseDatabaseReference;

//
//    static void setReference() {
//
//        mFirebaseDatabase = FirebaseDatabase.getInstance();
//        mFirebaseDatabaseReference = mFirebaseDatabase.getReference();
//    }

    static ArrayList<String> getAllApartmentSearcherIds(DataSnapshot dataSnapshotRootUsers) {
        ArrayList<String> allAptSearcherUsersIds = new ArrayList<>();
        DataSnapshot refDSS = dataSnapshotRootUsers.child("ApartmentSearcherUser");

        for (DataSnapshot aptS : refDSS.getChildren()) {
            String userAKey = aptS.getKey();
            allAptSearcherUsersIds.add(userAKey);
        }
        return allAptSearcherUsersIds;
    }

    static ArrayList<ApartmentSearcherUser> getAllApartmentSearcher(DataSnapshot dataSnapshotRootUsers) {
        ArrayList<ApartmentSearcherUser> allAptSearcherUsers = new ArrayList<>();
        DataSnapshot refDSS = dataSnapshotRootUsers.child("ApartmentSearcherUser");

        for (DataSnapshot aptS : refDSS.getChildren()) {
            ApartmentSearcherUser userA = aptS.getValue(ApartmentSearcherUser.class);
            allAptSearcherUsers.add(userA);
        }
        return allAptSearcherUsers;
    }


    static ArrayList<String> getAllRoommateSearcherIds(DataSnapshot dataSnapshotRootUsers) {
        ArrayList<String> allRoommateSearcherUsersIds = new ArrayList<>();
        DataSnapshot refDSS = dataSnapshotRootUsers.child("RoommateSearcherUser");

        for (DataSnapshot roomS : refDSS.getChildren()) {
            String userRKey = roomS.getKey();
            allRoommateSearcherUsersIds.add(userRKey);
        }
        return allRoommateSearcherUsersIds;
    }

    static ArrayList<ApartmentSearcherUser> getAllRoommateSearcher(DataSnapshot dataSnapshotRootUsers) {
        ArrayList<ApartmentSearcherUser> allRoomateSearcherUsers = new ArrayList<>();
        DataSnapshot refDSS = dataSnapshotRootUsers.child("RoommateSearcherUser");

        for (DataSnapshot roomS : refDSS.getChildren()) {
            ApartmentSearcherUser userR = roomS.getValue(ApartmentSearcherUser.class);
            allRoomateSearcherUsers.add(userR);
        }
        return allRoomateSearcherUsers;
    }
}
