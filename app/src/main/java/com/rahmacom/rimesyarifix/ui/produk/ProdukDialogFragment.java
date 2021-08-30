package com.rahmacom.rimesyarifix.ui.produk;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.rahmacom.rimesyarifix.data.entity.Cart;
import com.rahmacom.rimesyarifix.databinding.FragmentProdukDialogBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.ui.keranjang.KeranjangViewModel;
import com.rahmacom.rimesyarifix.utils.Const;

import java.util.ArrayList;
import java.util.Arrays;

public class ProdukDialogFragment extends BottomSheetDialogFragment {

    private FragmentProdukDialogBinding binding;
    private KeranjangViewModel viewModel;
    private NavController navController;
    private PreferenceManager manager;
    private ProdukDialogAdapter adapter;
    private ProdukDialogFragmentArgs args;
    private ProdukDialogFragmentDirections.ProdukDialogFragmentToKeranjangDetailFragment action;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProdukDialogBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(KeranjangViewModel.class);
        navController = Navigation.findNavController(getParentFragment().requireView());
        args = ProdukDialogFragmentArgs.fromBundle(getArguments());
        manager = new PreferenceManager(requireContext());
        action = ProdukDialogFragmentDirections.produkDialogFragmentToKeranjangDetailFragment();

        int productId = args.getProductId();
        int colorId = args.getColorId();
        int sizeId = args.getSizeId();

        viewModel.setLiveToken(manager.getString(Const.KEY_TOKEN));

        viewModel.getAllCarts.observe(getViewLifecycleOwner(), carts -> {
            if (carts.getData() != null) {
                switch (carts.getStatus()) {
                    case SUCCESS:
                        setupRecyclerView((ArrayList<Cart>) carts.getData());
                        adapter.setOnItemClickListener(cart -> {

                        });
                        break;

                    case EMPTY:
                        break;

                    case ERROR:
                        break;
                }
            }
        });

        binding.btnProdukDialogKeranjangBaru.setOnClickListener(v -> {
            viewModel.setKeranjang(manager.getString(Const.KEY_TOKEN), productId, colorId, sizeId);
            viewModel.newCart.observe(getViewLifecycleOwner(), cart -> {
                switch (cart.getStatus()) {
                    case SUCCESS:
                        action.setCartId(cart.getData().getId());
                        navController.navigate(action);
                        break;

                    case INVALID:
                        break;

                    case ERROR:
                        break;

                    case UNPROCESSABLE_ENTITY:
                        break;
                }
            });
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void setupRecyclerView(ArrayList<Cart> items) {
        adapter = new ProdukDialogAdapter();
        adapter.setList(items);
        binding.rvProdukDialogListKeranjang.setAdapter(adapter);
        binding.rvProdukDialogListKeranjang.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvProdukDialogListKeranjang.setHasFixedSize(true);
    }


}