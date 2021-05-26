package com.liniocodingtest.models;

import com.google.gson.annotations.SerializedName;

public class Owner {

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("linioId")
    private String linioId;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getLinioId() {
        return linioId;
    }
}