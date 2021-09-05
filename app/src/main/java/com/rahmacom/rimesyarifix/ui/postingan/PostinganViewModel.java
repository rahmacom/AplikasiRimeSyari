package com.rahmacom.rimesyarifix.ui.postingan;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.MainRepository;
import com.rahmacom.rimesyarifix.data.model.Post;
import com.rahmacom.rimesyarifix.data.vo.Resource;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PostinganViewModel extends ViewModel {

    private MainRepository mainRepository;
    private final MutableLiveData<String> liveToken = new MutableLiveData<>();
    private final MutableLiveData<Integer> livePostId = new MutableLiveData<>();

    @Inject
    public PostinganViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }

    public void setLiveToken(String token) {
        liveToken.setValue(token);
    }

    public void setLivePostId(int postId) {
        livePostId.setValue(postId);
    }

    public final LiveData<Resource<Post>> viewPost = Transformations.switchMap(livePostId, postId ->
            mainRepository.viewPost(liveToken.getValue(), postId));
}