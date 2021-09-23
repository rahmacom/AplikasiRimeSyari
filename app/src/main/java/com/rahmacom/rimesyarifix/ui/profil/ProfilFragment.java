package com.rahmacom.rimesyarifix.ui.profil;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.data.vo.Status;
import com.rahmacom.rimesyarifix.databinding.FragmentProfilBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;
import timber.log.Timber;

@AndroidEntryPoint
public class ProfilFragment extends Fragment {

    private FragmentProfilBinding binding;
    private ProfilViewModel viewModel;
    private ProfilMenuAdapter adapter;
    private PreferenceManager manager;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfilBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(ProfilViewModel.class);
        navController = Navigation.findNavController(view);
        manager = new PreferenceManager(requireContext());

        viewModel.setLiveToken(manager.getString(Const.KEY_TOKEN));

        setUpDataProfil();
    }

    private void setUpDataProfil() {
        ArrayList<Profil> listProfil = getListProfil();
        adapter = new ProfilMenuAdapter();
        adapter.setLists(listProfil);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        binding.rvListAkun.setAdapter(adapter);
        binding.rvListAkun.setLayoutManager(linearLayoutManager);
        binding.rvListAkun.addItemDecoration(new DividerItemDecoration(requireContext(), linearLayoutManager.getOrientation()));

        binding.tvProfilNamaLengkapUser.setText(manager.getString(Const.KEY_NAMA_LENGKAP));
        binding.tvProfilEmailUser.setText(manager.getString(Const.KEY_EMAIL));

        Glide.with(binding.getRoot())
                .load(manager.getString(Const.KEY_AVATAR))
                .into(binding.ivProfilFoto);

        setupMenuClick();
    }

    private ArrayList<Profil> getListProfil() {
        String[] judul = getResources().getStringArray(R.array.list_profil_menu_title);
        TypedArray drawable = getResources().obtainTypedArray(R.array.list_profil_menu_icon);
        TypedArray action = getResources().obtainTypedArray(R.array.list_profil_menu_action);

        ArrayList<Profil> list = new ArrayList<>();

        for (int i = 0; i < judul.length; i++) {
            Profil profil = new Profil();
            profil.setTitle(judul[i]);
            profil.setDrawableId(drawable.getResourceId(i, -1));
            profil.setActionId(action.getResourceId(i, -1));
            list.add(profil);
        }

        return list;
    }

    private void setupMenuClick() {
        adapter.setOnItemClickListener((adapterBinding, position) -> {
            switch (position) {
                case 0:
                    navController.navigate(ProfilFragmentDirections.navProfilToProfilBiodataFragment());
                    break;

                case 1:
                    navController.navigate(ProfilFragmentDirections.navProfilToResellerInfoFragment());
                    break;

                case 2:
                    navController.navigate(ProfilFragmentDirections.navProfilToProfilTestimoniFragment());
                    break;

                case 3:
                    navController.navigate(ProfilFragmentDirections.navProfilToProfilTentangFragment());
                    break;

                case 4:
                    logout();
                    break;
            }
        });
    }

    private void logout() {
        viewModel.logout.observe(getViewLifecycleOwner(), logout -> {
            Timber.d(logout.getStatus().toString());
            switch (logout.getStatus()) {
                case SUCCESS:
                    manager.removePreference(Const.KEY_TOKEN);
                    manager.removePreference(Const.KEY_TYPE);
                    manager.removePreference(Const.KEY_NIK);
                    manager.removePreference(Const.KEY_NAMA_LENGKAP);
                    manager.removePreference(Const.KEY_JENIS_KELAMIN);
                    manager.removePreference(Const.KEY_EMAIL);
                    manager.removePreference(Const.KEY_TEMPAT_LAHIR);
                    manager.removePreference(Const.KEY_TGL_LAHIR);
                    manager.removePreference(Const.KEY_ALAMAT);
                    manager.removePreference(Const.KEY_NO_TELP);
                    manager.removePreference(Const.KEY_NO_WA);
                    manager.removePreference(Const.KEY_ROLE);
                    manager.removePreference(Const.KEY_AVATAR);

                    Toast.makeText(requireContext(), "Berhasil logout", Toast.LENGTH_SHORT).show();
                    getParentFragmentManager().getFragments().clear();

                    navController.navigate(ProfilFragmentDirections.globalToLoginFragment());
                case LOADING:
                case EMPTY:
                    break;

                case ERROR:
                case INVALID:
                case UNAUTHORIZED:
                case FORBIDDEN:
                case UNPROCESSABLE_ENTITY:
                    manager.removePreference(Const.KEY_TOKEN);
                    manager.removePreference(Const.KEY_TYPE);
                    manager.removePreference(Const.KEY_NIK);
                    manager.removePreference(Const.KEY_NAMA_LENGKAP);
                    manager.removePreference(Const.KEY_JENIS_KELAMIN);
                    manager.removePreference(Const.KEY_EMAIL);
                    manager.removePreference(Const.KEY_TEMPAT_LAHIR);
                    manager.removePreference(Const.KEY_TGL_LAHIR);
                    manager.removePreference(Const.KEY_ALAMAT);
                    manager.removePreference(Const.KEY_NO_TELP);
                    manager.removePreference(Const.KEY_NO_WA);
                    manager.removePreference(Const.KEY_ROLE);
                    manager.removePreference(Const.KEY_AVATAR);
                    Toast.makeText(requireContext(), "Terjadi error! Silahkan hubungi admin atau restart aplikasi", Toast.LENGTH_SHORT).show();
                    navController.navigate(ProfilFragmentDirections.globalToLoginFragment());
                    break;
            }
        });
    }
}