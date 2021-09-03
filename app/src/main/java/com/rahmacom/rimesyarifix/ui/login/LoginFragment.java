package com.rahmacom.rimesyarifix.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.rahmacom.rimesyarifix.databinding.FragmentLoginBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class LoginFragment extends Fragment {

    private LoginViewModel viewModel;
    private FragmentLoginBinding binding;
    private PreferenceManager manager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        manager = new PreferenceManager(requireContext());

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
                switch (login.getStatus()) {
                    case SUCCESS:
                        Toast.makeText(requireContext(), "Login berhasil", Toast.LENGTH_SHORT)
                                .show();
                        manager.setString(Const.KEY_TOKEN, login.getData()
                                .getTokenType() + " " + login.getData()
                                .getAccessToken());
                        manager.setString(Const.KEY_TYPE, login.getData()
                                .getTokenType());
                        manager.setInt(Const.KEY_TTL, login.getData()
                                .getExpiresIn());

                        navController.navigate(LoginFragmentDirections.loginFragmentToNavHome());
                        break;

                    case ERROR:
                        binding.pbLoginLoading.setVisibility(View.INVISIBLE);
                        binding.btnLogin.setText("Login");
                        Toast.makeText(requireContext(), "Terjadi error! Silahkan hubungi admin Rime Syari", Toast.LENGTH_SHORT)
                                .show();
                        break;

                    case EMPTY:
                        break;

                    case LOADING:
                        binding.pbLoginLoading.setVisibility(View.VISIBLE);
                        binding.btnLogin.setText("");
                        Toast.makeText(requireContext(), "Tunggu sebentar...", Toast.LENGTH_SHORT)
                                .show();
                        break;

                    case INVALID:
                        binding.pbLoginLoading.setVisibility(View.INVISIBLE);
                        binding.btnLogin.setText("Login");
                        etUser.setText("");
                        etPass.setText("");
                        Toast.makeText(requireContext(), "Login gagal! Username atau password salah", Toast.LENGTH_SHORT)
                                .show();
                        break;
                }
            });
        });

        binding.tvRegisterLink.setOnClickListener(v -> {
            navController.navigate(LoginFragmentDirections.loginFragmentToRegisterFragment());
        });

        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        };

        requireActivity()
                .getOnBackPressedDispatcher()
                .addCallback(onBackPressedCallback);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}