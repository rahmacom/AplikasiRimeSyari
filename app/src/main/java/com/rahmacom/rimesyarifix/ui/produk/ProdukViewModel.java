package com.rahmacom.rimesyarifix.ui.produk;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.ProductRepository;
import com.rahmacom.rimesyarifix.data.entity.Product;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProdukViewModel extends ViewModel {

    private ProductRepository productRepository;
    private final SavedStateHandle savedStateHandle;

    private final MutableLiveData<ProductId> productId = new MutableLiveData<>();

    @Inject
    public ProdukViewModel(ProductRepository productRepository, SavedStateHandle savedStateHandle) {
        this.productRepository = productRepository;
        this.savedStateHandle = savedStateHandle;
    }

    public final LiveData<Resource<Product>> viewProduct = Transformations.switchMap(productId,
            product -> productRepository.viewProduct(product.token, product.id));

    public void setProductId(String token, int id) {
        productId.setValue(new ProductId(token, id));
    }

    static class ProductId {
        private final String token;
        private final int id;

        public ProductId(String token, int id) {
            this.token = token;
            this.id = id;
        }
    }
}