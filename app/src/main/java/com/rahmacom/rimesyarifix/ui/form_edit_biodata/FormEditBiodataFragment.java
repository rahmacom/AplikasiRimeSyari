package com.rahmacom.rimesyarifix.ui.form_edit_biodata;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rahmacom.rimesyarifix.R;

public class FormEditBiodataFragment extends Fragment {

    private FormEditBiodataViewModel mViewModel;

    public static FormEditBiodataFragment newInstance() {
        return new FormEditBiodataFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_form_edit_biodata, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(FormEditBiodataViewModel.class);
        // TODO: Use the ViewModel
    }

}