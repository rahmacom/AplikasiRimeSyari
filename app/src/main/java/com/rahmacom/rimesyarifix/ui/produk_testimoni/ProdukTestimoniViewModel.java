package com.rahmacom.rimesyarifix.ui.produk_testimoni;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.MainRepository;
import com.rahmacom.rimesyarifix.data.model.Testimony;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ProdukTestimoniViewModel extends ViewModel {

    private MainRepository mainRepository;
    private MutableLiveData<String> liveToken = new MutableLiveData<>();
    private MutableLiveData<Testimoni> liveTestimoni = new MutableLiveData<>();

    @Inject
    public ProdukTestimoniViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public final LiveData<Resource<Testimony>> createTestimony = Transformations.switchMap(liveTestimoni, testimoni ->
            mainRepository.newTestimony(liveToken.getValue(), testimoni.isi, testimoni.rating, testimoni.productId));

    public void setLiveToken(String token) {
        liveToken.setValue(token);
    }

    public void setLiveTestimoni(String isi, int rating, int productId) {
        Testimoni testimoni = new Testimoni();
        testimoni.isi = isi;
        testimoni.rating = rating;
        testimoni.productId = productId;

        liveTestimoni.setValue(testimoni);
    }

    static class Testimoni {
        String isi;
        int rating;
        int productId;
    }
}