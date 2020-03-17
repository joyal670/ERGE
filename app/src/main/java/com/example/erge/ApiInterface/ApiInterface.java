package com.example.erge.ApiInterface;

import com.example.erge.ApiModel.ChangeProfileNameModel;
import com.example.erge.ApiModel.DefaultAddressModel;
import com.example.erge.ApiModel.LoadUserProfileModel;
import com.example.erge.ApiModel.PaymentResponseModel;
import com.example.erge.ApiModel.PlaceOrderModel;
import com.example.erge.ApiModel.SearchProductModel;
import com.example.erge.ApiModel.SocialLoginCheck;
import com.example.erge.ApiModel.UpdatePasswordModel;
import com.example.erge.ApiModel.UpdateProfilePicModel;
import com.example.erge.ApiModel.UserLoginModel;
import com.example.erge.ApiModel.UserRegistrationModel;
import com.example.erge.ApiModel.acceptDriverModel;
import com.example.erge.ApiModel.addAddressModel;
import com.example.erge.ApiModel.currentOrderModel;
import com.example.erge.ApiModel.rejectDriverModel;
import com.example.erge.ApiModel.searchStoreModel;
import com.example.erge.ApiModel.viewAddressModel;
import com.example.erge.ApiModel.viewNotificationModel;

import java.util.List;
import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Url;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("auth/user_reg")
    Call<UserRegistrationModel> userRegister(@Field("first_name") String firstname, @Field("email") String email, @Field("password") String password, @Field("phone") String phone, @Field("device") String device, @Field("device_token") String device_token, @Field("social_agent") String social_agent, @Field("social_identifier") String social_identifier, @Field("social_image_url") String social_image_url);

    @FormUrlEncoded
    @POST("auth/user_login")
    Call<UserLoginModel> userLogin(@Field("email") String email, @Field("password") String password, @Field("device") String device, @Field("device_token") String device_token, @Field("social_agent") String social_agent, @Field("social_identifier") String social_identifier, @Field("social_image_url") String social_image_url);

    @FormUrlEncoded
    @POST("auth/social_login_check")
    Call<SocialLoginCheck> checkStatus(@Field("social_agent") String social_agent, @Field("social_identifier") String social_identifier);

    @GET
    Call<SearchProductModel> searchProduct(@Url String url);

//    @FormUrlEncoded
//    @POST("user/address")
//    Call<addAddressModel> userAddaddress(@Field("location") String location, @Field("lat") String lat, @Field("long") String longi, @Field("housename") String housename);

    @GET("user/address")
    Call<viewAddressModel> viewAddress(@Header("JWTumaAuth") String JWTumaAuth);

    @FormUrlEncoded
    @POST("user/address")
    Call<addAddressModel> addAddress(@Header("JWTumaAuth") String JWTumaAuth, @Field("location") String location, @Field("lat") String lat, @Field("long") String longi, @Field("housename") String housename, @Field("is_visible") String is_visible );

    @GET
    Call<List<searchStoreModel>> searchStore(@Url String url);

    @FormUrlEncoded
    @POST("user/default_address")
    Call<DefaultAddressModel> defaultAddress(@Header("JWTumaAuth") String JWTumaAuth, @Field("address_id") String address_id);

    @POST("order/cod")
    Call<PlaceOrderModel> placeOrder(@Header("JWTumaAuth") String JWTumaAuth, @Body RequestBody params);

    @GET("order/history")
    Call<currentOrderModel> currentOrder(@Header("JWTumaAuth") String JWTumaAuth);

    @FormUrlEncoded
    @POST("Payment/firstpayment")
    Call<PaymentResponseModel> payment(@Field("email") String email,
                                       @Field("orderId") String orderId,
                                       @Field("deliveryCharge") String deliveryCharge,
                                       @Field("amount") String amount,
                                       @Field("number") String number,
                                       @Field("exp_month") String exp_month,
                                       @Field("exp_year") String exp_year,
                                       @Field("cvv") String cvv,
                                       @Header("JWTumaAuth") String JWTumaAuth);

    @FormUrlEncoded
    @POST("user/profile")
    Call<ChangeProfileNameModel> changeName(@Header("JWTumaAuth") String JWTumaAuth, @Field("full_name") String full_name);

    @GET("user/profile")
    Call<LoadUserProfileModel> loadProfile(@Header("JWTumaAuth") String JWTumaAuth);

    @Multipart
    @POST("user/profile_picture")
    Call<UpdateProfilePicModel> updatePic(@PartMap Map<String, RequestBody> params, @Header("JWTumaAuth") String JWTumaAuth);

    @GET("order/driver_list")
    Call<viewNotificationModel> notifications(@Header("JWTumaAuth") String JWTumaAuth);

    @FormUrlEncoded
    @POST("order/reject_driver")
    Call<rejectDriverModel> rejectDriver(@Header("JWTumaAuth") String JWTumaAuth, @Field("driver_id") String driver_id, @Field("order_id") String order_id);

    @FormUrlEncoded
    @POST("order/accept_driver")
    Call<acceptDriverModel> acceptDriver(@Header("JWTumaAuth") String JWTumaAuth, @Field("driver_id") String driver_id, @Field("order_id") String order_id);

    @FormUrlEncoded
    @POST("auth/password/")
    Call<UpdatePasswordModel> updatepassword(@Header("JWTumaAuth") String JWTumaAuth, @Field("old_pass") String old_pass, @Field("new_pass") String new_pass);
}