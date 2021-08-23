package com.rahmacom.rimesyarifix.ui.login;

import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.AuthRepository;
import com.rahmacom.rimesyarifix.data.entity.User;
import com.rahmacom.rimesyarifix.data.network.response.LoginResponse;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    private AuthRepository authRepository;
    private SavedStateHandle savedStateHandle;

    private MutableLiveData<Login> credentials = new MutableLiveData<>();
    private MutableLiveData<String> liveToken = new MutableLiveData<>();

    public MutableLiveData<String> username = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    @Inject
    public LoginViewModel(AuthRepository authRepository, SavedStateHandle savedStateHandle) {
        this.authRepository = authRepository;
        this.savedStateHandle = savedStateHandle;
    }

    public final LiveData<Resource<LoginResponse>> login = Transformations.switchMap(credentials,
            user -> authRepository.login(user.username, user.password));

    public final LiveData<Resource<User>> profile = Transformations.switchMap(liveToken,
            token -> authRepository.profile(token));

    public void setLiveToken(String token) {
        liveToken.setValue(token);
    }

    public void setLogin(String email, String password) {
        Login login = new Login(email, password);
        credentials.setValue(login);
    }

    static class Login {
        String username;
        String password;

        Login(String username, String password) {
            this.username = username;
            this.password = password;
        }

        boolean isUsernameValid() {
            return !TextUtils.isEmpty(username);
        }

        boolean isPasswordValid() {
            return password.length() >= 8 && password.matches("[A-Za-z0-9]");
        }
    }
}