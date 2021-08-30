package com.rahmacom.rimesyarifix.ui.order;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.rahmacom.rimesyarifix.ui.order_list.OrderListFragment;

public class OrderTabAdapter extends FragmentStateAdapter {

    private int tabCount = 0;

    public OrderTabAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return new OrderListFragment();
    }

    @Override
    public int getItemCount() {
        return tabCount;
    }

    public void setItemCount(int tabCount) {
        this.tabCount = tabCount;
    }
}
