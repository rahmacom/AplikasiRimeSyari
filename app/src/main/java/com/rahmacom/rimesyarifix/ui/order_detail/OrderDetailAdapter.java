package com.rahmacom.rimesyarifix.ui.order_detail;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rahmacom.rimesyarifix.data.model.Product;
import com.rahmacom.rimesyarifix.databinding.ItemOrderDetailListBinding;
import com.rahmacom.rimesyarifix.utils.Const;
import com.rahmacom.rimesyarifix.utils.Helper;

import java.util.ArrayList;

public class OrderDetailAdapter extends RecyclerView.Adapter<OrderDetailAdapter.ViewHolder> {

    private final ArrayList<Product> list = new ArrayList<>();
    private ItemOrderDetailListBinding binding;
    private OnItemClickListener onItemClickListener;

    public void setList(ArrayList<Product> items) {
        list.clear();
        list.addAll(items);
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemOrderDetailListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    interface OnItemClickListener {
        void onItemClick(Product product);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemOrderDetailListBinding binding;

        public ViewHolder(ItemOrderDetailListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Product product) {
            binding.tvOrderProdukNama.setText(product.getNama());
            binding.tvOrderProdukHarga.setText(Helper.convertToRP(product.getHarga()));
            binding.tvOrderProdukJumlah.setText(String.format("x%d item", product.getPivot()
                    .getJumlah()));
            binding.tvOrderProdukWarna.setText("warna " + product.getPivot()
                    .getColor()
                    .getName());
            binding.tvOrderProdukSize.setText("size" + product.getPivot()
                    .getSize()
                    .getName());
            binding.tvOrderProdukSubtotal.setText(Helper.convertToRP(product.getPivot().getSubTotal()));

            Glide.with(binding.getRoot())
                    .load(Const.BASE_URL + product.getImage()
                            .getPath())
                    .into(binding.ivOrderProdukFoto);
        }
    }
}
