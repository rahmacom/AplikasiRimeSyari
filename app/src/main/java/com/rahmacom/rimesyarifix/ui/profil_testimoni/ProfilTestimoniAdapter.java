package com.rahmacom.rimesyarifix.ui.profil_testimoni;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rahmacom.rimesyarifix.data.model.Testimony;
import com.rahmacom.rimesyarifix.data.model.User;
import com.rahmacom.rimesyarifix.databinding.ItemProfilTestimoniListBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;
import com.rahmacom.rimesyarifix.utils.Helper;

import java.util.ArrayList;

public class ProfilTestimoniAdapter extends RecyclerView.Adapter<ProfilTestimoniAdapter.ViewHolder> {

    private final ArrayList<Testimony> lists = new ArrayList<>();
    private ItemProfilTestimoniListBinding binding;
    private Context context;

    public void setLists(ArrayList<Testimony> items) {
        lists.clear();
        lists.addAll(items);
        notifyDataSetChanged();
    }

    public ProfilTestimoniAdapter() {
    }

    public ProfilTestimoniAdapter(Context context) {
        this.context = context;
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

    class ViewHolder extends RecyclerView.ViewHolder {

        private final ItemProfilTestimoniListBinding binding;

        public ViewHolder(ItemProfilTestimoniListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Testimony testimony) {
            User user = testimony.getUser();
            if (user != null) {
                binding.tvProfilTestimoniNama.setText(user.getEmail());
                if (user.getAvatar() != null) {
                    Glide.with(binding.getRoot())
                            .load(Const.BASE_URL + testimony.getUser().getAvatar().getPath())
                            .into(binding.ivProfilTestimoniAvatar);
                }
            } else {
                PreferenceManager manager = new PreferenceManager(context);
                String nama = manager.getString(Const.KEY_EMAIL);

                Glide.with(binding.getRoot())
                        .load(manager.getString(Const.KEY_AVATAR))
                        .into(binding.ivProfilTestimoniAvatar);

                binding.tvProfilTestimoniNama.setText(nama);
            }

            if (testimony.getProduct() != null) {
                binding.tvProfilTestimoniProdukNama.setText("Produk " + testimony.getProduct().getNama());
            }

            binding.tvProfilTestimoniIsi.setText(testimony.getIsi());
            binding.ratingProfilTestimoniList.setRating(testimony.getRating());
        }
    }
}
