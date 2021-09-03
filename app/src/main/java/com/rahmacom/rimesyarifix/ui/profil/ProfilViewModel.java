package com.rahmacom.rimesyarifix.ui.profil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.MainRepository;
import com.rahmacom.rimesyarifix.data.model.User;
import com.rahmacom.rimesyarifix.data.network.response.ResponseLogin;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProfilViewModel extends ViewModel {

    private final MutableLiveData<String> liveToken = new MutableLiveData<>();
    private MainRepository mainRepository;
    public LiveData<Resource<User>> profile = Transformations.switchMap(liveToken,
            token -> mainRepository.profile(token));

    @Inject
    public ProfilViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public LiveData<Resource<ResponseLogin>> logout = Transformations.switchMap(liveToken,
            token -> mainRepository.logout(token));

    public void setLiveToken(String token) {
        liveToken.setValue(token);
    }
}
