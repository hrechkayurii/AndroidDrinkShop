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
import com.ua.yuriihrechka.androiddrinkshop.Database.ModelDB.Favorite;

@Database(entities = {Cart.class, Favorite.class}, version = 1, exportSchema = false)
public abstract class RoomCartDatabase extends RoomDatabase {

    public abstract CartDAO cartDAO();
    public abstract FavoriteDAO favoriteDAO();

    private static RoomCartDatabase instance;

    public static RoomCartDatabase getInstance(Context context){

        if (instance == null){
            instance = Room.databaseBuilder(context, RoomCartDatabase.class
            , "YH_DrinkShopDB")
                    .allowMainThreadQueries()
                    .build();


        }

        return instance;
    }


}
