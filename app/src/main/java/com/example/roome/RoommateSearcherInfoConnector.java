package com.example.roome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * This class bind between roommateId to the house drawable ,
 * We didn't implement the import / upload images to the firebase.
 */
public final class RoommateSearcherInfoConnector {
    private static Map<String, Integer> uidToDrawable = new HashMap<String, Integer>();
    private static ArrayList<Integer> apartmentImages;
    private static ArrayList<String> roommateSearchersUids;

    /**
     * Initialize the houses images
     */
    private static void initDrawablesImg() {
        apartmentImages = new ArrayList<>(Arrays.asList(R.drawable.house1,
                R.drawable.house2,
                R.drawable.house3, R.drawable.house4,
                R.drawable.house5,
                R.drawable.house6, R.drawable.house7));
    }

    /**
     * Initialize roommate searcher Id's (keys from firebase).
     */
    private static void initRSUids() {
        roommateSearchersUids = new ArrayList<>(Arrays.asList("-Ly_2md3br6ex6H_4iGF", "-Ly_8mKAe1c_aPjqdese", "-Ly_Av7DnafQgO9fA3DL",
                "-Ly_B0kHTiAnAmuwK5ol", "-Ly_F9eAenahqGxLChRo", "-LymPFoE5xAfXLBnHWl4", "-LymRsVQOemjzaCqRISh"));
    }

    /**
     * Initialize the uidToDrawable hashmap(bind between id to drawable).
     */
    public static void initD() {
        initDrawablesImg();
        initRSUids();
        for (int i = 0; i < roommateSearchersUids.size(); i++) {
            uidToDrawable.put(roommateSearchersUids.get(i), apartmentImages.get(i));
        }
    }

    /**
     * Get the house drawable according to Id string
     * @param roommateUid - roommate Id
     * @return - The drawable
     */
    public static int getImageByUid(String roommateUid){
        return uidToDrawable.get(roommateUid);
    }

    /**
     * Get the Id of roommate user according to the drawable
     * @param imgId - The drawable image Id
     * @return - The roommate Id
     */
    public static String getUidByImg(int imgId){
        for (String key : uidToDrawable.keySet()){
            int val = uidToDrawable.get(key);
            if (val == imgId){
                return key;
            }
        }
        return null;
    }
}
