package com.rahmacom.rimesyarifix.ui.order_konfirmasi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.databinding.FragmentOrderKonfirmasiBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OrderKonfirmasiFragment extends Fragment {
    private OrderKonfirmasiViewModel viewModel;
    private NavController navController;
    private PreferenceManager manager;
    private FragmentOrderKonfirmasiBinding binding;
    private OrderKonfirmasiFragmentArgs args;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderKonfirmasiBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        manager = new PreferenceManager(requireContext());
        navController = Navigation.findNavController(view);
        viewModel = new ViewModelProvider(requireActivity()).get(OrderKonfirmasiViewModel.class);
        args = OrderKonfirmasiFragmentArgs.fromBundle(getArguments());

        binding.btnOrderKonfirmasiToHome.setOnClickListener(v -> navToHome());
        binding.btnOrderKonfirmasToDetail.setOnClickListener(v -> navToOrderDetail());
    }

    private void navToHome() {
        navController.navigate(OrderKonfirmasiFragmentDirections.orderKonfirmasiFragmentToNavHome());
    }

    private void navToOrderDetail() {
        OrderKonfirmasiFragmentDirections.ActionOrderKonfirmasiFragmentToOrderDetailFragment action = OrderKonfirmasiFragmentDirections.actionOrderKonfirmasiFragmentToOrderDetailFragment();
        action.setOrderId(args.getOrderId());
        navController.navigate(action);
    }
}