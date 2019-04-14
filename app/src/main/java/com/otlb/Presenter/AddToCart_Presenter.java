package com.otlb.Presenter;

import android.content.Context;

import com.otlb.Model.AddtoCart_Response;
import com.otlb.Model.CartResponse;
import com.otlb.Model.CartUpdate_Response;
import com.otlb.Model.UserLoginResponse;
import com.otlb.Model.UserRegister;
import com.otlb.Retrofit.ApiCLint;
import com.otlb.Retrofit.Apiinterface;
import com.otlb.View.AddToCart_View;
import com.otlb.View.LoginView;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddToCart_Presenter {

    AddToCart_View addToCart_view;

    public AddToCart_Presenter(Context context, AddToCart_View Loginview)
    {
        this.addToCart_view=Loginview;

    }

    public void Addtocart(String id,String Quantity,String token) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("menu_details_id", id);
        queryMap.put("qty",Quantity);

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<AddtoCart_Response> call = apiInterface.AddToCart(queryMap,"Bearer "+token);
        call.enqueue(new Callback<AddtoCart_Response>() {
            @Override
            public void onResponse(Call<AddtoCart_Response> call, Response<AddtoCart_Response> response) {

                if(response.code()==200){
                    if(response.body().getData().getMessage().equals("save cart success")) {
                        addToCart_view.Result(response.body().getData().getMessage());
                    }else if(response.body().getData().getMessage().equals("update cart success")){

                        addToCart_view.Updated(response.body().getData().getMessage());
                    }
                } else {
                    addToCart_view.Error();
                }

            }




            @Override
            public void onFailure(Call<AddtoCart_Response> call, Throwable t) {
                addToCart_view.Error();

            }
        });
    }

    public void Delete_toCart(String lang,String user,String product_id) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        queryMap.put("user_token", user);
        queryMap.put("customers_basket_id", product_id);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<CartResponse> call = apiInterface.DeleteCart(queryMap);
        call.enqueue(new Callback<CartResponse>() {
            @Override
            public void onResponse(Call<CartResponse> call, Response<CartResponse> response) {

                if (response.isSuccessful()) {
//                    addToCart_view.DeleteCart(response.body().getData().g());
                } else {
                    addToCart_view.Failed();
                }
            }
            @Override
            public void onFailure(Call<CartResponse> call, Throwable t) {
                addToCart_view.Failed();

            }
        });
    }

    public void UpdateCart(String lang,String user,String product_id) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        queryMap.put("user_token", user);
        queryMap.put("customers_basket_id", product_id);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<CartUpdate_Response> call = apiInterface.UpdateCart(queryMap);
        call.enqueue(new Callback<CartUpdate_Response>() {
            @Override
            public void onResponse(Call<CartUpdate_Response> call, Response<CartUpdate_Response> response) {

                if (response.isSuccessful()) {
                    addToCart_view.Success();
                } else {
                    addToCart_view.Failed();
                }
            }
            @Override
            public void onFailure(Call<CartUpdate_Response> call, Throwable t) {
                addToCart_view.Failed();

            }
        });
    }

    public void MinusCart(String lang,String user,String product_id) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);
        queryMap.put("api_token", "100");
        queryMap.put("user_token", user);
        queryMap.put("customers_basket_id", product_id);
        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);

        Call<CartUpdate_Response> call = apiInterface.MinusCart(queryMap);
        call.enqueue(new Callback<CartUpdate_Response>() {
            @Override
            public void onResponse(Call<CartUpdate_Response> call, Response<CartUpdate_Response> response) {

                if (response.isSuccessful()) {
                    addToCart_view.Success();
                } else {
                    addToCart_view.Failed();
                }
            }
            @Override
            public void onFailure(Call<CartUpdate_Response> call, Throwable t) {
                addToCart_view.Failed();

            }
        });
    }

}
