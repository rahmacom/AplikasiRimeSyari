package com.rahmacom.rimesyarifix.ui.status_verifikasi;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rahmacom.rimesyarifix.databinding.FragmentStatusVerifikasiBinding;

public class StatusVerifikasiFragment extends Fragment {

    private StatusVerifikasiViewModel mViewModel;
    private FragmentStatusVerifikasiBinding binding;

    public static StatusVerifikasiFragment newInstance() {
        return new StatusVerifikasiFragment();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentStatusVerifikasiBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}