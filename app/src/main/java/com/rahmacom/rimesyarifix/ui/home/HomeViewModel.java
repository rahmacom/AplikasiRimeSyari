package com.rahmacom.rimesyarifix.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.MainRepository;
import com.rahmacom.rimesyarifix.data.model.Post;
import com.rahmacom.rimesyarifix.data.model.Product;
import com.rahmacom.rimesyarifix.data.model.User;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> liveToken = new MutableLiveData<>();
    private MainRepository mainRepository;


    @Inject
    public HomeViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public final LiveData<Resource<List<Product>>> getAllProducts = Transformations.switchMap(liveToken, token -> mainRepository.allProducts(token));
    public final LiveData<Resource<List<Post>>> getLatestPosts = Transformations.switchMap(liveToken, token -> mainRepository.getLatestPosts(token));
    public final LiveData<Resource<User>> getUserProfile = Transformations.switchMap(liveToken, token -> mainRepository.profile(token));

    public void setLiveToken(String liveToken) {
        this.liveToken.setValue(liveToken);
    }
}
