package com.rahmacom.rimesyarifix.ui.produk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.rahmacom.rimesyarifix.data.model.Cart;
import com.rahmacom.rimesyarifix.databinding.DialogFragmentProdukBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.ui.keranjang.KeranjangViewModel;
import com.rahmacom.rimesyarifix.ui.keranjang_detail.KeranjangDetailFragment;
import com.rahmacom.rimesyarifix.utils.Const;

import java.util.ArrayList;

import timber.log.Timber;

public class ProdukDialogFragment extends BottomSheetDialogFragment {

    private DialogFragmentProdukBinding binding;
    private KeranjangViewModel viewModel;
    private NavController navController;
    private ProdukDialogAdapter adapter;
    private ProdukDialogFragmentDirections.ProdukDialogFragmentToKeranjangDetailFragment action;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogFragmentProdukBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(KeranjangViewModel.class);
        navController = Navigation.findNavController(requireParentFragment().requireView());
        com.rahmacom.rimesyarifix.ui.produk.ProdukDialogFragmentArgs args = ProdukDialogFragmentArgs.fromBundle(getArguments());
        PreferenceManager manager = new PreferenceManager(requireContext());
        action = ProdukDialogFragmentDirections.produkDialogFragmentToKeranjangDetailFragment();

        int productId = args.getProductId();
        int colorId = args.getColorId();
        int sizeId = args.getSizeId();
        int jumlah = args.getJumlah();

        viewModel.setLiveToken(manager.getString(Const.KEY_TOKEN));
        viewModel.getAllCarts.observe(getViewLifecycleOwner(), carts -> {
            switch (carts.getStatus()) {
                case SUCCESS:
                    setupRecyclerView((ArrayList<Cart>) carts.getData());
                    adapter.setOnItemClickListener(cart -> updateCart(
                            cart.getId(),
                            productId, colorId, sizeId, jumlah));
                    break;

                case EMPTY:
                    Toast.makeText(requireContext(), "Tidak ada keranjang yang dapat ditampilkan", Toast.LENGTH_SHORT).show();
                    break;

                case ERROR:
                    Toast.makeText(requireContext(), "Terjadi error!" + carts.getMessage(), Toast.LENGTH_SHORT).show();
                    break;
            }
        });

        binding.btnProdukDialogKeranjangBaru.setOnClickListener(v -> newCart(productId, colorId, sizeId, jumlah));
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
        binding.rvProdukDialogListKeranjang.addItemDecoration(new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL));
        binding.rvProdukDialogListKeranjang.setHasFixedSize(true);
    }

    private void newCart(int productId, int colorId, int sizeId, int jumlah) {
        action.setViewState(KeranjangDetailFragment.IS_CREATING);
        action.setProductId(productId);
        action.setColorId(colorId);
        action.setSizeId(sizeId);
        action.setJumlah(jumlah);

        navController.navigate(action);
    }

    private void updateCart(int cartId, int productId, int colorId, int sizeId, int jumlah) {
        viewModel.setLiveKeranjang(cartId, productId, colorId, sizeId, jumlah);
        viewModel.addProductToCart.observe(getViewLifecycleOwner(), cart -> {
            Timber.d(cart.getStatus().toString());
            Timber.d(cart.getMessage());
            switch (cart.getStatus()) {
                case SUCCESS:
                    action.setViewState(KeranjangDetailFragment.IS_SHOWING);
                    action.setCartId(cartId);
                    navController.navigate(action);
                    Toast.makeText(requireContext(), "Produk berhasil ditambahkan", Toast.LENGTH_SHORT).show();
                    break;

                case EMPTY:
                case ERROR:
                case INVALID:
                case LOADING:
                case UNAUTHORIZED:
                case FORBIDDEN:
                case UNPROCESSABLE_ENTITY:
                    break;
            }
        });
    }
}