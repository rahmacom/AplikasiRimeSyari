package com.rahmacom.rimesyarifix.ui.profil_testimoni;

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
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rahmacom.rimesyarifix.data.model.Testimony;
import com.rahmacom.rimesyarifix.databinding.FragmentProfilTestimoniBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;

import java.util.ArrayList;

public class ProfilTestimoniFragment extends Fragment {

    private FragmentProfilTestimoniBinding binding;
    private ProfilTestimoniViewModel viewModel;
    private PreferenceManager manager;
    private NavController navController;
    private ProfilTestimoniAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfilTestimoniBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(ProfilTestimoniViewModel.class);
        manager = new PreferenceManager(requireContext());
        navController = Navigation.findNavController(view);
        viewModel.setLiveToken(manager.getString(Const.KEY_TOKEN));

        setupToolbar();
        userTestimonies();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private void setupToolbar() {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(binding.toolbarProfilTestimoni, navController, appBarConfiguration);
    }

    private void setupRecyclerView(ArrayList<Testimony> list) {
        adapter = new ProfilTestimoniAdapter();
        adapter.setLists(list);

        binding.rvProfilTestimoni.setAdapter(adapter);
        binding.rvProfilTestimoni.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvProfilTestimoni.setHasFixedSize(true);
    }

    private void userTestimonies() {
        viewModel.userTestimonies.observe(getViewLifecycleOwner(), testimonies -> {
            switch (testimonies.getStatus()) {
                case SUCCESS:
                    setupRecyclerView((ArrayList<Testimony>) testimonies.getData());
                    break;

                case LOADING:
                    break;

                case EMPTY:
                case ERROR:
                case INVALID:
                case UNAUTHORIZED:
                case FORBIDDEN:
                case UNPROCESSABLE_ENTITY:
                    Toast.makeText(requireContext(), "Terjadi error! Silahkan hubungi admin rime atau restart aplikasi", Toast.LENGTH_SHORT).show();
                    break;
            }
        });
    }
}