package com.rahmacom.rimesyarifix.ui.produk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rahmacom.rimesyarifix.databinding.FragmentProdukBinding;

public class ProdukFragment extends Fragment {

    private FragmentProdukBinding binding;
    private ProdukViewModel mViewModel;

    public static ProdukFragment newInstance() {
        return new ProdukFragment();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentProdukBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}