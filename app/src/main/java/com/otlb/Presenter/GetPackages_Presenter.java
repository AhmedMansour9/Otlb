package com.otlb.Presenter;

import android.content.Context;

import com.otlb.Model.MenuDetails_Response;
import com.otlb.Retrofit.ApiCLint;
import com.otlb.Retrofit.Apiinterface;
import com.otlb.View.GetMenuDetails_View;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetPackages_Presenter {

    GetMenuDetails_View getCities_view;

    public GetPackages_Presenter(Context context, GetMenuDetails_View getCities_view) {
        this.getCities_view = getCities_view;

    }

    public void GetMenuDetails(String lang) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("lang", lang);

        Apiinterface apiInterface = ApiCLint.getClient().create(Apiinterface.class);
        Call<MenuDetails_Response> call = apiInterface.GetMenuDetails(queryMap);
        call.enqueue(new Callback<MenuDetails_Response>() {
            @Override
            public void onResponse(Call<MenuDetails_Response> call, Response<MenuDetails_Response> response) {

                if (response.isSuccessful()) {
                    getCities_view.listMenus(response.body().getData());
                } else {
                    getCities_view.Error("");
                }
            }


            @Override
            public void onFailure(Call<MenuDetails_Response> call, Throwable t) {
                getCities_view.Error("");

            }
        });
    }
}

