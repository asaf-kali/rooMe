package com.example.roome.Apartment_searcher_tabs_classes;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roome.ChoosingActivity;
import com.example.roome.Data;
import com.example.roome.FirebaseMediate;
import com.example.roome.MyPreferences;
import com.example.roome.R;
import com.example.roome.RecyclerAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MatchesApartmentSearcher extends Fragment {

    private RecyclerView recyclerView;
    //todo:refactor in the end and call it matches
    private int[] images;
    private RecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_matches_apartment_searcher);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.activity_matches_apartment_searcher, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        ArrayList<String> matchedUids = FirebaseMediate.getAptPrefList(ChoosingActivity.YES_TO_HOUSE, MyPreferences.getUserUid(getContext()));
        initMatchedImages(matchedUids);
        if (matchedUids.size() == 0){
            RecyclerView rv = getView().findViewById(R.id.rv_matches_as);
            rv.setBackground(ContextCompat.getDrawable(getContext(),R.drawable.no_matches));
        }
        else {
            recyclerView = getView().findViewById(R.id.rv_matches_as);
            layoutManager = new GridLayoutManager(getContext(), 2);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new RecyclerAdapter(images);
            recyclerView.setAdapter(adapter);
        }
        super.onActivityCreated(savedInstanceState);
    }

    private void initMatchedImages(ArrayList<String> matchedUids) {
        images = new int[matchedUids.size()];
        for (int i=0;i<matchedUids.size();i++){
            images[i]= Data.getImageByUid(matchedUids.get(i));
        }
    }

}
