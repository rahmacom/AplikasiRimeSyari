package com.rahmacom.rimesyarifix.ui.login;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.rahmacom.rimesyarifix.data.network.response.ResponseLogin;
import com.rahmacom.rimesyarifix.databinding.FragmentLoginBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;

import java.util.Objects;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    private LoginViewModel viewModel;
    private FragmentLoginBinding binding;

    private SavedStateHandle savedStateHandle;

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

        binding.tvLoginForget.setOnClickListener(v -> {

        });

        viewModel = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
        NavController navController = NavHostFragment.findNavController(this);

        final EditText etUser = binding.edtLoginUsername;
        final EditText etPass = binding.edtLoginPassword;

        binding.btnLogin.setOnClickListener(v -> {
            viewModel.setLogin(
                    etUser.getText().toString(),
                    etPass.getText().toString()
            );
            viewModel.login.observe(getViewLifecycleOwner(), login -> {
                if (login != null) {
                    switch (login.getStatus()) {
                        case SUCCESS:
                            Toast.makeText(requireContext(), "Login berhasil",
                                    Toast.LENGTH_SHORT).show();

                            PreferenceManager preferenceManager =
                                    new PreferenceManager(requireContext());
                            preferenceManager.setString(Const.KEY_TOKEN, login.getData().getAccessToken());
                            preferenceManager.setString(Const.KEY_TYPE, login.getData().getTokenType());
                            preferenceManager.setInt(Const.KEY_TTL, login.getData().getExpiresIn());

                            navController.navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment());
                            break;

                        case ERROR:
                            binding.pbLoginLoading.setVisibility(View.INVISIBLE);
                            binding.btnLogin.setText("Login");
                            Toast.makeText(
                                    requireContext(),
                                    "Terjadi error! Silahkan hubungi admin Rime Syari",
                                    Toast.LENGTH_SHORT
                            ).show();
                            break;

                        case EMPTY:
                            break;

                        case LOADING:
                            binding.pbLoginLoading.setVisibility(View.VISIBLE);
                            binding.btnLogin.setText("");
                            Toast.makeText(
                                    requireContext(),
                                    "Tunggu sebentar...",
                                    Toast.LENGTH_SHORT
                            ).show();
                            break;

                        case INVALID:
                            binding.pbLoginLoading.setVisibility(View.INVISIBLE);
                            binding.btnLogin.setText("Login");
                            etUser.setText("");
                            etPass.setText("");
                            Toast.makeText(
                                    requireContext(),
                                    "Login gagal! Username atau password salah",
                                    Toast.LENGTH_SHORT
                            ).show();
                            break;
                    }
                }
            });
        });

        binding.tvRegisterLink.setOnClickListener(v -> {
            navController.navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment());
        });

        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(onBackPressedCallback);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}