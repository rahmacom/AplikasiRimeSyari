package com.rahmacom.rimesyarifix.ui.transaksi_detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.rahmacom.rimesyarifix.databinding.FragmentTransaksiDetailBinding;

public class TransaksiDetailFragment extends Fragment {

    private TransaksiDetailViewModel mViewModel;
    private FragmentTransaksiDetailBinding binding;

    public static TransaksiDetailFragment newInstance() {
        return new TransaksiDetailFragment();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentTransaksiDetailBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}