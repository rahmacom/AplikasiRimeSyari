package com.rahmacom.rimesyarifix.ui.produk;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rahmacom.rimesyarifix.data.entity.Cart;
import com.rahmacom.rimesyarifix.databinding.ItemProdukDialogListBinding;

import java.util.ArrayList;

public class ProdukDialogAdapter extends RecyclerView.Adapter<ProdukDialogAdapter.ViewHolder> {

    private ItemProdukDialogListBinding binding;
    private ArrayList<Cart> list = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setList(ArrayList<Cart> items) {
        list.clear();
        list.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemProdukDialogListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));

        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(list.get(position)));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ItemProdukDialogListBinding binding;

        public ViewHolder(ItemProdukDialogListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Cart cart) {
            binding.tvItemProdukDialogNama.setText(cart.getJudul());
        }
    }

    interface OnItemClickListener {
        void onItemClick(Cart cart);
    }
}
