package com.rahmacom.rimesyarifix.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
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
import com.rahmacom.rimesyarifix.data.network.response.ResponseProduk;
import com.rahmacom.rimesyarifix.databinding.FragmentHomeBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public static final String ARGS_LOGIN = "args_login";
    public static final String ARG_USER = "arg_user_has_login";
    public static final String ARG_GUEST = "arg_user_not_login";

    private static final String[] TITLES = new String[] {
            "ABAYA",
            "OUTER",
            "KHIMAR",
            "MASKER"
    };

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        HomeViewModel viewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        Log.d("HomeFragment", "This is home fragment");

        NavController navController = Navigation.findNavController(view);
        PreferenceManager manager = new PreferenceManager(requireContext());

        if (!manager.keyExists(Const.KEY_TOKEN) || manager.getString(Const.KEY_TOKEN) == null) {
            navController.navigate(HomeFragmentDirections.globalToLoginFragment());
        }

        viewModel.setToken(manager.getString(Const.KEY_TOKEN));
        viewModel.getAllProducts.observe(getViewLifecycleOwner(), produk -> {
            if (produk != null) {
                switch (produk.getStatus()) {
                    case SUCCESS:
//                        Log.d("asdf", produk.getData().toString());
                        ArrayList<ResponseProduk> produks = new ArrayList<>();
                        produks.addAll(produk.getData());
                        setUpDataProduk(produks);
                    case ERROR:
                    case LOADING:
                    case EMPTY:
                    case INVALID:
                }
            }
        });

        binding.fragmentHomeToolbar.inflateMenu(R.menu.menu_main);
    }


//    private ArrayList<Produk> getListProduk() {
//        String[] gambar = getResources().getStringArray(R.array.produk_gambar);
//        String[] nama = getResources().getStringArray(R.array.produk_nama);
//        String[] harga = getResources().getStringArray(R.array.produk_harga);
//        int[] like = getResources().getIntArray(R.array.produk_like);
//        int[] preOrderReady = getResources().getIntArray(R.array.produk_preoder_ready);
//
//        ArrayList<Produk> list = new ArrayList<>();
//
//        for (int i = 0; i < gambar.length; i++) {
//            Produk produk = new Produk();
//            produk.setGambar(gambar[i]);
//            produk.setNama(nama[i]);
//            produk.setHarga(harga[i]);
//            produk.setLike(like[i]);
//            produk.setPreOrderReady(preOrderReady[i]);
//            list.add(produk);
//        }
//        return list;
//    }
//
//    private void setUpDataProduk2(ArrayList<ResponseProduk> list) {
//        DataProdukAdapter adapter = new DataProdukAdapter();
//        adapter.setLists2(list);
//        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
//
//        binding.rvFragmentHome.setAdapter(adapter);
//        binding.rvFragmentHome.setLayoutManager(gridLayoutManager);
//    }

    private void setUpDataProduk(ArrayList<ResponseProduk> list) {
        DataProdukAdapter adapter = new DataProdukAdapter(requireContext());
        adapter.setLists(list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);

        binding.rvFragmentHome.setAdapter(adapter);
        binding.rvFragmentHome.setLayoutManager(gridLayoutManager);
    }
}