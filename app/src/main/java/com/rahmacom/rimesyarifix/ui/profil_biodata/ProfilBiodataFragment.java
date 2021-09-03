package com.rahmacom.rimesyarifix.ui.profil_biodata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.databinding.FragmentProfilBiodataBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProfilBiodataFragment extends Fragment {

    private ProfilBiodataViewModel viewModel;
    private FragmentProfilBiodataBinding binding;
    private PreferenceManager manager;
    private NavController navController;
    private BiodataProfilAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfilBiodataBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(ProfilBiodataViewModel.class);
        manager = new PreferenceManager(requireContext());
        navController = Navigation.findNavController(view);
        adapter = new BiodataProfilAdapter();

        setupToolbar();
        setUpBiodataProfil();
        viewModel.setLiveToken(manager.getString(Const.KEY_TOKEN));

        binding.toolbarProfilBiodata.setTitle("Biodata user");

        binding.btnEditProfil.setOnClickListener(v -> {
            navController.navigate(ProfilBiodataFragmentDirections.profilBiodataFragmentToFormEditBiodataFragment());
        });
    }

    private void setUpBiodataProfil() {
        adapter.setLists(getUserBiodata());
        binding.rvBiodataProfil.setAdapter(adapter);
        binding.rvBiodataProfil.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());

        binding.rvBiodataProfil.setLayoutManager(linearLayoutManager);
        binding.rvBiodataProfil.addItemDecoration(new DividerItemDecoration(requireContext(), linearLayoutManager.getOrientation()));
    }

    private ArrayList<Biodata> getUserBiodata() {
        ArrayList<Biodata> items = new ArrayList<>();
        String[] keys = getResources().getStringArray(R.array.list_biodata_profil_judul);
        String[] value = new String[]{
                manager.getString(Const.KEY_NAMA_LENGKAP),
                manager.getString(Const.KEY_JENIS_KELAMIN),
                manager.getString(Const.KEY_ALAMAT),
                manager.getString(Const.KEY_NO_TELP),
                manager.getString(Const.KEY_NO_WA),
                manager.getString(Const.KEY_EMAIL),
                manager.getString(Const.KEY_ROLE)
        };

        for (int i = 0; i < keys.length; i++) {
            Biodata biodata = new Biodata(keys[i], value[i]);
            items.add(biodata);
        }

        return items;
    }

    private void setupToolbar() {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(binding.toolbarProfilBiodata, navController, appBarConfiguration);
    }

    private void getUserAddresses() {

    }
}