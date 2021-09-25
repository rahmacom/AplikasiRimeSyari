package com.rahmacom.rimesyarifix.ui.order_detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.MainRepository;
import com.rahmacom.rimesyarifix.data.model.Image;
import com.rahmacom.rimesyarifix.data.model.Order;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import java.io.File;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class OrderDetailViewModel extends ViewModel {

    private final MutableLiveData<String> liveToken = new MutableLiveData<>();
    private final MutableLiveData<Integer> liveOrderId = new MutableLiveData<>();
    private final MutableLiveData<File> liveFile = new MutableLiveData<>();
    private MainRepository mainRepository;
    public LiveData<Resource<Order>> viewOrder = Transformations.switchMap(liveOrderId, order ->
            mainRepository.viewOrder(liveToken.getValue(), order));

    @Inject
    public OrderDetailViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public void setLiveToken(String token) {
        liveToken.setValue(token);
    }

    public void setLiveOrderId(int orderId) {
        liveOrderId.setValue(orderId);
    }

    public void setLiveFile(File file) {
        liveFile.setValue(file);
    }

    public LiveData<Resource<Boolean>> payOrder = Transformations.switchMap(liveFile, file ->
            mainRepository.payOrder(liveToken.getValue(), liveOrderId.getValue(), file));
}