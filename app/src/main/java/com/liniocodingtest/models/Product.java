package com.liniocodingtest.models;

import com.google.gson.annotations.SerializedName;

public class Product {

	@SerializedName("image")
	private String image;

	@SerializedName("freeShipping")
	private boolean freeShipping;

	@SerializedName("imported")
	private boolean imported;

	@SerializedName("name")
	private String name;

	@SerializedName("active")
	private boolean active;

	@SerializedName("wishListPrice")
	private int wishListPrice;

	@SerializedName("conditionType")
	private String conditionType;

	@SerializedName("id")
	private int id;

	@SerializedName("linioPlusLevel")
	private int linioPlusLevel;

	@SerializedName("slug")
	private String slug;

	@SerializedName("url")
	private String url;

	public String getImage(){
		return image;
	}

	public boolean isFreeShipping(){
		return freeShipping;
	}

	public boolean isImported(){
		return imported;
	}

	public String getName(){
		return name;
	}

	public boolean isActive(){
		return active;
	}

	public int getWishListPrice(){
		return wishListPrice;
	}

	public String getConditionType(){
		return conditionType;
	}

	public int getId(){
		return id;
	}

	public int getLinioPlusLevel(){
		return linioPlusLevel;
	}

	public String getSlug(){
		return slug;
	}

	public String getUrl(){
		return url;
	}
}