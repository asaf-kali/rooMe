package com.example.roome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public final class Data {
    private static Map<String, Integer> uidToDrawable = new HashMap<String, Integer>();
    private static ArrayList<Integer> apartmentImages;
    private static ArrayList<String> roommateSearchersUids;

    private static void initDrawablesImg() {
        apartmentImages = new ArrayList<>(Arrays.asList(R.drawable.home_example1, R.drawable.home_example2,
                R.drawable.home_example3, R.drawable.home_example1, R.drawable.home_example2,
                R.drawable.home_example3, R.drawable.home_example1, R.drawable.home_example2,
                R.drawable.home_example3, R.drawable.home_example1));
    }

    private static void initRSUids() {
        roommateSearchersUids = new ArrayList<>(Arrays.asList("-Ly_2md3br6ex6H_4iGF", "-Ly_8mKAe1c_aPjqdese", "-Ly_Av7DnafQgO9fA3DL",
                "-Ly_B0kHTiAnAmuwK5ol", "-Ly_F9eAenahqGxLChRo", "-LymPFoE5xAfXLBnHWl4", "-LymRsVQOemjzaCqRISh"));
    }

    public static void initD() { //todo need to call this
        initDrawablesImg();
        initRSUids();
        for (int i = 0; i < roommateSearchersUids.size(); i++) {
            uidToDrawable.put(roommateSearchersUids.get(i), apartmentImages.get(i));
        }
    }
    public static int getImageByUid(String roommateUid){
        return uidToDrawable.get(roommateUid);
    }

}
