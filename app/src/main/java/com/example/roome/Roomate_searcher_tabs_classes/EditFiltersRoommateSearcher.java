package com.example.roome.Roomate_searcher_tabs_classes;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.roome.R;
import com.yahoo.mobile.client.android.util.rangeseekbar.RangeSeekBar;

import java.util.ArrayList;
import java.util.Calendar;

//todo: send all data if save pressed
public class EditFiltersRoommateSearcher extends Fragment {

    private RangeSeekBar costBar; //todo: present same vals when entering after change


    private Button mChooseLocations;
    TextView mChosenLocations;
    String[] locations;
    boolean[] checkedLocations;
    ArrayList<Integer> mUserLocations = new ArrayList<>(); //todo:send the locations chosen to db when save pressed

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private RangeSeekBar numRoommatesBar;

    private RangeSeekBar ageRoommatesBar;

    //todo:create onclick for the save button
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_filter_roommate_searcher, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        //-----------------------------cost range-------------------------------------
        costBar = getView().findViewById(R.id.rsb_cost_bar);
        costBar.setRangeValues(1000, 4000);

        costBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Object minValue, Object maxValue) {
                Number minVal = bar.getSelectedMinValue();
                Number maxVal = bar.getSelectedMaxValue();
                int min = (int) minVal;
                int max = (int) maxVal;

                //todo:send these vals as the new ones chosen
            }

        });
        //---------------------------------------------------------------------------
        //----------------------------location selection----------------------------
        mChooseLocations = (Button) getView().findViewById(R.id.btn_choose_locations);
        mChosenLocations = (TextView) getView().findViewById(R.id.tv_chosen_locations);

        locations = getResources().getStringArray(R.array.locations);
        checkedLocations = new boolean[locations.length];

        mChooseLocations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
                mBuilder.setTitle("Locations in Jerusalem");
                mBuilder.setMultiChoiceItems(locations, checkedLocations, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if (isChecked) {
                            mUserLocations.add(position);
                        } else {
                            mUserLocations.remove((Integer.valueOf(position)));
                        }
                    }
                });

                mBuilder.setCancelable(false);
                mBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String item = "";
                        for (int i = 0; i < mUserLocations.size(); i++) {
                            item = item + locations[mUserLocations.get(i)];
                            if (i != mUserLocations.size() - 1) {
                                item = item + ", ";
                            }
                        }
                        mChosenLocations.setText(item);
                    }
                });

                mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                mBuilder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for (int i = 0; i < checkedLocations.length; i++) {
                            checkedLocations[i] = false;
                            mUserLocations.clear();
                            mChosenLocations.setText("");
                        }
                    }
                });

                AlertDialog mDialog = mBuilder.create();
                mDialog.show();
            }
        });
        //---------------------------------------------------------------------------
        //----------------------------entry date selection----------------------------
        mDisplayDate = (TextView) getView().findViewById(R.id.tv_entry_date_as);

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
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                mDisplayDate.setText(date);
            }
        };

        //---------------------------------------------------------------------------
        //----------------------------num roommates selection----------------------------
        numRoommatesBar = getView().findViewById(R.id.rsb_num_roommates_bar);
        numRoommatesBar.setRangeValues(1, 5);

        numRoommatesBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Object minValue, Object maxValue) {
                Number minVal = bar.getSelectedMinValue();
                Number maxVal = bar.getSelectedMaxValue();
                int min = (int) minVal;
                int max = (int) maxVal;

                //todo:send these vals as the new ones chosen
            }

        });
        //---------------------------------------------------------------------------
        //----------------------------roommates' age selection----------------------------
        ageRoommatesBar = getView().findViewById(R.id.rsb_age_roommates_bar);
        ageRoommatesBar.setRangeValues(16, 35);

        ageRoommatesBar.setOnRangeSeekBarChangeListener(new RangeSeekBar.OnRangeSeekBarChangeListener() {
            @Override
            public void onRangeSeekBarValuesChanged(RangeSeekBar bar, Object minValue, Object maxValue) {
                Number minVal = bar.getSelectedMinValue();
                Number maxVal = bar.getSelectedMaxValue();
                int min = (int) minVal;
                int max = (int) maxVal;

                //todo:send these vals as the new ones chosen
            }

        });

        //---------------------------------------------------------------------------
        //----------------------------kosher selection----------------------------
//todo:extract the kosher preference
        super.onActivityCreated(savedInstanceState);
    }

}
