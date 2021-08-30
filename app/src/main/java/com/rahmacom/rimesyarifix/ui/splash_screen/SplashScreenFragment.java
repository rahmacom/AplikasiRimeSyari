package com.rahmacom.rimesyarifix.ui.splash_screen;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.rahmacom.rimesyarifix.R;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;

public class SplashScreenFragment extends Fragment {

    private SplashScreenViewModel viewModel;
    private PreferenceManager manager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(SplashScreenViewModel.class);
        manager = new PreferenceManager(requireContext());
        NavController navController = Navigation.findNavController(view);

        CountDownTimer timer = new CountDownTimer(3000, 3000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if (manager.keyExists(Const.KEY_TOKEN)) {
                    viewModel.setToken(manager.getString(Const.KEY_TOKEN));
                    viewModel.refreshLogin.observe(getViewLifecycleOwner(), login -> {
                        switch (login.getStatus()) {
                            case SUCCESS:
                                Log.d("login", login.getData()
                                        .toString());
                                manager.setString(Const.KEY_TOKEN, "Bearer " + login.getData()
                                        .getAccessToken());
                                navController.navigate(SplashScreenFragmentDirections.splashScreenFragmentToLoginFragment());
                                break;

                            case ERROR:
                            case INVALID:
                            case UNAUTHORIZED:
                        }
                    });
                }
            }
        }.start();

    }
}