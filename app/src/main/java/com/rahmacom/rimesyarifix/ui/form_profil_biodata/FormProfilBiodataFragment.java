package com.rahmacom.rimesyarifix.ui.form_profil_biodata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.rahmacom.rimesyarifix.data.model.User;
import com.rahmacom.rimesyarifix.databinding.FragmentFormProfilBiodataBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FormProfilBiodataFragment extends Fragment {

    private FormProfilBiodataViewModel viewModel;
    private FragmentFormProfilBiodataBinding binding;
    private NavController navController;
    private PreferenceManager manager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFormProfilBiodataBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(FormProfilBiodataViewModel.class);
    }

    private void setDataBinding(User user) {
        binding.etFormFormProfilBiodataEditProfilNamaLengkap.setText(user.getNamaLengkap());
        binding.etFormFormProfilBiodataEditProfilTempatLahir.setText(user.getTempatLahir());
        binding.etFormFormProfilBiodataEditProfilTglLahir.setText(user.getTglLahir());
    }
}