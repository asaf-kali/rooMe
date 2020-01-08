package com.example.roome.Apartment_searcher_tabs_classes;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.roome.R;
import com.example.roome.user_classes.ApartmentSearcherUser;
import com.example.roome.user_classes.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditProfileApartmentSearcher extends Fragment {
    private static final int GALLERY_REQUEST_CODE = 1;
    private Boolean isUserFirstNameValid;
    private Boolean isUserLastNameValid;
    private Boolean isUserAgeValid;
    private Boolean isUserPhoneValid;

    private EditText mEnterFirstNameEditText;
    private EditText mEnterLastNameEditText;
    private EditText ageEditText;
    private EditText phoneNumberEditText;

    // Firebase instance variables
    private FirebaseAuth mFirebaseAuth;
    private FirebaseUser mFirebaseUser;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mFirebaseDatabaseReference;

    public ApartmentSearcherUser getAsUser() {
        return asUser;
    }

    public void setAsUser(ApartmentSearcherUser aUser) {
        asUser = aUser;
        validateUserInput();
    }

    private ApartmentSearcherUser asUser;

    //profile pic
    ImageView profilePic;
    ImageButton addProfilePic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // Initialize Firebase
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseAuth = FirebaseAuth.getInstance();
        mFirebaseUser = mFirebaseAuth.getCurrentUser();
        mFirebaseDatabaseReference = mFirebaseDatabase.getReference();
        mFirebaseDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mFirebaseUser = mFirebaseAuth.getCurrentUser();
                asUser = dataSnapshot.child("users").child("ApartmentSearcherUser").child(mFirebaseUser.getUid()).getValue(ApartmentSearcherUser.class);
                validateUserInput();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        asUser = new ApartmentSearcherUser();
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_edit_profile_apartment_searcher, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        isUserFirstNameValid = false;
        isUserLastNameValid = false;
        isUserAgeValid = false;
        isUserPhoneValid = false;
        addProfilePic = getView().findViewById(R.id.ib_addPhoto);
        profilePic = getView().findViewById(R.id.iv_missingPhoto);
        addProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadPhotoOnClickAS();
            }
        });
//        saveProfileAS.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (isUserInputValid()){
//                    //todo: upload obj to DB
//                }
//                else {
//                    //todo: toast error that data isn't saved cuz user's input is shit
//                }
//            }
//        });
        validateUserInput();
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * validating relevant fields filled by the user
     */

    public void validateUserInput() {
        validateUserFirstName();
        validateUserLastName();
        validateAge();
        validatePhoneNumber();
    }

    /**
     * Returns a boolean if all of the user's input is valid
     */
    private boolean isUserInputValid() {
        return isUserFirstNameValid && isUserLastNameValid && isUserAgeValid && isUserPhoneValid;
    }


    public void uploadPhotoOnClickAS() {
        //Create an Intent with action as ACTION_PICK
        Intent intent = new Intent(Intent.ACTION_PICK);
        // Sets the type as image/*. This ensures only components of type image are selected
        intent.setType("image/*");
        //We pass an extra array with the accepted mime types. This will ensure only components with these MIME types as targeted.
        String[] mimeTypes = {"image/jpeg", "image/png"};
        intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        // Launching the Intent
        startActivityForResult(intent, GALLERY_REQUEST_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Result code is RESULT_OK only if the user selects an Image
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case GALLERY_REQUEST_CODE:
                    //data.getData returns the content URI for the selected Image
                    Uri selectedImage = data.getData(); //todo:save profile pic to db
                    profilePic.setImageURI(selectedImage);
                    break;
            }
    }


    /**
     * validate the entered name.
     */
    private void validateUserFirstName() {
        mEnterFirstNameEditText = getView().findViewById(R.id.et_enterFirstName);
        mEnterFirstNameEditText.setText(asUser.getFirstName() + "***"); //todo

        mEnterFirstNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                mEnterFirstNameEditText.setText(aUser.getFirstName() + "***"); //todo erase *
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isUserFirstNameValid = false;
                int inputLength = mEnterFirstNameEditText.getText().toString().length();
                if (inputLength >= User.NAME_MAXIMUM_LENGTH) {
                    mEnterFirstNameEditText.setError("Maximum Limit Reached!");
                    return;
                } else if (inputLength == 0) {
                    mEnterFirstNameEditText.setError("First name is required!");
                } else {
                    isUserFirstNameValid = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * validate the entered name.
     */
    private void validateUserLastName() {
        mEnterLastNameEditText = getView().findViewById(R.id.et_enterLastName);
        mEnterLastNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //todo: get last name from db
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isUserLastNameValid = false;
                int inputLength = mEnterLastNameEditText.getText().toString().length();
                if (inputLength >= User.NAME_MAXIMUM_LENGTH) {
                    mEnterLastNameEditText.setError("Maximum Limit Reached!");
                    return;
                } else if (inputLength == 0) {
                    mEnterLastNameEditText.setError("Last name is required!");
                } else {
                    isUserLastNameValid = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * validating the age entered. Age has to be between 6 and 120.
     */
    private void validateAge() {
        ageEditText = getView().findViewById(R.id.et_enterAge);
        ageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //todo:if in db get it, else present the hint
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isUserAgeValid = false;
                int inputLength = ageEditText.getText().toString().length();
                if (inputLength > User.MAXIMUM_AGE_LENGTH) {
                    ageEditText.setError("Maximum Limit Reached!");
                    return;
                }
                if (inputLength != 0) {
                    int curAge = Integer.parseInt(ageEditText.getText().toString());
                    if (curAge <= User.MAXIMUM_AGE && curAge >= User.MINIMUM_AGE) {
                        isUserAgeValid = true;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        ageEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    int inputLength = ageEditText.getText().toString().length();
                    if (inputLength == 0) {
                        ageEditText.setError("Age is required!");
                        return;
                    }
                    if (inputLength > User.MAXIMUM_AGE_LENGTH) {
                        ageEditText.setError("Maximum Limit Reached!");
                        return;
                    }
                    int curAge = Integer.parseInt(ageEditText.getText().toString());
                    if (curAge > User.MAXIMUM_AGE) {
                        ageEditText.setError("Age is too old!");
                    } else if (curAge < User.MINIMUM_AGE) {
                        ageEditText.setError("Age is too young!");
                    } else {
                        isUserAgeValid = true;
                    }
                }
            }
        });
    }

    /**
     * validating the PhoneNumber entered.
     */
    private void validatePhoneNumber() {
        phoneNumberEditText = getView().findViewById(R.id.et_phoneNumber);
        phoneNumberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //todo:if in db get it' else present the hint

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        phoneNumberEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) { //todo:change to math phone requirements
                if (!hasFocus) {
                    int inputLength = phoneNumberEditText.getText().toString().length();
                    if (inputLength == 0) {
                        phoneNumberEditText.setError("Phone number is required!");
                        return;
                    }
                    if (inputLength != User.PHONE_NUMBER_LENGTH) {
                        phoneNumberEditText.setError("Invalid phone number");
                        return;
                    }
                    isUserPhoneValid = true;
                }
            }
        });
    }

}

