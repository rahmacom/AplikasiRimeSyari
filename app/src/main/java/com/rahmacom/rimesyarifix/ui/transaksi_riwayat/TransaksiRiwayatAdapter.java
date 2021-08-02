package com.rahmacom.rimesyarifix.ui.transaksi_riwayat;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rahmacom.rimesyarifix.databinding.ItemTransaksiRiwayatBinding;

import java.util.ArrayList;

public class TransaksiRiwayatAdapter extends RecyclerView.Adapter<TransaksiRiwayatAdapter.ViewHolder> {
    private ItemTransaksiRiwayatBinding binding;
    private ArrayList<Transaksi> listData = new ArrayList<>();

    public void setLists(ArrayList<Transaksi> list) {
        listData.clear();
        listData.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TransaksiRiwayatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemTransaksiRiwayatBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(listData.get(position));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemTransaksiRiwayatBinding binding;

        ViewHolder(@NonNull ItemTransaksiRiwayatBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Transaksi transaksi) {
            binding.tvItemNoTransaksi.setText(transaksi.getNoTransaksi());
        }
    }
}
