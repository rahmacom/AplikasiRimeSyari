package com.rahmacom.rimesyarifix.ui.profil_biodata;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rahmacom.rimesyarifix.databinding.ItemBiodataProfilBinding;
import com.rahmacom.rimesyarifix.ui.profil.Profil;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class    BiodataProfilAdapter extends RecyclerView.Adapter<BiodataProfilAdapter.ViewHolder> {

    private ItemBiodataProfilBinding binding;
    private ArrayList<Profil> listBiodata = new ArrayList<>();
    private OnItemClickCallback onItemClickCallback;

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public void setLists(ArrayList<Profil> list) {
        listBiodata.clear();
        listBiodata.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        binding = ItemBiodataProfilBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(listBiodata.get(position));
        holder.itemView.setOnClickListener((View.OnClickListener) onItemClickCallback);
    }

    @Override
    public int getItemCount() {
        return listBiodata.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ItemBiodataProfilBinding binding;

        public ViewHolder(ItemBiodataProfilBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Profil profil) {
            binding.tvBiodataProfilJudul.setText(profil.getTitle());
            binding.tvBiodataProfilData.setText(profil.getValue());
        }
    }

    interface OnItemClickCallback {
        void onItemClicked(Profil profil);
    }
}
