package com.rahmacom.rimesyarifix.ui.home;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rahmacom.rimesyarifix.data.model.Post;
import com.rahmacom.rimesyarifix.databinding.ItemHomePostSliderBinding;
import com.rahmacom.rimesyarifix.utils.Const;

import java.util.ArrayList;

public class HomePostAdapter extends RecyclerView.Adapter<HomePostAdapter.ViewHolder> {

    private ItemHomePostSliderBinding binding;
    private ArrayList<Post> list;
    private OnItemClickListener onItemClickListener;

    public void setList(ArrayList<Post> list) {
        this.list = list;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemHomePostSliderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(list.get(position));

        holder.itemView.setOnClickListener(v -> onItemClickListener.onItemClick(list.get(position)));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ItemHomePostSliderBinding binding;

        public ViewHolder(ItemHomePostSliderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Post post) {
            Glide.with(binding.getRoot())
                    .load(Const.BASE_URL + post.getImage().getPath())
                    .into(binding.ivItemPostSlider);
        }
    }

    interface OnItemClickListener {
        void onItemClick(Post post);
    }
}
