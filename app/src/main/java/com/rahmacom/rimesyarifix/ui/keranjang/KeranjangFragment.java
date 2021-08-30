package com.rahmacom.rimesyarifix.ui.keranjang;

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

import com.rahmacom.rimesyarifix.data.entity.Cart;
import com.rahmacom.rimesyarifix.databinding.FragmentKeranjangBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class KeranjangFragment extends Fragment {

    private KeranjangViewModel viewModel;
    private PreferenceManager manager;
    private NavController navController;
    private FragmentKeranjangBinding binding;
    private KeranjangListAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentKeranjangBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        manager = new PreferenceManager(requireContext());
        navController = Navigation.findNavController(view);
        viewModel = new ViewModelProvider(requireActivity()).get(KeranjangViewModel.class);

        setupActionBar();

        viewModel.setLiveToken(manager.getString(Const.KEY_TOKEN));
        viewModel.getAllCarts.observe(getViewLifecycleOwner(), carts -> {
            switch (carts.getStatus()) {
                case SUCCESS:
                    setupRecyclerView((ArrayList<Cart>) carts.getData());
                    adapter.setOnItemClickListener(cart -> {
                        KeranjangFragmentDirections.KeranjangFragmentToKeranjangDetailFragment action = KeranjangFragmentDirections.keranjangFragmentToKeranjangDetailFragment();
                        action.setCartId(cart.getId());
                        navController.navigate(action);
                    });
                case LOADING:
                case ERROR:
                case INVALID:
                case FORBIDDEN:
                case EMPTY:
                case UNPROCESSABLE_ENTITY:
            }
        });
    }

    void setupRecyclerView(ArrayList<Cart> carts) {
        adapter = new KeranjangListAdapter();
        adapter.setCarts(carts);
        binding.rvKeranjangList.setAdapter(adapter);
        binding.rvKeranjangList.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    void setupActionBar() {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(binding.toolbarFragmentKeranjang, navController, appBarConfiguration);
    }
}