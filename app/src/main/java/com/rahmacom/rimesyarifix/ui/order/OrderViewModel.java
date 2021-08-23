package com.rahmacom.rimesyarifix.ui.order;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.OrderRepository;
import com.rahmacom.rimesyarifix.data.entity.Order;
import com.rahmacom.rimesyarifix.data.entity.Product;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class OrderViewModel extends ViewModel {

    private OrderRepository orderRepository;
    private final SavedStateHandle savedStateHandle;

    private MutableLiveData<StatusId> statusId = new MutableLiveData<>();

    @Inject
    public OrderViewModel(OrderRepository orderRepository, SavedStateHandle savedStateHandle) {
        this.orderRepository = orderRepository;
        this.savedStateHandle = savedStateHandle;
    }

    public void setStatusId(String token, int statusId) {
        this.statusId.postValue(new StatusId(token, statusId));
    }

    public LiveData<Resource<List<Order>>> getAllOrders = Transformations.switchMap(statusId,
            id -> orderRepository.getAllOrders(id.token, id.statusId));

    static class StatusId {
        private String token;
        private int statusId;

        public StatusId(String token, int statusId) {
            this.token = token;
            this.statusId = statusId;
        }

        public String getToken() {
            return token;
        }

        public int getStatusId() {
            return statusId;
        }
    }
}