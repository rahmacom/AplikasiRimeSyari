package com.rahmacom.rimesyarifix.ui.profil_testimoni;

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

import com.rahmacom.rimesyarifix.databinding.FragmentProfilTestimoniBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;

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
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private void setupRecyclerView() {

    }
}