package com.rahmacom.rimesyarifix.ui.keranjang_detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.MainRepository;
import com.rahmacom.rimesyarifix.data.model.Cart;
import com.rahmacom.rimesyarifix.data.model.Product;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class KeranjangDetailViewModel extends ViewModel {

    private final MutableLiveData<String> liveToken = new MutableLiveData<>();
    private final MutableLiveData<Keranjang> liveKeranjang = new MutableLiveData<>();

    private MainRepository mainRepository;

    public LiveData<Resource<Product>> getProduct = Transformations.switchMap(liveKeranjang, cart ->
            mainRepository.newCartViewProduct(liveToken.getValue(), cart.productId, cart.colorId, cart.sizeId));

    public LiveData<Resource<Cart>> viewCart = Transformations.switchMap(liveKeranjang, cart ->
            mainRepository.viewCart(liveToken.getValue(), cart.id));

    public LiveData<Resource<Cart>> newCart = Transformations.switchMap(liveKeranjang, cart ->
            mainRepository.newCart(liveToken.getValue(), cart.judul, cart.deskripsi, cart.productId, cart.colorId, cart.sizeId, cart.jumlah));

    public LiveData<Resource<Cart>> updateCart = Transformations.switchMap(liveKeranjang, cart ->
            mainRepository.updateCart(liveToken.getValue(), cart.id, cart.judul, cart.deskripsi));

    public LiveData<Resource<Cart>> removeCart = Transformations.switchMap(liveKeranjang, cart ->
            mainRepository.deleteCart(liveToken.getValue(), cart.id));

    public LiveData<Resource<Cart>> updateCartWithDetails = Transformations.switchMap(liveKeranjang, cart ->
            mainRepository.updateCartWithDetails(liveToken.getValue(), cart.id, cart.judul, cart.deskripsi, cart.productIds, cart.colorIds, cart.sizeIds, cart.quantities));

    public LiveData<Resource<Cart>> updateProductsInCart = Transformations.switchMap(liveKeranjang, cart -> {
        return mainRepository.updateProductsInCart(liveToken.getValue(), cart.id, cart.productIds, cart.colorIds, cart.sizeIds, cart.quantities);
    });

    public LiveData<Resource<Cart>> removeProductsFromCart = Transformations.switchMap(liveKeranjang, cart ->
            mainRepository.removeProductFromCart(liveToken.getValue(), cart.id, cart.productId));

    @Inject
    public KeranjangDetailViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public void setLiveToken(String token) {
        liveToken.setValue(token);
    }

    public void setLiveKeranjang(int id) {
        Keranjang keranjang = new Keranjang();
        keranjang.id = id;
        liveKeranjang.setValue(keranjang);
    }

    public void setLiveKeranjang(int productId, int colorId, int sizeId) {
        Keranjang keranjang = new Keranjang();
        keranjang.productId = productId;
        keranjang.colorId = colorId;
        keranjang.sizeId = sizeId;
        liveKeranjang.setValue(keranjang);
    }

    public void setLiveKeranjang(int id, String judul, String deskripsi) {
        Keranjang keranjang = new Keranjang();
        keranjang.id = id;
        keranjang.judul = judul;
        keranjang.deskripsi = deskripsi;
        liveKeranjang.setValue(keranjang);
    }

    public void setLiveKeranjang(int id, List<Integer> productIds, List<Integer> colorIds, List<Integer> sizeIds, List<Integer> quantities) {
        Keranjang keranjang = new Keranjang();
        keranjang.id = id;
        keranjang.productIds = productIds;
        keranjang.colorIds = colorIds;
        keranjang.sizeIds = sizeIds;
        keranjang.quantities = quantities;
        liveKeranjang.setValue(keranjang);
    }

    public void setLiveKeranjang(int id, String judul, String deskripsi, List<Integer> productIds, List<Integer> colorIds, List<Integer> sizeIds, List<Integer> quantities) {
        Keranjang keranjang = new Keranjang();
        keranjang.id = id;
        keranjang.judul = judul;
        keranjang.deskripsi = deskripsi;
        keranjang.productIds = productIds;
        keranjang.colorIds = colorIds;
        keranjang.sizeIds = sizeIds;
        keranjang.quantities = quantities;
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
        private int id;
        private int productId;
        private int colorId;
        private int sizeId;
        private int jumlah;

        private List<Integer> productIds;
        private List<Integer> colorIds;
        private List<Integer> sizeIds;
        private List<Integer> quantities;

        private String judul;
        private String deskripsi;
    }
}
