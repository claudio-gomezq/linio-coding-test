package com.liniocodingtest.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.liniocodingtest.R;
import com.liniocodingtest.models.Collection;
import com.liniocodingtest.network.CollectionClient;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    @Inject
    public CollectionClient collectionClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        collectionClient.getCollections().subscribe((List<Collection> collections) -> {
            System.out.println(collections);
        });
    }
}