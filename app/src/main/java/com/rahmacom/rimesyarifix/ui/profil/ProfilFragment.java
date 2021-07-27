package com.rahmacom.rimesyarifix.ui.profil;

import android.content.res.TypedArray;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.databinding.FragmentProfilBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ProfilFragment extends Fragment {

    private FragmentProfilBinding binding;
    private ProfilViewModel viewModel;

    public static ProfilFragment newInstance() {
        return new ProfilFragment();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentProfilBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //setUpBiodataProfil();
        setUpDataProfil();
    }

    private void setUpDataProfil() {
        ArrayList<Profil> listProfil = getListProfil();
        DataProfilAdapter adapter = new DataProfilAdapter();
        adapter.setLists(listProfil);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        binding.rvListAkun.setAdapter(adapter);
        binding.rvListAkun.setLayoutManager(linearLayoutManager);
        binding.rvListAkun.addItemDecoration(new DividerItemDecoration(requireContext(),
                linearLayoutManager.getOrientation()));
    }



    private ArrayList<Profil> getListProfil() {
        String[] judul = getResources().getStringArray(R.array.list_data_profil_judul);
        TypedArray drawable = getResources().obtainTypedArray(R.array.list_data_profil_icon);
        TypedArray action = getResources().obtainTypedArray(R.array.list_data_profil_action);

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