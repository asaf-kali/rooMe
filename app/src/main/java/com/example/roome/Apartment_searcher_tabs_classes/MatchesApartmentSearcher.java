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
    //todo:dict of roommate's UID:drawable img (int)
    private Map<String, Integer> uidToDrawable = new HashMap<String, Integer>() {
    };
    //todo:refactor in the end and call it matches
    private int[] images;
    private ArrayList<Integer> apartmentImages;
    private ArrayList<String> roommateSearchersUids;
    //    private int[] noMatchesImg = {R.drawable.no_matches};
//    private int[] images = noMatchesImg;
    private boolean hasMatches = false;
    private RecyclerAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        initD();
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
            images[i]=uidToDrawable.get(matchedUids.get(i));
        }
    }

    /**
     * The method sets the given images as the matched apartments to be seen in the Matches fragment
     *
     * @param matches matched photos of apartments
     */
    public void setMatches(int[] matches) {
        layoutManager = new GridLayoutManager(getContext(), 2);
        images = matches;
    }

    private void initDrawablesImg() {
        apartmentImages = new ArrayList<>(Arrays.asList(R.drawable.home_example1, R.drawable.home_example2,
                R.drawable.home_example3, R.drawable.home_example1, R.drawable.home_example2,
                R.drawable.home_example3,R.drawable.home_example1, R.drawable.home_example2,
                R.drawable.home_example3,R.drawable.home_example1));
    }

    private void initRSUids() {
        roommateSearchersUids = new ArrayList<>(Arrays.asList("-Ly_2md3br6ex6H_4iGF", "-Ly_8mKAe1c_aPjqdese", "-Ly_Av7DnafQgO9fA3DL",
                "-Ly_B0kHTiAnAmuwK5ol", "-Ly_F9eAenahqGxLChRo", "-LyjcH9KYsFKSAqPZa_W", "-LykLzlqKS1twRsQmrWR", "-LymPFoE5xAfXLBnHWl4", "-LymRsVQOemjzaCqRISh",
                "-Lyn-ahuNhlaiyfgriP7"));
    }

    private void initD() {
        initDrawablesImg();
        initRSUids();
        for (int i=0;i<roommateSearchersUids.size();i++) {
            uidToDrawable.put(roommateSearchersUids.get(i),apartmentImages.get(i));
        }
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
