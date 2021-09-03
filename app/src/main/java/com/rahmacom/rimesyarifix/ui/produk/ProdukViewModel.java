package com.rahmacom.rimesyarifix.ui.produk;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
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

@HiltViewModel
public class ProdukViewModel extends ViewModel {

    private final MutableLiveData<String> liveToken = new MutableLiveData<>();
    private final MutableLiveData<Integer> liveProductId = new MutableLiveData<>();
    private MainRepository mainRepository;

    public final LiveData<Resource<Product>> viewProduct =
            Transformations.switchMap(liveProductId, product -> mainRepository.viewProduct(liveToken.getValue(), product));

    public final LiveData<Resource<List<Color>>> getProductColors =
            Transformations.switchMap(liveProductId, product -> mainRepository.getProductColors(liveToken.getValue(), product));

    public final LiveData<Resource<List<Size>>> getProductSizes =
            Transformations.switchMap(liveProductId, product -> mainRepository.getProductSizes(liveToken.getValue(), product));

    @Inject
    public ProdukViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public void setLiveToken(String token) {
        liveToken.setValue(token);
    }

    public void setProductId(int productId) {
        liveProductId.setValue(productId);
    }
}