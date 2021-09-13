package com.rahmacom.rimesyarifix.ui.reseller_info;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.MainRepository;
import com.rahmacom.rimesyarifix.data.model.User;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ResellerInfoViewModel extends ViewModel {

    private MainRepository mainRepository;
    private MutableLiveData<String> liveToken = new MutableLiveData<>();
    private MutableLiveData<User> liveUser = new MutableLiveData<>();

    @Inject
    public ResellerInfoViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public void setLiveToken(String token) {
        liveToken.setValue(token);
    }

    public void setLiveUser(User user) {
        liveUser.setValue(user);
    }

    public void setLiveUser(String nik, String namaLengkap, char jk, String tempatLahir, String tglLahir, String alamat, String noHP, String noWA) {
        User user = new User();
        user.setNik(nik);
        user.setNamaLengkap(namaLengkap);
        user.setJk(jk);
        user.setTempatLahir(tempatLahir);
        user.setTglLahir(tglLahir);
        user.setAlamat(alamat);
        user.setNoHp(noHP);
        user.setNoWa(noWA);
        liveUser.setValue(user);
    }
}