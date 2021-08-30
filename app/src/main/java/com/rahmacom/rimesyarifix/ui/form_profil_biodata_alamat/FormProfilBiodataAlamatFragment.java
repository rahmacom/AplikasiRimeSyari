package com.rahmacom.rimesyarifix.ui.form_profil_biodata_alamat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.rahmacom.rimesyarifix.databinding.FragmentProfilBiodataAlamatBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;

public class FormProfilBiodataAlamatFragment extends Fragment {

    private FormProfilBiodataAlamatViewModel viewModel;
    private FragmentProfilBiodataAlamatBinding binding;
    private PreferenceManager manager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfilBiodataAlamatBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}