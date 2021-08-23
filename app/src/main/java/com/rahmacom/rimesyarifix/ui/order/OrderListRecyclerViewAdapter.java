package com.rahmacom.rimesyarifix.ui.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rahmacom.rimesyarifix.data.entity.Order;
import com.rahmacom.rimesyarifix.databinding.ItemOrderListBinding;
import com.rahmacom.rimesyarifix.utils.Helper;

import java.util.ArrayList;

public class OrderListRecyclerViewAdapter extends RecyclerView.Adapter<OrderListRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Order> list = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setList(ArrayList<Order> items) {
        this.list.clear();
        this.list.addAll(items);
        notifyDataSetChanged();
    }

    public void clearList() {
        list.clear();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemOrderListBinding binding = ItemOrderListBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));

        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClicked(list.get(position)));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ItemOrderListBinding binding;

        public ViewHolder(@NonNull ItemOrderListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Order order) {
            binding.tvOrderListTitle.setText(order.getNomor());
            binding.tvOrderListTotalItem.setText(order.getJumlah() + " item");
            binding.tvOrderListTotalHarga.setText(Helper.convertToRP(order.getTotal()));
            binding.tvOrderListStatus.setText(order.getStatus().getName());
        }
    }

    interface OnItemClickListener {
        void onItemClicked(Order order);
    }
}
