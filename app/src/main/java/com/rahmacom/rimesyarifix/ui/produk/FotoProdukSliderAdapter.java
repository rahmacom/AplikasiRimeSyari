package com.rahmacom.rimesyarifix.ui.produk;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rahmacom.rimesyarifix.data.model.Image;
import com.rahmacom.rimesyarifix.databinding.ItemProdukFotoSliderBinding;
import com.rahmacom.rimesyarifix.utils.Const;

import java.util.ArrayList;
import java.util.List;

public class FotoProdukSliderAdapter extends RecyclerView.Adapter<FotoProdukSliderAdapter.ViewHolder> {

    private final ArrayList<Image> images = new ArrayList<>();
    private ItemProdukFotoSliderBinding binding;

    public void setImages(@NonNull List<Image> items) {
        images.clear();
        images.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemProdukFotoSliderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemProdukFotoSliderBinding binding;

        public ViewHolder(@NonNull ItemProdukFotoSliderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Image image) {
            Glide.with(binding.getRoot())
                    .load(Const.BASE_URL + image.getPath())
                    .into(binding.ivItemProdukFotoSlider);
        }
    }
}
