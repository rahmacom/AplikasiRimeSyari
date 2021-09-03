package com.rahmacom.rimesyarifix.ui.keranjang;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rahmacom.rimesyarifix.data.model.Cart;
import com.rahmacom.rimesyarifix.databinding.ItemKeranjangListBinding;
import com.rahmacom.rimesyarifix.utils.Helper;

import java.util.ArrayList;

public class KeranjangListAdapter extends RecyclerView.Adapter<KeranjangListAdapter.ViewHolder> {

    private final ArrayList<Cart> carts = new ArrayList<>();
    private ItemKeranjangListBinding binding;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setCarts(ArrayList<Cart> carts) {
        this.carts.clear();
        this.carts.addAll(carts);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemKeranjangListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(carts.get(position));

        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClicked(carts.get(position)));
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    interface OnItemClickListener {
        void onItemClicked(Cart cart);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemKeranjangListBinding binding;

        public ViewHolder(ItemKeranjangListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Cart cart) {
            binding.tvItemKeranjangJudul.setText(cart.getJudul());
            binding.tvItemKeranjangJumlah.setText(String.format("%d item", cart.getJumlah()));
            binding.tvItemKeranjangTotal.setText(Helper.convertToRP(cart.getTotal()));
        }
    }
}
