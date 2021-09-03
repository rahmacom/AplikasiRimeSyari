package com.rahmacom.rimesyarifix.ui.profil_testimoni;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.MainRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProfilTestimoniViewModel extends ViewModel {

    private MainRepository mainRepository;
    private MutableLiveData<String> liveToken = new MutableLiveData<>();

    @Inject
    public ProfilTestimoniViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public void setLiveToken(String token) {
        liveToken.setValue(token);
    }
}