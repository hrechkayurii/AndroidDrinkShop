package com.ua.yuriihrechka.androiddrinkshop;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ua.yuriihrechka.androiddrinkshop.Adapter.CartAdapter;
import com.ua.yuriihrechka.androiddrinkshop.Adapter.FavoriteAdapter;
import com.ua.yuriihrechka.androiddrinkshop.Database.ModelDB.Cart;
import com.ua.yuriihrechka.androiddrinkshop.Database.ModelDB.Favorite;
import com.ua.yuriihrechka.androiddrinkshop.Utils.Common;
import com.ua.yuriihrechka.androiddrinkshop.Utils.RecyclerItemTouchHelper;
import com.ua.yuriihrechka.androiddrinkshop.Utils.RecyclerItemTouchHelperListener;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class CartActivity extends AppCompatActivity implements RecyclerItemTouchHelperListener {

    RecyclerView recyclerView_cart;
    Button btn_place_order;

    CompositeDisposable compositeDisposable;

    List<Cart> cartList = new ArrayList<>();
    CartAdapter cartAdapter;

    RelativeLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        compositeDisposable = new CompositeDisposable();

        recyclerView_cart = (RecyclerView)findViewById(R.id.recycler_cart);
        recyclerView_cart.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_cart.setHasFixedSize(true);

        btn_place_order = (Button)findViewById(R.id.btn_place_order);
        rootLayout = (RelativeLayout)findViewById(R.id.rootLayout);

        ItemTouchHelper.SimpleCallback simpleCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(simpleCallback).attachToRecyclerView(recyclerView_cart);

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

        cartList = carts;

        cartAdapter = new CartAdapter(this, carts);
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

    /*boolean isBackButtonClicked = false;

    @Override
    public void onBackPressed() {
        if (isBackButtonClicked) {
            super.onBackPressed();
            return;
        }

        this.isBackButtonClicked = true;
        Toast.makeText(this, "Please click back again to exit", Toast.LENGTH_LONG).show();

    }*/


    @Override
    protected void onResume() {
        super.onResume();
        //this.isBackButtonClicked = false;
        loadCartItem();
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
        if (viewHolder instanceof CartAdapter.CartViewHolder){

            String name = cartList.get(viewHolder.getAdapterPosition()).name;

            final Cart deleteItem = cartList.get(viewHolder.getAdapterPosition());
            final int deleteIndex = viewHolder.getAdapterPosition();

            cartAdapter.removeItem(deleteIndex);
            Common.cartRepository.deleteCartItem(deleteItem);

            Snackbar snackbar = Snackbar.make(rootLayout, new StringBuilder(name).append(" removed from Cart List").toString(), Snackbar.LENGTH_LONG);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    cartAdapter.restoreItem(deleteItem, deleteIndex);
                    Common.cartRepository.insertToCart(deleteItem);

                }
            });
            snackbar.setActionTextColor(Color.YELLOW);
            snackbar.show();

        }
    }
}
