package com.liniocodingtest.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.liniocodingtest.models.Collection;
import com.liniocodingtest.models.Product;
import com.liniocodingtest.repository.CollectionRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class MainViewModel extends ViewModel {

    private final CollectionRepository collectionRepository;
    private final LiveData<List<Collection>> collectionLiveData;
    private final LiveData<List<Product>> productListLiveData;

    @Inject
    public MainViewModel(CollectionRepository collectionRepository) {
        this.collectionRepository = collectionRepository;
        this.collectionLiveData = collectionRepository.getCollectionListLiveData();
        this.productListLiveData = collectionRepository.getProductListLiveData();
    }

    public void getCollections() {
        collectionRepository.getCollectionList();
    }

    public LiveData<List<Collection>> getCollectionLiveData() {
        return this.collectionLiveData;
    }

    public LiveData<List<Product>> getProductListLiveData() {
        return this.productListLiveData;
    }


}
