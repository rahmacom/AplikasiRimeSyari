package com.rahmacom.rimesyarifix.ui.pengiriman;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.rahmacom.rimesyarifix.databinding.FragmentPengirimanBinding;

public class PengirimanFragment extends Fragment {

    private PengirimanViewModel mViewModel;
    private FragmentPengirimanBinding binding;

    public static PengirimanFragment newInstance() {
        return new PengirimanFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPengirimanBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PengirimanViewModel.class);
        // TODO: Use the ViewModel
    }

}