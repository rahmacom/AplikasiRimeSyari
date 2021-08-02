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

import java.util.Objects;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    private RegisterViewModel viewModel;

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentRegisterBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        viewModel = new ViewModelProvider(requireActivity()).get(RegisterViewModel.class);
        NavController navController = Navigation.findNavController(view);
        PreferenceManager manager = new PreferenceManager(requireContext());

        binding.tvLoginClick.setOnClickListener(v -> {
            navController.navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment());
        });

        final EditText etUser = binding.edtRegisterNamaLengkap;
        final EditText etHp = binding.edtRegisterNoHp;
        final EditText etPassword = binding.edtRegisterPassword;
        final EditText etPasswordConfirm = binding.edtRegisterPasswordConfirm;

        binding.btnRegisterDaftar.setOnClickListener(v -> {
            if (validateCredentials(etPassword.getText().toString(),
                    etPasswordConfirm.getText().toString())) {

                Register register = new Register(
                        etUser.getText().toString(),
                        etHp.getText().toString(),
                        etPassword.getText().toString());

                viewModel.setRegister(register);

                callRegister(manager, navController);
            } else {
                Toast.makeText(requireContext(), "Password tidak cocok! Silahkan coba lagi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private Boolean validateCredentials(String password, String passwordConfirm) {
        return Objects.equals(password, passwordConfirm);
    }

    private void callRegister(PreferenceManager manager, NavController navController) {
        viewModel.register.observe(getViewLifecycleOwner(), responseLogin -> {
            if (responseLogin != null) {
                switch (responseLogin.getStatus()) {
                    case SUCCESS:
                        Toast.makeText(requireContext(), "Registrasi berhasil!", Toast.LENGTH_SHORT).show();
                        manager.setString(Const.KEY_TOKEN, responseLogin.getData().getAccessToken());
                        manager.setInt(Const.KEY_TTL,
                                responseLogin.getData().getExpiresIn());
                        manager.setString(Const.KEY_TYPE, responseLogin.getData().getTokenType());

                        navController.navigate(RegisterFragmentDirections.actionRegisterFragmentToHomeFragment());
                        break;

                    case ERROR:
                        binding.btnRegisterDaftar.setText("Daftar");
                        binding.pbRegisterLoading.setVisibility(View.INVISIBLE);
                        Toast.makeText(requireContext(), "Terjadi error! Silahkan hubungi " +
                                "admin Rime Syari", Toast.LENGTH_SHORT).show();
                        break;

                    case EMPTY:
                        break;

                    case LOADING:
                        Toast.makeText(requireContext(), "Tunggu sebentar...",
                                Toast.LENGTH_SHORT).show();
                        binding.pbRegisterLoading.setVisibility(View.VISIBLE);
                        binding.btnRegisterDaftar.setText("");
                        break;

                    case INVALID:
                        Toast.makeText(requireContext(), "Registrasi gagal!", Toast.LENGTH_SHORT).show();
                        binding.pbRegisterLoading.setVisibility(View.INVISIBLE);
                        binding.btnRegisterDaftar.setText("Daftar");

                        binding.edtRegisterNamaLengkap.setText("");
                        binding.edtRegisterNoHp.setText("");
                        binding.edtRegisterPassword.setText("");
                        binding.edtRegisterPasswordConfirm.setText("");
                        break;
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        binding = null;
    }
}