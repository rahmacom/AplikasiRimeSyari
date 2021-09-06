package com.rahmacom.rimesyarifix.ui.produk;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
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
import com.rahmacom.rimesyarifix.data.vo.Resource;
import com.rahmacom.rimesyarifix.data.vo.Status;
import com.rahmacom.rimesyarifix.databinding.FragmentProdukBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.ui.profil_testimoni.ProfilTestimoniAdapter;
import com.rahmacom.rimesyarifix.utils.Const;
import com.rahmacom.rimesyarifix.utils.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

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
        viewModel.setLiveProductId(productId);

        viewModel.viewProduct.observe(getViewLifecycleOwner(), product -> {
            if (product != null) {
                Timber.d(product.getStatus().toString());
                switch (product.getStatus()) {
                    case SUCCESS:
                        setDataBinding(Objects.requireNonNull(product.getData()));
                        binding.toolbarFragmentProduk.setTitle(product.getData()
                                .getNama());
                        binding.btnProdukAction.setOnClickListener(v -> {
                            int jumlah = 1;
                            if (Objects.equals(manager.getString(Const.KEY_ROLE), "reseller")) {
                                jumlah = product.getData().getResellerMinimum();
                            }
                            onBtnProdukActionClick(product.getData().getId(), jumlah);
                        });
                        break;

                    case EMPTY:
                        break;

                    case ERROR:
                    case LOADING:
                    case UNAUTHORIZED:
                        Timber.d(product.getMessage());
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
        binding.tvProdukDeskripsi.setText(HtmlCompat.fromHtml(product.getDeskripsi(), HtmlCompat.FROM_HTML_MODE_COMPACT));
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

        binding.chipgroupProdukWarna.setOnCheckedChangeListener((group, checkedId) -> {
            Timber.d("color_id: %s", String.valueOf(checkedId));
            viewModel.setLiveColorId(checkedId);
            viewModel.getProductSizes.removeObserver(productSizeObserver());
            viewModel.getProductSizes.observe(getViewLifecycleOwner(), productSizeObserver());
        });
    }

    private Observer<Resource<List<Size>>> productSizeObserver() {
        return sizes -> {
            if (sizes.getStatus() == Status.SUCCESS) {
                binding.chipgroupProdukUkuran.clearCheck();
                binding.chipgroupProdukUkuran.removeAllViews();
                for (Size size : sizes.getData()) {
                    Timber.d("data: %s", size.getName());
                    Chip chipUkuran = new Chip(binding.chipgroupProdukUkuran.getContext());
                    chipUkuran.setCheckable(true);
                    chipUkuran.setText(size.getName());
                    chipUkuran.setId(size.getId());
                    binding.chipgroupProdukUkuran.addView(chipUkuran);
                }
            }
        };
    }

    private void onBtnProdukActionClick(int productId, int jumlah) {
        int colorId = binding.chipgroupProdukWarna.getCheckedChipId();
        int sizeId = binding.chipgroupProdukUkuran.getCheckedChipId();

        ProdukFragmentDirections.ProdukFragmentToProdukDialogFragment action = ProdukFragmentDirections.produkFragmentToProdukDialogFragment();
        action.setProductId(productId);
        action.setColorId(colorId);
        action.setSizeId(sizeId);
        action.setJumlah(jumlah);

        navController.navigate(action);
    }
}