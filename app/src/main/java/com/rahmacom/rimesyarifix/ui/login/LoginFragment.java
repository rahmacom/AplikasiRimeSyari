package com.rahmacom.rimesyarifix.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.fragment.NavHostFragment;

import com.rahmacom.rimesyarifix.data.network.model.ResponseLogin;
import com.rahmacom.rimesyarifix.databinding.FragmentLoginBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;

public class LoginFragment extends Fragment {

    private LoginViewModel viewModel;
    private FragmentLoginBinding binding;

    private SavedStateHandle savedStateHandle;

    public static final String LOGIN_SUCCESS = "loginSuccess";

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);

        binding.btnLogin.setOnClickListener(v -> {
            viewModel.setLogin(
                    binding.edtLoginUsername.getText().toString(),
                    binding.edtLoginPassword.getText().toString()
            ).observe(getViewLifecycleOwner(), (Observer<ResponseLogin>) login -> {
                PreferenceManager manager = new PreferenceManager(requireContext());
                manager.setString(Const.KEY_TOKEN, login.getAccessToken());
                manager.setString(Const.KEY_TYPE, login.getTokenType());
                manager.setInt(Const.KEY_TTL, login.getExpiresIn());

                NavController navController = NavHostFragment.findNavController(this);
                NavGraph navGraph = navController.getGraph();
                navController.navigate(navGraph.getStartDestination());
            });
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}