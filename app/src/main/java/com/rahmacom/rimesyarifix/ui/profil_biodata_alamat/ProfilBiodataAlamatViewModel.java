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
    private final MutableLiveData<UserShipment> liveUserShipment = new MutableLiveData<>();

    @Inject
    public ProfilBiodataAlamatViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public LiveData<Resource<List<UserShipment>>> userShipmentAddresses = Transformations.switchMap(liveToken,
            token -> mainRepository.shipmentAddresses(token));

    public LiveData<Resource<UserShipment>> defaultUserShipment = Transformations.switchMap(liveToken,
            token -> mainRepository.defaultShipmentAddress(token));

    public LiveData<Resource<UserShipment>> viewUserShipmentAddress = Transformations.switchMap(liveUserShipment,
            userShipment -> mainRepository.viewShipmentAddress(liveToken.getValue(), userShipment.getId()));

    public LiveData<Resource<UserShipment>> removeUserShipmentAddress = Transformations.switchMap(liveUserShipment,
            userShipment -> mainRepository.removeShipmentAddress(liveToken.getValue(), userShipment.getId()));

    public LiveData<Resource<UserShipment>> setAsDefaultShipment = Transformations.switchMap(liveUserShipment,
            userShipment -> mainRepository.setAsDefaultShipmentAddress(liveToken.getValue(), userShipment.getId()));

    public void setLiveToken(String token) {
        liveToken.setValue(token);
    }

    public void setLiveUserShipment(int userShipmentId) {
        UserShipment userShipment = new UserShipment();
        userShipment.setId(userShipmentId);
        liveUserShipment.setValue(userShipment);
    }

    public void setLiveUserShipment(int userShipmentId, String alamat, String kodePos, String catatan, boolean isDefault, int villageId) {
        UserShipment userShipment = new UserShipment();
        userShipment.setId(userShipmentId);
        userShipment.setAlamat(alamat);
        userShipment.setKodePos(kodePos);
        userShipment.setCatatan(catatan);
        userShipment.setDefault(isDefault);
        userShipment.setVillageId(villageId);
        liveUserShipment.setValue(userShipment);
    }
}