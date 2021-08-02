package com.rahmacom.rimesyarifix.ui.home;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rahmacom.rimesyarifix.data.network.response.ResponseProduk;
import com.rahmacom.rimesyarifix.databinding.ItemHomeProdukBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class DataProdukAdapter extends RecyclerView.Adapter<DataProdukAdapter.ViewHolder> {

    private ItemHomeProdukBinding binding;
    private ArrayList<ResponseProduk> listData = new ArrayList<>();
    private Context context;

    public DataProdukAdapter(Context context) {
        this.context = context;
    }

    public void setLists(ArrayList<ResponseProduk> list) {
        listData.clear();
        listData.addAll(list);
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
        holder.bind(listData.get(position));

        holder.itemView.setOnClickListener(v -> {
            HomeFragmentDirections.ActionHomeFragmentToProdukFragment action=HomeFragmentDirections.actionHomeFragmentToProdukFragment();
            action.setProductId(listData.get(position).getId());
            Navigation.findNavController(v).navigate(action);
        });
    }

    @Override

    public int getItemCount() {
        return listData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemHomeProdukBinding binding;
        private PreferenceManager manager = new PreferenceManager(itemView.getContext());

        ViewHolder(ItemHomeProdukBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(ResponseProduk produk) {

            if (manager.keyExists(Const.KEY_ROLE)) {
                manager.getString(Const.KEY_ROLE);
            }

            binding.tvNamaProduk.setText(produk.getNama());
            binding.tvHargaProduk.setText(String.valueOf(produk.getHargaCustomer()));
            binding.tvLikeProduk.setText(String.valueOf(produk.getSuka()));

            if (produk.getTotalStok() > 0) {
                binding.tvPreorderReadyProduk.setText("Stok tersedia!");
            } else {
                binding.tvPreorderReadyProduk.setText("Stok habis!");
            }

            if (!produk.getFiles().isEmpty()) {
                Glide.with(binding.getRoot())
                        .load(produk.getFiles().get(0).getUrl())
                        .into(binding.ivGambarProduk);
            }
        }
    }
}
