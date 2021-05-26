package com.liniocodingtest.ui.views;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liniocodingtest.R;
import com.liniocodingtest.ui.adapters.ProductAdapter;
import com.liniocodingtest.ui.viewmodels.MainViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FavoriteFragment extends Fragment {

    private ProductAdapter productAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.initRecyclerView(view);
        this.initViewModel(view);
    }


    private void initRecyclerView(View view) {
        this.productAdapter = new ProductAdapter(view.getContext());
        RecyclerView recyclerView = view.findViewById(R.id.recycler_favorite);
        recyclerView.setAdapter(productAdapter);
    }

    private void initViewModel(View view) {
        MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        mainViewModel.getProductListLiveData().observe(getViewLifecycleOwner(), products -> {
            this.productAdapter.setProductList(products);
            String formatted = getString(R.string.all_my_favorites, Integer.toString(products.size()));
            TextView textView = view.findViewById(R.id.text_total);
            textView.setText(formatted);
        });
    }
}