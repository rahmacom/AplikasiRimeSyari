package com.rahmacom.rimesyarifix.ui.postingan;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.databinding.FragmentKeranjangDetailBinding;
import com.rahmacom.rimesyarifix.databinding.FragmentPostinganBinding;

public class PostinganFragment extends Fragment {

    private PostinganViewModel viewModel;
    private FragmentPostinganBinding binding;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState
    ) {
        binding = FragmentPostinganBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}