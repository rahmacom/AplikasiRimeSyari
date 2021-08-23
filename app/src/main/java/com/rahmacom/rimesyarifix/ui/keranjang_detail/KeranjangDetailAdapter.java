package com.rahmacom.rimesyarifix.ui.keranjang_detail;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rahmacom.rimesyarifix.data.entity.Product;
import com.rahmacom.rimesyarifix.databinding.ItemKeranjangDetailListBinding;
import com.rahmacom.rimesyarifix.utils.Const;
import com.rahmacom.rimesyarifix.utils.Helper;

import java.util.ArrayList;

public class KeranjangDetailAdapter
        extends RecyclerView.Adapter<KeranjangDetailAdapter.ViewHolder> {

    private ItemKeranjangDetailListBinding binding;
    private ArrayList<Product> products = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public void setLists(ArrayList<Product> items) {
        products.clear();
        products.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemKeranjangDetailListBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(products.get(position));

//        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(products.get(position)));
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ItemKeranjangDetailListBinding binding;

        public ViewHolder(ItemKeranjangDetailListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Product product) {
            Glide.with(binding.getRoot())
                    .load(Const.BASE_URL + product.getImage().getPath())
                    .into(binding.ivKeranjangProdukFoto);

            binding.tvKeranjangProdukNama.setText(product.getNama());
            binding.tvKeranjangProdukHarga.setText(Helper.convertToRP(product.getHargaCustomer()));
            binding.tvKeranjangProdukJumlah.setText(String.valueOf(product.getPivot().getJumlah()));
            binding.tvKeranjangProdukSize.setText("Size " + product.getPivot().getSize().getName());
            binding.tvKeranjangProdukWarna.setText("Warna " + product.getPivot().getColor().getName());
        }
    }

    interface OnItemClickListener {
        void onItemClick(Product product);
    }
}
