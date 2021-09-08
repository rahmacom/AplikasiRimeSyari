package com.rahmacom.rimesyarifix.ui.form_profil_biodata_alamat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.rahmacom.rimesyarifix.databinding.FragmentFormProfilBiodataAlamatBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;

public class FormProfilBiodataAlamatFragment extends Fragment {

    private FormProfilBiodataAlamatViewModel viewModel;
    private FragmentFormProfilBiodataAlamatBinding binding;
    private PreferenceManager manager;
    private NavController navController;
    private FormProfilBiodataAlamatFragmentArgs args;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFormProfilBiodataAlamatBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(FormProfilBiodataAlamatViewModel.class);
        manager = new PreferenceManager(requireContext());
        navController = Navigation.findNavController(view);
        args = FormProfilBiodataAlamatFragmentArgs.fromBundle(getArguments());

        setupToolbar();
    }

    private void setupToolbar() {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(binding.toolbarFormEditAlamat, navController, appBarConfiguration);
    }

    private void setDataBinding() {

    }
}