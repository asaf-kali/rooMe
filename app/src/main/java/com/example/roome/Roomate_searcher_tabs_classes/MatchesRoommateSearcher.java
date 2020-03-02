package com.example.roome.Roomate_searcher_tabs_classes;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.roome.R;

/**
 *  please don't check this class.
 */
public class MatchesRoommateSearcher extends Fragment {

    public MatchesRoommateSearcher() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Intent i = new Intent(getActivity(), EditProfileApartmentSearcher.class);
//        startActivity(i);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_matches_roommate_searcher, container, false);
    }

}