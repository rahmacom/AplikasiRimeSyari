package com.rahmacom.rimesyarifix.ui.reseller_status_verifikasi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.rahmacom.rimesyarifix.databinding.FragmentResellerStatusVerifikasiBinding;

public class ResellerStatusVerifikasiFragment extends Fragment {

    private ResellerStatusVerifikasiViewModel mViewModel;
    private FragmentResellerStatusVerifikasiBinding binding;

    public static ResellerStatusVerifikasiFragment newInstance() {
        return new ResellerStatusVerifikasiFragment();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentResellerStatusVerifikasiBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}