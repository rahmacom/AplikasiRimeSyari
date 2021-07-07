package com.rahmacom.rimesyarifix.ui.form_pendaftaran;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.databinding.FragmentResellerFormPendaftaranBinding;

public class FormPendaftaranFragment extends Fragment {

    private FormPendaftaranViewModel mViewModel;
    private FragmentResellerFormPendaftaranBinding binding;

    public static FormPendaftaranFragment newInstance() {
        return new FormPendaftaranFragment();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = FragmentResellerFormPendaftaranBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }
}