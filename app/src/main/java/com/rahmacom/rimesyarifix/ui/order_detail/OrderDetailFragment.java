package com.rahmacom.rimesyarifix.ui.order_detail;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.data.model.Order;
import com.rahmacom.rimesyarifix.data.model.Product;
import com.rahmacom.rimesyarifix.databinding.FragmentOrderDetailBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;
import com.rahmacom.rimesyarifix.utils.Helper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

@AndroidEntryPoint
public class OrderDetailFragment extends Fragment {

    private OrderDetailViewModel viewModel;
    private FragmentOrderDetailBinding binding;
    private NavController navController;
    private PreferenceManager manager;
    private OrderDetailAdapter adapter;

    private ActivityResultLauncher<String> galleryLauncher;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOrderDetailBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(OrderDetailViewModel.class);
        navController = Navigation.findNavController(view);
        manager = new PreferenceManager(requireContext());
        adapter = new OrderDetailAdapter();

        setupToolbar();

        int orderId = OrderDetailFragmentArgs.fromBundle(getArguments())
                .getOrderId();

        viewModel.setLiveToken(manager.getString(Const.KEY_TOKEN));
        viewModel.setLiveOrderId(orderId);
        viewModel.viewOrder.observe(getViewLifecycleOwner(), order -> {
            switch (order.getStatus()) {
                case SUCCESS:
                    setDataBinding(order.getData());
                case EMPTY:
                case ERROR:
                case LOADING:
                case INVALID:
                case UNAUTHORIZED:
                case FORBIDDEN:
                case UNPROCESSABLE_ENTITY:
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        galleryLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(),
                uriResult -> {
                    if (uriResult != null) {
                        try {
                            File file = Helper.bitmapToFile(MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), uriResult), requireActivity());

                            payOrder(file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    private void setupToolbar() {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(binding.toolbarOrderDetail, navController, appBarConfiguration);
    }

    private void setDataBinding(Order order) {
        binding.tvOrderDetailNomor.setText(order.getNomor());
        binding.tvOrderDetailAlamat.setText(order.getUserShipment()
                .getAlamat());
        binding.tvOrderDetailPesan.setText(order.getPesan());
        binding.tvOrderDetailTotalHarga.setText(Helper.convertToRP(order.getTotal()));
        binding.tvOrderDetailTotalJumlah.setText(String.format("%s item", order.getJumlah()));
        binding.tvOrderDetailStatus.setText(order.getStatus()
                .getName());
        binding.tvOrderDetailMetodePembayaranText.setText(order.getPaymentMethod().getName());

        if (!order.isPaid()) {
            if (order.getPaymentMethod().getName().equals("transfer")) {
                binding.tvOrderDetailPaymentNotice.setText(R.string.payment_method_transaction_warning);
            } else if (order.getPaymentMethod().getName().equals("COD")) {
                binding.tvOrderDetailPaymentNotice.setText(R.string.payment_method_cod);
            }
        } else {
            binding.tvOrderDetailPaymentNotice.setText(R.string.payment_method_success);
        }

        AlertDialog alertDialog = new MaterialAlertDialogBuilder(requireContext())
                .setTitle("Upload foto dari")
                .setIcon(R.drawable.icon_info)
                .setItems(R.array.dialog_form_upload_foto_list, ((dialog, which) -> {
                    if (which == 0) {
                        galleryLauncher.launch("image/*");
                    }
                }))
                .create();

        binding.tvOrderDetailPaymentNotice.setOnClickListener(v -> alertDialog.show());

        setupRecyclerView((ArrayList<Product>) order.getProducts());
    }

    private void setupRecyclerView(ArrayList<Product> products) {
        adapter.setList(products);
        binding.rvOrderDetailProdukList.setAdapter(adapter);
        binding.rvOrderDetailProdukList.setHasFixedSize(true);
        binding.rvOrderDetailProdukList.setLayoutManager(new LinearLayoutManager(requireContext()));
    }

    private void payOrder(File file) {
        viewModel.setLiveFile(file);
        viewModel.payOrder.observe(getViewLifecycleOwner(), isPaid -> {
            Timber.d(isPaid.getMessage());
            Timber.d(isPaid.getStatus().toString());

            switch (isPaid.getStatus()) {
                case SUCCESS:
                    Toast.makeText(requireContext(), "Bukti pembayaran berhasil diupload.", Toast.LENGTH_SHORT).show();
                    binding.tvOrderDetailPaymentNotice.setText(R.string.payment_method_success);
                    break;

                case LOADING:
                    Toast.makeText(requireContext(), "Tunggu sebentar...", Toast.LENGTH_SHORT).show();
                    break;

                case EMPTY:
                case ERROR:
                case INVALID:
                case UNAUTHORIZED:
                case FORBIDDEN:
                case UNPROCESSABLE_ENTITY:
                    Toast.makeText(requireContext(), "Terjadi error! Silahkan coba lagi", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }
}