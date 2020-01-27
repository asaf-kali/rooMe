package com.example.roome.Roomate_searcher_tabs_classes;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.roome.EditProfileAlertDialog;
import com.example.roome.FirebaseMediate;
import com.example.roome.MyPreferences;
import com.example.roome.R;
import com.example.roome.user_classes.RoommateSearcherUser;
import com.example.roome.user_classes.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.IOException;

public class EditProfileRoommateSearcher extends Fragment {
    private static final int GALLERY_REQUEST_CODE = 1;
    private Boolean isUserFirstNameValid;
    private Boolean isUserLastNameValid;
    private Boolean isUserAgeValid;
    private Boolean isUserPhoneValid;
    private Boolean changedPhoto = false;

    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText ageEditText;
    private EditText phoneNumberEditText;
    private EditText infoEditText;
    private RadioButton maleRadioButton;

    // Firebase instance variables
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference firebaseDatabaseReference;
    private FirebaseStorage storage;
    private StorageReference storageReference;
    private RoommateSearcherUser roommateSearcherUser;

    //profile pic
    ImageView profilePic;
    ImageView addProfilePic;
    private static final long ONE_MEGABYTE = 1024 * 1024;
    private Uri selectedImage;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        initializeFirebaseVariables();
        firebaseDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                roommateSearcherUser = FirebaseMediate.getRoommateSearcherUserByUid(MyPreferences.getUserUid(getContext()));
                setInfo();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        roommateSearcherUser = new RoommateSearcherUser();
        super.onCreate(savedInstanceState);
    }

    /**
     * This method initializes firebase variables.
     */
    private void initializeFirebaseVariables() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabaseReference = firebaseDatabase.getReference();
        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_edit_profile_roommate_searcher, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        initializeDataFieldsVariablesToFalse();
        addProfilePic = getView().findViewById(R.id.ib_add_photo);
        profilePic = getView().findViewById(R.id.iv_missingPhoto);
        addProfilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uploadPhotoOnClickAS();
            }
        });

        ImageView saveProfileButton = getView().findViewById(R.id.btn_save_profile_as);
        saveProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isUserInputValid()) {
                    //save user data to DB
                    if (changedPhoto) { //save image to DB only if it's a new one
                        FirebaseMediate.uploadPhotoToStorage(selectedImage, getActivity(), getContext(), "Roommate Searcher User", "Profile Pic");
                        changedPhoto = false;
                    }
                    roommateSearcherUser.setInfo(infoEditText.getText().toString());
                    firebaseDatabaseReference.child("users").child("RoommateSearcherUser").child(MyPreferences.getUserUid(getContext())).setValue(roommateSearcherUser);
                } else {
                    Intent intent = new Intent(EditProfileRoommateSearcher.this.getActivity(), EditProfileAlertDialog.class);
                    startActivity(intent);
                }
            }
        });
        validateUserInput();
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * This method initializes data fields variables to false.
     */
    void initializeDataFieldsVariablesToFalse() {
        isUserFirstNameValid = false;
        isUserLastNameValid = false;
        isUserAgeValid = false;
        isUserPhoneValid = false;
    }

    /**
     * This method sets the user's profile information from the firebase
     */
    private void setInfo() {
        firstNameEditText = getView().findViewById(R.id.et_enterFirstName);
        firstNameEditText.setText(roommateSearcherUser.getFirstName());

        lastNameEditText = getView().findViewById(R.id.et_enterLastName);
        lastNameEditText.setText(roommateSearcherUser.getLastName());

        ageEditText = getView().findViewById(R.id.et_enterAge);
        if (roommateSearcherUser.getAge() >= User.MINIMUM_AGE) {
            ageEditText.setText(Integer.toString(roommateSearcherUser.getAge()));
            isUserAgeValid = true;
        }

        maleRadioButton = getView().findViewById(R.id.radio_btn_male);
        RadioButton femaleRadioButton = getView().findViewById(R.id.radio_btn_female);
        if (("MALE").equals(roommateSearcherUser.getGender())) {
            maleRadioButton.setChecked(true);
        } else {
            femaleRadioButton.setChecked(true);
        }

        phoneNumberEditText = getView().findViewById(R.id.et_phoneNumber);
        phoneNumberEditText.setText(roommateSearcherUser.getPhoneNumber());
        if (roommateSearcherUser.getPhoneNumber() != null && roommateSearcherUser.getPhoneNumber().length() == User.PHONE_NUMBER_LENGTH) {
            isUserPhoneValid = true;
        }

        infoEditText = getView().findViewById(R.id.et_info);
        infoEditText.setText(roommateSearcherUser.getInfo());

        if (roommateSearcherUser.getHasProfilePic()) {
            //upload user's profile image from storage
            storageReference.child("Images").child("Roommate Searcher User").
                    child(MyPreferences.getUserUid(getContext())).child("Profile Pic").getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    profilePic.setImageBitmap(bmp);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Log.d("EditProfileRS", "No Such file or Path found!!");
                }
            });
        }
        isUserFirstNameValid = true;
        isUserLastNameValid = true;
    }

    /**
     * validating relevant fields filled by the user
     */
    private void validateUserInput() {
        validateUserFirstName();
        validateUserLastName();
        validateAge();
        validateGender();
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
                    selectedImage = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), selectedImage);
                        profilePic.setImageBitmap(bitmap);
                        roommateSearcherUser.setHasProfilePic(true);
                        changedPhoto = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
            }
    }

    /**
     * validate the entered name.
     */
    private void validateUserFirstName() {
        firstNameEditText = getView().findViewById(R.id.et_enterFirstName);
        firstNameEditText.setText(roommateSearcherUser.getFirstName());
        setIsUserFirstNameValidToTrueIfValidFirstName();
        firstNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isUserFirstNameValid = false;
                int inputLength = firstNameEditText.getText().toString().length();
                if (inputLength >= User.NAME_MAXIMUM_LENGTH) {
                    firstNameEditText.setError("Maximum Limit Reached!");
                    return;
                } else if (inputLength == 0) {
                    firstNameEditText.setError("First name is required!");
                } else {
                    roommateSearcherUser.setFirstName(firstNameEditText.getText().toString());
                    isUserFirstNameValid = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * This method sets IsUserFirstNameValid to true if valid first name.
     */
    private void setIsUserFirstNameValidToTrueIfValidFirstName() {
        int inputLength = firstNameEditText.getText().toString().length();
        if (inputLength < User.NAME_MAXIMUM_LENGTH && inputLength > 0) {
            isUserFirstNameValid = true;
        }
    }

    /**
     * validate the entered name.
     */
    private void validateUserLastName() {
        lastNameEditText = getView().findViewById(R.id.et_enterLastName);
        lastNameEditText.setText(roommateSearcherUser.getLastName());
        setIsUserLastNameValidToTrueIfValidLastName();
        lastNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isUserLastNameValid = false;
                int inputLength = lastNameEditText.getText().toString().length();
                if (inputLength >= User.NAME_MAXIMUM_LENGTH) {
                    lastNameEditText.setError("Maximum Limit Reached!");
                    return;
                } else if (inputLength == 0) {
                    lastNameEditText.setError("Last name is required!");
                } else {
                    roommateSearcherUser.setLastName(lastNameEditText.getText().toString());
                    isUserLastNameValid = true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * This method sets isUserLastNameValid to true if valid last name.
     */
    private void setIsUserLastNameValidToTrueIfValidLastName() {
        int inputLength = lastNameEditText.getText().toString().length();
        if (inputLength < User.NAME_MAXIMUM_LENGTH && inputLength > 0) {
            isUserLastNameValid = true;
        }
    }

    /**
     * validating the age entered. Age has to be between 6 and 120.
     */
    private void validateAge() {
        ageEditText = getView().findViewById(R.id.et_enterAge);
        if (roommateSearcherUser.getAge() >= User.MINIMUM_AGE) {
            ageEditText.setText(Integer.toString(roommateSearcherUser.getAge()));
        }
        setIsUserAgeValidToTrueIfValidAge();
        ageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
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
                        roommateSearcherUser.setAge(Integer.parseInt(ageEditText.getText().toString()));
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
                        roommateSearcherUser.setAge(Integer.parseInt(ageEditText.getText().toString()));
                        isUserAgeValid = true;
                    }
                }
            }
        });
    }

    /**
     * This method sets isUserAgeValid to true if valid age.
     */
    private void setIsUserAgeValidToTrueIfValidAge() {
        int inputLength = ageEditText.getText().toString().length();
        if (inputLength != 0) {
            int curAge = Integer.parseInt(ageEditText.getText().toString());
            if (curAge <= User.MAXIMUM_AGE && curAge >= User.MINIMUM_AGE) {
                isUserAgeValid = true;
            }
        }
    }

    /**
     * validating the Gender entered.
     */
    private void validateGender() {
        maleRadioButton = getView().findViewById(R.id.radio_btn_male);
        RadioButton femaleRadioButton = getView().findViewById(R.id.radio_btn_female);
        if (("MALE").equals(roommateSearcherUser.getGender())) {
            maleRadioButton.setChecked(true);
        } else {
            femaleRadioButton.setChecked(true);
        }
        maleRadioButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                roommateSearcherUser.setGender("MALE");
                maleRadioButton.setChecked(true);
            }
        });
        femaleRadioButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                roommateSearcherUser.setGender("FEMALE");
            }
        });
    }

    /**
     * validating the PhoneNumber entered.
     */
    private void validatePhoneNumber() {
        phoneNumberEditText = getView().findViewById(R.id.et_phoneNumber);
        phoneNumberEditText.setText(roommateSearcherUser.getPhoneNumber());
        setIsUserPhoneValidToTrueIfValidPhoneNumber();
        phoneNumberEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int inputLength = phoneNumberEditText.getText().toString().length();
                if (inputLength == User.PHONE_NUMBER_LENGTH) {
                    roommateSearcherUser.setPhoneNumber(phoneNumberEditText.getText().toString());
                    isUserPhoneValid = true;
                }
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
                    roommateSearcherUser.setPhoneNumber(phoneNumberEditText.getText().toString());
                    isUserPhoneValid = true;
                }
            }
        });
    }

    /**
     * This method sets isUserPhoneValid to true if valid phone number.
     */
    private void setIsUserPhoneValidToTrueIfValidPhoneNumber() {
        int inputLength = phoneNumberEditText.getText().toString().length();
        if (inputLength == User.PHONE_NUMBER_LENGTH) {
            isUserPhoneValid = true;
        }
    }

}