package com.rahmacom.rimesyarifix.ui.order;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.OrderRepository;
import com.rahmacom.rimesyarifix.data.entity.Order;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class OrderViewModel extends ViewModel {

    private final SavedStateHandle savedStateHandle;
    private final MutableLiveData<StatusId> statusId = new MutableLiveData<>();
    private OrderRepository orderRepository;
    public LiveData<Resource<List<Order>>> getAllOrders = Transformations.switchMap(statusId, id -> orderRepository.getAllOrders(id.token, id.statusId));

    @Inject
    public OrderViewModel(OrderRepository orderRepository, SavedStateHandle savedStateHandle) {
        this.orderRepository = orderRepository;
        this.savedStateHandle = savedStateHandle;
    }

    public void setStatusId(String token, int statusId) {
        this.statusId.postValue(new StatusId(token, statusId));
    }

    static class StatusId {
        private final String token;
        private final int statusId;

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