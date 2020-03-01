package com.example.roome.Apartment_searcher_tabs_classes;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.roome.ChoosingActivity;
import com.example.roome.FirebaseMediate;
import com.example.roome.MyPreferences;
import com.example.roome.R;
import com.example.roome.user_classes.ApartmentSearcherUser;
import com.example.roome.user_classes.RoommateSearcherUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yahoo.mobile.client.android.util.rangeseekbar.RangeSeekBar;

import java.util.ArrayList;
import java.util.Calendar;

public class EditFiltersApartmentSearcher extends DialogFragment {

    private ImageView mChooseLocations;
    TextView mChosenLocations;
    String[] locations;
    boolean[] checkedLocations;
    ArrayList<Integer> userLocations = new ArrayList<>();
    int[] checkBoxesValues = new int[]{R.id.check_box_pets, R.id.check_box_kosher, R.id.check_box_ac, R.id.check_box_smoking};
    private ImageView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private RadioButton twoRoommatesMax;
    private RadioButton threeRoommatesMax;
    private RadioButton fourRoommatesMax;

    private EditText minCostEditText;
    private EditText maxCostEditText;
    private boolean validCostRange = true;

    private EditText minAgeEditText;
    private EditText maxAgeEditText;
    private boolean validAgeRange = true;


    // Firebase instance variables
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference firebaseDatabaseReference;
    private ApartmentSearcherUser asUser;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize Firebase
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabaseReference = firebaseDatabase.getReference();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_filter_apartment_searcher, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        ImageView saveButton = getView().findViewById(R.id.btn_save_filters_as);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validCostRange && validAgeRange) {
                    firebaseDatabaseReference.child("users").child("ApartmentSearcherUser").child(MyPreferences.getUserUid(getContext())).setValue(asUser);
                    Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
                    setSavedFiltersToLists();
                    getDialog().dismiss();
                } else {
                    Toast.makeText(getContext(), "Invalid input", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //-----------------------------cost range-------------------------------------
        minCostEditText = getView().findViewById(R.id.et_min_cost_val);
        maxCostEditText = getView().findViewById(R.id.et_max_cost_val);

        minCostEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int minInputLen = minCostEditText.getText().toString().length();
                int maxInputLen = maxCostEditText.getText().toString().length();
                if (minInputLen != 0 && maxInputLen != 0) {
                    int minCostInput = Integer.parseInt(minCostEditText.getText().toString());
                    if (minCostInput > Integer.parseInt(maxCostEditText.getText().toString())) {
                        minCostEditText.setError("Min is bigger than max!");
                        validCostRange = false;
                    } else {
                        validCostRange = true;
                        asUser.setMinRent(minCostInput);
                    }
                }

            }
        });

        maxCostEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int minInputLen = minCostEditText.getText().toString().length();
                int maxInputLen = maxCostEditText.getText().toString().length();
                if (minInputLen != 0 && maxInputLen != 0) {
                    int maxCostInput = Integer.parseInt(maxCostEditText.getText().toString());
                    if (maxCostInput >= Integer.parseInt(minCostEditText.getText().toString())) {
                        validCostRange = true;
                        asUser.setMaxRent(maxCostInput);
                    } else {
                        validCostRange = false;
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        maxCostEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    int minInputLen = minCostEditText.getText().toString().length();
                    int maxInputLen = maxCostEditText.getText().toString().length();
                    if (minInputLen != 0 && maxInputLen != 0) {
                        int maxCostInput = Integer.parseInt(maxCostEditText.getText().toString());
                        if (maxCostInput < Integer.parseInt(minCostEditText.getText().toString())) {
                            validCostRange = false;
                            maxCostEditText.setError("Max is smaller than min!");
                        } else {
                            validCostRange = true;
                            asUser.setMaxRent(maxCostInput);
                        }
                    }
                }
            }
        });

        //---------------------------------------------------------------------------
        //----------------------------location selection----------------------------
        mChooseLocations = getView().findViewById(R.id.btn_choose_locations);
        mChosenLocations = getView().findViewById(R.id.tv_picked_locations);

        locations = getResources().getStringArray(R.array.locations);
        checkedLocations = new boolean[locations.length];

        mChooseLocations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
                alertDialogBuilder.setTitle("Locations in Jerusalem");
                setCheckedLocationsToTrue();
                alertDialogBuilder.setMultiChoiceItems(locations, checkedLocations, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if (isChecked) {
                            if (!userLocations.contains(position)) {
                                userLocations.add(position);
                            }
                        } else {
                            for (int i = 0; i < userLocations.size(); i++) {
                                if (userLocations.get(i) == position) {
                                    userLocations.remove(i);
                                    break;
                                }
                            }
                        }
                    }
                });

                alertDialogBuilder.setCancelable(false);
                alertDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String text = toStringUserLocations();
                        mChosenLocations.setText(text);
                        asUser.setOptionalNeighborhoods(userLocations);
                    }
                });

                alertDialogBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                alertDialogBuilder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for (int i = 0; i < checkedLocations.length; i++) {
                            checkedLocations[i] = false;
                            userLocations.clear();
                            mChosenLocations.setText("");
                        }
                    }
                });

                AlertDialog mDialog = alertDialogBuilder.create();
                mDialog.show();
            }
        });
        //---------------------------------------------------------------------------
        //----------------------------entry date selection----------------------------
        mDisplayDate = getView().findViewById(R.id.iv_choose_entry_date);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
                asUser.setEarliestEntryDate(dialog.toString());
            }
        });
        final TextView chosenDate = getView().findViewById(R.id.tv_click_here_entry_date);
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;

                chosenDate.setText(date);
                asUser.setEarliestEntryDate(date);

            }
        };

        //---------------------------------------------------------------------------
        //---------------------------- max num roommates selection----------------------------
        twoRoommatesMax = getView().findViewById(R.id.radio_btn_as_2);
        twoRoommatesMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asUser.setMaxNumDesiredRoommates(2);
                twoRoommatesMax.setChecked(true);
            }
        });

        threeRoommatesMax = getView().findViewById(R.id.radio_btn_as_3);
        threeRoommatesMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asUser.setMaxNumDesiredRoommates(3);
                threeRoommatesMax.setChecked(true);
            }
        });

        fourRoommatesMax = getView().findViewById(R.id.radio_btn_as_4);
        fourRoommatesMax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                asUser.setMaxNumDesiredRoommates(4);
                fourRoommatesMax.setChecked(true);
            }
        });
        //---------------------------------------------------------------------------
        //----------------------------roommates' age selection----------------------------
        minAgeEditText = getView().findViewById(R.id.et_min_age_val);
        maxAgeEditText = getView().findViewById(R.id.et_max_age_val);

        minAgeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int minInputLen = minAgeEditText.getText().toString().length();
                int maxInputLen = maxAgeEditText.getText().toString().length();
                if (minInputLen != 0 && maxInputLen != 0) {
                    int minAgeInput = Integer.parseInt(minAgeEditText.getText().toString());
                    if (minAgeInput > Integer.parseInt(maxAgeEditText.getText().toString())) {
                        minAgeEditText.setError("Min is bigger than max!");
                        validAgeRange = false;
                    } else {
                        validAgeRange = true;
                        asUser.setMinAgeRequired(minAgeInput);
                    }
                }
            }
        });


        maxAgeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int minInputLen = minAgeEditText.getText().toString().length();
                int maxInputLen = maxAgeEditText.getText().toString().length();
                if (minInputLen != 0 && maxInputLen != 0) {
                    int maxAgeInput = Integer.parseInt(maxAgeEditText.getText().toString());
                    if (maxAgeInput >= Integer.parseInt(minAgeEditText.getText().toString())) {
                        validAgeRange = true;
                        asUser.setMaxAgeRequired(maxAgeInput);
                    } else {
                        validAgeRange = false;

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        maxAgeEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    int minInputLen = minAgeEditText.getText().toString().length();
                    int maxInputLen = maxAgeEditText.getText().toString().length();
                    if (minInputLen != 0 && maxInputLen != 0) {
                        int maxAgeInput = Integer.parseInt(maxAgeEditText.getText().toString());
                        if (maxAgeInput < Integer.parseInt(minAgeEditText.getText().toString())) {
                            validAgeRange = false;
                            maxAgeEditText.setError("Max is smaller than min!");
                        } else {
                            validAgeRange = true;
                            asUser.setMaxAgeRequired(maxAgeInput);

                        }
                    }
                }
            }
        });


        //---------------------------------------------------------------------------
        //----------------------------Things i care about selection----------------------------
        addOnClickToCheckBoxes();

        super.onActivityCreated(savedInstanceState);
        asUser = FirebaseMediate.getApartmentSearcherUserByUid(MyPreferences.getUserUid(getContext()));
        setFiltersValuesFromDataBase();
    }

    private void setCheckedLocationsToTrue() {
        if (asUser.getOptionalNeighborhoods() != null) {
            for (int index : asUser.getOptionalNeighborhoods()) {
                checkedLocations[index] = true;
            }
        }
    }

    /**
     * This method returns a string from the user location list.
     */
    private String toStringUserLocations() {
        String text = "";
        for (int i = 0; i < userLocations.size(); i++) {
            text = text + locations[userLocations.get(i)];
            if (i != userLocations.size() - 1) {
                text = text + ", ";
            }
        }
        return text;
    }

    /**
     * This method calls the method to filters out the the irrelevant roommate users from with
     * a specified list.
     */
    private void setSavedFiltersToLists() {
        filterOutRoommatesFromList(ChoosingActivity.NOT_SEEN);
    }

    /**
     * This method filters out the the irrelevant roommate users from the specified list.
     *
     * @param listName - The list to filter the irrelevant roommate users from.
     */
    private void filterOutRoommatesFromList(String listName) {
        ArrayList<String> listRoommatesIds = FirebaseMediate.getAptPrefList(listName, MyPreferences.getUserUid(getContext()));
        ArrayList<String> filteredOutRoommatesIds = FirebaseMediate.getAptPrefList("filtered_out", MyPreferences.getUserUid(getContext()));
        ArrayList<String> updatedUnSeenRoommatesIds = new ArrayList<>();
        ArrayList<String> updatedFilteredOutRoommatesIds = new ArrayList<>();
        listRoommatesIds.addAll(filteredOutRoommatesIds);
        for (String roommateId : listRoommatesIds) {
            RoommateSearcherUser roommate = FirebaseMediate.getRoommateSearcherUserByUid(roommateId);
            if (roommate.getApartment() != null) {
                double roommatesApartmentRent = roommate.getApartment().getRent();
                if (roommatesApartmentRent <= asUser.getMaxRent() && roommatesApartmentRent >= asUser.getMinRent()) {
                    updatedUnSeenRoommatesIds.add(roommateId);
                } else {
                    updatedFilteredOutRoommatesIds.add(roommateId);
                }
            }
        }
        FirebaseMediate.setAptPrefList(listName, MyPreferences.getUserUid(getContext()), updatedUnSeenRoommatesIds);
        FirebaseMediate.setAptPrefList("filtered_out", MyPreferences.getUserUid(getContext()), updatedFilteredOutRoommatesIds);
    }

    private void setMaxNumRoommatesRB() {
        int chosenNum = asUser.getMaxNumDesiredRoommates();
        switch (chosenNum) {
            case 2:
                twoRoommatesMax.setChecked(true);
                break;
            case 3:
                threeRoommatesMax.setChecked(true);
                break;
            case 4:
                fourRoommatesMax.setChecked(true);
                break;
        }
    }

    private void setCostRangeValsFB() {
        int min = asUser.getMinRent();
        int max = asUser.getMaxRent();
        if (max != 0) {
            maxCostEditText.setText(Integer.toString(max));
        }
        minCostEditText.setText(Integer.toString(min));
    }

    private void setAgeRangeValsFB() {
        int min = asUser.getMinAgeRequired();
        int max = asUser.getMaxAgeRequired();
        if (max != 0) {
            maxAgeEditText.setText(Integer.toString(max));
        }
        minAgeEditText.setText(Integer.toString(min));
    }


    //todo: things i care about

    /**
     * This method sets the filters to the users preferences values stored in database.
     */
    private void setFiltersValuesFromDataBase() {
        setCostRangeValsFB();
        setMaxNumRoommatesRB();
        setAgeRangeValsFB();
        setCheckBoxesToUserPreferences();
        final TextView chosenDate = getView().findViewById(R.id.tv_click_here_entry_date);
        chosenDate.setText(asUser.getEarliestEntryDate());
        if (asUser.getOptionalNeighborhoods() != null) {
            userLocations = asUser.getOptionalNeighborhoods();
        }
        String text = toStringUserLocations();
        mChosenLocations.setText(text);
    }

    /**
     * This method sets the check boxes to user preferences
     */
    private void setCheckBoxesToUserPreferences() {
        for (int checkBoxValue : checkBoxesValues) {
            if (isCheckedForUser(checkBoxValue)) {
                CheckBox checkBox = getView().findViewById(checkBoxValue);
                checkBox.setChecked(true);
            }
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    /**
     * This method adds onClick listeners to the check boxes
     */
    private void addOnClickToCheckBoxes() {
        for (int checkbox : checkBoxesValues) {
            addOnClickListenerToCheckBox(checkbox);
        }
    }

    /**
     * This method adds a onClick listener
     *
     * @param checkBoxValue - the int value of the check box to add the listener to.
     */
    private void addOnClickListenerToCheckBox(final int checkBoxValue) {
        final CheckBox checkBox = getView().findViewById(checkBoxValue);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    setAsUserFilterValue(true, checkBoxValue);
                } else {
                    setAsUserFilterValue(false, checkBoxValue);
                }
            }
        });
    }

    /**
     * This method sets a user field to true if check box is checked otherwise false
     *
     * @param flag          - true if check box is checked otherwise false
     * @param checkBoxValue - the check box to change the value.
     */
    private void setAsUserFilterValue(boolean flag, int checkBoxValue) {
        switch (checkBoxValue) {
            case R.id.check_box_pets:
                asUser.setHasNoPets(flag);
                break;
            case R.id.check_box_kosher:
                asUser.setKosherImportance(flag);
                break;
            case R.id.check_box_smoking:
                asUser.setSmokingFree(flag);
                break;
            case R.id.check_box_ac:
                asUser.setHasAC(flag);
                break;
        }
    }

    /**
     * This method returns true if the filter is important for the user, otherwise false.
     *
     * @param checkBoxValue - the int value of the check box.
     * @return true if the filter is important for the user (he checked the box before), otherwise false.
     */
    private boolean isCheckedForUser(int checkBoxValue) {
        switch (checkBoxValue) {
            case R.id.check_box_pets:
                return asUser.isHasNoPets();
            case R.id.check_box_kosher:
                return asUser.getKosherImportance();
            case R.id.check_box_smoking:
                return asUser.isSmokingFree();
            case R.id.check_box_ac:
                return asUser.isHasAC();
            default:
                return false;
        }
    }
}
