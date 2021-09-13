package com.rahmacom.rimesyarifix.ui.profil_testimoni;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rahmacom.rimesyarifix.data.model.Testimony;
import com.rahmacom.rimesyarifix.databinding.ItemProfilTestimoniListBinding;

import java.util.ArrayList;

public class ProfilTestimoniAdapter extends RecyclerView.Adapter<ProfilTestimoniAdapter.ViewHolder> {

    private final ArrayList<Testimony> lists = new ArrayList<>();
    private ItemProfilTestimoniListBinding binding;

    public void setLists(ArrayList<Testimony> items) {
        lists.clear();
        lists.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemProfilTestimoniListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(lists.get(position));
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemProfilTestimoniListBinding binding;

        public ViewHolder(ItemProfilTestimoniListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Testimony testimony) {
            String nama = testimony.getUser().getNamaLengkap();
            if (nama == null) {
                nama = testimony.getUser().getEmail();
            }

            binding.tvProfilTestimoniNama.setText(nama);
            binding.tvProfilTestimoniIsi.setText(testimony.getIsi());
            binding.tvProfilTestimoniRating.setText(testimony.getReview() + " star");

            if (testimony.getUser().getAvatar() != null) {
                Glide.with(binding.getRoot())
                        .load(testimony.getUser().getAvatar())
                        .into(binding.ivProfilTestimoniAvatar);
            }
        }
    }
}
