package com.rahmacom.rimesyarifix.ui.profil_settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.PreferenceFragmentCompat;

import com.rahmacom.rimesyarifix.R;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ProfilSettingsFragment extends PreferenceFragmentCompat {

    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        Objects.requireNonNull(container)
                .getContext()
                .setTheme(R.style.RimeSyariFix);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey);
    }
}