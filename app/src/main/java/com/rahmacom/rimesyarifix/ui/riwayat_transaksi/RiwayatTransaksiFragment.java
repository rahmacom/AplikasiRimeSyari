package com.rahmacom.rimesyarifix.ui.riwayat_transaksi;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.rahmacom.rimesyarifix.databinding.FragmentRiwayatTransaksiBinding;

import org.jetbrains.annotations.NotNull;

public class RiwayatTransaksiFragment extends Fragment {

    private RiwayatTransaksiViewModel mViewModel;
    private FragmentRiwayatTransaksiBinding binding;

    public static RiwayatTransaksiFragment newInstance() {
        return new RiwayatTransaksiFragment();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentRiwayatTransaksiBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("RiwayatTransaksiFragment", "This is riwayat transaksi fragment");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}