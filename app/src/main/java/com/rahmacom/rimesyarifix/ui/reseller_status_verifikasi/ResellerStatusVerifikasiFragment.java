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
import com.rahmacom.rimesyarifix.utils.Const;

import java.io.File;

import timber.log.Timber;

public class ResellerStatusVerifikasiFragment extends Fragment {

    private ResellerStatusVerifikasiViewModel viewModel;
    private FragmentResellerStatusVerifikasiBinding binding;
    private NavController navController;
    private PreferenceManager manager;
    private ResellerStatusVerifikasiFragmentArgs args;
    private boolean[] isEligible;

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

        viewModel.setLiveToken(manager.getString(Const.KEY_TOKEN));

        setupToolbar();
        verificationStatus();
        checkUserEligibility();

        if (args.getImagePath() != null && args.getImageType() > 0) {
            uploadImage();
        }

        isEligible = new boolean[]{false, false, false};
    }

    private void setupToolbar() {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(binding.toolbarResellerStatusVerifikasi, navController, appBarConfiguration);
    }

    private void setDataBinding(UserVerification userVerification) {
        Timber.d("setDataBinding");
        binding.btnFragmentResellerStatusVerifikasiCekFotoWajah.setText((userVerification.getFacePath() != null) ? "Selesai" : "Belum selesai");
        binding.btnFragmentResellerStatusVerifikasiCekFotoIdentitas.setText((userVerification.getIdCardPath() != null) ? "Selesai" : "Belum selesai");
        binding.tvResellerStatusVerifikasiJudul.setText(userVerification.getVerificationStatus().getName());

        binding.btnFragmentResellerStatusVerifikasiCekBiodata.setOnClickListener(v -> {
            ResellerStatusVerifikasiFragmentDirections.ResellerStatusVerifikasiFragmentToFormEditBiodataFragment action = ResellerStatusVerifikasiFragmentDirections.resellerStatusVerifikasiFragmentToFormEditBiodataFragment();
            action.setState(FormProfilBiodataAlamatFragment.IS_UPDATING);
            navController.navigate(action);
        });

        binding.btnFragmentResellerStatusVerifikasiCekFotoWajah.setOnClickListener(v -> openCamera(ResellerKYCFragment.KYC_FACE));
        binding.btnFragmentResellerStatusVerifikasiCekFotoIdentitas.setOnClickListener(v -> openCamera(ResellerKYCFragment.KYC_ID_CARD));
        binding.btnResellerStatusVerifikasiMulaiVerifikasi.setOnClickListener(v -> {
            if (isEligible[0] && isEligible[1] && isEligible[2]) {
                beginVerification();
            } else {
                Toast.makeText(requireContext(), "Silahkan lengkapi persyaratan terlebih dahulu", Toast.LENGTH_SHORT).show();
            }
        });

        if (userVerification.getFacePath() != null) {
            isEligible[1] = true;
        }

        if (userVerification.getIdCardPath() != null) {
            isEligible[2] = true;
        }
    }

    private void verificationStatus() {
        Timber.d("verificationStatus");
        viewModel.verificationStatus.observe(getViewLifecycleOwner(), userVerificationObserver);
    }

    private void beginVerification() {
        viewModel.beginVerification.observe(getViewLifecycleOwner(), userVerificationResource -> {
            Timber.d(userVerificationResource.getMessage());
            switch (userVerificationResource.getStatus()) {
                case SUCCESS:
                    ResellerStatusVerifikasiFragmentDirections.ResellerStatusVerifikasiFragmentToResellerInfoFragment action = ResellerStatusVerifikasiFragmentDirections.resellerStatusVerifikasiFragmentToResellerInfoFragment();
                    if (userVerificationResource.getData().getVerificationStatus().getId() == 3) {
                        Toast.makeText(requireContext(), "Verifikasi selesai! Anda sekarang menjadi seorang reseller", Toast.LENGTH_SHORT).show();
                        action.setVerificationStatusId(userVerificationResource.getData().getVerificationStatus().getId());
                        navController.navigate(action);
                    } else if (userVerificationResource.getData().getVerificationStatus().getId() == 4) {
                        Toast.makeText(requireContext(), "Verifikasi gagal! Anda dapat mencoba lagi", Toast.LENGTH_SHORT).show();
                        action.setVerificationStatusId(userVerificationResource.getData().getVerificationStatus().getId());
                        navController.navigate(action);
                    }
                    break;

                case LOADING:
                    Toast.makeText(requireContext(), "Tunggu sebentar...", Toast.LENGTH_SHORT).show();
                    break;

                case EMPTY:
                    Timber.d("empty 204");
                    break;
                case ERROR:
                    Timber.d("error 404");
                    break;
                case INVALID:
                    Timber.d("invalid 400");
                    break;
                case UNAUTHORIZED:
                    Timber.d("unathorized 401");
                    break;
                case FORBIDDEN:
                    Timber.d("forbidden 403");
                    break;
                case UNPROCESSABLE_ENTITY:
                    Toast.makeText(requireContext(), "Terjadi error! Silahkan hubungi admin rimesyari", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    private void checkUserEligibility() {
        viewModel.checkIfUserIsEligible.observe(getViewLifecycleOwner(), isEligible -> {
            Timber.d(isEligible.getMessage());
            switch (isEligible.getStatus()) {
                case SUCCESS:
                    binding.btnFragmentResellerStatusVerifikasiCekBiodata.setText((isEligible.getData()) ? "Memenuhi syarat" : "Belum memenuhi syarat");
                    this.isEligible[0] = isEligible.getData();
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
        Timber.d(image.getPath());
        Timber.d(String.valueOf(image.isFile()));

        viewModel.setLivePhoto(image, type);
        viewModel.uploadImage.observe(getViewLifecycleOwner(), userVerificationObserver);
    }

    private final Observer<Resource<UserVerification>> userVerificationObserver = userVerification -> {
        Timber.d(userVerification.getStatus().toString());
        Timber.d(userVerification.getMessage());
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