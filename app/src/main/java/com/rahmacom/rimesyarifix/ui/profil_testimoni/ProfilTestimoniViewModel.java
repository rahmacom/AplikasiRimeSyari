package com.rahmacom.rimesyarifix.ui.profil_testimoni;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.MainRepository;
import com.rahmacom.rimesyarifix.data.model.Testimony;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import java.util.List;

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

    public final LiveData<Resource<List<Testimony>>> userTestimonies = Transformations.switchMap(liveToken, token ->
            mainRepository.userTestimonies(token));

    public void setLiveToken(String token) {
        liveToken.setValue(token);
    }
}