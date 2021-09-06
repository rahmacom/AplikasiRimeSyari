package com.rahmacom.rimesyarifix.ui.form_profil_biodata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;

import com.rahmacom.rimesyarifix.databinding.FragmentFormProfilBiodataBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;

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

        viewModel.setLiveToken(manager.getString(Const.KEY_TOKEN));
        getUserDetail();
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

        binding.etFormProfilBiodataNamaLengkap.setText(nama);
        binding.etFormProfilBiodataTempatLahir.setText(tempatLahir);
        binding.etFormProfilBiodataTglLahir.setText(tglLahir);
        binding.etFormProfilBiodataNoHp.setText(noHp);
        binding.etFormProfilBiodataNoWa.setText(noWa);
        binding.etFormProfilBiodataAlamat.setText(alamat);

        binding.btnFormProfilBiodataSelesai.setOnClickListener(v -> updateProfile());
    }

    private void updateProfile() {
        String nama = binding.etFormProfilBiodataNamaLengkap.getText().toString();
        String tempatLahir = binding.etFormProfilBiodataTempatLahir.getText().toString();
        String tglLahir = binding.etFormProfilBiodataTglLahir.getText().toString();
        String alamat = binding.etFormProfilBiodataAlamat.getText().toString();
        String noHp = binding.etFormProfilBiodataNoHp.getText().toString();
        String noWa = binding.etFormProfilBiodataNoWa.getText().toString();

        viewModel.setLiveUser(nama, tempatLahir, tglLahir, noHp, noWa, alamat);
        viewModel.updateProfile.observe(getViewLifecycleOwner(), user -> {
            Timber.d(user.getStatus().toString());
            switch (user.getStatus()) {
                case SUCCESS:
                    Toast.makeText(requireContext(), "Profil berhasil diubah", Toast.LENGTH_SHORT).show();
                    manager.setString(Const.KEY_NAMA_LENGKAP, user.getData().getNamaLengkap());
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
}