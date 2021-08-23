package com.rahmacom.rimesyarifix.ui.order;

import androidx.annotation.StringRes;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.databinding.FragmentOrderBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class OrderFragment extends Fragment {

    private OrderViewModel viewModel;
    private FragmentOrderBinding binding;
    private OrderTabAdapter tabAdapter;
    private NavController navController;
    private PreferenceManager manager;

    private String[] tabTitles;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = FragmentOrderBinding.inflate(inflater, container, false);
        tabTitles = getResources().getStringArray(R.array.tab_status_order);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(OrderViewModel.class);
        navController = Navigation.findNavController(view);
        manager = new PreferenceManager(requireContext());

        binding.tbOrder.setTitle("Riwayat Order");

        setupTabPages();

        new TabLayoutMediator(binding.tabsOrder,
                binding.vpTabOrder,
                (tab, position) -> tab.setText(tabTitles[position])).attach();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private void setupTabPages() {
        ArrayList<Fragment> fragments = new ArrayList<>();

        for (int i = 0; i < tabTitles.length; i++) {
            OrderListFragment fragment = new OrderListFragment();
            Bundle args = new Bundle();
            args.putInt(OrderListFragment.STATUS_ID, i);
            fragment.setArguments(args);
            fragments.add(fragment);
        }

        tabAdapter = new OrderTabAdapter(getChildFragmentManager(), getLifecycle());
        tabAdapter.setFragments(fragments);
        binding.vpTabOrder.setAdapter(tabAdapter);
    }
}