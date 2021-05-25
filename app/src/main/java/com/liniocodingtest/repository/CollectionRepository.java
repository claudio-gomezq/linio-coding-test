package com.liniocodingtest.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.liniocodingtest.models.Collection;
import com.liniocodingtest.models.Product;
import com.liniocodingtest.network.CollectionClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollectionRepository {
    private final MutableLiveData<List<Collection>> collectionListLiveData;
    private final MutableLiveData<List<Product>> productListLiveData;
    private final CollectionClient collectionClient;

    public CollectionRepository(CollectionClient collectionClient) {
        this.collectionClient = collectionClient;
        this.collectionListLiveData = new MutableLiveData<>();
        this.productListLiveData = new MutableLiveData<>();
    }

    public void getCollectionList() {
        this.collectionClient
            .getCollections()
            .enqueue(new Callback<List<Collection>>() {
                @Override
                public void onResponse(Call<List<Collection>> call, Response<List<Collection>> response) {

                    if(response.body() != null){
                        ArrayList<Product> productsList = new ArrayList<>();
                        for(Collection collection : response.body()){
                            productsList.addAll(collection.getProducts());
                        }
                        productListLiveData.postValue(productsList);
                    }

                    collectionListLiveData.postValue(response.body());
                }

                @Override
                public void onFailure(Call<List<Collection>> call, Throwable t) {
                    t.printStackTrace();
                    collectionListLiveData.postValue(null);
                }
            });
    }

    public LiveData<List<Collection>> getCollectionListLiveData(){
        return this.collectionListLiveData;
    }

    public LiveData<List<Product>> getProductListLiveData(){
        return this.productListLiveData;
    }
}
