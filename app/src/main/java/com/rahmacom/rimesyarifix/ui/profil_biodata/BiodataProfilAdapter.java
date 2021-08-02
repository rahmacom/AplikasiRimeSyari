package com.rahmacom.rimesyarifix.ui.profil_biodata;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.databinding.ItemProfilBiodataBinding;
import com.rahmacom.rimesyarifix.ui.profil.Profil;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BiodataProfilAdapter extends RecyclerView.Adapter<BiodataProfilAdapter.ViewHolder> {

    private ItemProfilBiodataBinding binding;
    private ArrayList<Profil> listBiodata = new ArrayList<>();
    private OnItemClickCallback onItemClickCallback;
    private Fragment fragment;

    public BiodataProfilAdapter(Fragment fragment) {
        this.fragment = fragment;
    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public void setLists(ArrayList<Profil> list) {
        listBiodata.clear();
        listBiodata.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        binding = ItemProfilBiodataBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(listBiodata.get(position));
        if (position == 2) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDialog(fragment);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return listBiodata.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private ItemProfilBiodataBinding binding;

        public ViewHolder(ItemProfilBiodataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Profil profil) {
            binding.tvBiodataProfilJudul.setText(profil.getTitle());
            binding.tvBiodataProfilData.setText(profil.getValue());
        }
    }

    interface OnItemClickCallback {
        void onItemClicked(Profil profil);
    }

    private void showDialog(Fragment fragment) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(fragment.getContext());
        alertDialog.setView(fragment.getLayoutInflater().inflate(R.layout.dialog_biodata, null));
        alertDialog.setPositiveButton("simpan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(fragment.getContext(), "data tersimpan", Toast.LENGTH_SHORT).show();
            }
        });
        alertDialog.show();
    }
}
