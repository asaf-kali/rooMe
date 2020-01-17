package com.example.roome.Apartment_searcher_tabs_classes;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roome.R;
import com.example.roome.RecyclerAdapter;

public class MatchesApartmentSearcher extends Fragment {

    private RecyclerView recyclerView;

    //todo:refactor in the end and call it matches
    private int[] images = {R.drawable.home_example1, R.drawable.home_example2,
            R.drawable.home_example3,R.drawable.home_example1, R.drawable.home_example2,
            R.drawable.home_example3};
//    private int[] noMatchesImg = {R.drawable.no_matches};
//    private int[] images = noMatchesImg;
    private boolean hasMatches = false;
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
        recyclerView = getView().findViewById(R.id.rv_matches_as);
        layoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(images);
        recyclerView.setAdapter(adapter);
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * The method sets the given images as the matched apartments to be seen in the Matches fragment
     * @param matches matched photos of apartments
     */
    public void setMatches(int[] matches){
        layoutManager = new GridLayoutManager(getContext(), 2);
        images = matches;
    }

//    public void setNoMatchesimg(){
//        layoutManager = new GridLayoutManager(getContext(), 1);
//        images = noMatchesImg;
//    }
//
//    public void setHasMatches(boolean bool){
//        hasMatches = bool;
//    }
//
//    public boolean getHasMatches(){
//        return hasMatches;
//    }

}
