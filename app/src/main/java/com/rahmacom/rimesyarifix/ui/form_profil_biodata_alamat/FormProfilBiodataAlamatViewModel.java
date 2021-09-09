package com.rahmacom.rimesyarifix.ui.form_profil_biodata_alamat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.MainRepository;
import com.rahmacom.rimesyarifix.data.model.UserShipment;
import com.rahmacom.rimesyarifix.data.model.Village;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FormProfilBiodataAlamatViewModel extends ViewModel {

    private MainRepository mainRepository;
    private MutableLiveData<String> liveToken = new MutableLiveData<>();
    private MutableLiveData<Alamat> liveAlamat = new MutableLiveData<>();
    private MutableLiveData<String> liveVillage = new MutableLiveData<>();

    @Inject
    public FormProfilBiodataAlamatViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public void setLiveToken(String token) {
        liveToken.setValue(token);
    }

    public void setLiveAlamat(int id) {
        Alamat alamat = new Alamat();
        alamat.id = id;
        liveAlamat.setValue(alamat);
    }

    public void setLiveAlamat(int id, String address, String kodePos, String catatan, boolean isDefault, long villageId) {
        Alamat alamat = new Alamat();
        alamat.id = id;
        alamat.alamat = address;
        alamat.kodePos = kodePos;
        alamat.catatan = catatan;
        alamat.isDefault = isDefault;
        alamat.villageId = villageId;

        liveAlamat.setValue(alamat);
    }

    public void setLiveVillage(String query) {
        liveVillage.setValue(query);
    }

    public final LiveData<Resource<UserShipment>> newShipmentAddress = Transformations.switchMap(liveAlamat, alamat ->
            mainRepository.newShipmentAddress(liveToken.getValue(), alamat.alamat, alamat.kodePos, alamat.catatan, alamat.isDefault, alamat.villageId));

    public final LiveData<Resource<UserShipment>> viewShipmentAddress = Transformations.switchMap(liveAlamat, alamat ->
            mainRepository.viewShipmentAddress(liveToken.getValue(), alamat.id));

    public final LiveData<Resource<UserShipment>> updateShipmentAddress = Transformations.switchMap(liveAlamat, alamat ->
            mainRepository.updateShipmentAddress(liveToken.getValue(), alamat.id, alamat.alamat, alamat.kodePos, alamat.catatan, alamat.isDefault, alamat.villageId));

    public final LiveData<Resource<List<Village>>> getVillages = Transformations.switchMap(liveToken, token ->
            mainRepository.getVillages(token));

    public final LiveData<Resource<List<Village>>> searchVillages = Transformations.switchMap(liveVillage, village ->
            mainRepository.searchVillages(liveToken.getValue(), village));

    static class Alamat {
        int id;
        String alamat;
        String kodePos;
        String catatan;
        boolean isDefault;
        long villageId;
    }
}