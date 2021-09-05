package com.rahmacom.rimesyarifix.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.data.model.Product;
import com.rahmacom.rimesyarifix.databinding.FragmentHomeBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HomeViewModel viewModel;

    private NavController navController;
    private PreferenceManager manager;
    private HomeProdukAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        navController = Navigation.findNavController(view);
        manager = new PreferenceManager(requireContext());

        if (!manager.keyExists(Const.KEY_TOKEN) || manager.getString(Const.KEY_TOKEN) == null) {
            navController.navigate(HomeFragmentDirections.globalToLoginFragment());
        }

        viewModel.setLiveToken(manager.getString(Const.KEY_TOKEN));
        viewModel.getAllProducts.observe(getViewLifecycleOwner(), product -> {
            if (product != null) {
                switch (product.getStatus()) {
                    case SUCCESS:
                        ArrayList<Product> products = new ArrayList<>(product.getData());
                        setUpDataProduk(products);
                        adapter.setOnItemClickListener(item -> {
                            HomeFragmentDirections.NavHomeToProdukFragment action = HomeFragmentDirections.navHomeToProdukFragment();
                            action.setProductId(item.getId());
                            navController.navigate(action);
                        });
                        break;

                    case ERROR:
                    case LOADING:
                    case EMPTY:
                    case INVALID:
                }
            }
        });

        binding.toolbarFragmentHome.inflateMenu(R.menu.menu_home);
        binding.toolbarFragmentHome.setOnMenuItemClickListener(this::onOptionsItemSelected);
    }

    private void setUpDataProduk(ArrayList<Product> list) {
        adapter = new HomeProdukAdapter(requireContext());
        adapter.setLists(list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);

        binding.rvHomeProdukGrid.setAdapter(adapter);
        binding.rvHomeProdukGrid.setLayoutManager(gridLayoutManager);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_keranjang) {
            navController.navigate(HomeFragmentDirections.navHomeToKeranjangFragment());
        }
        return super.onOptionsItemSelected(item);
    }
}