package com.rahmacom.rimesyarifix.ui.form_edit_alamat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.rahmacom.rimesyarifix.databinding.FragmentFormEditAlamatBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;

public class FormEditAlamatFragment extends Fragment {

    private FormEditAlamatViewModel viewModel;
    private FragmentFormEditAlamatBinding binding;
    private PreferenceManager manager;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentFormEditAlamatBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}