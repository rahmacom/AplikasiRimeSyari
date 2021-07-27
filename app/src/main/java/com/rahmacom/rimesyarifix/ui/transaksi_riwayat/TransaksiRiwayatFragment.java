package com.rahmacom.rimesyarifix.ui.transaksi_riwayat;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rahmacom.rimesyarifix.databinding.FragmentTransaksiRiwayatBinding;

public class TransaksiRiwayatFragment extends Fragment {

    private TransaksiRiwayatViewModel mViewModel;
    private FragmentTransaksiRiwayatBinding binding;

    public static TransaksiRiwayatFragment newInstance() {
        return new TransaksiRiwayatFragment();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentTransaksiRiwayatBinding.inflate(getLayoutInflater());
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