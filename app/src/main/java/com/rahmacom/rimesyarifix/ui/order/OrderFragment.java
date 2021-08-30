package com.rahmacom.rimesyarifix.ui.order;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayoutMediator;
import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.databinding.FragmentOrderBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;

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
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        tabTitles = getResources().getStringArray(R.array.tab_status_order_berjalan);
        binding = FragmentOrderBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(OrderViewModel.class);
        navController = Navigation.findNavController(view);
        manager = new PreferenceManager(requireContext());

        setupTabPages();

        binding.toolbarFragmentOrder.inflateMenu(R.menu.menu_order);
        binding.toolbarFragmentOrder.setTitle("Order");
        binding.toolbarFragmentOrder.setOnMenuItemClickListener(this::onOptionsItemSelected);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }

    private void setupTabPages() {
        tabAdapter = new OrderTabAdapter(getChildFragmentManager(), getLifecycle());
        tabAdapter.setItemCount(tabTitles.length);
        binding.vpListOrder.setAdapter(tabAdapter);

        new TabLayoutMediator(binding.tabsOrder, binding.vpListOrder, (tab, position) -> tab.setText(tabTitles[position])).attach();

        binding.vpListOrder.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                viewModel.setStatusId(manager.getString(Const.KEY_TOKEN), position);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menu_riwayat_order) {
            navController.navigate(OrderFragmentDirections.navOrderToOrderHistoryFragment());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}