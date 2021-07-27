package com.rahmacom.rimesyarifix.ui.order_pengiriman;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.rahmacom.rimesyarifix.databinding.FragmentOrderPengirimanBinding;

public class OrderPengirimanFragment extends Fragment {

    private OrderPengirimanViewModel mViewModel;
    private FragmentOrderPengirimanBinding binding;

    public static OrderPengirimanFragment newInstance() {
        return new OrderPengirimanFragment();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentOrderPengirimanBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}