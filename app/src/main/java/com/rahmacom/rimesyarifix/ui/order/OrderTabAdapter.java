package com.rahmacom.rimesyarifix.ui.order;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.rahmacom.rimesyarifix.data.entity.Order;
import com.rahmacom.rimesyarifix.databinding.ItemOrderPageBinding;

import java.util.ArrayList;

public class OrderTabAdapter extends FragmentStateAdapter {

    public OrderTabAdapter(
            @NonNull FragmentManager fragmentManager,
            @NonNull Lifecycle lifecycle
    ) {
        super(fragmentManager, lifecycle);
    }

    private ArrayList<Fragment> fragments = new ArrayList<>();

    public void setFragments(ArrayList<Fragment> fragments) {
        this.fragments.clear();
        this.fragments.addAll(fragments);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemCount() {
        return fragments.size();
    }
}
