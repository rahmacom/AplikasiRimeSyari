package com.rahmacom.rimesyarifix.ui.splash_screen;

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
public class SplashScreenViewModel extends ViewModel {

    private final MutableLiveData<String> liveToken = new MutableLiveData<>();
    private MainRepository mainRepository;
    public LiveData<Resource<ResponseLogin>> refreshLogin = Transformations.switchMap(liveToken, login -> mainRepository.refreshLogin(login));

    @Inject
    public SplashScreenViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public void setToken(String token) {
        liveToken.setValue(token);
    }
}
