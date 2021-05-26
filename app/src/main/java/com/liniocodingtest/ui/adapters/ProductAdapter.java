package com.liniocodingtest.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.liniocodingtest.R;
import com.liniocodingtest.models.BadgeEnum;
import com.liniocodingtest.models.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private List<Product> productList = new ArrayList<>();
    private final Context context;

    private final Map<BadgeEnum, Integer> badgeIconMap = new HashMap<BadgeEnum, Integer>() {{
        put(BadgeEnum.PLUS_LEVEL_1, R.drawable.nd_ic_plus_30);
        put(BadgeEnum.PLUS_LEVEL_2, R.drawable.nd_ic_plus_48_30);
        put(BadgeEnum.REFURBISHED, R.drawable.nd_ic_refurbished_30);
        put(BadgeEnum.NEW, R.drawable.nd_ic_new_30);
        put(BadgeEnum.IMPORTED, R.drawable.nd_ic_international_30);
        put(BadgeEnum.FREE_SHIPPING, R.drawable.nd_ic_free_shipping_30);
    }};

    public ProductAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_favorite, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product item = this.productList.get(position);

        Glide.with(context)
                .load(item.getImage())
                .placeholder(R.mipmap.ic_launcher)
                .into(holder.imageView);

        List<BadgeEnum> badgeIconList = item.getBadgeList();
        holder.linearLayout.removeAllViews();
        for (BadgeEnum badge : badgeIconList) {
            ImageView imageView = new ImageView(context);
            Integer resIcon = this.badgeIconMap.get(badge);
            if (resIcon != null) {
                imageView.setImageResource(resIcon);
            }
            holder.linearLayout.addView(imageView);
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
        notifyDataSetChanged();
    }

    static class ProductViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public LinearLayout linearLayout;

        public ProductViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.image_view);
            linearLayout = view.findViewById(R.id.image_list);
        }
    }
}
