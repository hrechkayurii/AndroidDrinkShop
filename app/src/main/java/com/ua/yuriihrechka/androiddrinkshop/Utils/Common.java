package com.ua.yuriihrechka.androiddrinkshop.Utils;

import com.ua.yuriihrechka.androiddrinkshop.Model.User;
import com.ua.yuriihrechka.androiddrinkshop.Retrofit.IDrinkShopAPI;
import com.ua.yuriihrechka.androiddrinkshop.Retrofit.RetrofitClient;

public class Common {

    //private static final String BASE_URL = "http://localhost/drinkshop/";  // http://localhost/drinkshop/

    public static User currentUser = null;
    private static final String BASE_URL = "http://192.168.0.109/drinkshop/";
    //private static final String BASE_URL = "http://localhost/drinkshop/";

    public static IDrinkShopAPI getApiDrinkShop(){
        return RetrofitClient.getClient(BASE_URL).create(IDrinkShopAPI.class);
    }
}
