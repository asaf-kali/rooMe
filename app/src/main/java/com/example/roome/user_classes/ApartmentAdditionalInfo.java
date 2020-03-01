package com.example.roome.user_classes;

import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.roome.Data;
import com.example.roome.FirebaseMediate;
import com.example.roome.R;

/**
 * A class representing a ApartmentAdditionalInfo - user apartment additional info.
 */
public class ApartmentAdditionalInfo extends DialogFragment {
    private ImageView exitOption,aptImg;
    private TextView additionalInfo, numOfRoommates, neighborhood, price;

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
                FirebaseMediate.getRoommateSearcherUserByUid(bundle.getString("roommateId"));
        return inflater.inflate(R.layout.fragment_apartment_additional_info, container, false);
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        exitOption = getView().findViewById(R.id.iv_btn_x);
        exitOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        additionalInfo = getView().findViewById(R.id.tv_additional_info);
        numOfRoommates = getView().findViewById(R.id.tv_roommate_num);
        price = getView().findViewById(R.id.tv_apt_price);
        neighborhood = getView().findViewById(R.id.tv_location);
        additionalInfo.setText(roommateSearcherUser.getAdditionalInfo());
        numOfRoommates.setText(Integer.toString(roommateSearcherUser.getApartment().getNumberOfRoommates()));
        price.setText(Integer.toString((int)roommateSearcherUser.getApartment().getRent()));
        neighborhood.setText(roommateSearcherUser.getApartment().getNeighborhood());
        aptImg = getView().findViewById(R.id.iv_house_img);
        aptImg.setImageResource(Data.getImageByUid(getArguments().getString(
                "roommateId")));

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
            params.width = ViewGroup.LayoutParams.WRAP_CONTENT;
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            window.setAttributes(params);
        }
    }
}
