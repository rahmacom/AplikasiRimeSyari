package com.rahmacom.rimesyarifix.ui.form_profil_biodata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
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

import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.databinding.FragmentFormProfilBiodataBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;

import java.util.Arrays;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

@AndroidEntryPoint
public class FormProfilBiodataFragment extends Fragment {

    private FormProfilBiodataViewModel viewModel;
    private FragmentFormProfilBiodataBinding binding;
    private NavController navController;
    private PreferenceManager manager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFormProfilBiodataBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(FormProfilBiodataViewModel.class);
        manager = new PreferenceManager(requireContext());
        navController = Navigation.findNavController(view);

        viewModel.setLiveToken(manager.getString(Const.KEY_TOKEN));
        getUserDetail();
        setupToolbar();
    }

    private void setupToolbar() {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(binding.toolbarFormProfilBiodata, navController, appBarConfiguration);

        binding.toolbarFormProfilBiodata.setTitle("Ubah biodata profil");
        binding.toolbarFormProfilBiodata.inflateMenu(R.menu.menu_form_profil_biodata);
        binding.toolbarFormProfilBiodata.setOnMenuItemClickListener(this::onOptionsItemSelected);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_form_profil_biodata_simpan) {
            updateProfile();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getUserDetail() {
        String nik = manager.getString(Const.KEY_NIK);
        String nama = manager.getString(Const.KEY_NAMA_LENGKAP);
        String jenisKelamin = manager.getString(Const.KEY_JENIS_KELAMIN);
        String alamat = manager.getString(Const.KEY_ALAMAT);
        String tempatLahir = manager.getString(Const.KEY_TEMPAT_LAHIR);
        String tglLahir = manager.getString(Const.KEY_TGL_LAHIR);
        String noHp = manager.getString(Const.KEY_NO_TELP);
        String noWa = manager.getString(Const.KEY_NO_WA);

        binding.etFormProfilBiodataNik.setText(nik);
        binding.etFormProfilBiodataNamaLengkap.setText(nama);
        binding.etFormProfilBiodataTempatLahir.setText(tempatLahir);
        binding.etFormProfilBiodataTglLahir.setText(tglLahir);
        binding.etFormProfilBiodataNoTelp.setText(noHp);
        binding.etFormProfilBiodataWhatsapp.setText(noWa);
        binding.etFormProfilBiodataAlamatUser.setText(alamat);
        spinnerJenisKelamin((!jenisKelamin.isEmpty()) ? jenisKelamin.toLowerCase().charAt(0) : 'p');
    }

    private void updateProfile() {
        String nama = binding.etFormProfilBiodataNamaLengkap.getText().toString();
        String jenisKelamin = (String) binding.spinnerFormProfilBiodataJenisKelamin.getSelectedItem();
        Character jk = jenisKelamin.toLowerCase().charAt(0);
        String tempatLahir = binding.etFormProfilBiodataTempatLahir.getText().toString();
        String tglLahir = binding.etFormProfilBiodataTglLahir.getText().toString();
        String alamat = binding.etFormProfilBiodataAlamatUser.getText().toString();
        String noHp = binding.etFormProfilBiodataNoTelp.getText().toString();
        String noWa = binding.etFormProfilBiodataWhatsapp.getText().toString();

        viewModel.setLiveUser(nama, jk, tempatLahir, tglLahir, noHp, noWa, alamat);
        viewModel.updateProfile.observe(getViewLifecycleOwner(), user -> {
            Timber.d(user.getStatus().toString());
            switch (user.getStatus()) {
                case SUCCESS:
                    Toast.makeText(requireContext(), "Profil berhasil diubah", Toast.LENGTH_SHORT).show();
                    manager.setString(Const.KEY_NAMA_LENGKAP, user.getData().getNamaLengkap());
                    manager.setString(Const.KEY_JENIS_KELAMIN, user.getData().getJenisKelamin());
                    manager.setString(Const.KEY_TEMPAT_LAHIR, user.getData().getTempatLahir());
                    manager.setString(Const.KEY_TGL_LAHIR, user.getData().getTglLahir());
                    manager.setString(Const.KEY_ALAMAT, user.getData().getAlamat());
                    manager.setString(Const.KEY_NO_TELP, user.getData().getNoHp());
                    manager.setString(Const.KEY_NO_WA, user.getData().getNoWa());
                    getUserDetail();
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

    private void spinnerJenisKelamin(Character jk) {
        List<String> jenisKelamin = Arrays.asList(getResources().getStringArray(R.array.jenis_kelamin));
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                jenisKelamin
        );

        binding.spinnerFormProfilBiodataJenisKelamin.setAdapter(spinnerAdapter);
        binding.spinnerFormProfilBiodataJenisKelamin.setSelection((jk == 'l') ? 0 : 1);
    }
}