package com.rahmacom.rimesyarifix.ui.produk;

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

import com.rahmacom.rimesyarifix.data.entity.Product;
import com.rahmacom.rimesyarifix.databinding.FragmentProdukBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.ui.home.HomeViewModel;
import com.rahmacom.rimesyarifix.utils.Const;
import com.rahmacom.rimesyarifix.utils.Helper;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProdukFragment extends Fragment {

    private FragmentProdukBinding binding;
    private ProdukViewModel viewModel;
    private NavController navController;
    private PreferenceManager manager;
    private FotoProdukSliderAdapter adapter;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentProdukBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(ProdukViewModel.class);
        navController = Navigation.findNavController(view);
        manager = new PreferenceManager(requireContext());

        setupToolbar();

        int productId = ProdukFragmentArgs.fromBundle(getArguments()).getProductId();
        viewModel.setProductId(manager.getString(Const.KEY_TOKEN), productId);
        viewModel.viewProduct.observe(getViewLifecycleOwner(), product -> {
            if (product != null) {
                Log.d("produkFragment", product.getStatus().toString());
                switch (product.getStatus()) {
                    case SUCCESS:
                        Log.d("produkFragment", product.getData().toString());
                        setBinding(Objects.requireNonNull(product.getData()));
                        binding.toolbarFragmentProduk.setTitle(product.getData().getNama());
                        break;

                    case EMPTY:
                        break;

                    case ERROR:
                        break;

                    case LOADING:
                        break;

                    case UNAUTHORIZED:
                        Log.d("produkFragment", product.getMessage());
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private void setBinding(Product product) {
        adapter = new FotoProdukSliderAdapter();
        adapter.setImages(product.getImages());

        binding.vpProdukSliderFoto.setAdapter(adapter);
        binding.tvProdukNama.setText(product.getNama());
        binding.tvProdukDeskripsi.setText(product.getDeskripsi());
        binding.tvProdukHarga.setText(Helper.convertToRP(product.getHargaCustomer()));
    }

    private void setupToolbar() {
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph())
                        .build();
        NavigationUI.setupWithNavController(binding.toolbarFragmentProduk, navController, appBarConfiguration);
    }
}