package com.rahmacom.rimesyarifix.ui.keranjang_detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.CartRepository;
import com.rahmacom.rimesyarifix.data.entity.Cart;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class KeranjangDetailViewModel extends ViewModel {

    private final MutableLiveData<CartId> liveCartid = new MutableLiveData<>();
    private final SavedStateHandle savedStateHandle;

    private CartRepository cartRepository;

    public LiveData<Resource<Cart>> viewCart = Transformations.switchMap(liveCartid, cart -> cartRepository.viewCart(cart.token, cart.cartId));

    @Inject
    public KeranjangDetailViewModel(CartRepository cartRepository, SavedStateHandle savedStateHandle) {
        this.cartRepository = cartRepository;
        this.savedStateHandle = savedStateHandle;
    }

    public void setCartId(String token, int cartId) {
        liveCartid.setValue(new CartId(cartId, token));
    }

    static class CartId {
        private final int cartId;
        private final String token;

        public CartId(int cartId, String token) {
            this.cartId = cartId;
            this.token = token;
        }
    }
}
