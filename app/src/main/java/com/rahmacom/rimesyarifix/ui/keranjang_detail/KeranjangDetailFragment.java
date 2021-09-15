package com.rahmacom.rimesyarifix.ui.keranjang_detail;

import android.app.AlertDialog;
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
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.data.model.Cart;
import com.rahmacom.rimesyarifix.data.model.Product;
import com.rahmacom.rimesyarifix.data.vo.Resource;
import com.rahmacom.rimesyarifix.data.vo.Status;
import com.rahmacom.rimesyarifix.databinding.FragmentKeranjangDetailBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;
import com.rahmacom.rimesyarifix.utils.Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

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

    private List<Integer> productIds;
    private List<Integer> productPrices;
    private List<Integer> colorIds;
    private List<Integer> sizeIds;
    private List<Integer> quantities;

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
        showCartDetail(args.getCartId());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_keranjang_simpan:
                if (state == IS_CREATING) {
                    Timber.d("IS_CREATING");
                    newCart();
                    return true;
                }

                if (state == IS_UPDATING) {
                    Timber.d("IS_UPDATING");
                    updateCart();
                    return true;
                }

                return false;

            case R.id.menu_keranjang_hapus:
                if (state == IS_CREATING) {
                    item.setTitle("Batal");
                    navController.popBackStack();
                    return true;
                } else {
                    item.setTitle("Hapus");
                    AlertDialog builder = new AlertDialog.Builder(requireContext())
                            .setIcon(R.drawable.icon_info)
                            .setPositiveButton("Ya", (dialog, which) -> deleteCart())
                            .setNegativeButton("Tidak", (dialog, which) -> dialog.dismiss())
                            .setTitle("Hapus keranjang?")
                            .setMessage("Anda yakin ingin menghapus keranjang ini?")
                            .setCancelable(true)
                            .create();
                    builder.show();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
        clear();
    }

    private void setupToolbar() {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(binding.toolbarKeranjangDetail, navController, appBarConfiguration);

        binding.toolbarKeranjangDetail.inflateMenu(R.menu.menu_keranjang_detail);
        binding.toolbarKeranjangDetail.setOnMenuItemClickListener(this::onOptionsItemSelected);
    }

    private void setDataBinding(Cart cart) {
        binding.etKeranjangDetailJudul.setText(cart.getJudul());
        binding.etKeranjangDetailDeskripsi.setText(cart.getDeskripsi());
        binding.tvKeranjangDetailTotalJumlah.setText(cart.getJumlah() + " item");
        binding.tvKeranjangDetailTotalHarga.setText(Helper.convertToRP(cart.getTotal()));

        List<Product> products = cart.getProducts();
        setupRecyclerView((ArrayList<Product>) products);

        productIds = new ArrayList<>();
        productPrices = new ArrayList<>();
        colorIds = new ArrayList<>();
        sizeIds = new ArrayList<>();
        quantities = new ArrayList<>();

        for (Product product : products) {
            productIds.add(product.getId());
            productPrices.add(product.getHarga());
            colorIds.add(product.getPivot().getColorId());
            sizeIds.add(product.getPivot().getSizeId());
            quantities.add(product.getPivot().getJumlah());
        }

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getLayoutPosition();

                productIds.remove(position);
                colorIds.remove(position);
                sizeIds.remove(position);
                productPrices.remove(position);
                quantities.remove(position);
                adapter.removeItem(position);
                binding.rvKeranjangDetailProdukList.swapAdapter(adapter, true);
                state = IS_UPDATING;
            }
        }).attachToRecyclerView(binding.rvKeranjangDetailProdukList);

        binding.btnKeranjangDetailBuatOrder.setOnClickListener(v -> createOrder());

        adapter.setOnProductItemChangedListener((product, jumlah) -> {
            if (product.getPivot().getJumlah() != jumlah) {
                int position = products.indexOf(product);
                quantities.set(position, jumlah);
                state = IS_UPDATING;

                Timber.d(Arrays.toString(adapter.getCheckedProducts().toArray()));
            } else {
            }
        });
    }

    private void setupRecyclerView(ArrayList<Product> items) {
        adapter = new KeranjangDetailAdapter();
        adapter.setLists(items);
        binding.rvKeranjangDetailProdukList.setAdapter(adapter);
        binding.rvKeranjangDetailProdukList.setHasFixedSize(true);
        binding.rvKeranjangDetailProdukList.setLayoutManager(new LinearLayoutManager(requireContext()));
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
        int jumlah = args.getJumlah();

        getProduct(productId);

        viewModel.setLiveKeranjang(judul, deskripsi, productId, colorId, sizeId, jumlah);
        viewModel.newCart.observe(getViewLifecycleOwner(), cart -> {
            Timber.d(cart.getStatus().toString());
            Timber.d(cart.getMessage());
            switch (cart.getStatus()) {
                case SUCCESS:
                    Toast.makeText(requireContext(), "Keranjang berhasil dibuat", Toast.LENGTH_SHORT).show();
                    setDataBinding(cart.getData());
                    state = IS_SHOWING;
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

    private void getProduct(int productId) {
        viewModel.setLiveKeranjang(productId);
        Observer<Resource<Product>> observer = productResource -> {
            if (productResource.getStatus() == Status.SUCCESS) {
                Product product = productResource.getData();
                binding.tvKeranjangDetailTotalHarga.setText(Helper.convertToRP(product.getHarga()));
                binding.tvKeranjangDetailTotalJumlah.setText(String.valueOf(1) + " item");
            }
        };
        viewModel.getProduct.observe(getViewLifecycleOwner(), observer);
        viewModel.getProduct.removeObserver(observer);
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
                    state = IS_SHOWING;
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

    private void deleteCart() {
        viewModel.setLiveKeranjang(args.getCartId());
        viewModel.removeCart.observe(getViewLifecycleOwner(), cart -> {
            switch (cart.getStatus()) {
                case SUCCESS:
                    navController.popBackStack();
                    Toast.makeText(requireContext(), "Keranjang berhasil dihapus!", Toast.LENGTH_SHORT).show();
                    break;

                case LOADING:
                case EMPTY:
                case ERROR:
                case INVALID:
                case UNAUTHORIZED:
                case FORBIDDEN:
                case UNPROCESSABLE_ENTITY:
                    break;
            }
        });

    }

    private void createOrder() {
        KeranjangDetailFragmentDirections.KeranjangDetailFragmentToOrderNewFragment action =
                KeranjangDetailFragmentDirections.keranjangDetailFragmentToOrderNewFragment();

        action.setCartId(args.getCartId());
        action.setProductIds(Helper.convertToIntArray(productIds));
        action.setColorIds(Helper.convertToIntArray(colorIds));
        action.setSizeIds(Helper.convertToIntArray(sizeIds));
        action.setQuantities(Helper.convertToIntArray(quantities));
        action.setProductPrices(Helper.convertToIntArray(productPrices));

        navController.navigate(action);
    }

    private void clear() {
        binding.etKeranjangDetailJudul.setText(null);
        binding.etKeranjangDetailDeskripsi.setText(null);
        binding.tvKeranjangDetailTotalJumlah.setText("0 item");
        binding.tvKeranjangDetailTotalHarga.setText("Rp 0");
        if (adapter != null) {
            adapter.clearItems();
        }
        state = args.getViewState();
    }
}