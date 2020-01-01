package com.example.roome.Apartment_searcher_tabs_classes;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.roome.R;
import com.example.roome.user_classes.User;

public class EditProfileApartmentSearcher extends Fragment {
    private Boolean isUserFirstNameValid;
    private Boolean isUserAgeValid;
    private Boolean isUserLastNameValid;
    private Boolean isUserPhoneValid;

    private EditText ageEditText;
    private EditText phoneNumberEditText;
    private EditText mEnterFirstNameEditText;
    private EditText heightEditText;
    //todo: add save button in the edit profile

    @Override
    public void onCreate(Bundle savedInstanceState) {
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
//        validateUserInput();
        super.onActivityCreated(savedInstanceState);
    }

    /**
     * validating all fields filled by the user
     */
//    private void validateUserInput() {
//        validateUserFirstName();
////        validateUserLastName();
//        validateAge();
////        validatePhoneNumber();
//        validateHeight();
//    }

    public void uploadPhotoOnClick(View view) {
//        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            //TODO: action
        }
    }

    /**
     * validate the entered name.
     */
    private void validateUserFirstName() {
        mEnterFirstNameEditText = getView().findViewById(R.id.et_enterFirstName);
        mEnterFirstNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isUserFirstNameValid = false;
                int inputLength = mEnterFirstNameEditText.getText().toString().length();
                if (inputLength >= User.NAME_MAXIMUM_LENGTH) {
                    mEnterFirstNameEditText.setError("Maximum Limit Reached!");
                    return;
                } else if (inputLength == 0) {
                    mEnterFirstNameEditText.setError("name is required!");
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
     * validating the age entered. Age has to be between 6 and 120.
     */
    private void validateAge() {
        ageEditText = getView().findViewById(R.id.et_enterAge);
        ageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                isUserAgeValid = false;
                int inputLength = ageEditText.getText().toString().length();
                if (inputLength >= User.MAXIMUM_LENGTH) {
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
                        ageEditText.setError("age is required!");
                        return;
                    }
                    if (inputLength > User.MAXIMUM_LENGTH) {
                        ageEditText.setError("Maximum Limit Reached!");
                        return;
                    }
                    int curAge = Integer.parseInt(ageEditText.getText().toString());
                    if (curAge > User.MAXIMUM_AGE) {
                        ageEditText.setError("age is too old!");
                    } else if (curAge < User.MINIMUM_AGE) {
                        ageEditText.setError("age is too young!");
                    } else {
                        isUserAgeValid = true;
                    }
                }
            }
        });
    }

//    /**
//     * validating the weight entered. Weight has to be between 6 and 200.
//     */
//    private void validatePhoneNumber() {
//        phoneNumberEditText = getView().findViewById(R.id.et_phoneNumber);
//        phoneNumberEditText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                isUserLastNameValid = false;
//                int inputLength = phoneNumberEditText.getText().toString().length();
//                if (inputLength != User.PHONE_NUMBER_LENGTH) {
//                    phoneNumberEditText.setError("Invalid Phone Number");
//                    return;
//                }
//                isUserLastNameValid = true;
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });
//        phoneNumberEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) { //todo:change to math phone requirements
//                if (!hasFocus) {
//                    int inputLength = phoneNumberEditText.getText().toString().length();
//                    if (inputLength == 0) {
//                        phoneNumberEditText.setError("weight is required!");
//                        return;
//                    }
//                    if (inputLength > MAXIMUM_LENGTH) {
//                        phoneNumberEditText.setError("Maximum Limit Reached!");
//                        return;
//                    }
//                    int curWeight = Integer.parseInt(phoneNumberEditText.getText().toString());
//                    if (curWeight > MAXIMUM_WEIGHT) {
//                        phoneNumberEditText.setError("weight is too big!");
//                    } else if (curWeight < MINIMUM_WEIGHT) {
//                        phoneNumberEditText.setError("weight is too low!");
//                    } else {
//                        isUserLastNameValid = true;
//                    }
//                }
//            }
//        });
//    }

//    /**
//     * validating the height entered. Height has to be between 50 and 250.
//     */
//    private void validateHeight() {
//        heightEditText = findViewById(R.id.et_enter_height);
//        heightEditText.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                isUserPhoneValid = false;
//                int inputLength = heightEditText.getText().toString().length();
//                if (inputLength >= MAXIMUM_LENGTH) {
//                    heightEditText.setError("Maximum Limit Reached!");
//                    return;
//                }
//                if (inputLength != 0) {
//                    int curHeight = Integer.parseInt(heightEditText.getText().toString());
//                    if (curHeight <= MAXIMUM_HEIGHT && curHeight >= MINIMUM_HEIGHT) {
//                        isUserPhoneValid = true;
//                    }
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//            }
//        });
//        heightEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (!hasFocus) {
//                    int inputLength = heightEditText.getText().toString().length();
//                    if (inputLength == 0) {
//                        heightEditText.setError("height is required!");
//                        return;
//                    }
//                    if (inputLength > MAXIMUM_LENGTH) {
//                        heightEditText.setError("Maximum Limit Reached!");
//                        return;
//                    }
//                    int curHeight = Integer.parseInt(heightEditText.getText().toString());
//                    if (curHeight > MAXIMUM_HEIGHT) {
//                        heightEditText.setError("max height is 250!");
//                    } else if (curHeight < MINIMUM_HEIGHT) {
//                        heightEditText.setError("minimum height is 50!");
//                    } else {
//                        isUserLastNameValid = true;
//                    }
//                }
//            }
//        });
//    }

}
