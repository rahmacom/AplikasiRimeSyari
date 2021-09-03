package com.rahmacom.rimesyarifix.ui.reseller_info;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.rahmacom.rimesyarifix.databinding.FragmentResellerInfoBinding;
import com.rahmacom.rimesyarifix.utils.Const;

public class ResellerInfoFragment extends Fragment {

    private ResellerInfoViewModel viewModel;
    private FragmentResellerInfoBinding binding;

    public static ResellerInfoFragment newInstance() {
        return new ResellerInfoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentResellerInfoBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        viewModel.setLiveToken(manager.getString(Const.KEY_TOKEN));
        binding.btnMulaiKycVerifikasi.setOnClickListener(v -> {
            Navigation.findNavController(view)
                    .navigate(ResellerInfoFragmentDirections.resellerInfoFragmentToResellerKYCFragment());
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}