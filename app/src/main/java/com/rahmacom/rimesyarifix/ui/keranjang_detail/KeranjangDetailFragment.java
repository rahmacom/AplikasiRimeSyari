package com.rahmacom.rimesyarifix.ui.keranjang_detail;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.data.model.Cart;
import com.rahmacom.rimesyarifix.data.model.Product;
import com.rahmacom.rimesyarifix.databinding.FragmentKeranjangDetailBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;
import com.rahmacom.rimesyarifix.utils.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class KeranjangDetailFragment extends Fragment {

    private FragmentKeranjangDetailBinding binding;
    private KeranjangDetailViewModel viewModel;
    private KeranjangDetailAdapter adapter;
    private NavController navController;
    private KeranjangDetailFragmentArgs args;
    private PreferenceManager manager;

    public static final int IS_CREATING = 0;
    public static final int IS_UPDATING = 1;
    public static final int IS_SHOWING = 2;

    private int state;

    private List<Integer> productIds = new ArrayList<>();
    private List<Integer> productPrices = new ArrayList<>();
    private List<Integer> colorIds = new ArrayList<>();
    private List<Integer> sizeIds = new ArrayList<>();
    private List<Integer> quantities = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentKeranjangDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(KeranjangDetailViewModel.class);
        navController = Navigation.findNavController(view);
        args = KeranjangDetailFragmentArgs.fromBundle(getArguments());
        manager = new PreferenceManager(requireContext());

        state = args.getViewState();
        viewModel.setLiveToken(manager.getString(Const.KEY_TOKEN));

        setupToolbar();
        setToolbarViewState(state);
        showCartDetail(args.getCartId());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;

        productIds.clear();
        colorIds.clear();
        sizeIds.clear();
        quantities.clear();
        productPrices.clear();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_keranjang_simpan:
                if (args.getViewState() == IS_CREATING) {
                    newCart();
                    return true;
                }

                if (args.getViewState() == IS_SHOWING) {
                    Log.d("update", String.valueOf(args.getViewState()));
                    updateCart();
                    return true;
                }

                return false;

            case R.id.menu_keranjang_hapus:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar() {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(binding.toolbarKeranjangDetail, navController, appBarConfiguration);

        binding.toolbarKeranjangDetail.setOnMenuItemClickListener(this::onOptionsItemSelected);
    }

    private void setDataBinding(Cart cart) {
        binding.etKeranjangDetailJudul.setText(cart.getJudul());
        binding.etKeranjangDetailDeskripsi.setText(cart.getDeskripsi());
        binding.tvKeranjangDetailTotalJumlah.setText(String.valueOf(cart.getJumlah()));
        binding.tvKeranjangDetailTotalHarga.setText(Helper.convertToRP(cart.getTotal()));

        List<Product> products = cart.getProducts();
        setupRecyclerView((ArrayList<Product>) products);

        for (Product product : products) {
            productIds.add(product.getId());
            productPrices.add(product.getHarga());
            colorIds.add(product.getPivot().getColorId());
            sizeIds.add(product.getPivot().getSizeId());
            quantities.add(product.getPivot().getJumlah());
        }

        binding.btnKeranjangDetailBuatOrder.setOnClickListener(v -> {
            createOrder();
        });

        adapter.setOnProductItemChangedListener((product, jumlah) -> {
            if (product.getPivot().getJumlah() != jumlah) {
                int position = products.indexOf(product);
                quantities.set(position, jumlah);
                setToolbarViewState(IS_UPDATING);

                Log.d("getChecked", Arrays.toString(adapter.getCheckedProducts().toArray()));
            } else {
                setToolbarViewState(IS_SHOWING);
            }
        });

        binding.etKeranjangDetailJudul.addTextChangedListener(watchEditTexts());
        binding.etKeranjangDetailDeskripsi.addTextChangedListener(watchEditTexts());
    }

    private void setupRecyclerView(ArrayList<Product> items) {
        adapter = new KeranjangDetailAdapter();
        adapter.setLists(items);
        binding.rvKeranjangDetailProdukList.setAdapter(adapter);
        binding.rvKeranjangDetailProdukList.setHasFixedSize(true);
        binding.rvKeranjangDetailProdukList.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void setToolbarViewState(int state) {
        if (binding.toolbarKeranjangDetail.getMenu() != null) {
            binding.toolbarKeranjangDetail.getMenu().clear();
        }

        switch (state) {
            case IS_CREATING:
            case IS_UPDATING:
                binding.toolbarKeranjangDetail.inflateMenu(R.menu.menu_keranjang_detail_update);
                break;

            case IS_SHOWING:
                binding.toolbarKeranjangDetail.inflateMenu(R.menu.menu_keranjang_detail);
                break;
        }
    }

    private void showCartDetail(int cartId) {
        viewModel.setLiveKeranjang(cartId);
        viewModel.viewCart.observe(getViewLifecycleOwner(), cart -> {
            switch (cart.getStatus()) {
                case SUCCESS:
                    setDataBinding(cart.getData());
                    break;

                case LOADING:
                case ERROR:
                case EMPTY:
                case UNAUTHORIZED:
                case FORBIDDEN:
                case INVALID:
                case UNPROCESSABLE_ENTITY:
                    break;
            }
        });
    }

    private void newCart() {
        String judul = binding.etKeranjangDetailJudul.getText().toString();
        String deskripsi = binding.etKeranjangDetailDeskripsi.getText().toString();
        int productId = args.getProductId();
        int colorId = args.getColorId();
        int sizeId = args.getSizeId();
        int jumlah = 1;

        viewModel.setLiveKeranjang(judul, deskripsi, productId, colorId, sizeId, jumlah);
        viewModel.newCart.observe(getViewLifecycleOwner(), cart -> {
            Log.d("newCart", cart.getStatus().toString());
            switch (cart.getStatus()) {
                case SUCCESS:
                    Toast.makeText(requireContext(), "Keranjang berhasil dibuat", Toast.LENGTH_SHORT).show();
                    setDataBinding(cart.getData());
                    break;

                case LOADING:
                case ERROR:
                case EMPTY:
                case UNAUTHORIZED:
                case FORBIDDEN:
                case INVALID:
                case UNPROCESSABLE_ENTITY:
                    break;
            }
        });
    }

    private void updateCart() {
        int cartId = args.getCartId();
        String judul = binding.etKeranjangDetailJudul.getText().toString();
        String deskripsi = binding.etKeranjangDetailDeskripsi.getText().toString();

        viewModel.setLiveKeranjang(cartId, judul, deskripsi, productIds, colorIds, sizeIds, quantities);
        viewModel.updateCartWithDetails.observe(getViewLifecycleOwner(), cart -> {
            Log.d("cartUpdate", cart.getStatus().toString());
            switch (cart.getStatus()) {
                case SUCCESS:
                    Toast.makeText(requireContext(), "Keranjang berhasil diupdate", Toast.LENGTH_SHORT).show();
                    setDataBinding(cart.getData());
                    break;

                case LOADING:
                case ERROR:
                case EMPTY:
                case UNAUTHORIZED:
                case FORBIDDEN:
                case INVALID:
                case UNPROCESSABLE_ENTITY:
                    break;
            }
        });

        productIds.clear();
        colorIds.clear();
        sizeIds.clear();
        quantities.clear();
    }

    private TextWatcher watchEditTexts() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                setToolbarViewState(IS_UPDATING);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    private void createOrder() {
        KeranjangDetailFragmentDirections.KeranjangDetailFragmentToOrderNewFragment action =
                KeranjangDetailFragmentDirections.keranjangDetailFragmentToOrderNewFragment();

        action.setCartId(args.getCartId());
        action.setProductId(Helper.convertToIntArray(productIds));
        action.setColorId(Helper.convertToIntArray(colorIds));
        action.setSizeId(Helper.convertToIntArray(sizeIds));
        action.setJumlah(Helper.convertToIntArray(quantities));
        action.setProductHarga(Helper.convertToIntArray(productPrices));

        navController.navigate(action);
    }
}