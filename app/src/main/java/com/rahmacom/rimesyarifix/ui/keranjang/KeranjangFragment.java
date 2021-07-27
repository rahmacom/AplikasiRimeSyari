package com.rahmacom.rimesyarifix.ui.keranjang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.rahmacom.rimesyarifix.databinding.FragmentKeranjangBinding;

public class KeranjangFragment extends Fragment {

    private FragmentKeranjangBinding binding;
    private KeranjangViewModel mViewModel;

    public static KeranjangFragment newInstance() {
        return new KeranjangFragment();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentKeranjangBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}