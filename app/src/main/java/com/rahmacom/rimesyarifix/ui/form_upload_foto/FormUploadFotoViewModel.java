package com.rahmacom.rimesyarifix.ui.form_upload_foto;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.MainRepository;
import com.rahmacom.rimesyarifix.data.model.Image;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import java.io.File;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class FormUploadFotoViewModel extends ViewModel {

    private MainRepository mainRepository;
    private MutableLiveData<String> liveToken = new MutableLiveData<>();
    private MutableLiveData<File> liveFile = new MutableLiveData<>();

    @Inject
    public FormUploadFotoViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public void setLiveToken(String token) {
        liveToken.setValue(token);
    }

    public void setLiveFile(File file) {
        liveFile.setValue(file);
    }

    public final LiveData<Resource<Image>> uploadImage = Transformations.switchMap(liveFile, file ->
            mainRepository.uploadProfilePhoto(liveToken.getValue(), file));
}