package com.rahmacom.rimesyarifix.ui.profil_biodata_alamat;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.databinding.FragmentProfilBiodataAlamatBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProfilBiodataAlamatFragment extends Fragment {

    private FragmentProfilBiodataAlamatBinding binding;
    private ProfilBiodataAlamatViewModel viewModel;
    private PreferenceManager manager;
    private NavController navController;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = FragmentProfilBiodataAlamatBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}