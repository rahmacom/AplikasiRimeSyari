package com.rahmacom.rimesyarifix.ui.profil;

import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.databinding.FragmentProfilBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ProfilFragment extends Fragment {

    private FragmentProfilBinding binding;
    private ProfilViewModel viewModel;
    private ProfilMenuAdapter adapter;
    private PreferenceManager manager;

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
        manager = new PreferenceManager(requireContext());
        setUpDataProfil();
    }

    private void setUpDataProfil() {
        ArrayList<Profil> listProfil = getListProfil();
        adapter = new ProfilMenuAdapter();
        adapter.setLists(listProfil);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        binding.rvListAkun.setAdapter(adapter);
        binding.rvListAkun.setLayoutManager(linearLayoutManager);
        binding.rvListAkun.addItemDecoration(new DividerItemDecoration(requireContext(),
                linearLayoutManager.getOrientation()));

        binding.tvProfilNamaLengkapUser.setText(manager.getString(Const.KEY_NAME));
        binding.tvProfilEmailUser.setText(manager.getString(Const.KEY_EMAIL));
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
}