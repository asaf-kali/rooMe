package com.example.roome;

import androidx.annotation.NonNull;

import com.example.roome.user_classes.ApartmentSearcherUser;
import com.example.roome.user_classes.RoommateSearcherUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private static FirebaseAuth mFirebaseAuth;
    private static FirebaseUser mFirebaseUser;
    private static DataSnapshot dataSs;

    public final static AtomicBoolean fmDone = new AtomicBoolean(false);

    public static void setDataSnapshot(@NonNull DataSnapshot dataSnapshot) {
        dataSs = dataSnapshot;
    }

    public static void setDataSnapshot() {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseDatabaseReference = mFirebaseDatabase.getReference();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSs = dataSnapshot;
                mFirebaseUser = mFirebaseAuth.getCurrentUser();
                fmDone.set(true);
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
        DataSnapshot temp = dataSs.child("users").child("ApartmentSearcherUser").child(uid);
        return temp.getValue(ApartmentSearcherUser.class);
    }

    public static ApartmentSearcherUser getCurrentApartmentSearcherUser() {
        if (mFirebaseUser == null) {
            return null;
        }
        return dataSs.child("users").child("ApartmentSearcherUser").child(mFirebaseUser.getUid()).getValue(ApartmentSearcherUser.class);
    }

    public static RoommateSearcherUser getRoommateSearcherUserByUid(String uid) {
        DataSnapshot temp = dataSs.child("users").child("RoommateSearcherUser").child(uid);
        return temp.getValue(RoommateSearcherUser.class);
    }

    public static ArrayList<String> getLikeUsersIdR(String aptKey) {
        GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {
        };
        ArrayList<String> allRoommateSearcherUsersIds;
        DataSnapshot refDSS =
                dataSs.child("preferences").child("ApartmentSearcherUser").child(aptKey).child(ChoosingActivity.YES_TO_HOUSE);
        allRoommateSearcherUsersIds = refDSS.getValue(t);
        return allRoommateSearcherUsersIds;
    }


    public static ArrayList<String> getUnlikeUsersIdR(String aptKey) {
        GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {
        };

        ArrayList<String> allRoommateSearcherUsersIds;
        DataSnapshot refDSS =
                dataSs.child("preferences").child("ApartmentSearcherUser").child(aptKey).child(ChoosingActivity.NO_TO_HOUSE);
        allRoommateSearcherUsersIds = refDSS.getValue(t);
        return allRoommateSearcherUsersIds;
    }


    public static ArrayList<String> getMaybeUsersIdR(String aptKey) {
        GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {
        };
        ArrayList<String> allRoommateSearcherUsersIds;
        DataSnapshot refDSS =
                dataSs.child("preferences").child("ApartmentSearcherUser").child(aptKey).child(ChoosingActivity.MAYBE_TO_HOUSE);
        allRoommateSearcherUsersIds = refDSS.getValue(t);
        return allRoommateSearcherUsersIds;
    }

    public static ArrayList<String> getHaventSeenUsersIdR(String aptKey) {
        GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {
        };
        ArrayList<String> allRoommateSearcherUsersIds;
        DataSnapshot refDSS =
                dataSs.child("preferences").child("ApartmentSearcherUser").child(aptKey).child(ChoosingActivity.NOT_SEEN);
        allRoommateSearcherUsersIds = refDSS.getValue(t);
        return allRoommateSearcherUsersIds;
    }
    public static String RoomateInApartmentSearcherPrefsList(String aptKey,
                                                            String roommateKey)
    {
        if (FirebaseMediate.getLikeUsersIdR(aptKey).contains(roommateKey)){
            return ChoosingActivity.YES_TO_HOUSE;
        }
        if (FirebaseMediate.getMaybeUsersIdR(aptKey).contains(roommateKey)){
            return ChoosingActivity.MAYBE_TO_HOUSE;
        }
        if (FirebaseMediate.getUnlikeUsersIdR(aptKey).contains(roommateKey)){
            return ChoosingActivity.NO_TO_HOUSE;
        }
        if (FirebaseMediate.getHaventSeenUsersIdR(aptKey).contains(roommateKey)){
            return ChoosingActivity.NOT_SEEN;
        }
        return ChoosingActivity.NOT_IN_LISTS;
    }
}
