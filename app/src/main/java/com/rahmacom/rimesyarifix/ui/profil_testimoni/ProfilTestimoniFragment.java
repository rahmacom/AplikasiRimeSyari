package com.rahmacom.rimesyarifix.ui.profil_testimoni;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rahmacom.rimesyarifix.R;

public class ProfilTestimoniFragment extends Fragment {

    private ProfilTestimoniViewModel mViewModel;

    public static ProfilTestimoniFragment newInstance() {
        return new ProfilTestimoniFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profil_testimoni, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ProfilTestimoniViewModel.class);
        // TODO: Use the ViewModel
    }

}