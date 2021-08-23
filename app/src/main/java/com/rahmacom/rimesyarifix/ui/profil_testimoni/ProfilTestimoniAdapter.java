package com.rahmacom.rimesyarifix.ui.profil_testimoni;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rahmacom.rimesyarifix.data.entity.Testimony;
import com.rahmacom.rimesyarifix.databinding.ItemProfilTestimoniListBinding;

import java.util.ArrayList;

public class ProfilTestimoniAdapter extends RecyclerView.Adapter<ProfilTestimoniAdapter.ViewHolder> {

    private ItemProfilTestimoniListBinding binding;
    private ArrayList<Testimony> lists = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public void setLists(ArrayList<Testimony> items) {
        lists.clear();
        lists.addAll(items);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemProfilTestimoniListBinding.inflate(LayoutInflater.from(
                parent.getContext()),
                parent,
                false);
        return new ViewHolder(binding);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(lists.get(position));

        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(lists.get(position)));
    }
    @Override
    public int getItemCount() {
        return lists.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemProfilTestimoniListBinding binding;

        public ViewHolder(ItemProfilTestimoniListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Testimony testimony) {

        }
    }

    interface OnItemClickListener {
        void onItemClick(Testimony testimony);
    }
}
