package com.rahmacom.rimesyarifix.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.ProductRepository;
import com.rahmacom.rimesyarifix.data.entity.Product;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    private ProductRepository productRepository;
    private SavedStateHandle savedStateHandle;

    private MutableLiveData<String> token = new MutableLiveData<>();

    @Inject
    public HomeViewModel(ProductRepository productRepository, SavedStateHandle savedStateHandle) {
        this.productRepository = productRepository;
        this.savedStateHandle = savedStateHandle;
    }

    public final LiveData<Resource<List<Product>>> getAllProducts =
            Transformations.switchMap(token, getToken -> productRepository.getAllProducts(token.getValue()));

    public void setToken(String token) {
        this.token.setValue(token);
    }
}
