package com.rahmacom.rimesyarifix.ui.form_profil_biodata;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.MainRepository;
import com.rahmacom.rimesyarifix.data.model.User;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FormProfilBiodataViewModel extends ViewModel {

    private MainRepository mainRepository;
    private MutableLiveData<String> liveToken = new MutableLiveData<>();
    private MutableLiveData<User> liveUser = new MutableLiveData<>();

    @Inject
    public FormProfilBiodataViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public final LiveData<Resource<User>> updateProfile = Transformations.switchMap(liveToken, token ->
            mainRepository.updateProfile(token, liveUser.getValue()));

    public void setLiveToken(String token) {
        liveToken.setValue(token);
    }

    public void setLiveUser(String namaLengkap, String tempatLahir, String tglLahir, String noHP, String noWA, String alamat) {
        User user = new User();
        user.setNamaLengkap(namaLengkap);
        user.setTempatLahir(tempatLahir);
        user.setTglLahir(tglLahir);
        user.setNoHp(noHP);
        user.setNoWa(noWA);
        user.setAlamat(alamat);

        liveUser.setValue(user);
    }
}