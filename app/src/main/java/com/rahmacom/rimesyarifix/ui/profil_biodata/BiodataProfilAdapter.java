package com.rahmacom.rimesyarifix.ui.profil_biodata;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rahmacom.rimesyarifix.databinding.ItemProfilBiodataListBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BiodataProfilAdapter extends RecyclerView.Adapter<BiodataProfilAdapter.ViewHolder> {

    private final ArrayList<Biodata> listBiodata = new ArrayList<>();
    private ItemProfilBiodataListBinding binding;

    public void setLists(ArrayList<Biodata> list) {
        listBiodata.clear();
        listBiodata.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        binding = ItemProfilBiodataListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(listBiodata.get(position));
    }

    @Override
    public int getItemCount() {
        return listBiodata.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemProfilBiodataListBinding binding;

        public ViewHolder(ItemProfilBiodataListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Biodata biodata) {
            binding.tvProfilBiodataListTitle.setText(biodata.getKey());
            binding.tvProfilBiodataListText.setText(biodata.getValue());
        }
    }
}
