package com.ua.yuriihrechka.androiddrinkshop.Retrofit;

import com.ua.yuriihrechka.androiddrinkshop.Model.Banner;
import com.ua.yuriihrechka.androiddrinkshop.Model.CheckUserResponse;
import com.ua.yuriihrechka.androiddrinkshop.Model.User;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;



    public interface IDrinkShopAPI {
        @FormUrlEncoded
        @POST("checkuser.php")
        Call<CheckUserResponse> checkUserExists(@Field("phone") String phone);

        @FormUrlEncoded
        @POST("register.php")
        Call<User> registerNewUser(@Field("phone") String phone,
                                   @Field("name") String name,
                                   @Field("address") String address,
                                   @Field("birthdate") String birthdate);



        @FormUrlEncoded
        @POST("getuser.php")
        Call<User> getUserInformation(@Field("phone") String phone);

        @GET("getbanner.php")
        Observable<List<Banner>>getBanners();

        // image
        // https://picua.org
}
