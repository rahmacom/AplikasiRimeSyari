package com.rahmacom.rimesyarifix.ui.splash_screen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.AuthRepository;
import com.rahmacom.rimesyarifix.data.network.response.LoginResponse;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SplashScreenViewModel extends ViewModel {

    private final SavedStateHandle savedStateHandle;
    private final MutableLiveData<String> liveToken = new MutableLiveData<>();
    private AuthRepository authRepository;
    public LiveData<Resource<LoginResponse>> refreshLogin = Transformations.switchMap(liveToken, login -> authRepository.refreshLogin(login));

    @Inject
    public SplashScreenViewModel(AuthRepository authRepository, SavedStateHandle savedStateHandle) {
        this.authRepository = authRepository;
        this.savedStateHandle = savedStateHandle;
    }

    public void setToken(String token) {
        liveToken.setValue(token);
    }
}
