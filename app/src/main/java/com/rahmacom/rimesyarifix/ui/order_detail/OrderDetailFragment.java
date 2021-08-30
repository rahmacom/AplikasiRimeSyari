package com.rahmacom.rimesyarifix.ui.order_detail;

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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rahmacom.rimesyarifix.data.entity.Order;
import com.rahmacom.rimesyarifix.data.entity.Product;
import com.rahmacom.rimesyarifix.databinding.FragmentOrderDetailBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;
import com.rahmacom.rimesyarifix.utils.Helper;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OrderDetailFragment extends Fragment {

    private OrderDetailViewModel viewModel;
    private FragmentOrderDetailBinding binding;
    private NavController navController;
    private PreferenceManager manager;
    private OrderDetailAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOrderDetailBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(OrderDetailViewModel.class);
        navController = Navigation.findNavController(view);
        manager = new PreferenceManager(requireContext());
        adapter = new OrderDetailAdapter();

        setupToolbar();

        int orderId = OrderDetailFragmentArgs.fromBundle(getArguments())
                .getOrderId();

        viewModel.setLiveOrderId(manager.getString(Const.KEY_TOKEN), orderId);
        viewModel.viewOrder.observe(getViewLifecycleOwner(), order -> {
            switch (order.getStatus()) {
                case SUCCESS:
                    setDataBinding(order.getData());
                case EMPTY:
                case ERROR:
                case LOADING:
                case INVALID:
                case UNAUTHORIZED:
                case FORBIDDEN:
                case UNPROCESSABLE_ENTITY:
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupToolbar() {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(binding.toolbarOrderDetail, navController, appBarConfiguration);
    }

    private void setDataBinding(Order order) {
        binding.tvOrderDetailNomor.setText(order.getNomor());
        binding.tvOrderDetailAlamat.setText(order.getShipment()
                .getAlamat());
        binding.tvOrderDetailPesan.setText(order.getPesan());
        binding.tvOrderDetailTotalHarga.setText(Helper.convertToRP(order.getTotal()));
        binding.tvOrderDetailTotalJumlah.setText(String.format("%s item", order.getJumlah()));
        binding.tvOrderDetailStatus.setText(order.getStatus()
                .getName());

        setupRecyclerView((ArrayList<Product>) order.getProducts());
    }

    private void setupRecyclerView(ArrayList<Product> products) {
        adapter.setList(products);
        binding.rvOrderDetailProdukList.setAdapter(adapter);
        binding.rvOrderDetailProdukList.setHasFixedSize(true);
        binding.rvOrderDetailProdukList.setLayoutManager(new LinearLayoutManager(requireContext()));
    }
}