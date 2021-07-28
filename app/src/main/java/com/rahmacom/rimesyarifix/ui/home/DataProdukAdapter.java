package com.rahmacom.rimesyarifix.ui.home;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rahmacom.rimesyarifix.databinding.ItemDataProfilBinding;
import com.rahmacom.rimesyarifix.databinding.ItemHomeProdukBinding;
import com.rahmacom.rimesyarifix.ui.profil.DataProfilAdapter;

import java.util.ArrayList;

public class DataProdukAdapter extends RecyclerView.Adapter<DataProdukAdapter.ViewHolder> {

    private ItemHomeProdukBinding binding;
    private ArrayList<Produk> listData = new ArrayList<>();

    public void setLists(ArrayList<Produk> list) {
        listData.clear();
        listData.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DataProdukAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemHomeProdukBinding.inflate (
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

    public int getItemCount() { return listData.size(); }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemHomeProdukBinding binding;

        ViewHolder(ItemHomeProdukBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Produk produk) {

            binding.tvNamaProduk.setText(produk.getNama());
            Glide.with(binding.getRoot())
                    .load(produk.getGambar())
                    .into(binding.ivGambarProduk);

            binding.tvHargaProduk.setText(produk.getHarga());
            binding.tvLikeProduk.setText(produk.getLike());

        }
    }
}
