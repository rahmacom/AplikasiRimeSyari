package com.rahmacom.rimesyarifix.ui.form_profil_biodata;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.MainRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FormProfilBiodataViewModel extends ViewModel {

    private MainRepository mainRepository;
    private MutableLiveData<String> liveToken = new MutableLiveData<>();

    @Inject
    public FormProfilBiodataViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }
}