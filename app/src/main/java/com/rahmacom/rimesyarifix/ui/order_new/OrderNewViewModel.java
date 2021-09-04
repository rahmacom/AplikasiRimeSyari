package com.rahmacom.rimesyarifix.ui.order_new;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.MainRepository;
import com.rahmacom.rimesyarifix.data.model.Cart;
import com.rahmacom.rimesyarifix.data.model.PaymentMethod;
import com.rahmacom.rimesyarifix.data.model.UserShipment;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class OrderNewViewModel extends ViewModel {

    private MainRepository mainRepository;

    private MutableLiveData<String> liveToken = new MutableLiveData<>();
    private MutableLiveData<Integer> liveCartId = new MutableLiveData<>();
    private MutableLiveData<Integer> liveUserShipmentId = new MutableLiveData<>();
    private MutableLiveData<Order> liveOrder = new MutableLiveData<>();

    @Inject
    public OrderNewViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public LiveData<Resource<Cart>> viewCart = Transformations.switchMap(liveCartId,
            cart -> mainRepository.viewCart(liveToken.getValue(), cart));

    public LiveData<Resource<com.rahmacom.rimesyarifix.data.model.Order>> newOrder = Transformations.switchMap(liveOrder,
            order -> mainRepository.newOrder(liveToken.getValue(), order.pesan, order.kodeDiskon, order.userShipmentId, order.paymentMethodId, order.productIds, order.colorIds, order.sizeIds, order.quantities));

    public LiveData<Resource<List<PaymentMethod>>> getAvailablePaymentMethod = Transformations.switchMap(liveToken,
            paymentMethod -> mainRepository.getAvailablePaymentMethods(paymentMethod));

    public LiveData<Resource<UserShipment>> getShipmentAddressDetail = Transformations.switchMap(liveUserShipmentId,
            userShipmentId -> mainRepository.viewShipmentAddress(liveToken.getValue(), userShipmentId));

    public void setLiveToken(String token) {
        liveToken.setValue(token);
    }

    public void setLiveCartId(int cartId) {
        liveCartId.setValue(cartId);
    }

    public void setLiveUserShipmentId(int userShipmentId) {
        liveUserShipmentId.setValue(userShipmentId);
    }

    public void setLiveOrder(String pesan, String kodeDiskon, int userShipmentId, int paymentMethodId, List<Integer> productIds, List<Integer> colorIds, List<Integer> sizeIds, List<Integer> quantities) {
        Order order = new Order();
        order.pesan = pesan;
        order.kodeDiskon = kodeDiskon;
        order.userShipmentId = userShipmentId;
        order.paymentMethodId = paymentMethodId;
        order.productIds = productIds;
        order.colorIds = colorIds;
        order.sizeIds = sizeIds;
        order.quantities = quantities;
        liveOrder.setValue(order);
    }

    static class Order {
        private String pesan;
        private String kodeDiskon;
        private int userShipmentId;
        private int paymentMethodId;
        private List<Integer> productIds;
        private List<Integer> colorIds;
        private List<Integer> sizeIds;
        private List<Integer> quantities;
    }
}
