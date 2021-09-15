package com.rahmacom.rimesyarifix.ui.form_profil_biodata_alamat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.MainRepository;
import com.rahmacom.rimesyarifix.data.model.District;
import com.rahmacom.rimesyarifix.data.model.Province;
import com.rahmacom.rimesyarifix.data.model.Regency;
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
    private MutableLiveData<String> liveDistrict = new MutableLiveData<>();
    private MutableLiveData<String> liveRegency = new MutableLiveData<>();
    private MutableLiveData<String> liveProvince = new MutableLiveData<>();

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

    public void setLiveAlamat(int id, String address, String kodePos, String catatan, boolean isDefault, String village, String district, String regency, String province) {
        Alamat alamat = new Alamat();
        alamat.id = id;
        alamat.alamat = address;
        alamat.kodePos = kodePos;
        alamat.catatan = catatan;
        alamat.isDefault = isDefault;
        alamat.village = village;
        alamat.district = district;
        alamat.regency = regency;
        alamat.province = province;

        liveAlamat.setValue(alamat);
    }

    public void setLiveAlamat(String address, String kodePos, String catatan, boolean isDefault, String village, String district, String regency, String province) {
        Alamat alamat = new Alamat();
        alamat.alamat = address;
        alamat.kodePos = kodePos;
        alamat.catatan = catatan;
        alamat.isDefault = isDefault;
        alamat.village = village;
        alamat.district = district;
        alamat.regency = regency;
        alamat.province = province;

        liveAlamat.setValue(alamat);
    }

    public void setLiveDistrict(String query) {
        liveDistrict.setValue(query);
    }

    public void setLiveRegency(String query) {
        liveRegency.setValue(query);
    }

    public void setLiveProvince(String query) {
        liveProvince.setValue(query);
    }

    public final LiveData<Resource<UserShipment>> newShipmentAddress = Transformations.switchMap(liveAlamat, alamat ->
            mainRepository.newShipmentAddress(liveToken.getValue(), alamat.alamat, alamat.kodePos, alamat.catatan, alamat.isDefault, alamat.village, alamat.district, alamat.regency, alamat.province));

    public final LiveData<Resource<UserShipment>> viewShipmentAddress = Transformations.switchMap(liveAlamat, alamat ->
            mainRepository.viewShipmentAddress(liveToken.getValue(), alamat.id));

    public final LiveData<Resource<UserShipment>> updateShipmentAddress = Transformations.switchMap(liveAlamat, alamat ->
            mainRepository.updateShipmentAddress(liveToken.getValue(), alamat.id, alamat.alamat, alamat.kodePos, alamat.catatan, alamat.isDefault, alamat.village, alamat.district, alamat.regency, alamat.province));

    public final LiveData<Resource<List<Province>>> getProvinces = Transformations.switchMap(liveToken, token ->
            mainRepository.getProvinces(token));

    public final LiveData<Resource<List<Regency>>> getRegencies = Transformations.switchMap(liveProvince, province ->
            mainRepository.getRegencies(liveToken.getValue(), province));

    public final LiveData<Resource<List<District>>> getDistricts = Transformations.switchMap(liveRegency, regency ->
            mainRepository.getDistricts(liveToken.getValue(), regency));

    public final LiveData<Resource<List<Village>>> getVillages = Transformations.switchMap(liveDistrict, district ->
            mainRepository.getVillages(liveToken.getValue(), district));

    static class Alamat {
        int id;
        String alamat;
        String kodePos;
        String catatan;
        String village;
        String district;
        String regency;
        String province;
        boolean isDefault;
    }
}