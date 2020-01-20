package com.example.roome;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * this class is responsible for saving app state and date to preferences file.
 */
public class MyPreferences {
    static final String MY_PREFERENCES = "myPreferences";
    static final String IS_FIRST_TIME = "isFirstTime";
    static final String IS_ROOMMATE_SEARCHER = "isRoommateSearcher";
    static final String USER_UID = "userFirebaseUid";
    static final String MANUAL_FIRST_NAME = "FIRSTNAME";
    static final String MANUAL_LAST_NAME = "LASTNAME";


    public static boolean isFirstTime(Context context) {
        final SharedPreferences reader = getSharedPreferences(context);
        return reader.getBoolean(IS_FIRST_TIME, true);
    }
    public static String getManualFirstName(Context context) {
        final SharedPreferences reader = getSharedPreferences(context);
        return reader.getString(MANUAL_FIRST_NAME, "null");
    }

    public static String getManualLastName(Context context) {
        final SharedPreferences reader = getSharedPreferences(context);
        return reader.getString(MANUAL_LAST_NAME, "null");
    }

    public static String getUserUid(Context context) {
        final SharedPreferences reader = getSharedPreferences(context);
        return reader.getString(USER_UID, null);
    }


    static void setUserUid(Context context, String userUid) {
        final SharedPreferences reader = getSharedPreferences(context);
        final SharedPreferences.Editor preferencesEditor = reader.edit();
        preferencesEditor.putString(USER_UID, userUid);
        preferencesEditor.apply();
    }

    public static void setIsFirstTimeToFalse(Context context) {
        final SharedPreferences reader = getSharedPreferences(context);
        final SharedPreferences.Editor preferencesEditor = reader.edit();
        preferencesEditor.putBoolean(IS_FIRST_TIME, false);
        preferencesEditor.apply();
    }

    static void setIsRoommateSearcherToFalse(Context context) {
        final SharedPreferences reader = getSharedPreferences(context);
        final SharedPreferences.Editor preferencesEditor = reader.edit();
        preferencesEditor.putBoolean(IS_ROOMMATE_SEARCHER, false);
        preferencesEditor.apply();
    }

    static boolean isRoommateSearcher(Context context) {
        final SharedPreferences reader = getSharedPreferences(context);
        return reader.getBoolean(IS_ROOMMATE_SEARCHER, true);
    }

    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
    }
}
