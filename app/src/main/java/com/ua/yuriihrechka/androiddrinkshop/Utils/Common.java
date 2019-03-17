package com.ua.yuriihrechka.androiddrinkshop.Utils;

import com.ua.yuriihrechka.androiddrinkshop.Retrofit.IDrinkShopAPI;
import com.ua.yuriihrechka.androiddrinkshop.Retrofit.RetrofitClient;

public class Common {

    private static final String BASE_URL = "http://drinkshop/";

    public static IDrinkShopAPI getAPI(){
        return RetrofitClient.getClient(BASE_URL).create(IDrinkShopAPI.class);
    }
}
