package com.rahmacom.rimesyarifix.ui.reseller_kyc_preview;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.databinding.FragmentResellerKycCameraBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;

public class ResellerKYCPreviewFragment extends Fragment {

    private ResellerKYCPreviewViewModel viewModel;
    private FragmentResellerKycCameraBinding binding;
    private NavController navController;
    private PreferenceManager manager;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentResellerKycCameraBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(ResellerKYCPreviewViewModel.class);
        navController = Navigation.findNavController(view);
        manager = new PreferenceManager(requireContext());
    }
}