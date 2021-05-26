package com.liniocodingtest.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.liniocodingtest.models.Collection;
import com.liniocodingtest.network.CollectionClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CollectionRepository {
    private final MutableLiveData<List<Collection>> collectionListLiveData;
    private final CollectionClient collectionClient;

    public CollectionRepository(CollectionClient collectionClient) {
        this.collectionClient = collectionClient;
        this.collectionListLiveData = new MutableLiveData<>();
    }

    public void getCollectionList() {
        if (this.collectionListLiveData.getValue() != null) {
            return;
        }

        this.collectionClient
            .getCollections()
            .enqueue(new Callback<List<Collection>>() {
                @Override
                public void onResponse(Call<List<Collection>> call, Response<List<Collection>> response) {
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
}
