package com.rahmacom.rimesyarifix.ui.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.AuthRepository;
import com.rahmacom.rimesyarifix.data.network.response.LoginResponse;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import java.util.Objects;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class RegisterViewModel extends ViewModel {

    private final SavedStateHandle savedStateHandle;
    private final MutableLiveData<Register> register = new MutableLiveData<>();
    private AuthRepository authRepository;
    public final LiveData<Resource<LoginResponse>> registerUser = Transformations.switchMap(register, user -> authRepository.register(user.name, user.email, user.password));

    @Inject
    public RegisterViewModel(AuthRepository authRepository, SavedStateHandle savedStateHandle) {
        this.authRepository = authRepository;
        this.savedStateHandle = savedStateHandle;
    }

    public void setRegister(String name, String email, String password, String passwordConfirm) throws Exception {
        Register register = new Register(name, email, password, passwordConfirm);
        if (register.isPasswordConfirmable()) {
            this.register.setValue(register);
        } else {
            throw new Exception("Password do not match");
        }
    }

    static class Register {
        String name;
        String email;
        String password;
        String passwordConfirm;

        public Register(String name, String email, String password, String passwordConfirm) {
            this.name = name;
            this.email = email;
            this.password = password;
            this.passwordConfirm = passwordConfirm;
        }

        Boolean isPasswordConfirmable(String password, String passwordConfirm) {
            if (password == null && passwordConfirm == null) {
                return false;
            }

            return Objects.equals(password, passwordConfirm);
        }

        public Boolean isPasswordConfirmable() {
            return isPasswordConfirmable(this.password, this.passwordConfirm);
        }
    }
}