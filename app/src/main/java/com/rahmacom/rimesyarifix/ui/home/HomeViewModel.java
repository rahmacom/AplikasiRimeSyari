package com.rahmacom.rimesyarifix.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.MainRepository;
import com.rahmacom.rimesyarifix.data.network.response.ResponseProduk;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    private MainRepository mainRepository;
    private SavedStateHandle savedStateHandle;

    private MutableLiveData<String> token = new MutableLiveData<>();

    @Inject
    public HomeViewModel(MainRepository mainRepository, SavedStateHandle savedStateHandle) {
        this.mainRepository = mainRepository;
        this.savedStateHandle = savedStateHandle;
    }

    public final LiveData<Resource<List<ResponseProduk>>> getAllProducts =
            Transformations.switchMap(token, getToken -> mainRepository.getAllProducts(token.getValue()));

    public void setToken(String token) {
        this.token.setValue(token);
    }
}
