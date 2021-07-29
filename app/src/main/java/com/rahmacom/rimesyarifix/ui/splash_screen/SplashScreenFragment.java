package com.rahmacom.rimesyarifix.ui.splash_screen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.databinding.FragmentSplashScreenBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.ui.login.LoginFragmentDirections;
import com.rahmacom.rimesyarifix.utils.Const;

import org.jetbrains.annotations.NotNull;

public class SplashScreenFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController = Navigation.findNavController(view);

        CountDownTimer timer = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                PreferenceManager preferenceManager = new PreferenceManager(requireContext());
                if (!preferenceManager.keyExists(Const.KEY_TOKEN)) {
                    navController.navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToLoginFragment());
                } else {
                    navController.navigate(SplashScreenFragmentDirections.actionSplashScreenFragmentToHomeFragment());
                }
            }
        }.start();
    }
}