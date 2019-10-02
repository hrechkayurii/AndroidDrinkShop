package com.ua.yuriihrechka.androiddrinkshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.Toast;

import com.ua.yuriihrechka.androiddrinkshop.Adapter.CartAdapter;
import com.ua.yuriihrechka.androiddrinkshop.Database.ModelDB.Cart;
import com.ua.yuriihrechka.androiddrinkshop.Utils.Common;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView_cart;
    Button btn_place_order;

    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        compositeDisposable = new CompositeDisposable();

        recyclerView_cart = (RecyclerView)findViewById(R.id.recycler_cart);
        recyclerView_cart.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_cart.setHasFixedSize(true);

        btn_place_order = (Button)findViewById(R.id.btn_place_order);

        loadCartItem();
    }

    private void loadCartItem() {

        compositeDisposable.add(
                Common.cartRepository.getCartItem()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Cart>>() {
                    @Override
                    public void accept(List<Cart> carts) throws Exception {
                        displayCartItem(carts);
                    }
                })
        );
    }

    private void displayCartItem(List<Cart> carts) {

        CartAdapter cartAdapter = new CartAdapter(this, carts);
        recyclerView_cart.setAdapter(cartAdapter);
    }

    @Override
    protected void onDestroy() {

        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }

    // exit

    boolean isBackButtonClicked = false;

    @Override
    public void onBackPressed() {
        if (isBackButtonClicked) {
            super.onBackPressed();
            return;
        }

        this.isBackButtonClicked = true;
        Toast.makeText(this, "Please click back again to exit", Toast.LENGTH_LONG).show();

    }


    @Override
    protected void onResume() {
        super.onResume();
        this.isBackButtonClicked = false;
    }
}
