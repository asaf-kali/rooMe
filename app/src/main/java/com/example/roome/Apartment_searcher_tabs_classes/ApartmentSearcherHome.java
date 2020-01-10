package com.example.roome.Apartment_searcher_tabs_classes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.roome.ChoosingActivity;
import com.example.roome.FirebaseMediate;
import com.example.roome.MainActivityApartmentSearcher;
import com.example.roome.MyPreferences;
import com.example.roome.R;
import com.example.roome.user_classes.RoommateSearcherUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ApartmentSearcherHome extends Fragment {

    private ArrayList<RoommateSearcherUser> relevantRoommateSearchers;
    private ArrayList<Integer> temp_img;
    private int currentPlaceInList = -1;
    private Button yesButton, maybeButton, noButton;
    private ImageView mainImage;
    private TextView noMoreHousesText;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mFirebaseDatabaseReference;


    @Override
    public void onCreate(Bundle savedInstanceState) {

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mFirebaseDatabaseReference = mFirebaseDatabase.getReference();
        super.onCreate(savedInstanceState);

//        FirebaseMediate.setDataSnapshot(); //todo
//        setContentView(R.layout.activity_apartment_searcher_home);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_apartment_searcher_home, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        currentPlaceInList = -1;

//        retrieveRelevantRoommateSearchers();
        mainImage = getView().findViewById(R.id.iv_home_display);
        yesButton = getView().findViewById(R.id.btn_yes_house);
        maybeButton = getView().findViewById(R.id.btn_maybe_house);
        noButton = getView().findViewById(R.id.btn_no_house);
        noMoreHousesText = getView().findViewById(R.id.tv_no_more_houses);
        fillTempImgArray(); //todo delete
        setClickListeners();
        setFirebaseListeners();
        moveToNextOption();
        super.onActivityCreated(savedInstanceState);

    }

    private void fillTempImgArray() {

        temp_img = new ArrayList<Integer>();
        temp_img.add(R.drawable.home_example1);
        temp_img.add(R.drawable.home_example2);
        temp_img.add(R.drawable.home_example3);
    }

    private void retrieveRelevantRoommateSearchers() {
        relevantRoommateSearchers = FirebaseMediate.getAllRoommateSearcher();
    }

    private void setClickListeners() {
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressedYesToApartment(view);
            }
        });
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressedNoToApartment(view);
            }
        });
        maybeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pressedMaybeToApartment(view);
            }
        });

    }

    private String getUserUid() { //todo delete
        return "delete this function";
    }

    private void setFirebaseListeners() {
        mFirebaseDatabaseReference.child("users").child("RoommateSearcherUser").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                onChildChanged(dataSnapshot, s);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String roommateKey = dataSnapshot.getKey();
                String aUserKey = getUserUid();
                String inWhichList =
                        FirebaseMediate.RoomateInApartmentSearcherPrefsList(aUserKey, roommateKey);
                if (FirebaseMediate.RoomateInApartmentSearcherPrefsList(aUserKey, roommateKey).equals(ChoosingActivity.NOT_IN_LISTS)) {
                    //todo if theres a match - add to the havent seen list of
                    // AptUser
                } else {
                    //todo if there is !!no!! match -> remove roommatekey from apt prefs list
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                String roommateKey = dataSnapshot.getKey();
                String aUserKey = getUserUid();
                String inWhichList =
                        FirebaseMediate.RoomateInApartmentSearcherPrefsList(aUserKey, roommateKey);
                if (!inWhichList.equals(ChoosingActivity.NOT_IN_LISTS)) {
                    //todo remove from the list
                    //todo we need to remember to update the apt lists when
                    // he is offline
                }
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void updateMainImage() {
        //todo add all this
//        RoommateSearcherUser currentRoomateSearcher = relevantRoommateSearchers.get(currentPlaceInList);
//        Apartment cur_apt = currentRoomateSearcher.getApartment();
//        Image mainImg = cur_apt.getMainImage();
        //todo update the imageview (iv_display_house) to mainImg
        mainImage.setImageResource(R.drawable.home_example1); //todo delete this
    }

    public void pressedYesToApartment(View view) {
        //todo user want to apt of the relevantRoommateSearchers[currentPlaceInList] - update!
        moveToNextOption();
    }

    public void pressedNoToApartment(View view) {
        //todo user don't want to apt of the relevantRoommateSearchers[currentPlaceInList] - update!
        moveToNextOption();
    }

    public void pressedMaybeToApartment(View view) {
        //todo user maybe to apt of the relevantRoommateSearchers[currentPlaceInList] - update?
        moveToNextOption();
    }

    private void moveToNextOption() { //todo delete , undelete the function below
        currentPlaceInList++;
        if (currentPlaceInList < temp_img.size()) {
            Toast.makeText(getActivity(), Integer.toString(currentPlaceInList), Toast.LENGTH_SHORT).show();
            mainImage.setImageResource(temp_img.get(currentPlaceInList));
        } else {
            noMoreHouses();
        }
    }
//    private void moveToNextOption() {
//        //todo check boundries of array
//        currentPlaceInList++;
//        if (currentPlaceInList < relevantRoommateSearchers.size()) {
//            Toast.makeText(getActivity(), Integer.toString(currentPlaceInList), Toast.LENGTH_SHORT).show();
//            updateMainImage();
//        } else {
//            noMoreHouses();
//        }
//    }

    private void noMoreHouses() {
        yesButton.setVisibility(View.INVISIBLE);
        noButton.setVisibility(View.INVISIBLE);
        maybeButton.setVisibility(View.INVISIBLE);
        mainImage.setImageResource(R.drawable.no_more_houses_2);
        noMoreHousesText.setVisibility(View.VISIBLE);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            // todo if we want to refresh when page is uploaded - do it here
        }
        super.setUserVisibleHint(isVisibleToUser);
    }
}
