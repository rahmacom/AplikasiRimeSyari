package com.rahmacom.rimesyarifix.ui.register;

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
public class RegisterViewModel extends ViewModel {

    private MutableLiveData<Register> registerCredentials = new MutableLiveData<>();

    private MainRepository mainRepository;
    private SavedStateHandle savedStateHandle;

    @Inject
    public RegisterViewModel(MainRepository mainRepository, SavedStateHandle savedStateHandle) {
        this.mainRepository = mainRepository;
        this.savedStateHandle = savedStateHandle;
    }

    public final LiveData<Resource<ResponseLogin>> register =
            Transformations.switchMap(registerCredentials,
                    (user) -> mainRepository.register(user.getUsername(), user.getEmail(),
                            user.getPassword()));

    public void setRegister(Register register) {
        registerCredentials.setValue(register);
    }
}