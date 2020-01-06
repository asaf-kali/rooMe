package com.example.roome;

import androidx.annotation.NonNull;

import com.example.roome.user_classes.ApartmentSearcherUser;
import com.example.roome.user_classes.RoommateSearcherUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class FirebaseMediate {

    private static FirebaseDatabase mFirebaseDatabase;
    private static DatabaseReference mFirebaseDatabaseReference;
    private static DataSnapshot dataSs;
    public final static AtomicBoolean done = new AtomicBoolean(false);

    public static void setDataSnapshot(@NonNull DataSnapshot dataSnapshot) {
        dataSs = dataSnapshot;
    }

    public static void setDataSnapshot() {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseDatabaseReference = mFirebaseDatabase.getReference();
        mFirebaseDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSs = dataSnapshot;
                done.set(true);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public static ArrayList<String> getAllApartmentSearcherIds() {
        DataSnapshot dataSnapshotRootUsers = dataSs.child("users");
        ArrayList<String> allAptSearcherUsersIds = new ArrayList<>();
        DataSnapshot refDSS = dataSnapshotRootUsers.child("ApartmentSearcherUser");

        for (DataSnapshot aptS : refDSS.getChildren()) {
            String userAKey = aptS.getKey();
            allAptSearcherUsersIds.add(userAKey);
        }
        return allAptSearcherUsersIds;
    }

    public static ArrayList<ApartmentSearcherUser> getAllApartmentSearcher() {
        DataSnapshot dataSnapshotRootUsers = dataSs.child("users");
        ArrayList<ApartmentSearcherUser> allAptSearcherUsers = new ArrayList<>();
        DataSnapshot refDSS = dataSnapshotRootUsers.child("ApartmentSearcherUser");

        for (DataSnapshot aptS : refDSS.getChildren()) {
            ApartmentSearcherUser userA = aptS.getValue(ApartmentSearcherUser.class);
            allAptSearcherUsers.add(userA);
        }
        return allAptSearcherUsers;
    }


    public static ArrayList<String> getAllRoommateSearcherIds() {
        DataSnapshot dataSnapshotRootUsers = dataSs.child("users");
        ArrayList<String> allRoommateSearcherUsersIds = new ArrayList<>();
        DataSnapshot refDSS = dataSnapshotRootUsers.child("RoommateSearcherUser");

        for (DataSnapshot roomS : refDSS.getChildren()) {
            String userRKey = roomS.getKey();
            allRoommateSearcherUsersIds.add(userRKey);
        }
        return allRoommateSearcherUsersIds;
    }

    public static ArrayList<RoommateSearcherUser> getAllRoommateSearcher() {
        DataSnapshot dataSnapshotRootUsers = dataSs.child("users");
        ArrayList<RoommateSearcherUser> allRoomateSearcherUsers = new ArrayList<>();
        DataSnapshot refDSS = dataSnapshotRootUsers.child("RoommateSearcherUser");

        for (DataSnapshot roomS : refDSS.getChildren()) {
            RoommateSearcherUser userR = roomS.getValue(RoommateSearcherUser.class);
            allRoomateSearcherUsers.add(userR);
        }
        return allRoomateSearcherUsers;
    }


    /**
     * @param uid - uid of apartment searcher user
     * @return the apartment searcher user object
     */
    public static ApartmentSearcherUser getApartmentSearcherUserByUid(String uid) {
        DataSnapshot temp = dataSs.child("ApartmentSearcherUser").child(uid);
        return temp.getValue(ApartmentSearcherUser.class);
    }

    public static RoommateSearcherUser getRoommateSearcherUserByUid(String uid) {
        DataSnapshot temp = dataSs.child("users").child("RoommateSearcherUser").child(uid);
        return temp.getValue(RoommateSearcherUser.class);
    }

    public static ArrayList<String> getLikeUsersIdR(DataSnapshot dataSnapshotRootSpecificAptUser) {
        GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {
        };
        ArrayList<String> allRoommateSearcherUsersIds;
        DataSnapshot refDSS = dataSnapshotRootSpecificAptUser.child("Like");
        allRoommateSearcherUsersIds = refDSS.getValue(t);
        return allRoommateSearcherUsersIds;
    }


    public static ArrayList<String> getUnlikeUsersIdR(DataSnapshot dataSnapshotRootSpecificAptUser) {
        GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {
        };

        ArrayList<String> allRoommateSearcherUsersIds;
        DataSnapshot refDSS = dataSnapshotRootSpecificAptUser.child("Unlike");
        allRoommateSearcherUsersIds = refDSS.getValue(t);
        return allRoommateSearcherUsersIds;
    }


    public static ArrayList<String> getMaybeUsersIdR(DataSnapshot dataSnapshotRootSpecificAptUser) {
        GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {
        };
        ArrayList<String> allRoommateSearcherUsersIds;
        DataSnapshot refDSS = dataSnapshotRootSpecificAptUser.child("Maybe");
        allRoommateSearcherUsersIds = refDSS.getValue(t);
        return allRoommateSearcherUsersIds;
    }
}
