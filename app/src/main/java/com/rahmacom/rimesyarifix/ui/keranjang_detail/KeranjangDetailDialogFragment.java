package com.rahmacom.rimesyarifix.ui.keranjang_detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.databinding.FragmentKeranjangDetailDialogBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.ui.order.OrderViewModel;

public class KeranjangDetailDialogFragment extends DialogFragment {

    private FragmentKeranjangDetailDialogBinding binding;
    private OrderViewModel viewModel;
    private PreferenceManager manager;
    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_keranjang_detail_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(OrderViewModel.class);
        manager = new PreferenceManager(requireContext());
        navController = Navigation.findNavController(requireParentFragment().requireView());
    }

    private void setDataBinding() {

    }
}