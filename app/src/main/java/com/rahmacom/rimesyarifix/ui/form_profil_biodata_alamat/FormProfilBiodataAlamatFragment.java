package com.rahmacom.rimesyarifix.ui.form_profil_biodata_alamat;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.rahmacom.rimesyarifix.data.model.UserShipment;
import com.rahmacom.rimesyarifix.data.model.Village;
import com.rahmacom.rimesyarifix.databinding.FragmentFormProfilBiodataAlamatBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;

import java.util.ArrayList;

import timber.log.Timber;

public class FormProfilBiodataAlamatFragment extends Fragment {

    private FormProfilBiodataAlamatViewModel viewModel;
    private FragmentFormProfilBiodataAlamatBinding binding;
    private PreferenceManager manager;
    private NavController navController;
    private FormProfilBiodataAlamatFragmentArgs args;

    private long villageId = 0;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFormProfilBiodataAlamatBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(FormProfilBiodataAlamatViewModel.class);
        manager = new PreferenceManager(requireContext());
        navController = Navigation.findNavController(view);
        args = FormProfilBiodataAlamatFragmentArgs.fromBundle(getArguments());

        viewModel.setLiveAlamat(args.getUserShipmentId());
        viewModel.setLiveToken(manager.getString(Const.KEY_TOKEN));

        setupToolbar();
        getShipmentDetail();
    }

    private void setupToolbar() {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(binding.toolbarFormProfilBiodataAlamat, navController, appBarConfiguration);
    }

    private void setDataBinding(UserShipment userShipment) {
        binding.etFormProfilBiodataAlamatEdit.setText(userShipment.getAlamat());
        binding.etFormProfilBiodataAlamatCatatanEdit.setText(userShipment.getCatatan());
        binding.etFormProfilBiodataAlamatKodePosEdit.setText(userShipment.getKodePos());
        binding.cbFormProfilBiodataAlamatSetDefault.setChecked(userShipment.isDefault());
        binding.autoetFormProfilBiodataAlamatDesaKelurahan.setText(userShipment.getVillage().getName());
        binding.autoetFormProfilBiodataAlamatDesaKelurahan.setThreshold(4);
        getVillages();
        binding.btnFormProfilBiodataAlamatSimpan.setOnClickListener(v -> saveAddress());
    }

    private void getShipmentDetail() {
        viewModel.viewShipmentAddress.observe(getViewLifecycleOwner(), shipmentDetail -> {
            switch (shipmentDetail.getStatus()) {
                case SUCCESS:
                    setDataBinding(shipmentDetail.getData());
                    break;

                case LOADING:
                case EMPTY:
                case ERROR:
                case INVALID:
                case UNAUTHORIZED:
                case FORBIDDEN:
                case UNPROCESSABLE_ENTITY:
                    Toast.makeText(requireContext(), "Terjadi Error! Silahkan coba lagi.", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }

    private void getVillages() {
        viewModel.getVillages.observe(getViewLifecycleOwner(), village -> {
            Timber.d(village.getStatus().toString());
            Timber.d(village.getMessage());
            switch(village.getStatus()) {
                case SUCCESS:
                    ArrayList<String> list = new ArrayList<>();
                    for (Village item : village.getData()) {
                        list.add(item.getName());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, list);
                    binding.autoetFormProfilBiodataAlamatDesaKelurahan.setAdapter(adapter);
                    binding.autoetFormProfilBiodataAlamatDesaKelurahan.setOnItemClickListener((parent, view, position, id) -> {
                        villageId = village.getData().get(position).getId();
                    });
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

    private void saveAddress() {
        String alamat = binding.etFormProfilBiodataAlamatEdit.getText().toString();
        String kodePos = binding.etFormProfilBiodataAlamatKodePosEdit.getText().toString();
        String catatan = binding.etFormProfilBiodataAlamatCatatanEdit.getText().toString();
        boolean isDefault = binding.cbFormProfilBiodataAlamatSetDefault.isChecked();

        viewModel.setLiveAlamat(args.getUserShipmentId(), alamat, kodePos, catatan, isDefault, villageId);
        viewModel.updateShipmentAddress.observe(getViewLifecycleOwner(), shipment -> {
            switch (shipment.getStatus()) {
                case SUCCESS:
                    Toast.makeText(requireContext(), "Alamat berhasil disimpan", Toast.LENGTH_SHORT).show();
                    navController.popBackStack();
                    break;

                case LOADING:
                case EMPTY:
                case ERROR:
                case INVALID:
                case UNAUTHORIZED:
                case FORBIDDEN:
                case UNPROCESSABLE_ENTITY:
                    Toast.makeText(requireContext(), "Terjadi error! Silahkan coba lagi.", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }
}