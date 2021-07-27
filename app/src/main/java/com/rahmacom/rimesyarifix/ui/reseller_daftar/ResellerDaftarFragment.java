package com.rahmacom.rimesyarifix.ui.reseller_daftar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.rahmacom.rimesyarifix.databinding.FragmentResellerDaftarBinding;

public class ResellerDaftarFragment extends Fragment {

    private ResellerDaftarViewModel mViewModel;
    private FragmentResellerDaftarBinding binding;

    public static ResellerDaftarFragment newInstance() {
        return new ResellerDaftarFragment();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentResellerDaftarBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}