package com.liniocodingtest.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.liniocodingtest.R;
import com.liniocodingtest.adapters.ProductAdapter;
import com.liniocodingtest.viewmodels.MainViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {

    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.initRecyclerView();
        this.initViewModel();
    }

    private void initRecyclerView(){
        this.productAdapter = new ProductAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(productAdapter);
    }

    private void initViewModel(){
        MainViewModel mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getCollections();
        mainViewModel.getProductListLiveData().observe(this, products -> {
            this.productAdapter.setProductList(products);
            String formatted = getString(R.string.all_my_favorites, Integer.toString(products.size()));
            TextView textView = findViewById(R.id.total_text);
            textView.setText(formatted);
        });
    }
}