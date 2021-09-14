package com.rahmacom.rimesyarifix.ui.form_profil_biodata_alamat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import timber.log.Timber;

public class FormProfilBiodataAlamatFragment extends Fragment {

    private FormProfilBiodataAlamatViewModel viewModel;
    private FragmentFormProfilBiodataAlamatBinding binding;
    private PreferenceManager manager;
    private NavController navController;
    private FormProfilBiodataAlamatFragmentArgs args;
    private ArrayAdapter<Village> adapter;

    public static final int IS_CREATING = 1;
    public static final int IS_UPDATING = 2;

    private long villageId = 0;
    private int state;

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

        state = args.getState();

        viewModel.setLiveAlamat(args.getUserShipmentId());
        viewModel.setLiveToken(manager.getString(Const.KEY_TOKEN));

        binding.autoetFormProfilBiodataAlamatDesaKelurahan.setThreshold(2);
        binding.autoetFormProfilBiodataAlamatDesaKelurahan.setOnItemClickListener((parent, v, position, id) -> {
            villageId = adapter.getItem(position).getId();
        });
        getVillages();
        binding.btnFormProfilBiodataAlamatSimpan.setOnClickListener(v -> saveAddress());

        setupToolbar();

        if (state == IS_UPDATING) {
            getShipmentDetail();
        }
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
    }

    private void getShipmentDetail() {
        viewModel.viewShipmentAddress.observe(getViewLifecycleOwner(), shipmentDetail -> {
            switch (shipmentDetail.getStatus()) {
                case SUCCESS:
                    setDataBinding(shipmentDetail.getData());
                    break;

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
            switch(village.getStatus()) {
                case SUCCESS:
                    adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, village.getData());
                    binding.autoetFormProfilBiodataAlamatDesaKelurahan.setAdapter(adapter);
                    break;

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

        Timber.d("{ alamat: " + alamat + ", kode_pos: " + kodePos + ", catatan: " + catatan + ", is_default: " + isDefault + " }");

        if (state == IS_CREATING) {
            viewModel.setLiveAlamat(alamat, kodePos, catatan, isDefault, villageId);
        } else {
            viewModel.setLiveAlamat(args.getUserShipmentId(), alamat, kodePos, catatan, isDefault, villageId);
        }
        viewModel.updateShipmentAddress.observe(getViewLifecycleOwner(), shipment -> {
            Timber.d("saveAddress: %s", shipment.getStatus().toString());
            Timber.d("saveAddress: %s", shipment.getMessage());
            switch (shipment.getStatus()) {
                case SUCCESS:
                    Toast.makeText(requireContext(), "Alamat berhasil disimpan", Toast.LENGTH_SHORT).show();
                    navController.popBackStack();
                    break;

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