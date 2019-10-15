package com.ua.yuriihrechka.androiddrinkshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ua.yuriihrechka.androiddrinkshop.Adapter.CartAdapter;
import com.ua.yuriihrechka.androiddrinkshop.Adapter.FavoriteAdapter;
import com.ua.yuriihrechka.androiddrinkshop.Database.ModelDB.Cart;
import com.ua.yuriihrechka.androiddrinkshop.Database.ModelDB.Favorite;
import com.ua.yuriihrechka.androiddrinkshop.Utils.Common;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FavoriteListActivity extends AppCompatActivity {

    RecyclerView recyclerView_fav;
    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);

        compositeDisposable = new CompositeDisposable();

        recyclerView_fav = (RecyclerView)findViewById(R.id.recycler_fav);
        recyclerView_fav.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_fav.setHasFixedSize(true);

        loadFavoriteData();


    }

    private void loadFavoriteData() {

        compositeDisposable.add(
                Common.favoriteRepository.getFavItem()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeOn(Schedulers.io())
                        .subscribe(new Consumer<List<Favorite>>() {
                            @Override
                            public void accept(List<Favorite> favorites) throws Exception {
                                displayFavoriteItem(favorites);
                            }
                        })
        );

    }

    private void displayFavoriteItem(List<Favorite> favorites) {

        FavoriteAdapter favoriteAdapter = new FavoriteAdapter(this, favorites);
        recyclerView_fav.setAdapter(favoriteAdapter);
    }
}
