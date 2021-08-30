package com.rahmacom.rimesyarifix.ui.order_detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.OrderRepository;
import com.rahmacom.rimesyarifix.data.entity.Order;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class OrderDetailViewModel extends ViewModel {

    private final SavedStateHandle savedStateHandle;
    private final MutableLiveData<OrderId> liveOrderId = new MutableLiveData<>();
    private OrderRepository orderRepository;
    public LiveData<Resource<Order>> viewOrder = Transformations.switchMap(liveOrderId, order -> orderRepository.viewOrder(order.token, order.orderId));

    @Inject
    public OrderDetailViewModel(OrderRepository orderRepository, SavedStateHandle savedStateHandle) {
        this.orderRepository = orderRepository;
        this.savedStateHandle = savedStateHandle;
    }

    public void setLiveOrderId(String token, int id) {
        OrderId orderId = new OrderId(id, token);
        liveOrderId.setValue(orderId);
    }

    static class OrderId {
        private final int orderId;
        private final String token;

        public OrderId(int orderId, String token) {
            this.orderId = orderId;
            this.token = token;
        }
    }
}