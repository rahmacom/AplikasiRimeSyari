package com.rahmacom.rimesyarifix.ui.profil_biodata_alamat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.MainRepository;
import com.rahmacom.rimesyarifix.data.model.UserShipment;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProfilBiodataAlamatViewModel extends ViewModel {

    private MainRepository mainRepository;
    private final MutableLiveData<String> liveToken = new MutableLiveData<>();

    @Inject
    public ProfilBiodataAlamatViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public LiveData<Resource<List<UserShipment>>> getUserShipmentAddresses = Transformations.switchMap(liveToken,
            token -> mainRepository.getShipmentAddresses(token));

    public void setLiveToken(String token) {
        liveToken.setValue(token);
    }
}