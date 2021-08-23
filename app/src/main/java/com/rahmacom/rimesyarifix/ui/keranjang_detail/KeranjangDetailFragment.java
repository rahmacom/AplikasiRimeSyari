package com.rahmacom.rimesyarifix.ui.keranjang_detail;

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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rahmacom.rimesyarifix.data.entity.Cart;
import com.rahmacom.rimesyarifix.data.entity.Product;
import com.rahmacom.rimesyarifix.databinding.FragmentKeranjangDetailBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;
import com.rahmacom.rimesyarifix.utils.Helper;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class KeranjangDetailFragment extends Fragment {

    private FragmentKeranjangDetailBinding binding;
    private KeranjangDetailViewModel viewModel;
    private PreferenceManager manager;
    private KeranjangDetailAdapter adapter;
    private NavController navController;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentKeranjangDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(KeranjangDetailViewModel.class);
        navController = Navigation.findNavController(view);
        manager = new PreferenceManager(requireContext());

        setupToolbar();

        int cartId = KeranjangDetailFragmentArgs.fromBundle(getArguments()).getCartId();
        viewModel.setCartId(manager.getString(Const.KEY_TOKEN), cartId);
        viewModel.viewCart.observe(getViewLifecycleOwner(), cart -> {
            Log.d("cartDetail", String.valueOf(cart.getStatus()));
            switch (cart.getStatus()) {
                case SUCCESS:
                    setDataBinding(cart.getData());
                case ERROR:
                case EMPTY:
                case LOADING:
                case UNAUTHORIZED:
                case INVALID:
                case FORBIDDEN:
                case UNPROCESSABLE_ENTITY:
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private void setupToolbar() {
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(binding.toolbarKeranjangDetail, navController, appBarConfiguration);
    }

    private void setDataBinding(Cart cart) {
        binding.tvKeranjangDetailJudul.setText(cart.getJudul());
        binding.tvKeranjangDetailDeskripsi.setText(cart.getDeskripsi());
        binding.tvKeranjangDetailTotalJumlah.setText(cart.getJumlah() + " item");
        binding.tvKeranjangDetailTotalHarga.setText(Helper.convertToRP(cart.getTotal()));

        setupRecyclerView((ArrayList<Product>) cart.getProducts());
    }

    private void setupRecyclerView(ArrayList<Product> items) {
        adapter = new KeranjangDetailAdapter();
        adapter.setLists(items);
        binding.rvKeranjangDetailProdukList.setAdapter(adapter);
        binding.rvKeranjangDetailProdukList.setHasFixedSize(true);
        binding.rvKeranjangDetailProdukList.setLayoutManager(new LinearLayoutManager(requireContext()));
    }
}