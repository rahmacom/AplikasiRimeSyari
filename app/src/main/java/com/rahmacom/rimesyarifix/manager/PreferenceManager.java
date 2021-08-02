package com.rahmacom.rimesyarifix.manager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class PreferenceManager {

    private static final String PREFERENCE_NAME = "preference_manager";
    private static final String IS_FIRST_TIME_LAUNCH = "is_first_time_launch";

    public static final int PRIVATE_MODE = 0;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context context;

    @SuppressLint("CommitPrefEdits")
    public PreferenceManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE);
        editor = preferences.edit();
    }

    public void setString(String prefName, String value) {
        editor.putString(prefName, value);
        editor.commit();
    }

    public void setInt(String prefName, int value) {
        editor.putInt(prefName, value);
        editor.commit();
    }

    public void setFloat(String prefName, float value) {
        editor.putFloat(prefName, value);
        editor.commit();
    }

    public void setLong(String prefName, long value) {
        editor.putLong(prefName, value);
        editor.commit();
    }

    public void setBoolean(String prefName, Boolean value) {
        editor.putBoolean(prefName, value);
        editor.commit();
    }

    public String getString(String prefName) {
        return preferences.contains(prefName)
                ? preferences.getString(prefName, null)
                : "";
    }

    public int getInt(String prefName) {
        return preferences.getInt(prefName, 0);
    }

    public float getFloat(String prefName) {
        return preferences.getFloat(prefName, 0);
    }

    public long getLong(String prefName) {
        return preferences.getLong(prefName, 0);
    }

    public boolean isBoolean(String prefName) {
        return preferences.getBoolean(prefName, false);
    }

    public void removePreference(String prefName) {
        if (preferences.contains(prefName)) {
            editor.remove(prefName);
            editor.commit();
        }
    }

    public boolean keyExists(String prefName) {
        return preferences.contains(prefName);
    }
}
