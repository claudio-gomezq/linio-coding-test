package com.liniocodingtest.network;

import com.liniocodingtest.models.Collection;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;

public class CollectionClient {

    public CollectionService collectionService;

    @Inject
    public CollectionClient(CollectionService collectionService){
        this.collectionService = collectionService;
    }

    public Call<List<Collection>> getCollections(){
        return collectionService.getCollections();
    }
}
