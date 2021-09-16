package com.rahmacom.rimesyarifix.ui.reseller_status_verifikasi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.rahmacom.rimesyarifix.data.model.UserVerification;
import com.rahmacom.rimesyarifix.data.vo.Resource;
import com.rahmacom.rimesyarifix.databinding.FragmentResellerStatusVerifikasiBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.ui.form_profil_biodata_alamat.FormProfilBiodataAlamatFragment;
import com.rahmacom.rimesyarifix.ui.reseller_kyc.ResellerKYCFragment;

import java.io.File;

import timber.log.Timber;

public class ResellerStatusVerifikasiFragment extends Fragment {

    private ResellerStatusVerifikasiViewModel viewModel;
    private FragmentResellerStatusVerifikasiBinding binding;
    private NavController navController;
    private PreferenceManager manager;
    private ResellerStatusVerifikasiFragmentArgs args;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentResellerStatusVerifikasiBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(ResellerStatusVerifikasiViewModel.class);
        navController = Navigation.findNavController(view);
        manager = new PreferenceManager(requireContext());
        args = ResellerStatusVerifikasiFragmentArgs.fromBundle(getArguments());

        setupToolbar();
        verificationStatus();
        checkUserEligibility();

        if (args.getImagePath() != null && args.getImageType() > 0) {
            uploadImage();
        }
    }

    private void setupToolbar() {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(binding.toolbarResellerStatusVerifikasi, navController, appBarConfiguration);
    }

    private void setDataBinding(UserVerification userVerification) {
        binding.tvResellerStatusVerifikasiFotoWajah.setText((userVerification.getFacePath() != null) ? "Selesai" : "Belum selesai");
        binding.tvResellerStatusVerifikasiFotoIdentitas.setText((userVerification.getIdCardPath() != null) ? "Selesai" : "Belum selesai");
        binding.tvResellerStatusVerifikasiJudul.setText(userVerification.getVerificationStatus().getName());

        binding.tvResellerStatusVerifikasiBiodata.setOnClickListener(v -> {
            ResellerStatusVerifikasiFragmentDirections.ResellerStatusVerifikasiFragmentToFormEditBiodataFragment action = ResellerStatusVerifikasiFragmentDirections.resellerStatusVerifikasiFragmentToFormEditBiodataFragment();
            action.setState(FormProfilBiodataAlamatFragment.IS_UPDATING);
            navController.navigate(action);
        });

        binding.tvResellerStatusVerifikasiFotoWajah.setOnClickListener(v -> openCamera(ResellerKYCFragment.KYC_FACE));
        binding.tvResellerStatusVerifikasiFotoIdentitas.setOnClickListener(v -> openCamera(ResellerKYCFragment.KYC_ID_CARD));
    }

    private void verificationStatus() {
        viewModel.verificationStatus.observe(getViewLifecycleOwner(), userVerificationObserver());
    }

    private void checkUserEligibility() {
        viewModel.checkIfUserIsEligible.observe(getViewLifecycleOwner(), isEligible -> {
            Timber.d(isEligible.getMessage());
            switch (isEligible.getStatus()) {
                case SUCCESS:
                    binding.tvResellerStatusVerifikasiBiodata.setText((isEligible.getData()) ? "Memenuhi syarat" : "Belum memenuhi syarat");
                    break;

                case LOADING:
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

    private void openCamera(int imageType) {
        ResellerStatusVerifikasiFragmentDirections.ResellerStatusVerifikasiFragmentToResellerKYCFragment action = ResellerStatusVerifikasiFragmentDirections.resellerStatusVerifikasiFragmentToResellerKYCFragment();
        action.setImageType(imageType);
        navController.navigate(action);
    }

    private void uploadImage() {
        int type = args.getImageType();
        File image = new File(args.getImagePath());

        viewModel.setLivePhoto(image, type);
        viewModel.uploadImage.observe(getViewLifecycleOwner(), userVerificationObserver());
    }

    private Observer<Resource<UserVerification>> userVerificationObserver() {
        return userVerification -> {
            switch (userVerification.getStatus()) {
                case SUCCESS:
                    setDataBinding(userVerification.getData());
                    break;

                case LOADING:
                    break;

                case EMPTY:
                case ERROR:
                case INVALID:
                case UNAUTHORIZED:
                case FORBIDDEN:
                case UNPROCESSABLE_ENTITY:
                    Toast.makeText(requireContext(), "Terjadi Error! Silahkan coba lagi atau hubungi admin Rime Syari", Toast.LENGTH_SHORT).show();
                    break;
            }
        };
    }
}