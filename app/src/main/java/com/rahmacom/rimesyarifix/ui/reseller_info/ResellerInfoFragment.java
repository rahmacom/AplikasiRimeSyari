package com.rahmacom.rimesyarifix.ui.reseller_info;

import android.os.Bundle;
import android.util.Log;
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

import com.rahmacom.rimesyarifix.databinding.FragmentResellerInfoBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

@AndroidEntryPoint
public class ResellerInfoFragment extends Fragment {
    private ResellerInfoViewModel viewModel;
    private FragmentResellerInfoBinding binding;
    private PreferenceManager manager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentResellerInfoBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(ResellerInfoViewModel.class);
        manager = new PreferenceManager(requireContext());
        navController = Navigation.findNavController(view);

        setupToolbar();

        viewModel.setLiveToken(manager.getString(Const.KEY_TOKEN));
        binding.btnMulaiKycVerifikasi.setOnClickListener(v -> newVerification());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupToolbar() {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(binding.toolbarResellerInfo, navController, appBarConfiguration);
    }

    private void newVerification() {
        viewModel.beginResellerVerification.observe(getViewLifecycleOwner(), userVerificationResource -> {
            Timber.d(userVerificationResource.getMessage());
            switch (userVerificationResource.getStatus()) {
                case SUCCESS:
                    navController.navigate(ResellerInfoFragmentDirections.resellerInfoFragmentToResellerStatusVerifikasiFragment(null));
                    break;

                case EMPTY:
                case ERROR:
                case INVALID:
                case UNAUTHORIZED:
                case FORBIDDEN:
                case UNPROCESSABLE_ENTITY:
                    break;
            }
        });
    }
}