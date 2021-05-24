package com.liniocodingtest.models;

import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Collection {

    @SerializedName("owner")
    private Owner owner;

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("default")
    private boolean isDefault;

    @SerializedName("visibility")
    private String visibility;

    @SerializedName("name")
    private String name;

    @SerializedName("description")
    private String description;

    @SerializedName("id")
    private int id;

    @SerializedName("products")
    @JsonAdapter(ProductDeserializer.class)
    private ArrayList<Product> products;

    public Owner getOwner() {
        return owner;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}