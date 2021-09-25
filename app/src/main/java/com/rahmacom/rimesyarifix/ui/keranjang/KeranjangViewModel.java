package com.rahmacom.rimesyarifix.ui.keranjang;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.MainRepository;
import com.rahmacom.rimesyarifix.data.model.Cart;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class KeranjangViewModel extends ViewModel {

    private final MutableLiveData<Keranjang> liveKeranjang = new MutableLiveData<>();
    private final MutableLiveData<String> liveToken = new MutableLiveData<>();
    private MainRepository mainRepository;

    public final LiveData<Resource<List<Cart>>> getAllCarts = Transformations.switchMap(liveToken, keranjang ->
            mainRepository.allCarts(liveToken.getValue()));

    public final LiveData<Resource<Cart>> newCart =
            Transformations.switchMap(liveKeranjang, keranjang ->
                    mainRepository.newCart(
                            liveToken.getValue(),
                            keranjang.judul,
                            keranjang.deskripsi,
                            keranjang.productId,
                            keranjang.colorId,
                            keranjang.sizeId,
                            keranjang.jumlah));

    public LiveData<Resource<Cart>> addProductToCart = Transformations.switchMap(liveKeranjang, cart ->
            mainRepository.addProductToCart(liveToken.getValue(), cart.id, cart.productId, cart.colorId, cart.sizeId, cart.jumlah));

    @Inject
    public KeranjangViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public void setLiveToken(String token) {
        liveToken.setValue(token);
    }

    public void setLiveKeranjang(int productId, int colorId, int sizeId, int jumlah) {
        Keranjang keranjang = new Keranjang();

        keranjang.productId = productId;
        keranjang.colorId = colorId;
        keranjang.sizeId = sizeId;
        keranjang.jumlah = jumlah;

        liveKeranjang.setValue(keranjang);
    }

    public void setLiveKeranjang(int id, int productId, int colorId, int sizeId, int jumlah) {
        Keranjang keranjang = new Keranjang();

        keranjang.id = id;
        keranjang.productId = productId;
        keranjang.colorId = colorId;
        keranjang.sizeId = sizeId;
        keranjang.jumlah = jumlah;

        liveKeranjang.setValue(keranjang);
    }

    public void setLiveKeranjang(String judul, String deskripsi, int productId, int colorId, int sizeId, int jumlah) {
        Keranjang keranjang = new Keranjang();

        keranjang.judul = judul;
        keranjang.deskripsi = deskripsi;
        keranjang.productId = productId;
        keranjang.colorId = colorId;
        keranjang.sizeId = sizeId;
        keranjang.jumlah = jumlah;

        liveKeranjang.setValue(keranjang);
    }

    static class Keranjang {
        private String judul;
        private String deskripsi;

        private int id;
        private int productId;
        private int colorId;
        private int sizeId;
        private int jumlah;
    }
}