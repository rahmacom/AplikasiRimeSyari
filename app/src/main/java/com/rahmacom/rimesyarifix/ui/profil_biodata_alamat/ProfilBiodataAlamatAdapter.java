package com.rahmacom.rimesyarifix.ui.profil_biodata_alamat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rahmacom.rimesyarifix.data.model.UserShipment;
import com.rahmacom.rimesyarifix.databinding.ItemProfilBiodataAlamatListBinding;

import java.util.ArrayList;

public class ProfilBiodataAlamatAdapter extends RecyclerView.Adapter<ProfilBiodataAlamatAdapter.ViewHolder> {

    private ItemProfilBiodataAlamatListBinding binding;
    private ArrayList<UserShipment> list = new ArrayList<>();
    private OnItemClickListener onItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public void setList(ArrayList<UserShipment> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void removeItem(UserShipment userShipment) {
        int position = list.indexOf(userShipment);
        list.remove(position);
        notifyItemRemoved(position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnLongItemClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemProfilBiodataAlamatListBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));

        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(list.get(position)));

        holder.itemView.setOnLongClickListener(v -> {
            onItemLongClickListener.onLongItemClick(v, list.get(position));
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemProfilBiodataAlamatListBinding binding;

        public ViewHolder(ItemProfilBiodataAlamatListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(UserShipment userShipment) {
            binding.tvFormAlamatText.setText(userShipment.getAlamat());
            binding.tvFormAlamatKotaText.setText(userShipment.getVillage().getName());
        }
    }

    interface OnItemClickListener {
        void onItemClick(UserShipment userShipment);
    }

    interface OnItemLongClickListener {
        void onLongItemClick(View view, UserShipment userShipment);
    }
}