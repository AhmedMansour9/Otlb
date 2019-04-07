package com.otlb.Retrofit;

import com.otlb.Model.RegisterFaceResponse;
import com.otlb.Model.RegisterResponse;
import com.otlb.Model.UserLoginResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by HP on 04/09/2018.
 */

public interface Apiinterface {


    @POST("signupMobile")
    Call<RegisterResponse> register(@QueryMap Map<String, String> queryMab);
    @POST("signupMobileFacebook")
    Call<RegisterFaceResponse> RegisterFace_Book(@QueryMap Map<String,String> queryMab);
    @POST("signupMobileGoogle")
    Call<UserLoginResponse> RegisterGoogle(@QueryMap Map<String,String> queryMab);


//    @POST("changeStatusOrders")
//    Call<RegisterResponse> changestat(@QueryMap Map<String, String> queryMab);
//
//
    @POST("login")
    Call<UserLoginResponse> Login(@QueryMap Map<String, String> queryMab);
//
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

