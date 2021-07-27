package com.rahmacom.rimesyarifix.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;

import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.databinding.FragmentHomeBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    public static final String ARGS_LOGIN = "args_login";
    public static final String ARG_USER = "arg_user_has_login";
    public static final String ARG_GUEST = "arg_user_not_login";

    private static final String[] TITLES = new String[]{
            "ABAYA",
            "OUTER",
            "KHIMAR",
            "MASKER"
    };

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

        NavController navController = Navigation.findNavController(view);
        PreferenceManager manager = new PreferenceManager(requireContext());

        if (!manager.keyExists(Const.KEY_TOKEN) || manager.getString(Const.KEY_TOKEN) == null) {
            navController.navigate(R.id.loginFragment);
        }

        binding.fragmentHomeToolbar.inflateMenu(R.menu.menu_main);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}