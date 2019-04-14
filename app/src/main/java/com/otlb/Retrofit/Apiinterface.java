package com.otlb.Retrofit;

import com.otlb.Model.AddtoCart_Response;
import com.otlb.Model.CartResponse;
import com.otlb.Model.CartUpdate_Response;
import com.otlb.Model.ChangePassword_Response;
import com.otlb.Model.ChangeProfile_Response;
import com.otlb.Model.Cities_Response;
import com.otlb.Model.Details_Offers_Response;
import com.otlb.Model.MenuDetails_Response;
import com.otlb.Model.Offers_Response;
import com.otlb.Model.Packages_Response;
import com.otlb.Model.Profile_Response;
import com.otlb.Model.RegisterFaceResponse;
import com.otlb.Model.RegisterResponse;
import com.otlb.Model.Restaurants_Response;
import com.otlb.Model.StatesResponse;
import com.otlb.Model.TypesFoodResponse;
import com.otlb.Model.UserLoginResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by HP on 04/09/2018.
 */

public interface Apiinterface {


    @POST("register")
    Call<RegisterResponse> register(@QueryMap Map<String, String> queryMab);
    @POST("signupMobileFacebook")
    Call<RegisterFaceResponse> RegisterFace_Book(@QueryMap Map<String,String> queryMab);
    @POST("signupMobileGoogle")
    Call<UserLoginResponse> RegisterGoogle(@QueryMap Map<String,String> queryMab);

    @POST("getCity")
    Call<Cities_Response> GetCities(@QueryMap Map<String,String> queryMab);

    @POST("getOffers")
    Call<Details_Offers_Response> GetOffers(@QueryMap Map<String,String> queryMab);


    @POST("deleteFromCart")
    Call<CartResponse> DeleteCart(@QueryMap Map<String,String> queryMab);

    @POST("updateCartAddQty")
    Call<CartUpdate_Response> UpdateCart(@QueryMap Map<String,String> queryMab);

    @POST("updateCartdeleteQty")
    Call<CartUpdate_Response> MinusCart(@QueryMap Map<String,String> queryMab);


    @POST("getType")
    Call<TypesFoodResponse> GetTypes(@QueryMap Map<String,String> queryMab);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("auth/myCart")
    Call<CartResponse> ShowCart(@QueryMap Map<String,String> queryMab,@Header("Authorization") String auth);

    @POST("getState")
    Call<StatesResponse> GetStates(@QueryMap Map<String,String> queryMab);

    @POST("getMenu")
    Call<Cities_Response> GetMenu(@QueryMap Map<String,String> queryMab);


    @POST("getMenuDetails")
    Call<MenuDetails_Response> GetMenuDetails(@QueryMap Map<String,String> queryMab);
    @POST("getPackage")
    Call<Packages_Response> GetPackages(@QueryMap Map<String,String> queryMab);


    @POST("search")
    Call<Restaurants_Response> GetRestaurants(@QueryMap Map<String,String> queryMab);

    @POST("getOffersRestaurant")
    Call<Offers_Response> GetRestaurantsOffers(@QueryMap Map<String,String> queryMab);

    //    @POST("changeStatusOrders")
//    Call<RegisterResponse> changestat(@QueryMap Map<String, String> queryMab);
//
    @POST("login")
    Call<UserLoginResponse> Login(@QueryMap Map<String, String> queryMab);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("auth/updateProfile")
    Call<ChangeProfile_Response> ChangeProfile(@QueryMap Map<String, String> queryMab,@Header("Authorization") String auth);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("auth/user")
    Call<Profile_Response> GetProfile(@Header("Authorization") String auth);

    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("auth/updatePassword")
    Call<ChangePassword_Response> Changepass(@QueryMap Map<String, String> queryMab,@Header("Authorization") String auth);


    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @POST("auth/addCart")
    Call<AddtoCart_Response> AddToCart(@QueryMap Map<String, String> queryMab,@Header("Authorization") String auth);

//    @POST("changPassword")
//    Call<UserLoginResponse> changepass(@QueryMap Map<String, String> queryMab);
//
//    @POST("updateProfile")
//    Call<UserLoginResponse> change(@QueryMap Map<String, String> queryMab);
//
//
//    @POST("signupMobileGoogle")
//    Call<UserLoginResponse> RegisterGoogle(@QueryMap Map<String, String> queryMab);
//    @POST("signupMobileFacebook")
//    Call<RegisterFaceResponse> RegisterFace_Book(@QueryMap Map<String, String> queryMab);



}

