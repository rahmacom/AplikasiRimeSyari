package com.rahmacom.rimesyarifix.ui.produk_testimoni;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.databinding.FragmentProdukTestimoniBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;

import timber.log.Timber;

public class ProdukTestimoniFragment extends Fragment {

    private ProdukTestimoniViewModel viewModel;
    private FragmentProdukTestimoniBinding binding;
    private PreferenceManager manager;
    private NavController navController;
    private ProdukTestimoniFragmentArgs args;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = FragmentProdukTestimoniBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(ProdukTestimoniViewModel.class);
        manager = new PreferenceManager(requireContext());
        navController = Navigation.findNavController(view);
        args = ProdukTestimoniFragmentArgs.fromBundle(getArguments());

        viewModel.setLiveToken(manager.getString(Const.KEY_TOKEN));
        setupToolbar();

        binding.btnProdukTestimoniSelesai.setOnClickListener(v -> newTestimoni());
    }

    private void setupToolbar() {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupWithNavController(binding.toolbarProdukTestimoni, navController, appBarConfiguration);
    }

    private void newTestimoni() {
        String isi = binding.etProdukTestimoniIsi.getText().toString();
        int rating = (int) binding.ratingProdukTestimoni.getRating();
        int productId = args.getProductId();

        viewModel.setLiveTestimoni(isi, rating, productId);

        viewModel.createTestimony.observe(getViewLifecycleOwner(), testimony -> {
            Timber.d(testimony.getMessage());
            switch (testimony.getStatus()) {
                case SUCCESS:
                    Toast.makeText(requireContext(), "Testimoni berhasil ditambahkan!", Toast.LENGTH_SHORT).show();
                    navController.popBackStack();
                    break;

                case LOADING:
                case EMPTY:
                case ERROR:
                case UNAUTHORIZED:
                case FORBIDDEN:
                case INVALID:
                case UNPROCESSABLE_ENTITY:
                    break;
            }
        });
    }
}