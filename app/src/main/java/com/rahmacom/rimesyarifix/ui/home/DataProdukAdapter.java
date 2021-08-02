package com.rahmacom.rimesyarifix.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rahmacom.rimesyarifix.data.network.response.ResponseProduk;
import com.rahmacom.rimesyarifix.databinding.ItemHomeProdukBinding;

import java.util.ArrayList;

public class DataProdukAdapter extends RecyclerView.Adapter<DataProdukAdapter.ViewHolder> {

    private ItemHomeProdukBinding binding;
    private ArrayList<Produk> listData = new ArrayList<>();
    private ArrayList<ResponseProduk> listData2 = new ArrayList<>();

    public void setLists(ArrayList<Produk> list) {
        listData.clear();
        listData.addAll(list);
        notifyDataSetChanged();
    }

    public void setLists2(ArrayList<ResponseProduk> list) {
        listData2.clear();
        listData2.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DataProdukAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemHomeProdukBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.bind(listData.get(position));
        Log.d("listData2", listData2.toString());
        holder.bind2(listData2.get(position));
    }

    @Override

    public int getItemCount() {
//        return listData.size();
        return listData2.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemHomeProdukBinding binding;

        ViewHolder(ItemHomeProdukBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Produk produk) {
            binding.tvNamaProduk.setText(produk.getNama());
            binding.tvHargaProduk.setText(produk.getHarga());
            binding.tvLikeProduk.setText(String.valueOf(produk.getLike()));
            binding.tvPreorderReadyProduk.setText(String.valueOf(produk.getPreOrderReady()));
            Glide.with(binding.getRoot())
                    .load(produk.getGambar())
                    .into(binding.ivGambarProduk);

        }

        void bind2(ResponseProduk produk) {
            binding.tvNamaProduk.setText(produk.getNama());
            binding.tvHargaProduk.setText(String.valueOf(produk.getHargaCustomer()));
            binding.tvLikeProduk.setText(String.valueOf(produk.getSuka()));
            binding.tvPreorderReadyProduk.setText(String.valueOf(produk.getTotalStok()));
//            Glide.with(binding.getRoot())
//                    .load(produk.getGambar())
//                    .into(binding.ivGambarProduk);

        }
    }
}
