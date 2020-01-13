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

    private static FirebaseDatabase firebaseDatabase;
    private static DatabaseReference firebaseDatabaseReference;
    private static FirebaseAuth firebaseAuth;
    private static FirebaseUser firebaseUser;
    private static DataSnapshot dataSs;

    public final static AtomicBoolean fmDone = new AtomicBoolean(false);

    public static void setDataSnapshot(@NonNull DataSnapshot dataSnapshot) {
        dataSs = dataSnapshot;
    }

    public static void setDataSnapshot() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabaseReference = firebaseDatabase.getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSs = dataSnapshot;
                fmDone.set(true);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        firebaseDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataSs = dataSnapshot;
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

//    public static ApartmentSearcherUser getCurrentApartmentSearcherUser() {
//        if (firebaseUser == null) {
//            return null;
//        }
//        return dataSs.child("users").child("ApartmentSearcherUser").child(firebaseUser.getUid()).getValue(ApartmentSearcherUser.class);
//    }

    public static RoommateSearcherUser getRoommateSearcherUserByUid(String uid) {
        DataSnapshot temp = dataSs.child("users").child("RoommateSearcherUser").child(uid);
        return temp.getValue(RoommateSearcherUser.class);
    }


    public static ArrayList<String> getAptPrefList(String list, String aptKey) {
        GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {
        };

        ArrayList<String> allRoommateSearcherUsersIds;
        DataSnapshot refDSS =
                dataSs.child("preferences").child("ApartmentSearcherUser").child(aptKey).child(list);
        allRoommateSearcherUsersIds = refDSS.getValue(t);
        if (allRoommateSearcherUsersIds == null) {
            return new ArrayList<String>();

        }
        return allRoommateSearcherUsersIds;
    }

    public static ArrayList<String> getRoommatePrefList(String list,
                                                        String roommateKey) {
        GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {
        };

        ArrayList<String> allAptSearcherUsersIds;
        DataSnapshot refDSS =
                dataSs.child("preferences").child("RoommateSearcherUser").child(roommateKey).child(list);
        allAptSearcherUsersIds = refDSS.getValue(t);
        if (allAptSearcherUsersIds == null){
            return new ArrayList<String>();
        }
        return allAptSearcherUsersIds;
    }

    public static void setAptPrefList(String list, String aptKey
            , ArrayList<String> relevantUids) {
        firebaseDatabaseReference.child("preferences").child(
                "ApartmentSearcherUser").child(aptKey).child(list).setValue(relevantUids);
    }

    public static void setRoommatePrefList(String list, String roommateKey
            , ArrayList<String> relevantUids) {
        firebaseDatabaseReference.child("preferences").child(
                "RoommateSearcherUser").child(roommateKey).child(list).setValue(relevantUids);
    }

//    public static ArrayList<String> getLikeUsersIdR(String aptKey) {
//        GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {
//        };
//        ArrayList<String> allRoommateSearcherUsersIds;
//        DataSnapshot refDSS =
//                dataSs.child("preferences").child("ApartmentSearcherUser").child(aptKey).child(ChoosingActivity.YES_TO_HOUSE);
//        allRoommateSearcherUsersIds = refDSS.getValue(t);
//        return allRoommateSearcherUsersIds;
//    }
//
//    public static ArrayList<String> getUnlikeUsersIdR(String aptKey) {
//        GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {
//        };
//
//        ArrayList<String> allRoommateSearcherUsersIds;
//        DataSnapshot refDSS =
//                dataSs.child("preferences").child("ApartmentSearcherUser").child(aptKey).child(ChoosingActivity.NO_TO_HOUSE);
//        allRoommateSearcherUsersIds = refDSS.getValue(t);
//        return allRoommateSearcherUsersIds;
//    }
//
//
//    public static ArrayList<String> getMaybeUsersIdR(String aptKey) {
//        GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {
//        };
//        ArrayList<String> allRoommateSearcherUsersIds;
//        DataSnapshot refDSS =
//                dataSs.child("preferences").child("ApartmentSearcherUser").child(aptKey).child(ChoosingActivity.MAYBE_TO_HOUSE);
//        allRoommateSearcherUsersIds = refDSS.getValue(t);
//        return allRoommateSearcherUsersIds;
//    }
//
//    public static ArrayList<String> getHaventSeenUsersIdR(String aptKey) {
//        GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {
//        };
//        ArrayList<String> allRoommateSearcherUsersIds;
//        DataSnapshot refDSS =
//                dataSs.child("preferences").child("ApartmentSearcherUser").child(aptKey).child(ChoosingActivity.NOT_SEEN);
//        allRoommateSearcherUsersIds = refDSS.getValue(t);
//        return allRoommateSearcherUsersIds;
//    }

    public static String RoomateInApartmentSearcherPrefsList(String aptKey,
                                                             String roommateKey) {
        ArrayList<String> likedArr, maybeArr, unlikedArr, notSeenArr;
        likedArr = FirebaseMediate.getAptPrefList(ChoosingActivity.YES_TO_HOUSE,
                aptKey);
        maybeArr = FirebaseMediate.getAptPrefList(ChoosingActivity.MAYBE_TO_HOUSE,
                aptKey);
        unlikedArr = FirebaseMediate.getAptPrefList(ChoosingActivity.NO_TO_HOUSE,
                aptKey);
        notSeenArr = FirebaseMediate.getAptPrefList(ChoosingActivity.NOT_SEEN,
                aptKey);
        if (likedArr.contains(roommateKey)) {
            return ChoosingActivity.YES_TO_HOUSE;
        }
        if (maybeArr.contains(roommateKey)) {
            return ChoosingActivity.MAYBE_TO_HOUSE;
        }
        if (unlikedArr.contains(roommateKey)) {
            return ChoosingActivity.NO_TO_HOUSE;
        }
        if (notSeenArr.contains(roommateKey)) {
            return ChoosingActivity.NOT_SEEN;
        }
        return ChoosingActivity.NOT_IN_LISTS;
    }

    public static void removeFromAptPrefList(String list, String aptUid,
                                             String roommateUid) {

        ArrayList<String> prefListRoomates = getAptPrefList(list, aptUid);
        prefListRoomates.remove(roommateUid);
        setAptPrefList(list, aptUid, prefListRoomates);
    }

    public static void removeFromRoommatePrefList(String list,
                                                  String roommateUid,
                                                  String aptUid) {

        ArrayList<String> prefListApt = getRoommatePrefList(list, roommateUid);
        prefListApt.remove(aptUid);
        setRoommatePrefList(list, roommateUid, prefListApt);
    }

    public static void addToAptPrefList(String list, String aptUid,
                                        String roomateUid) {
        ArrayList<String> allRelevantRoomatesUid = getAptPrefList(list, aptUid);
        allRelevantRoomatesUid.add(roomateUid);
        setAptPrefList(list, aptUid, allRelevantRoomatesUid);
    }

    public static void addToRoommatePrefList(String list, String roommateUid,
                                             String aptUid) {
        ArrayList<String> allRelevantAptUid = getRoommatePrefList(list,
                roommateUid);
        allRelevantAptUid.add(aptUid);
        setRoommatePrefList(list, roommateUid, allRelevantAptUid);
    }


    public static void deleteAllAptUsers() //todo delete thid at the end
    {
        DataSnapshot ds = dataSs.child("users").child("ApartmentSearcherUser");
        for (DataSnapshot child : ds.getChildren()) {
            String key = child.getKey();
            firebaseDatabaseReference.child("users").child(
                    "ApartmentSearcherUser").child(key).removeValue();
        }
        DataSnapshot ds2 = dataSs.child("preferences").child(
                "ApartmentSearcherUser");
        for (DataSnapshot child : ds.getChildren()) {
            String key = child.getKey();
            firebaseDatabaseReference.child("preferences").child(
                    "ApartmentSearcherUser").child(key).removeValue();
        }

    }
}
