package com.liniocodingtest.ui.views;

import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liniocodingtest.R;
import com.liniocodingtest.ui.adapters.CollectionAdapter;
import com.liniocodingtest.ui.viewmodels.MainViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CollectionFragment extends Fragment {

    private CollectionAdapter collectionAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_collection, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initRecyclerView(view);
        this.initViewModel();
    }

    private void initRecyclerView(View view){
        this.collectionAdapter = new CollectionAdapter(view.getContext());
        RecyclerView recyclerView = view.findViewById(R.id.recycler_collection);
        recyclerView.setAdapter(collectionAdapter);
    }

    private void initViewModel(){
        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        mainViewModel.getCollectionLiveData().observe(getViewLifecycleOwner(), collections -> {
            collectionAdapter.setCollectionList(collections);
        });
    }
}