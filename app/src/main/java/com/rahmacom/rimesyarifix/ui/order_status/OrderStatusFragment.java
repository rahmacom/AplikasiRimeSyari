package com.rahmacom.rimesyarifix.ui.order_status;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rahmacom.rimesyarifix.databinding.FragmentOrderStatusBinding;

public class OrderStatusFragment extends Fragment {

    private OrderStatusViewModel mViewModel;
    private FragmentOrderStatusBinding binding;

    public static OrderStatusFragment newInstance() {
        return new OrderStatusFragment();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentOrderStatusBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("OrderStatus", "This is status pengiriman fragment");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}