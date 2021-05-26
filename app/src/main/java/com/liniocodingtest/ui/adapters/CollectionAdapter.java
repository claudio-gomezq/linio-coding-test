package com.liniocodingtest.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.liniocodingtest.R;
import com.liniocodingtest.models.Collection;
import com.liniocodingtest.models.Product;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.CollectionViewHolder> {

    private List<Collection> collectionList = new ArrayList<>();
    private final Context context;

    public CollectionAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CollectionViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_collection, parent, false);
        return new CollectionViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CollectionViewHolder holder, int position) {
        Collection collection = this.collectionList.get(position);
        holder.collectionName.setText(collection.getName());
        String size = Integer.toString(collection.getProducts().size());
        holder.totalCollection.setText(size);

        int index = 0;
        for (ImageView imageView : holder.imageViewList) {
            Product product;
            try {
                product = collection.getProducts().get(index++);
            } catch (IndexOutOfBoundsException e) {
                break;
            }
            Glide.with(context)
                    .load(product.getImage())
                    .transform(new RoundedCorners(6))
                    .placeholder(R.mipmap.ic_launcher)
                    .into(imageView);
        }
    }

    @Override
    public int getItemCount() {
        return collectionList.size();
    }

    public void setCollectionList(List<Collection> collectionList) {
        this.collectionList = collectionList;
        notifyDataSetChanged();
    }

    static class CollectionViewHolder extends RecyclerView.ViewHolder {

        public final List<ImageView> imageViewList = new ArrayList<>();
        public TextView collectionName;
        public TextView totalCollection;

        public CollectionViewHolder(View view) {
            super(view);
            imageViewList.add(view.findViewById(R.id.first_image));
            imageViewList.add(view.findViewById(R.id.second_image));
            imageViewList.add(view.findViewById(R.id.third_image));
            collectionName = view.findViewById(R.id.text_collection_name);
            totalCollection = view.findViewById(R.id.text_total_collection);
        }
    }
}
