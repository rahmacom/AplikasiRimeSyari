package com.rahmacom.rimesyarifix.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rahmacom.rimesyarifix.data.model.Product;
import com.rahmacom.rimesyarifix.databinding.ItemHomeProdukGridBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;
import com.rahmacom.rimesyarifix.utils.Helper;

import java.util.ArrayList;

public class HomeProdukAdapter extends RecyclerView.Adapter<HomeProdukAdapter.ViewHolder> {

    private final ArrayList<Product> listData = new ArrayList<>();
    private ItemHomeProdukGridBinding binding;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setLists(ArrayList<Product> list) {
        listData.clear();
        listData.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public HomeProdukAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemHomeProdukGridBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(listData.get(position));

        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(listData.get(position)));
    }

    @Override

    public int getItemCount() {
        return listData.size();
    }

    interface OnItemClickListener {
        void onItemClick(Product product);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemHomeProdukGridBinding binding;
        private final PreferenceManager manager = new PreferenceManager(itemView.getContext());

        ViewHolder(ItemHomeProdukGridBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Product product) {
            binding.tvHomeProdukNama.setText(product.getNama());
            binding.tvHomeProdukHarga.setText(Helper.convertToRP(product.getHarga()));
            binding.tvHomeProdukSuka.setText(String.valueOf(product.getSuka()));
            binding.tvHomeProdukRating.setText(String.format("%s / 5", product.getReviewAvg()));

            if (product.getTotalStok() > 0) {
                binding.tvHomeProdukStokTersedia.setText("Stok tersedia!");
            } else {
                binding.tvHomeProdukStokTersedia.setText("Stok habis!");
            }

            if (product.getImage() != null) {
                Glide.with(binding.getRoot())
                        .load(Const.BASE_URL + product.getImage()
                                .getPath())
                        .into(binding.ivHomeProdukFoto);
            }
        }
    }
}
