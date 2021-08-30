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

    private final SavedStateHandle savedStateHandle;
    private final MutableLiveData<String> liveToken = new MutableLiveData<>();
    private UserRepository userRepository;
    public LiveData<Resource<User>> profile = Transformations.switchMap(liveToken, token -> userRepository.profile(token));

    @Inject
    public ProfilViewModel(UserRepository userRepository, SavedStateHandle savedStateHandle) {
        this.userRepository = userRepository;
        this.savedStateHandle = savedStateHandle;
    }

    public void setLiveToken(String token) {
        liveToken.setValue(token);
    }
}
