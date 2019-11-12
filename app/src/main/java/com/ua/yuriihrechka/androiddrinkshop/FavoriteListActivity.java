package com.ua.yuriihrechka.androiddrinkshop;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.RelativeLayout;


import com.ua.yuriihrechka.androiddrinkshop.Adapter.FavoriteAdapter;

import com.ua.yuriihrechka.androiddrinkshop.Database.ModelDB.Favorite;
import com.ua.yuriihrechka.androiddrinkshop.Utils.Common;
import com.ua.yuriihrechka.androiddrinkshop.Utils.RecyclerItemTouchHelper;
import com.ua.yuriihrechka.androiddrinkshop.Utils.RecyclerItemTouchHelperListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class FavoriteListActivity extends AppCompatActivity implements RecyclerItemTouchHelperListener {

    RecyclerView recyclerView_fav;
    CompositeDisposable compositeDisposable;
    FavoriteAdapter favoriteAdapter;
    List<Favorite> localFavorites = new ArrayList<>();
    RelativeLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);

        compositeDisposable = new CompositeDisposable();

        rootLayout = (RelativeLayout)findViewById(R.id.rootLayout);

        recyclerView_fav = (RecyclerView)findViewById(R.id.recycler_fav);
        recyclerView_fav.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_fav.setHasFixedSize(true);

        ItemTouchHelper.SimpleCallback simpleCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView_fav);

        loadFavoriteData();


    }

    @Override
    protected void onResume() {
        super.onResume();
        loadFavoriteData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeDisposable.clear();
    }

    private void loadFavoriteData() {

        compositeDisposable.add(
                Common.favoriteRepository.getFavItems()
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

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {

        if (viewHolder instanceof FavoriteAdapter.FavoriteViewHolder){

            String name = localFavorites.get(viewHolder.getAdapterPosition()).name;

            final Favorite deleteItem = localFavorites.get(viewHolder.getAdapterPosition());
            final int deleteIndex = viewHolder.getAdapterPosition();

            favoriteAdapter.removeItem(deleteIndex);
            Common.favoriteRepository.delete(deleteItem);

            Snackbar snackbar = Snackbar.make(rootLayout, new StringBuilder(name).append(" removed from Favorite List").toString(), Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    favoriteAdapter.restoreItem(deleteItem, deleteIndex);
                    Common.favoriteRepository.insertToFavorite(deleteItem);

                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();

        }
    }
}
