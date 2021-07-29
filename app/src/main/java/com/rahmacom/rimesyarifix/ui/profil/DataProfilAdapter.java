package com.rahmacom.rimesyarifix.ui.profil;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.databinding.ItemDataProfilBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;

import java.util.ArrayList;

public class DataProfilAdapter extends RecyclerView.Adapter<DataProfilAdapter.ViewHolder> {

    private ItemDataProfilBinding binding;
    private ArrayList<Profil> listData = new ArrayList<>();
    private OnItemClickCallback onItemClickCallback = null;

    public void setLists(ArrayList<Profil> list) {
        listData.clear();
        listData.addAll(list);
        notifyDataSetChanged();
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public DataProfilAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemDataProfilBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull DataProfilAdapter.ViewHolder holder, int position) {

        holder.bind(listData.get(position));

        if (position == getItemCount() - 1) {
            holder.binding.ivCaretRight.setVisibility(View.INVISIBLE);
            holder.itemView.setOnClickListener(v -> {
                PreferenceManager preferenceManager = new PreferenceManager(v.getContext());
                preferenceManager.removePreference(Const.KEY_TOKEN);
                preferenceManager.removePreference(Const.KEY_TTL);
                preferenceManager.removePreference(Const.KEY_TYPE);
                Navigation.findNavController(v).navigate(R.id.global_to_loginFragment);
                Toast.makeText(v.getContext(), "Logged out", Toast.LENGTH_SHORT).show();
            });
        } else {
            holder.binding.ivCaretRight.setVisibility(View.VISIBLE);
            holder.itemView.setOnClickListener(v ->
                    Navigation.findNavController(v).navigate(listData.get(position).getActionId())
            );
        }
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private ItemDataProfilBinding binding;

        ViewHolder(@NonNull ItemDataProfilBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Profil profil) {
            binding.tvProfilText.setText(profil.getTitle());
            Glide.with(binding.getRoot())
                    .load(profil.getDrawableId())
                    .into(binding.ivProfilIcon);
        }
    }

    interface OnItemClickCallback {
        void onItemClicked(Profil profil);
    }
}
