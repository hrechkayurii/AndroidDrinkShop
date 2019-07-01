package com.ua.yuriihrechka.androiddrinkshop.Database.Local;

import android.arch.persistence.db.SupportSQLiteOpenHelper;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.DatabaseConfiguration;
import android.arch.persistence.room.InvalidationTracker;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import com.ua.yuriihrechka.androiddrinkshop.Database.ModelDB.Cart;

@Database(entities = {Cart.class}, version = 1, exportSchema = false)
public abstract class CartDatabase extends RoomDatabase {

    public abstract CartDAO cartDAO();
    private static CartDatabase instance;

    public static CartDatabase getInstance(Context context){

        if (instance == null){
            instance = Room.databaseBuilder(context, CartDatabase.class
            , "YH_DrinkShopDB")
                    .allowMainThreadQueries()
                    .build();


        }

        return instance;
    }


}
