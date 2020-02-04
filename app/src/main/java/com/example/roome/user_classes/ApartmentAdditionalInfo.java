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

import com.example.roome.FirebaseMediate;
import com.example.roome.R;

public class ApartmentAdditionalInfo extends DialogFragment {
    ImageView iv_exit_btn;
    TextView additionalInfo;
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
        iv_exit_btn = getView().findViewById(R.id.iv_btn_x);
        iv_exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        additionalInfo = getView().findViewById(R.id.tv_additional_info);
        additionalInfo.setText(roommateSearcherUser.getAdditionalInfo());
        super.onActivityCreated(savedInstanceState);
    }

//    @Override
//    public void onStart()
//    {
//        super.onStart();
//        Dialog dialog = getDialog();
//        if (dialog != null)
//        {
//            Window window = getDialog().getWindow();
//            WindowManager.LayoutParams params = window.getAttributes();
//            params.width = SIZE_OF_DIALOG;
//            params.height = SIZE_OF_DIALOG;
//            window.setAttributes(params);
//        }
//    }
}
