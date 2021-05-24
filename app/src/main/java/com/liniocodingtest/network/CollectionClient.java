package com.liniocodingtest.network;

import com.liniocodingtest.models.Collection;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class CollectionClient {

    public CollectionService collectionService;

    @Inject
    public CollectionClient(CollectionService collectionService){
        this.collectionService = collectionService;
    }

    public Observable<List<Collection>> getCollections(){
        return collectionService.getCollections();
    }
}
