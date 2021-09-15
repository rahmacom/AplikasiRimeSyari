package com.rahmacom.rimesyarifix.ui.form_profil_biodata_alamat;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.rahmacom.rimesyarifix.data.model.District;
import com.rahmacom.rimesyarifix.data.model.Province;
import com.rahmacom.rimesyarifix.data.model.Regency;
import com.rahmacom.rimesyarifix.data.model.UserShipment;
import com.rahmacom.rimesyarifix.data.model.Village;
import com.rahmacom.rimesyarifix.data.vo.Resource;
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
    private ArrayAdapter<Village> villageArrayAdapter;
    private ArrayAdapter<District> districtArrayAdapter;
    private ArrayAdapter<Regency> regencyArrayAdapter;
    private ArrayAdapter<Province> provinceArrayAdapter;

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

        binding.autoetFormProfilBiodataAlamatDesaKelurahan.setThreshold(0);
        binding.autoetFormProfilBiodataAlamatKecamatan.setThreshold(0);
        binding.autoetFormProfilBiodataAlamatKabupatenKota.setThreshold(0);
        binding.autoetFormProfilBiodataAlamatProvinsi.setThreshold(0);
        createTextWatcher();
        getProvinces();
        getRegencies();
        getDistricts();
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
        binding.autoetFormProfilBiodataAlamatKecamatan.setText(userShipment.getVillage().getDistrict().getName());
        binding.autoetFormProfilBiodataAlamatKabupatenKota.setText(userShipment.getVillage().getDistrict().getRegency().getName());
        binding.autoetFormProfilBiodataAlamatProvinsi.setText(userShipment.getVillage().getDistrict().getRegency().getProvince().getName());
    }

    private void createTextWatcher() {
        binding.autoetFormProfilBiodataAlamatProvinsi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                viewModel.setLiveProvince(s.toString());
                Timber.d(s.toString());
            }
        });

        binding.autoetFormProfilBiodataAlamatKabupatenKota.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                viewModel.setLiveRegency(s.toString());
                Timber.d(s.toString());
            }
        });

        binding.autoetFormProfilBiodataAlamatKecamatan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                viewModel.setLiveDistrict(s.toString());
                Timber.d(s.toString());
            }
        });
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

    private void getProvinces() {
        viewModel.getProvinces.observe(getViewLifecycleOwner(), provinces -> {
            switch (provinces.getStatus()) {
                case SUCCESS:
                    provinceArrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, provinces.getData());
                    binding.autoetFormProfilBiodataAlamatProvinsi.setAdapter(provinceArrayAdapter);
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

    private void getRegencies() {
        viewModel.getRegencies.observe(getViewLifecycleOwner(), regency -> {
            switch (regency.getStatus()) {
                case SUCCESS:
                    regencyArrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, regency.getData());
                    binding.autoetFormProfilBiodataAlamatKabupatenKota.setAdapter(regencyArrayAdapter);
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

    private void getDistricts() {
        viewModel.getDistricts.observe(getViewLifecycleOwner(), district -> {
            Timber.d(district.getStatus().toString());
            Timber.d(district.getMessage());
            switch (district.getStatus()) {
                case SUCCESS:
                    districtArrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, district.getData());
                    binding.autoetFormProfilBiodataAlamatKecamatan.setAdapter(districtArrayAdapter);
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

    private void getVillages() {
        viewModel.getVillages.observe(getViewLifecycleOwner(), village -> {
            Timber.d(village.getStatus().toString());
            Timber.d(village.getMessage());
            switch (village.getStatus()) {
                case SUCCESS:
                    villageArrayAdapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, village.getData());
                    binding.autoetFormProfilBiodataAlamatDesaKelurahan.setAdapter(villageArrayAdapter);
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
        String village = binding.autoetFormProfilBiodataAlamatDesaKelurahan.getText().toString();
        String district = binding.autoetFormProfilBiodataAlamatKecamatan.getText().toString();
        String regency = binding.autoetFormProfilBiodataAlamatKabupatenKota.getText().toString();
        String province = binding.autoetFormProfilBiodataAlamatProvinsi.getText().toString();
        boolean isDefault = binding.cbFormProfilBiodataAlamatSetDefault.isChecked();

        Timber.d("{ alamat: " + alamat + ", kode_pos: " + kodePos + ", catatan: " + catatan + ", is_default: " + isDefault + " }");
        LiveData<Resource<UserShipment>> saveAddress = null;

        if (state == IS_CREATING) {
            viewModel.setLiveAlamat(alamat, kodePos, catatan, isDefault, village, district, regency, province);
            saveAddress = viewModel.newShipmentAddress;
        } else {
            viewModel.setLiveAlamat(args.getUserShipmentId(), alamat, kodePos, catatan, isDefault, village, district, regency, province);
            saveAddress = viewModel.updateShipmentAddress;
        }

        saveAddress.observe(getViewLifecycleOwner(), shipment -> {
            Timber.d("saveAddress: %s", shipment.getStatus().toString());
            Timber.d("saveAddress: %s", shipment.getMessage());
            switch (shipment.getStatus()) {
                case SUCCESS:
                    Toast.makeText(requireContext(), "Alamat berhasil disimpan", Toast.LENGTH_SHORT).show();
                    if (isDefault) {
                        manager.setInt(Const.KEY_USER_SHIPMENT_ID, shipment.getData().getId());
                    }
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