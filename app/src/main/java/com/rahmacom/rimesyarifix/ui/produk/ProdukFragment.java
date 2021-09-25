package com.rahmacom.rimesyarifix.ui.produk;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.chip.Chip;
import com.rahmacom.rimesyarifix.R;
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
    private ProdukFragmentArgs args;

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
        args = ProdukFragmentArgs.fromBundle(getArguments());

        setupToolbar();;

        viewModel.setLiveToken(manager.getString(Const.KEY_TOKEN));
        viewModel.setLiveProductId(args.getProductId());

        viewModel.viewProduct.observe(getViewLifecycleOwner(), product -> {
            switch (product.getStatus()) {
                case SUCCESS:
                    setDataBinding(product.getData());
                    binding.toolbarFragmentProduk.setTitle(product.getData()
                            .getNama());
                    binding.btnProdukAction.setOnClickListener(v -> {
                        int jumlah = 1;
                        if (Objects.equals(manager.getString(Const.KEY_ROLE), "reseller")) {
                            jumlah = product.getData().getResellerMinimum();
                        }
                        onBtnProdukActionClick(jumlah);
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
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private void setDataBinding(Product product) {
        FotoProdukSliderAdapter fotoProdukSliderAdapter = new FotoProdukSliderAdapter();
        fotoProdukSliderAdapter.setImages(product.getImages());

        ProfilTestimoniAdapter testimoniAdapter = new ProfilTestimoniAdapter();
        testimoniAdapter.setLists((ArrayList<Testimony>) product.getTestimonies());
        binding.rvTestimoniList.setAdapter(testimoniAdapter);
        binding.rvTestimoniList.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvTestimoniList.setHasFixedSize(true);

        binding.vpProdukSliderFoto.setAdapter(fotoProdukSliderAdapter);
        binding.tvProdukNama.setText(product.getNama());
        binding.tvProdukDeskripsi.setText(HtmlCompat.fromHtml(product.getDeskripsi(), HtmlCompat.FROM_HTML_MODE_COMPACT));
        binding.tvProdukHarga.setText(Helper.convertToRP(product.getHarga()));
        binding.tvProdukSukaText.setText(String.valueOf(product.getLikesCount()));
        binding.tvProdukRatingText.setText(product.getReviewAvg() + " / 5 (" + product.getReviewCount() + " review)");

        if (product.isLiked()) {
            binding.ivProdukSuka.setColorFilter(ContextCompat.getColor(requireContext(), R.color.pink_400));
        } else {
            binding.ivProdukSuka.setColorFilter(ContextCompat.getColor(requireContext(), R.color.gray_600));
        }

        binding.tvProdukRatingText.setOnClickListener(v -> {
            ProdukFragmentDirections.ProdukFragmentToProdukTestimoniFragment action = ProdukFragmentDirections.produkFragmentToProdukTestimoniFragment();
            action.setProductId(product.getId());
            navController.navigate(action);
        });

        binding.ivProdukSuka.setOnClickListener(v -> {
            likeProduct(product.isLiked());
            product.setLiked(!product.isLiked());
        });

        binding.chipgroupProdukUkuran.removeAllViews();
        binding.chipgroupProdukWarna.removeAllViews();
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

    private void onBtnProdukActionClick(int jumlah) {
        int productId = args.getProductId();
        int colorId = binding.chipgroupProdukWarna.getCheckedChipId();
        int sizeId = binding.chipgroupProdukUkuran.getCheckedChipId();

        Timber.d("size_id: %s", sizeId);

        if (colorId < 1 && sizeId < 0) {
            Toast.makeText(requireContext(), "Pilih warna dan ukuran terlebih dahulu", Toast.LENGTH_SHORT).show();
        } else {
            ProdukFragmentDirections.ProdukFragmentToProdukDialogFragment action = ProdukFragmentDirections.produkFragmentToProdukDialogFragment();
            action.setProductId(productId);
            action.setColorId(colorId);
            action.setSizeId(sizeId);
            action.setJumlah(jumlah);

            navController.navigate(action);
        }
    }

    private void likeProduct(boolean liked) {
        LiveData<Resource<Integer>> liveData = null;
        if (liked) {
            liveData = viewModel.likeProduct;
        } else {
            liveData = viewModel.dislikeProduct;
        }

        liveData.observe(getViewLifecycleOwner(), integer -> {
            Timber.d(integer.getMessage());
            switch (integer.getStatus()) {
                case SUCCESS:
                    if (liked) {
                        binding.ivProdukSuka.setColorFilter(ContextCompat.getColor(requireContext(), R.color.pink_400));
                    } else {
                        binding.ivProdukSuka.setColorFilter(ContextCompat.getColor(requireContext(), R.color.gray_600));
                    }
                    binding.tvProdukSukaText.setText(String.valueOf(integer.getData()));
                    break;

                case LOADING:
                    break;

                case EMPTY:
                case ERROR:
                    Toast.makeText(requireContext(), "Terjadi error! Silahkan coba lagi", Toast.LENGTH_SHORT).show();
                    break;

                case INVALID:
                case UNAUTHORIZED:
                case FORBIDDEN:
                case UNPROCESSABLE_ENTITY:
                    Toast.makeText(requireContext(), "Tidak dapat memproses permintaan. Silahkan coba lagi", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }
}