package com.rahmacom.rimesyarifix.ui;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rahmacom.rimesyarifix.databinding.ItemCardProdukBinding;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    @NonNull
    @Override
    public RecycleViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCardProdukBinding binding = ItemCardProdukBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.ViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemCardProdukBinding binding;

        public ViewHolder(ItemCardProdukBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind() {
            binding.productName.setText("Khimar Kayla");
            binding.productPrice.setText("Rp. 135.000");
        }
    }

    interface OnItemClickCallback {
        void onItemCliCkListener();
    }
}
