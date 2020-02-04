package com.example.roome.user_classes;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.roome.Apartment_searcher_tabs_classes.ApartmentSearcherHome;
import com.example.roome.FirebaseMediate;
import com.example.roome.MyPreferences;
import com.example.roome.R;

public class ApartmentAdditionalInfo extends DialogFragment {
    TextView updateInfoTV;
    TextView bio;
    RoommateSearcherUser roommateSearcherUser;
    public void ApartmentAdditionalInfo(){

    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = getArguments();
        roommateSearcherUser =
                FirebaseMediate.getRoommateSearcherUserByUid(bundle.getString("roomateId"));
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
        bio = getView().findViewById(R.id.tv_bio);
        bio.setText(roommateSearcherUser.getBio());
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            Window window = getDialog().getWindow();
            WindowManager.LayoutParams params = window.getAttributes();
            params.width = 400;
            params.height = 400;
            window.setAttributes(params);
        }
    }
}
