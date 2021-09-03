package com.rahmacom.rimesyarifix.ui.login;

import android.text.TextUtils;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.MainRepository;
import com.rahmacom.rimesyarifix.data.network.response.ResponseLogin;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class LoginViewModel extends ViewModel {

    private final MutableLiveData<Login> credentials = new MutableLiveData<>();
    private MainRepository mainRepository;
    public final LiveData<Resource<ResponseLogin>> login = Transformations.switchMap(credentials, user ->
            mainRepository.login(user.email, user.password));

    @Inject
    public LoginViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public void setLogin(String email, String password) {
        Login login = new Login(email, password);
        credentials.setValue(login);
    }

    static class Login {
        String email;
        String password;

        Login(String email, String password) {
            this.email = email;
            this.password = password;
        }

        boolean isEmailValid() {
            return !TextUtils.isEmpty(email);
        }

        boolean isPasswordValid() {
            return password.length() >= 8 && password.matches("[A-Za-z0-9]");
        }
    }
}