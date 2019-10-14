package com.ua.yuriihrechka.androiddrinkshop.Database.DataSource;

import com.ua.yuriihrechka.androiddrinkshop.Database.ModelDB.Favorite;

import java.util.List;

import io.reactivex.Flowable;

public class FavoriteRepository implements IFavoriteDataSource {


    private IFavoriteDataSource favoriteDataSource;
    private static FavoriteRepository instance;

    public FavoriteRepository(IFavoriteDataSource favoriteDataSource) {
        this.favoriteDataSource = favoriteDataSource;
    }

    public static FavoriteRepository getInstance(IFavoriteDataSource favoriteDataSource){

        if (favoriteDataSource == null){
            instance = new FavoriteRepository(favoriteDataSource);

        }
        return instance;
    }

    @Override
    public Flowable<List<Favorite>> getFavItem() {
        return favoriteDataSource.getFavItem();
    }

    @Override
    public int isFavorite(int itemId) {
        return favoriteDataSource.isFavorite(itemId);
    }

    @Override
    public void delete(Favorite favorite) {
        favoriteDataSource.delete(favorite);
    }
}
