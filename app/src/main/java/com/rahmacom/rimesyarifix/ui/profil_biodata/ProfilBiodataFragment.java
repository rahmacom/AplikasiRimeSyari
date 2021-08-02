package com.rahmacom.rimesyarifix.ui.profil_biodata;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.databinding.FragmentProfilBiodataBinding;
import com.rahmacom.rimesyarifix.ui.profil.Profil;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ProfilBiodataFragment extends Fragment {

    private ProfilBiodataViewModel mViewModel;
    private FragmentProfilBiodataBinding binding;

    public static ProfilBiodataFragment newInstance() {
        return new ProfilBiodataFragment();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = FragmentProfilBiodataBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpBiodataProfil();
        //showDialog();
    }

    private void setUpBiodataProfil() {
        ArrayList<Profil> listBiodata = getListBiodata();
        BiodataProfilAdapter adapter = new BiodataProfilAdapter(this);
        adapter.setLists(listBiodata);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        binding.rvBiodataProfil.setAdapter(adapter);
        binding.rvBiodataProfil.setLayoutManager(linearLayoutManager);
        binding.rvBiodataProfil.addItemDecoration(new DividerItemDecoration(requireContext(),
                linearLayoutManager.getOrientation()));
    }

    private ArrayList<Profil> getListBiodata() {
        String[] judul = getResources().getStringArray(R.array.list_biodata_profil_judul);
        String[] dummy = getResources().getStringArray(R.array.list_biodata_profil_dummy);

        ArrayList<Profil> list = new ArrayList<>();

        for (int i = 0; i < judul.length; i++) {
            Profil profil = new Profil();
            profil.setTitle(judul[i]);
            profil.setValue(dummy[i]);
            list.add(profil);
        }
        return list;
    }
}