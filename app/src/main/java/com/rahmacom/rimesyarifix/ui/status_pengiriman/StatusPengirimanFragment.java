package com.rahmacom.rimesyarifix.ui.status_pengiriman;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.databinding.FragmentStatusPengirimanBinding;

import org.jetbrains.annotations.NotNull;

public class StatusPengirimanFragment extends Fragment {

    private StatusPengirimanViewModel mViewModel;
    private FragmentStatusPengirimanBinding binding;

    public static StatusPengirimanFragment newInstance() {
        return new StatusPengirimanFragment();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentStatusPengirimanBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("StatusPengirimanFragment", "This is status pengiriman fragment");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}