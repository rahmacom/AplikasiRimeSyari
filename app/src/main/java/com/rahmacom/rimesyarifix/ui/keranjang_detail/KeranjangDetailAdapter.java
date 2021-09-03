package com.rahmacom.rimesyarifix.ui.keranjang_detail;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rahmacom.rimesyarifix.data.model.Product;
import com.rahmacom.rimesyarifix.databinding.ItemKeranjangDetailListBinding;
import com.rahmacom.rimesyarifix.utils.Const;
import com.rahmacom.rimesyarifix.utils.Helper;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class KeranjangDetailAdapter extends RecyclerView.Adapter<KeranjangDetailAdapter.ViewHolder> {

    private final ArrayList<Product> products = new ArrayList<>();
    private ItemKeranjangDetailListBinding binding;
    private OnProductItemChangedListener onProductItemChangedListener;

    private final ArrayList<Integer> checkedProducts = new ArrayList<>();

    public void setLists(ArrayList<Product> items) {
        products.clear();
        products.addAll(items);
        notifyDataSetChanged();
    }

    public void setOnProductItemChangedListener(OnProductItemChangedListener onProductItemChangedListener) {
        this.onProductItemChangedListener = onProductItemChangedListener;
    }

    public List<Integer> getCheckedProducts() {
        return checkedProducts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemKeranjangDetailListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.bind(product);

        AtomicInteger jumlah = new AtomicInteger(product.getPivot().getJumlah());

        holder.binding.btnKeranjangProdukKurang.setOnClickListener(v -> {
            jumlah.getAndDecrement();
            holder.binding.tvKeranjangProdukJumlah.setText(String.valueOf(jumlah.get()));
            onProductItemChangedListener.onQuantityChanged(product, jumlah.get());
        });

        holder.binding.btnKeranjangProdukTambah.setOnClickListener(v -> {
            jumlah.getAndIncrement();
            holder.binding.tvKeranjangProdukJumlah.setText(String.valueOf(jumlah.get()));
            onProductItemChangedListener.onQuantityChanged(product, jumlah.get());
        });

        holder.binding.cbKeranjangProdukCheck.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                checkedProducts.add(product.getId());
            } else {
                checkedProducts.remove(position);
            }
        });
    }



    @Override
    public int getItemCount() {
        return products.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemKeranjangDetailListBinding binding;

        public ViewHolder(ItemKeranjangDetailListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Product product) {
            Glide.with(binding.getRoot())
                    .load(Const.BASE_URL + product.getImage()
                            .getPath())
                    .into(binding.ivKeranjangProdukFoto);

            binding.tvKeranjangProdukNama.setText(product.getNama());
            binding.tvKeranjangProdukHarga.setText(Helper.convertToRP(product.getHarga()));
            binding.tvKeranjangProdukJumlah.setText(String.valueOf(product.getPivot()
                    .getJumlah()));
            binding.tvKeranjangProdukSize.setText("Size: " + product.getPivot()
                    .getSize()
                    .getName());
            binding.tvKeranjangProdukWarna.setText("Warna: " + product.getPivot()
                    .getColor()
                    .getName());
            binding.tvKeranjangProdukSubTotal.setText(Helper.convertToRP(product.getPivot()
                    .getSubTotal()));
        }
    }

    interface OnProductItemChangedListener {
        void onQuantityChanged(Product product, int jumlah);
    }
}
