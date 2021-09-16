package com.rahmacom.rimesyarifix.ui.reseller_kyc_preview;

import androidx.lifecycle.ViewModel;

import com.rahmacom.rimesyarifix.data.MainRepository;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class ResellerKYCPreviewViewModel extends ViewModel {

    private MainRepository mainRepository;

    @Inject
    public ResellerKYCPreviewViewModel(MainRepository mainRepository) {
        this.mainRepository = mainRepository;
    }
}