package com.rahmacom.rimesyarifix.ui.produk;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.MainRepository;
import com.rahmacom.rimesyarifix.data.model.Color;
import com.rahmacom.rimesyarifix.data.model.Product;
import com.rahmacom.rimesyarifix.data.model.Size;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import timber.log.Timber;

@HiltViewModel
public class ProdukViewModel extends ViewModel {

    private final MutableLiveData<String> liveToken = new MutableLiveData<>();
    private final MutableLiveData<Integer> liveProductId = new MutableLiveData<>();
    private final MutableLiveData<Integer> liveColorId = new MutableLiveData<>();
    private MainRepository mainRepository;

    public final LiveData<Resource<Product>> viewProduct =
            Transformations.switchMap(liveProductId, product -> mainRepository.viewProduct(liveToken.getValue(), product));

    public final LiveData<Resource<List<Color>>> getProductColors =
            Transformations.switchMap(liveProductId, product -> mainRepository.productColors(liveToken.getValue(), product));

    public final LiveData<Resource<List<Size>>> getProductSizes =
            Transformations.switchMap(liveColorId, product -> mainRepository.productSizes(liveToken.getValue(), liveProductId.getValue(), product));

    public final LiveData<Resource<Integer>> likeProduct = Transformations.switchMap(liveProductId, productId ->
            mainRepository.likeProduct(liveToken.getValue(), productId));

    public final LiveData<Resource<Integer>> dislikeProduct = Transformations.switchMap(liveProductId, productId ->
            mainRepository.dislikeProduct(liveToken.getValue(), productId));

    @Inject
    public ProdukViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public void setLiveToken(String token) {
        liveToken.setValue(token);
    }

    public void setLiveProductId(int productId) {
        liveProductId.setValue(productId);
    }

    public void setLiveColorId(int colorId) {
        liveColorId.setValue(colorId);
        Timber.d("color_id: " + liveColorId.getValue());
    }
}