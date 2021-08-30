package com.rahmacom.rimesyarifix.ui.order_konfirmasi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.rahmacom.rimesyarifix.R;

public class OrderKonfirmasiFragment extends Fragment {

    private OrderKonfirmasiViewModel mViewModel;

    public static OrderKonfirmasiFragment newInstance() {
        return new OrderKonfirmasiFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_order_konfirmasi, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(OrderKonfirmasiViewModel.class);
        // TODO: Use the ViewModel
    }

}