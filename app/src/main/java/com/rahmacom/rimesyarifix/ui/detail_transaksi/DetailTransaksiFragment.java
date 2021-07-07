package com.rahmacom.rimesyarifix.ui.detail_transaksi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.databinding.FragmentDetailTransaksiBinding;

public class DetailTransaksiFragment extends Fragment {

    private DetailTransaksiViewModel mViewModel;
    private FragmentDetailTransaksiBinding binding;

    public static DetailTransaksiFragment newInstance() {
        return new DetailTransaksiFragment();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentDetailTransaksiBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}