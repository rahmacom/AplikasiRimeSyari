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

    private final SavedStateHandle savedStateHandle;
    private final MutableLiveData<Keranjang> liveKeranjang = new MutableLiveData<>();
    private CartRepository cartRepository;

    @Inject
    public KeranjangViewModel(CartRepository cartRepository, SavedStateHandle savedStateHandle) {
        this.cartRepository = cartRepository;
        this.savedStateHandle = savedStateHandle;
    }

    public final LiveData<Resource<List<Cart>>> getAllCarts =
            Transformations.switchMap(liveKeranjang,
                    keranjang -> cartRepository.getAllCarts(keranjang.token));

    public final LiveData<Resource<Cart>> newCart =
            Transformations.switchMap(liveKeranjang,
                    keranjang -> cartRepository.createNewCart(
                            keranjang.token,
                            keranjang.productId,
                            keranjang.colorId,
                            keranjang.sizeId));

    public void setLiveToken(String token) {
        Keranjang keranjang = new Keranjang();
        keranjang.token = token;
        liveKeranjang.setValue(keranjang);
    }

    public void setKeranjang(String token, int productId, int colorId, int sizeId) {
        Keranjang keranjang = new Keranjang(token, productId, colorId, sizeId);
        liveKeranjang.setValue(keranjang);
    }

    static class Keranjang {
        String token;
        int productId;
        int colorId;
        int sizeId;

        public Keranjang(String token, int productId, int colorId, int sizeId) {
            this.token = token;
            this.productId = productId;
            this.colorId = colorId;
            this.sizeId = sizeId;
        }

        public Keranjang() { }
    }
}