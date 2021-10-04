package com.rahmacom.rimesyarifix.ui.profil_kontak;

import androidx.appcompat.app.WindowDecorActionBar;
import androidx.lifecycle.ViewModelProvider;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.databinding.FragmentProfilKontakBinding;

import timber.log.Timber;

public class ProfilKontakFragment extends Fragment {

    private ProfilKontakViewModel viewModel;
    private FragmentProfilKontakBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            binding = FragmentProfilKontakBinding.inflate(inflater, container, false);
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(ProfilKontakViewModel.class);

        binding.ivProfilKontakIg.setOnClickListener(v -> goToIg());
        binding.tvProfilKontakIg.setOnClickListener(v -> goToIg());
        binding.ivProfilKontakWa.setOnClickListener(v -> goToWa());
        binding.tvProfilKontakWa.setOnClickListener(v -> goToWa());
    }

    private void goToWa() {
        Uri uri = Uri.parse("https://wa.me/6282291920631?text=Assalamualaikum%20kak,%20mau%20bertanya...");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private void goToIg() {
        Uri appUri = Uri.parse("http://instagram.com/_u/rime.syari");
        Intent appIntent = new Intent(Intent.ACTION_VIEW, appUri);

        Uri webUri = Uri.parse("http://instagram.com/rime.syari");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webUri);

        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            Timber.e(ex);
            startActivity(webIntent);
        }
    }
}