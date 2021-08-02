package com.rahmacom.rimesyarifix.ui.keranjang_checkout;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.databinding.FragmentKeranjangCheckoutBinding;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class KeranjangCheckoutFragment extends Fragment {

    private KeranjangCheckoutViewModel viewModel;
    private FragmentKeranjangCheckoutBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = FragmentKeranjangCheckoutBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(KeranjangCheckoutViewModel.class);
    }
}