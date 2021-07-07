package com.rahmacom.rimesyarifix.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.rahmacom.rimesyarifix.databinding.FragmentHomeBinding;
import com.rahmacom.rimesyarifix.ui.RecycleViewAdapter;

public class HomeFragment extends Fragment {

    private RecycleViewAdapter rvAdapter;
    private FragmentHomeBinding binding;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.d("HomeFragment", "This is home fragment");

        rvAdapter = new RecycleViewAdapter();
        binding.rvProductMain.setHasFixedSize(true);
        binding.rvProductMain.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rvProductMain.setAdapter(rvAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}