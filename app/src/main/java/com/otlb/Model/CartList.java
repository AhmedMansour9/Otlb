package com.otlb.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CartList {

    @SerializedName("cartId")
    @Expose
    private String cartId;
    @SerializedName("mealName")
    @Expose
    private String mealName;
    @SerializedName("mealPrice")
    @Expose
    private String mealPrice;
    @SerializedName("mealImage")
    @Expose
    private String mealImage;
    @SerializedName("qty")
    @Expose
    private String qty;
    @SerializedName("totalPrice")
    @Expose
    private Integer totalPrice;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public String getMealPrice() {
        return mealPrice;
    }

    public void setMealPrice(String mealPrice) {
        this.mealPrice = mealPrice;
    }

    public String getMealImage() {
        return mealImage;
    }

    public void setMealImage(String mealImage) {
        this.mealImage = mealImage;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

}
