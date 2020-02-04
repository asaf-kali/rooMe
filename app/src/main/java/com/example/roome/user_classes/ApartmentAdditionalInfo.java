package com.example.roome.user_classes;

import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.roome.R;

public class ApartmentAdditionalInfo extends DialogFragment {
    TextView updateInfoTV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_apartment_additional_info, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        updateInfoTV = getView().findViewById(R.id.tv_save_info_btn);
        updateInfoTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        super.onActivityCreated(savedInstanceState);

    }
    }
