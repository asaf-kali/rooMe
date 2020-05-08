package com.example.roome.Roommate_searcher_tabs_classes;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.roome.R;

/**
 * **** DON'T CHECK! ****
 * this class is for future implementation (we did not implement the roommate searcher side)
 */
public class RoommateSearcherHome extends Fragment {

    public RoommateSearcherHome() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_roommate_searcher_home, container, false);
    }
    public void onActivityCreated(){

    }

}