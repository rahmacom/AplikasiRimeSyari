package com.rahmacom.rimesyarifix.ui.login;

import android.view.View;

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

    private MainRepository mainRepository;
    private SavedStateHandle savedStateHandle;

    private MutableLiveData<Login> credentials = new MutableLiveData<>();

    public MutableLiveData<String> username = new MutableLiveData<>();
    public MutableLiveData<String> password = new MutableLiveData<>();

    @Inject
    public LoginViewModel(MainRepository mainRepository, SavedStateHandle savedStateHandle) {
        this.mainRepository = mainRepository;
        this.savedStateHandle = savedStateHandle;
    }

    public final LiveData<Resource<ResponseLogin>> login = Transformations.switchMap(credentials,
            (user) -> mainRepository.login(user.getUsername(), user.getPassword()));

    public void setLogin(String email, String password) {
        Login login = new Login(email, password);
        credentials.setValue(login);
    }
}