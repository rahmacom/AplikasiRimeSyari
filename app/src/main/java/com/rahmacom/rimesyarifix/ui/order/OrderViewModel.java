package com.rahmacom.rimesyarifix.ui.order;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.MainRepository;
import com.rahmacom.rimesyarifix.data.model.Order;
import com.rahmacom.rimesyarifix.data.model.UserShipment;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class OrderViewModel extends ViewModel {

    private final MutableLiveData<LiveOrder> liveOrder = new MutableLiveData<>();
    private final MutableLiveData<String> liveToken = new MutableLiveData<>();
    private MainRepository mainRepository;

    public LiveData<Resource<List<Order>>> getAllOrders = Transformations.switchMap(liveOrder, order ->
            mainRepository.allOrders(liveToken.getValue(), order.statusId));
    public LiveData<Resource<List<UserShipment>>> getUserShipmentAddresses = Transformations.switchMap(liveToken, token ->
            mainRepository.shipmentAddresses(token));

    @Inject
    public OrderViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public void setLiveToken(String token) {
        liveToken.setValue(token);
    }

    public void setLiveOrder(int statusId) {
        LiveOrder liveOrder = new LiveOrder();
        liveOrder.statusId = statusId;

        this.liveOrder.setValue(liveOrder);
    }

    public void setLiveOrder(
            String pesan,
            int userShipmentId,
            int paymentMethodId,
            List<Integer> productIds,
            List<Integer> colorIds,
            List<Integer> sizeIds,
            List<Integer> quantities
    ) {
        LiveOrder liveOrder = new LiveOrder();
        liveOrder.pesan = pesan;
        liveOrder.userShipmentId = userShipmentId;
        liveOrder.paymentMethodId = paymentMethodId;
        liveOrder.productIds.addAll(productIds);
        liveOrder.colorIds.addAll(colorIds);
        liveOrder.sizeIds.addAll(sizeIds);
        liveOrder.quantities.addAll(quantities);

        this.liveOrder.setValue(liveOrder);
    }

    static class LiveOrder {
        private int id;
        private String pesan;
        private String kodeDiskon;
        private int statusId;
        private int userShipmentId;
        private int paymentMethodId;

        private List<Integer> productIds;
        private List<Integer> colorIds;
        private List<Integer> sizeIds;
        private List<Integer> quantities;
    }
}