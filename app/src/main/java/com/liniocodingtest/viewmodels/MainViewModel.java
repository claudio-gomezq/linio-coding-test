package com.liniocodingtest.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.liniocodingtest.models.BadgeEnum;
import com.liniocodingtest.models.Collection;
import com.liniocodingtest.models.Product;
import com.liniocodingtest.repository.CollectionRepository;

import java.util.ArrayList;
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
        this.productListLiveData = this.mapCollectionToProducts();
    }

    public void getCollections() {
        collectionRepository.getCollectionList();
    }

    private LiveData<List<Product>> mapCollectionToProducts() {
        return Transformations.map(this.collectionLiveData, collections -> {
            ArrayList<Product> productsList = new ArrayList<>();
            for (Collection item : collections) {
                productsList.addAll(item.getProducts());
            }

            for (Product product : productsList) {
                List<BadgeEnum> badgeList = this.getBadgesIconList(product);
                product.setBadgeList(badgeList);
            }
            return productsList;
        });
    }

    private List<BadgeEnum> getBadgesIconList(Product product) {
        ArrayList<BadgeEnum> badgesList = new ArrayList<>();
        if (product.getConditionType().equals("refurbished")) {
            badgesList.add(BadgeEnum.REFURBISHED);
        } else if (product.getConditionType().equals("new")) {
            badgesList.add(BadgeEnum.NEW);
        }

        if (product.getLinioPlusLevel() == 1) {
            badgesList.add(BadgeEnum.PLUS_LEVEL_1);
        } else if (product.getLinioPlusLevel() == 2) {
            badgesList.add(BadgeEnum.PLUS_LEVEL_2);
        }

        if (product.isImported()) {
            badgesList.add(BadgeEnum.IMPORTED);
        }

        if (product.isFreeShipping()) {
            badgesList.add(BadgeEnum.FREE_SHIPPING);
        }

        return badgesList;
    }

    public LiveData<List<Collection>> getCollectionLiveData() {
        return this.collectionLiveData;
    }

    public LiveData<List<Product>> getProductListLiveData() {
        return this.productListLiveData;
    }
}
