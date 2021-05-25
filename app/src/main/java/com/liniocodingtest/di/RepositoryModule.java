package com.liniocodingtest.di;

import com.liniocodingtest.network.CollectionClient;
import com.liniocodingtest.repository.CollectionRepository;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;
import dagger.hilt.android.scopes.ViewModelScoped;

@Module
@InstallIn(ViewModelComponent.class)
public class RepositoryModule {

    @Provides
    @ViewModelScoped
    public CollectionRepository provideCollectionRepository(CollectionClient collectionClient){
        return new CollectionRepository(collectionClient);
    }
}
