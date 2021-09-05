package com.rahmacom.rimesyarifix.ui.form_profil_biodata_alamat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.rahmacom.rimesyarifix.databinding.FragmentProfilBiodataAlamatBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;

public class FormProfilBiodataAlamatFragment extends Fragment {

    private FormProfilBiodataAlamatViewModel viewModel;
    private FragmentProfilBiodataAlamatBinding binding;
    private PreferenceManager manager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfilBiodataAlamatBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(FormProfilBiodataAlamatViewModel.class);
        manager = new PreferenceManager(requireContext());
    }
}