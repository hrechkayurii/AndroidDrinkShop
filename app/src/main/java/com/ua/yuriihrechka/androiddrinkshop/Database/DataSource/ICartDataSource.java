package com.ua.yuriihrechka.androiddrinkshop.Database.DataSource;

import com.ua.yuriihrechka.androiddrinkshop.Database.ModelDB.Cart;

import java.util.List;

import io.reactivex.Flowable;

public interface ICartDataSource {


    Flowable<List<Cart>> getCartItem();


    Flowable<List<Cart>> getCartItemById(int cartItemId);


    int countCartItems();


    void emptyCart();


    void insertToCart(Cart...carts);


    void updateCart(Cart...carts);


    void deleteCartItem(Cart cart);

}
