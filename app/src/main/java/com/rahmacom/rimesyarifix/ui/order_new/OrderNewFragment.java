package com.rahmacom.rimesyarifix.ui.order_new;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.rahmacom.rimesyarifix.data.model.PaymentMethod;
import com.rahmacom.rimesyarifix.data.model.Product;
import com.rahmacom.rimesyarifix.data.vo.Status;
import com.rahmacom.rimesyarifix.databinding.FragmentOrderNewBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.ui.profil_biodata_alamat.ProfilBiodataAlamatFragment;
import com.rahmacom.rimesyarifix.utils.Const;
import com.rahmacom.rimesyarifix.utils.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

@AndroidEntryPoint
public class OrderNewFragment extends Fragment {

    private OrderNewViewModel viewModel;
    private PreferenceManager manager;
    private NavController navController;
    private FragmentOrderNewBinding binding;
    private OrderNewFragmentArgs args;

    private List<Integer> productIds;
    private List<Integer> productPrices;
    private List<Integer> colorIds;
    private List<Integer> sizeIds;
    private List<Integer> quantities;

    private int cartSubTotal;
    private int userShipmentId;
    private int shipmentCost;
    private int discount;
    private int total;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentOrderNewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(OrderNewViewModel.class);
        manager = new PreferenceManager(requireContext());
        navController = Navigation.findNavController(view);
        args = OrderNewFragmentArgs.fromBundle(getArguments());

        setupToolbar();
        viewModel.setLiveToken(manager.getString(Const.KEY_TOKEN));

        setDataBinding("Jalan Kulintang No. 33 RT 36");
        setShipmentAddress();
    }

    private void setupToolbar() {
        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(binding.toolbarOrderNew, navController, appBarConfiguration);
    }

    private void setDataBinding(String alamat) {
        binding.tvOrderNewAlamat.setText(alamat);
        binding.tvOrderNewTotal.setText(Helper.convertToRP(cartSubTotal));
        setPaymentMethod();

        if (args.getCartId() > -1) {
            getCart();
        }

        if (args.getProductId() != null) {
            getPreOrderDetails();
        }

        binding.tvOrderNewAlamat.setOnClickListener(v -> {
            OrderNewFragmentDirections.OrderNewFragmentToProfilBiodataAlamatFragment action = OrderNewFragmentDirections.orderNewFragmentToProfilBiodataAlamatFragment();
            action.setViewState(ProfilBiodataAlamatFragment.IS_SELECTING);
            navController.navigate(action);
        });

        binding.btnOrderNewPesanSekarang.setOnClickListener(v -> createOrder());
    }

    private void getCart() {
        int cartId = args.getCartId();
        viewModel.viewCart.observe(getViewLifecycleOwner(), cart -> {
            switch (cart.getStatus()) {
                case SUCCESS:
                    cartSubTotal = cart.getData().getTotal();
                    convertToFields(cart.getData().getProducts());
                    break;

                case EMPTY:
                    break;

                case ERROR:
                    Toast.makeText(requireContext(), "Terjadi error! Silahkan coba lagi", Toast.LENGTH_SHORT).show();
                    Log.e("cartError", cart.getStatus().toString() + ": " + cart.getMessage());
                    break;

                case UNPROCESSABLE_ENTITY:
                    break;
            }
        });
    }

    private void convertToFields(List<Product> items) {
        productIds = new ArrayList<>();
        colorIds = new ArrayList<>();
        sizeIds = new ArrayList<>();
        quantities = new ArrayList<>();

        for (Product product : items) {
            productIds.add(product.getId());
            colorIds.add(product.getPivot().getColorId());
            sizeIds.add(product.getPivot().getSizeId());
            quantities.add(product.getPivot().getJumlah());
        }
    }

    private void getPreOrderDetails() {
        cartSubTotal = 0;

        productIds = Helper.convertToList(Objects.requireNonNull(args.getProductId()));
        productPrices = Helper.convertToList(Objects.requireNonNull(args.getProductHarga()));
        colorIds = Helper.convertToList(Objects.requireNonNull(args.getColorId()));
        sizeIds = Helper.convertToList(Objects.requireNonNull(args.getSizeId()));
        quantities = Helper.convertToList(Objects.requireNonNull(args.getJumlah()));

        for (int i = 0; i < quantities.size(); i++) {
            int subTotal = productPrices.get(i) * quantities.get(i);
            cartSubTotal += subTotal;
        }

        binding.tvOrderNewSubtotal.setText(Helper.convertToRP(cartSubTotal));
    }

    private void setPaymentMethod() {
        viewModel.getAvailablePaymentMethod.observe(getViewLifecycleOwner(), paymentMethod -> {
            if (paymentMethod.getStatus() == Status.SUCCESS) {
                for (PaymentMethod pm : paymentMethod.getData()) {
                    RadioButton rb = new RadioButton(requireContext());
                    rb.setText(pm.getName());
                    rb.setId(pm.getId());
                    binding.cbgOrderNewMetodePembayaran.addView(rb);
                }
            }
        });
    }

    private void getShipmentAddress(int shipmentId) {
        viewModel.setLiveUserShipmentId(shipmentId);
        viewModel.getShipmentAddressDetail.observe(getViewLifecycleOwner(), userShipment -> {
            Timber.d(userShipment.getStatus().toString());
            switch (userShipment.getStatus()) {
                case SUCCESS:
                    this.userShipmentId = userShipment.getData().getId();
                    binding.tvOrderNewAlamat.setText(userShipment.getData().getAlamat());
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

    private void setShipmentAddress() {
        MutableLiveData<Integer> liveShipmentId = navController.getCurrentBackStackEntry()
                .getSavedStateHandle()
                .getLiveData("user_shipment_id");

        liveShipmentId.observe(getViewLifecycleOwner(), this::getShipmentAddress);
    }

    private void createOrder() {
        int paymentMethodId = binding.cbgOrderNewMetodePembayaran.getCheckedRadioButtonId();
        int userShipmentId = this.userShipmentId;
        String pesan = binding.etOrderNewPesan.getText().toString();
        String kodeDiskon = binding.etOrderNewKodeDiskon.getText().toString();

        viewModel.setLiveOrder(pesan, kodeDiskon, userShipmentId, paymentMethodId, productIds, colorIds, sizeIds, quantities);
        viewModel.newOrder.observe(getViewLifecycleOwner(), order -> {
            Timber.d(order.getStatus().toString());
            switch (order.getStatus()) {
                case SUCCESS:

                    break;

                case EMPTY:
                case ERROR:
                case UNPROCESSABLE_ENTITY:
                    break;
            }
        });
    }
}