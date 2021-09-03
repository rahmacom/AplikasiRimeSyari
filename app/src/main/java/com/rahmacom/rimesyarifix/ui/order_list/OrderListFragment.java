package com.rahmacom.rimesyarifix.ui.order_list;

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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rahmacom.rimesyarifix.data.model.Order;
import com.rahmacom.rimesyarifix.databinding.FragmentOrderListBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.ui.order.OrderFragmentDirections;
import com.rahmacom.rimesyarifix.ui.order.OrderViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OrderListFragment extends Fragment {

    public static final String STATUS_ID = "status_id";
    private FragmentOrderListBinding binding;
    private OrderViewModel viewModel;
    private NavController navController;
    private PreferenceManager manager;
    private OrderListRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOrderListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        manager = new PreferenceManager(requireContext());
        navController = Navigation.findNavController(requireParentFragment().requireView());
        viewModel = new ViewModelProvider(requireActivity()).get(OrderViewModel.class);

        loadOrders();
    }

    void setupList(ArrayList<Order> orders) {
        adapter = new OrderListRecyclerViewAdapter();
        adapter.setList(orders);
        binding.rvListOrder.setHasFixedSize(true);
        binding.rvListOrder.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvListOrder.setAdapter(adapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("orderList", "onDestroy");
        binding = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("orderList", "onPause");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("orderList", "onResume");
    }

    private void loadOrders() {
        viewModel.getAllOrders.observe(getViewLifecycleOwner(), orders -> {
            switch (orders.getStatus()) {
                case SUCCESS:
                    Log.d("orderList", orders.getData()
                            .toString());
                    setupList((ArrayList<Order>) orders.getData());

                    adapter.setOnItemClickListener(order -> {
                        OrderFragmentDirections.NavOrderToOrderDetailFragment action = OrderFragmentDirections.navOrderToOrderDetailFragment();
                        action.setOrderId(order.getId());
                        navController.navigate(action);
                    });
                case ERROR:
                case EMPTY:
                case LOADING:
                case UNPROCESSABLE_ENTITY:
                case UNAUTHORIZED:
            }
        });
    }
}