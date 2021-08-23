package com.rahmacom.rimesyarifix.ui.profil;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.UserRepository;
import com.rahmacom.rimesyarifix.data.entity.User;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProfilViewModel extends ViewModel {

    private UserRepository userRepository;
    private SavedStateHandle savedStateHandle;

    private MutableLiveData<String> liveToken = new MutableLiveData<>();

    @Inject
    public ProfilViewModel(UserRepository userRepository, SavedStateHandle savedStateHandle) {
        this.userRepository = userRepository;
        this.savedStateHandle = savedStateHandle;
    }

    public LiveData<Resource<User>> profile = Transformations.switchMap(liveToken,
            token -> userRepository.profile(token));

    public void setLiveToken(String token) {
        liveToken.setValue(token);
    }
}
