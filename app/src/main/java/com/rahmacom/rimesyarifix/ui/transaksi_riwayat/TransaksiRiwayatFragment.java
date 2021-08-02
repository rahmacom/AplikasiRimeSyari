package com.rahmacom.rimesyarifix.ui.transaksi_riwayat;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.databinding.FragmentTransaksiRiwayatBinding;
import com.rahmacom.rimesyarifix.ui.home.DataProdukAdapter;
import com.rahmacom.rimesyarifix.ui.home.Produk;

import java.util.ArrayList;

public class TransaksiRiwayatFragment extends Fragment {

    private TransaksiRiwayatViewModel mViewModel;
    private FragmentTransaksiRiwayatBinding binding;

    public static TransaksiRiwayatFragment newInstance() {
        return new TransaksiRiwayatFragment();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentTransaksiRiwayatBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpDataProduk();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private ArrayList<Transaksi> getListTransaksi() {
        String[] noTransaksi = getResources().getStringArray(R.array.no_transaksi);
        String[] tanggal = getResources().getStringArray(R.array.tanggal_transaksi);
        String[] jumlah = getResources().getStringArray(R.array.jumlah_transaksi);
        String[] total = getResources().getStringArray(R.array.total_harga);

        ArrayList<Transaksi> list = new ArrayList<>();

        for (int i = 0; i < noTransaksi.length; i++) {
            Transaksi transaksi = new Transaksi();
            transaksi.setNoTransaksi(noTransaksi[i]);
            transaksi.setTanggal(tanggal[i]);
            transaksi.setJumlah(jumlah[i]);
            transaksi.setTotal(total[i]);
            list.add(transaksi);
        }
        return list;
    }

    private void setUpDataProduk() {
        ArrayList<Transaksi> listTransaksi = getListTransaksi();
        TransaksiRiwayatAdapter adapter = new TransaksiRiwayatAdapter();
        adapter.setLists(listTransaksi);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);

        binding.rvFragmentTransaksiRiwayat.setAdapter(adapter);
        binding.rvFragmentTransaksiRiwayat.setLayoutManager(gridLayoutManager);
    }
}