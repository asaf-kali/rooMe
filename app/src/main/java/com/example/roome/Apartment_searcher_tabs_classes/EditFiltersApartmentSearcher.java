package com.example.roome.Apartment_searcher_tabs_classes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.roome.R;
import com.yahoo.mobile.client.android.util.rangeseekbar.RangeSeekBar;


public class EditFiltersApartmentSearcher extends Fragment {

    private RangeSeekBar costBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_filter_apartment_searcher, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        costBar = getView().findViewById(R.id.rsb_costBar);
        costBar.setRangeValues(1000,4000);

        costBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Object minValue, Object maxValue) {
                Number minVal = bar.getSelectedMinValue();
                Number maxVal = bar.getSelectedMaxValue();
                int min = (int)minVal;
                int max = (int)maxVal;

                //todo:send these vals as the new ones chosen
            }

        });
        super.onActivityCreated(savedInstanceState);
    }

}
