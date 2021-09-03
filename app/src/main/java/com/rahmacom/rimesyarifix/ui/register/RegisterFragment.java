package com.rahmacom.rimesyarifix.ui.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.rahmacom.rimesyarifix.databinding.FragmentRegisterBinding;
import com.rahmacom.rimesyarifix.manager.PreferenceManager;
import com.rahmacom.rimesyarifix.utils.Const;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    private RegisterViewModel viewModel;
    private NavController navController;
    private PreferenceManager manager;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(RegisterViewModel.class);
        navController = Navigation.findNavController(view);
        manager = new PreferenceManager(requireContext());

        binding.tvLoginClick.setOnClickListener(v -> {
            navController.navigate(RegisterFragmentDirections.registerFragmentToLoginFragment());
        });

        final EditText etUser = binding.edtRegisterNamaLengkap;
        final EditText etHp = binding.edtRegisterNoHp;
        final EditText etPassword = binding.edtRegisterPassword;
        final EditText etPasswordConfirm = binding.edtRegisterPasswordConfirm;

        binding.btnRegisterDaftar.setOnClickListener(v -> {
            try {
                viewModel.setRegister(etUser.getText().toString(),
                        etHp.getText().toString(),
                        etPassword.getText().toString(),
                        etPasswordConfirm.getText().toString());
            } catch (Exception e) {
                Toast.makeText(requireContext(), e.getMessage(), Toast.LENGTH_SHORT)
                        .show();
            } finally {
                callRegister();
            }
        });
    }

    private void callRegister() {
        viewModel.registerUser.observe(getViewLifecycleOwner(), register -> {
            switch (register.getStatus()) {
                case SUCCESS:
                    Toast.makeText(requireContext(), "Registrasi berhasil!", Toast.LENGTH_SHORT)
                            .show();
                    manager.setString(Const.KEY_TOKEN, register.getData()
                            .getAccessToken());
                    manager.setInt(Const.KEY_TTL, register.getData()
                            .getExpiresIn());
                    manager.setString(Const.KEY_TYPE, register.getData()
                            .getTokenType());

                    navController.navigate(RegisterFragmentDirections.registerFragmentToNavHome());
                    break;

                case ERROR:
                    binding.btnRegisterDaftar.setText("Daftar");
                    binding.pbRegisterLoading.setVisibility(View.INVISIBLE);
                    Toast.makeText(requireContext(), "Terjadi error! Silahkan hubungi " + "admin Rime Syari", Toast.LENGTH_SHORT)
                            .show();
                    break;

                case EMPTY:
                    break;

                case LOADING:
                    Toast.makeText(requireContext(), "Tunggu sebentar...", Toast.LENGTH_SHORT)
                            .show();
                    binding.pbRegisterLoading.setVisibility(View.VISIBLE);
                    binding.btnRegisterDaftar.setText("");
                    break;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}