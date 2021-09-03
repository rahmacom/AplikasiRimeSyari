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
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.chip.Chip;
import com.rahmacom.rimesyarifix.data.model.Color;
import com.rahmacom.rimesyarifix.data.model.Product;
import com.rahmacom.rimesyarifix.data.model.Size;
import com.rahmacom.rimesyarifix.data.model.Testimony;
import com.rahmacom.rimesyarifix.data.vo.Status;
import com.rahmacom.rimesyarifix.databinding.FragmentProdukBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.ui.profil_testimoni.ProfilTestimoniAdapter;
import com.rahmacom.rimesyarifix.utils.Const;
import com.rahmacom.rimesyarifix.utils.Helper;

import java.util.ArrayList;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProdukFragment extends Fragment {

    private FragmentProdukBinding binding;
    private ProdukViewModel viewModel;
    private NavController navController;
    private PreferenceManager manager;
    private FotoProdukSliderAdapter fotoProdukSliderAdapter;
    private ProfilTestimoniAdapter testimoniAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProdukBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(ProdukViewModel.class);
        navController = Navigation.findNavController(view);
        manager = new PreferenceManager(requireContext());

        setupToolbar();

        int productId = ProdukFragmentArgs.fromBundle(getArguments())
                .getProductId();

        viewModel.setLiveToken(manager.getString(Const.KEY_TOKEN));
        viewModel.setProductId(productId);

        viewModel.viewProduct.observe(getViewLifecycleOwner(), product -> {
            if (product != null) {
                Log.d("produkFragment", product.getStatus()
                        .toString());
                switch (product.getStatus()) {
                    case SUCCESS:
                        Log.d("produkFragment", product.getData()
                                .toString());
                        setDataBinding(Objects.requireNonNull(product.getData()));
                        binding.toolbarFragmentProduk.setTitle(product.getData()
                                .getNama());
                        binding.btnProdukAction.setOnClickListener(v -> {
                            onBtnProdukActionClick(product.getData().getId());
                        });
                        break;

                    case EMPTY:
                        break;

                    case ERROR:
                        break;

                    case LOADING:
                        break;

                    case UNAUTHORIZED:
                        Log.d("produkFragment", product.getMessage());
                        break;
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private void setDataBinding(Product product) {
        fotoProdukSliderAdapter = new FotoProdukSliderAdapter();
        fotoProdukSliderAdapter.setImages(product.getImages());

        testimoniAdapter = new ProfilTestimoniAdapter();
        testimoniAdapter.setLists((ArrayList<Testimony>) product.getTestimonies());
        binding.rvTestimoniList.setAdapter(testimoniAdapter);
        binding.rvTestimoniList.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvTestimoniList.setHasFixedSize(true);

        binding.vpProdukSliderFoto.setAdapter(fotoProdukSliderAdapter);
        binding.tvProdukNama.setText(product.getNama());
        binding.tvProdukDeskripsi.setText(product.getDeskripsi());
        binding.tvProdukHarga.setText(Helper.convertToRP(product.getHarga()));
        binding.tvProdukSukaText.setText(String.valueOf(product.getSuka()));
        binding.tvProdukRatingText.setText(product.getReviewAvg() + " / 5 (" + product.getReviewCount() + " review)");

        createChips();
    }

    private void setupToolbar() {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(binding.toolbarFragmentProduk, navController, appBarConfiguration);
    }

    private void createChips() {
        viewModel.getProductColors.observe(getViewLifecycleOwner(), colors -> {
            if (colors.getStatus() == Status.SUCCESS) {
                for (Color color : colors.getData()) {
                    Chip chipWarna = new Chip(binding.chipgroupProdukWarna.getContext());
                    chipWarna.setCheckable(true);
                    chipWarna.setText(color.getName());
                    chipWarna.setId(color.getId());
                    binding.chipgroupProdukWarna.addView(chipWarna);
                }
            }
        });

        viewModel.getProductSizes.observe(getViewLifecycleOwner(), sizes -> {
            if (sizes.getStatus() == Status.SUCCESS) {
                for (Size size : sizes.getData()) {
                    Chip chipUkuran = new Chip(binding.chipgroupProdukUkuran.getContext());
                    chipUkuran.setCheckable(true);
                    chipUkuran.setText(size.getName());
                    chipUkuran.setId(size.getId());
                    binding.chipgroupProdukUkuran.addView(chipUkuran);
                }
            }
        });
    }

    private void onBtnProdukActionClick(int productId) {
        int colorId = binding.chipgroupProdukWarna.getCheckedChipId();
        int sizeId = binding.chipgroupProdukUkuran.getCheckedChipId();

        ProdukFragmentDirections.ProdukFragmentToProdukDialogFragment action = ProdukFragmentDirections.produkFragmentToProdukDialogFragment();
        action.setProductId(productId);
        action.setColorId(colorId);
        action.setSizeId(sizeId);

        navController.navigate(action);
    }
}