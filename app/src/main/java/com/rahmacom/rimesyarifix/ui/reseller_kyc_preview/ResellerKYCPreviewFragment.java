package com.rahmacom.rimesyarifix.ui.reseller_kyc_preview;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.rahmacom.rimesyarifix.databinding.FragmentResellerKycPreviewBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

@AndroidEntryPoint
public class ResellerKYCPreviewFragment extends Fragment {

    private ResellerKYCPreviewViewModel viewModel;
    private FragmentResellerKycPreviewBinding binding;
    private NavController navController;
    private PreferenceManager manager;
    private ResellerKYCPreviewFragmentArgs args;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentResellerKycPreviewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(ResellerKYCPreviewViewModel.class);
        navController = Navigation.findNavController(view);
        manager = new PreferenceManager(requireContext());
        args = ResellerKYCPreviewFragmentArgs.fromBundle(getArguments());

        setupToolbar();
        Glide.with(view)
                .load(args.getFileUri())
                .into(binding.ivResellerKycPreviewFoto);

        binding.btnResellerKycPreviewGunakan.setOnClickListener(v -> useImage());
        binding.btnResellerKycPreviewAmbilBaru.setOnClickListener(v -> retakeImage());
    }

    private void setupToolbar() {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(binding.toolbarResellerKycPreview, navController, appBarConfiguration);
        binding.toolbarResellerKycPreview.setTitle("Hasil foto");
    }

    private void useImage() {
        ResellerKYCPreviewFragmentDirections.ResellerKYCPreviewFragmentToResellerStatusVerifikasiFragment action =
                ResellerKYCPreviewFragmentDirections
                        .resellerKYCPreviewFragmentToResellerStatusVerifikasiFragment(args.getFileUri());
        action.setImageType(args.getImageType());
        navController.navigate(action);
    }

    private void retakeImage() {
        navController.popBackStack();
    }

}