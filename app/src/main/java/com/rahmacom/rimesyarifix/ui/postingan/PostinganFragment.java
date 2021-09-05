package com.rahmacom.rimesyarifix.ui.postingan;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.rahmacom.rimesyarifix.databinding.FragmentPostinganBinding;

public class PostinganFragment extends Fragment {

    private PostinganViewModel viewModel;
    private FragmentPostinganBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentPostinganBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}