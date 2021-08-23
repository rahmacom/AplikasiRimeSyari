package com.rahmacom.rimesyarifix.ui.keranjang;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.CartRepository;
import com.rahmacom.rimesyarifix.data.entity.Cart;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class KeranjangViewModel extends ViewModel {

    private CartRepository cartRepository;
    private final SavedStateHandle savedStateHandle;

    private MutableLiveData<String> liveToken = new MutableLiveData<>();

    @Inject
    public KeranjangViewModel(CartRepository cartRepository, SavedStateHandle savedStateHandle) {
        this.cartRepository = cartRepository;
        this.savedStateHandle = savedStateHandle;
    }

    public LiveData<Resource<List<Cart>>> getAllCarts = Transformations.switchMap(liveToken,
            token -> cartRepository.getAllCarts(token));

    public void setLiveToken(String token) {
        liveToken.setValue(token);
    }
}