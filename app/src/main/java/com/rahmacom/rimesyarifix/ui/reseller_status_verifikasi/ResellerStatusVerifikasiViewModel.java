package com.rahmacom.rimesyarifix.ui.reseller_status_verifikasi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.MainRepository;
import com.rahmacom.rimesyarifix.data.model.UserVerification;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import java.io.File;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ResellerStatusVerifikasiViewModel extends ViewModel {

    private MainRepository mainRepository;
    private MutableLiveData<String> liveToken = new MutableLiveData<>();
    private MutableLiveData<Photo> livePhoto = new MutableLiveData<>();

    @Inject
    public ResellerStatusVerifikasiViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public void setLiveToken(String token) {
        liveToken.setValue(token);
    }

    public void setLivePhoto(File file, int type) {
        Photo photo = new Photo();
        photo.image = file;
        photo.imageType = type;
        livePhoto.setValue(photo);
    }

    public final LiveData<Resource<UserVerification>> verificationStatus = Transformations.switchMap(liveToken, token ->
            mainRepository.verificationStatus(token));

    public final LiveData<Resource<UserVerification>> uploadImage = Transformations.switchMap(livePhoto, photo ->
            mainRepository.uploadImage(liveToken.getValue(), photo.image, photo.imageType));

    public final LiveData<Resource<Boolean>> checkIfUserIsEligible = Transformations.switchMap(liveToken, token ->
            mainRepository.checkIfUserIsElligible(token));

    static class Photo {
        File image;
        int imageType;
    }
}