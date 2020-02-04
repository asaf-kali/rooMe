package com.example.roome.Apartment_searcher_tabs_classes;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.roome.ChoosingActivity;
import com.example.roome.Data;
import com.example.roome.FirebaseMediate;
import com.example.roome.MyPreferences;
import com.example.roome.R;
import com.example.roome.user_classes.ApartmentAdditionalInfo;
import com.example.roome.user_classes.RoommateSearcherUser;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.lorenzos.flingswipe.*;

import java.util.ArrayList;

public class ApartmentSearcherHome extends Fragment {

    private ArrayList<String> relevantRoommateSearchersIds;
    private ArrayList<Integer> temp_img;
    private int currentPlaceInList = 0;
    private ImageView yesButton, maybeButton, noButton;
    private ImageView mainImage;
    private ImageView trashCanImage;
    private TextView noMoreHousesText;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mFirebaseDatabaseReference;
    private TextView locationText;
    private TextView peopleText;
    private TextView priceText;
    private ImageView editFiltersImage;
    private FrameLayout cardImg;

    private EditFiltersApartmentSearcher editFiltersDialog;
    private ApartmentAdditionalInfo additionalInfoDialog;


    //for swipe
    public static MyAppAdapter myAppAdapter;
    public static ViewHolder viewHolder;
    private SwipeFlingAdapterView flingContainer;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseDatabaseReference = mFirebaseDatabase.getReference();
        boolean isFirstTime = MyPreferences.isFirstTime(getContext());
        if (isFirstTime) {
            MyPreferences.setIsFirstTimeToFalse(getContext());
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_apartment_searcher_home, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        mainImage = getView().findViewById(R.id.cardImage);
        yesButton = getView().findViewById(R.id.btn_yes_house);
        maybeButton = getView().findViewById(R.id.btn_maybe_house);
        trashCanImage = getView().findViewById(R.id.iv_trash_can);
        noButton = getView().findViewById(R.id.btn_no_house);
        noMoreHousesText = getView().findViewById(R.id.tv_no_more_houses);
        editFiltersImage = getView().findViewById(R.id.iv_edit_filters);
        cardImg = getView().findViewById(R.id.fl_card);
        editFiltersDialog = new EditFiltersApartmentSearcher();
        additionalInfoDialog = new ApartmentAdditionalInfo();


        setClickListeners();
        setFirebaseListeners();
        retrieveRelevantRoommateSearchers();
        swipeOnCreate();
        moreHouses();
        super.onActivityCreated(savedInstanceState);
    }

    private void swipeOnCreate() {
        flingContainer =
                (SwipeFlingAdapterView) getView().findViewById(R.id.frame);
        myAppAdapter = new MyAppAdapter(relevantRoommateSearchersIds, getContext());
        flingContainer.setAdapter(myAppAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {

            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                pressedNoToApartment();
                myAppAdapter.notifyDataSetChanged();
                relevantRoommateSearchersIds.remove(0);
                temp_img.remove(0);
//                fillTempImgArray();
//                if (relevantRoommateSearchersIds.size() == 0) {
//                    Glide.with(getContext()).load(R.drawable.no_more_houses_2).into(viewHolder.cardImage);
//                }
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                pressedYesToApartment();
                relevantRoommateSearchersIds.remove(0);
                temp_img.remove(0);
//                fillTempImgArray();
//                if (relevantRoommateSearchersIds.size() == 0) {
//                    Glide.with(getContext()).load(R.drawable.no_more_houses_2).into(viewHolder.cardImage);
//                }
                myAppAdapter.notifyDataSetChanged();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.fl_background).setAlpha(0);
                view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                View view = flingContainer.getSelectedView();
                view.findViewById(R.id.fl_background).setAlpha(0);
                myAppAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "Clicked", Toast.LENGTH_SHORT).show();
                additionalInfoDialog.show(getFragmentManager(),
                        "additionalInfo");

            }
        });
    }


    private void fillTempImgArray() {
        temp_img = new ArrayList<>();
        for (String uid : relevantRoommateSearchersIds) {
            temp_img.add(Data.getImageByUid(uid));
        }
    }

    private void retrieveRelevantRoommateSearchers() {
        relevantRoommateSearchersIds =
                FirebaseMediate.getAptPrefList(ChoosingActivity.NOT_SEEN,
                        MyPreferences.getUserUid(getContext()));
        ArrayList<String> allMaybeUid = FirebaseMediate.getAptPrefList(ChoosingActivity.MAYBE_TO_HOUSE,
                MyPreferences.getUserUid(getContext()));
        relevantRoommateSearchersIds.addAll(allMaybeUid);
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

        trashCanImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        editFiltersImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editFiltersDialog.show(getFragmentManager(), "EditFilters");
            }
        });
//        cardImg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

    }

    private String getUserUid() {
        return MyPreferences.getUserUid(getContext());
    }

    private boolean isMatch(String aptUid, String roommateUid) {
        //todo this function returns if theres a match between users
        return true; //todo delete
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
                        FirebaseMediate.RoommateInApartmentSearcherPrefsList(aUserKey, roommateKey);
                if (inWhichList.equals(ChoosingActivity.NOT_IN_LISTS)) {
                    if (isMatch(aUserKey, roommateKey)) {
                        //if theres a match - add to the havent seen list of
                        // AptUser
                        FirebaseMediate.addToAptPrefList(ChoosingActivity.NOT_SEEN, aUserKey, roommateKey);
                    }
                } else {
                    if (!isMatch(aUserKey, roommateKey)) {
                        //if there is !!no!! match -> remove roommatekey from apt prefs list
                        FirebaseMediate.removeFromAptPrefList(inWhichList,
                                aUserKey, roommateKey);
                    }
                }
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                String roommateKey = dataSnapshot.getKey();
                String aUserKey = getUserUid();
                String inWhichList =
                        FirebaseMediate.RoommateInApartmentSearcherPrefsList(aUserKey, roommateKey);
                if (!inWhichList.equals(ChoosingActivity.NOT_IN_LISTS)) {
                    //remove from the list
                    FirebaseMediate.removeFromAptPrefList(inWhichList,
                            aUserKey, roommateKey);
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
        mFirebaseDatabaseReference.child("preferences").child(
                "ApartmentSearcherUser").child(getUserUid()).child(ChoosingActivity.NOT_SEEN).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {
                };
                relevantRoommateSearchersIds = dataSnapshot.getValue(t);
                if (relevantRoommateSearchersIds == null) {
                    relevantRoommateSearchersIds = new ArrayList<>();
                }
                myAppAdapter.setParkingList(relevantRoommateSearchersIds);
                refreshList();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void refreshList() {
        moreHouses();
    }

    private void removeFromHaveNotSeen(String roommateUid) {
        FirebaseMediate.removeFromAptPrefList(ChoosingActivity.NOT_SEEN,
                getUserUid(), roommateUid);
    }

    public void pressedYesToApartment(View view) {
        pressedYesToApartment();
    }

    public void pressedYesToApartment() {
        String likedRoomateId =
                relevantRoommateSearchersIds.get(0);
        String myUid = getUserUid();
        removeFromHaveNotSeen(likedRoomateId);
        FirebaseMediate.addToAptPrefList(ChoosingActivity.YES_TO_HOUSE,
                myUid, likedRoomateId);
        FirebaseMediate.addToRoommatePrefList(ChoosingActivity.NOT_SEEN,
                likedRoomateId, myUid);
//        moveToNextOption();
    }

    public void pressedNoToApartment(View view) {
        pressedNoToApartment();
    }

    public void pressedNoToApartment() {

        String unlikedRoommateId =
                relevantRoommateSearchersIds.get(0);
        removeFromHaveNotSeen(unlikedRoommateId);
        FirebaseMediate.addToAptPrefList(ChoosingActivity.NO_TO_HOUSE,
                getUserUid(), unlikedRoommateId);
//        moveToNextOption();
    }

    public void pressedMaybeToApartment(View view) {
        pressedMaybeToApartment();
    }

    public void pressedMaybeToApartment() {

        String maybeRoommateId =
                relevantRoommateSearchersIds.get(0);
        removeFromHaveNotSeen(maybeRoommateId);
        FirebaseMediate.addToAptPrefList(ChoosingActivity.MAYBE_TO_HOUSE,
                getUserUid(), maybeRoommateId);
//        moveToNextOption();
    }


    private void moreHouses() {
//
//        yesButton.setVisibility(View.VISIBLE);
//        noButton.setVisibility(View.VISIBLE);
//        maybeButton.setVisibility(View.VISIBLE); //todo uncomment this

        //-----------------------------------------
        yesButton.setVisibility(View.INVISIBLE);
        noButton.setVisibility(View.INVISIBLE);
        maybeButton.setVisibility(View.INVISIBLE);
        //---------------------------------------//todo delete this

        noMoreHousesText.setVisibility(View.INVISIBLE);
        currentPlaceInList = 0;
        fillTempImgArray(); //todo delete
//        moveToNextOption();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser) {
            // todo if we want to refresh when page is uploaded - do it here
        }
        super.setUserVisibleHint(isVisibleToUser);
    }


    //for swipe
    public static class ViewHolder {
        public static FrameLayout background;
        public LinearLayout basicInfo;
        public ImageView cardImage;
    }

    public class MyAppAdapter extends BaseAdapter {


        public ArrayList<String> parkingList;
        public Context context;

        private MyAppAdapter(ArrayList<String> apps, Context context) {
            this.parkingList = apps;
            this.context = context;
        }


        public void setParkingList(ArrayList<String> parkingList) {
            this.parkingList = parkingList;
        }

        @Override
        public int getCount() {
            return parkingList.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View rowView = convertView;


            if (rowView == null) {

                LayoutInflater inflater = getLayoutInflater();
                rowView = inflater.inflate(R.layout.card_item, parent, false);
                // configure view holder
                viewHolder = new ViewHolder();
                viewHolder.basicInfo =
                        (LinearLayout) rowView.findViewById(R.id.basic_info_ll);
                TextView tv = rowView.findViewById(R.id.tv_location);
                RoommateSearcherUser currentRoommateSearcher =
                        FirebaseMediate.getRoommateSearcherUserByUid(relevantRoommateSearchersIds.get(position));
                peopleText = rowView.findViewById(R.id.tv_people);
                locationText = rowView.findViewById(R.id.tv_location);
                priceText = rowView.findViewById(R.id.tv_price);
                peopleText.setText(Integer.toString(currentRoommateSearcher.getApartment().getNumberOfRoommates()));
                locationText.setText(currentRoommateSearcher.getApartment().getNeighborhood());
                priceText.setText(Integer.toString((int) (currentRoommateSearcher.getApartment().getRent())));
                viewHolder.background =
                        (FrameLayout) rowView.findViewById(R.id.fl_background);
                viewHolder.cardImage = (ImageView) rowView.findViewById(R.id.cardImage);
                rowView.setTag(viewHolder);

            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
//            viewHolder.basicInfo.setText("UserId : ".concat(parkingList.get(position)));
            Glide.with(getContext()).load(temp_img.get(position)).into(viewHolder.cardImage);

            return rowView;
        }
    }

}

