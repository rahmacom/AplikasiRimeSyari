package com.rahmacom.rimesyarifix.ui.pengaturan;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.rahmacom.rimesyarifix.R;

public class PengaturanFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}