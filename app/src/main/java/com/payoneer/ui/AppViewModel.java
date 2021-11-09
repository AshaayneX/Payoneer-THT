package com.payoneer.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.payoneer.data.AppRepository;
import com.payoneer.model.ApplicableNetwork;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import lombok.Getter;
/**
 * ViewModel class, contains data required by the entire UI
 */
@HiltViewModel
public class AppViewModel extends ViewModel {
    /** repository instance to query for data */
    private final AppRepository repository;

    /** share LiveData object */
    @Getter
    private MutableLiveData<List<ApplicableNetwork>> paymentOptionLiveData ;

    /** init Repo and grab LiveData Object */
    @Inject
    AppViewModel(AppRepository repository) {
        this.repository = repository;
        paymentOptionLiveData = repository.getPaymentOptionLiveData();
    }
    /** trigger Api call */
    public void loadPaymentOptions(){
        repository.callPaymentApi();
    }



}

