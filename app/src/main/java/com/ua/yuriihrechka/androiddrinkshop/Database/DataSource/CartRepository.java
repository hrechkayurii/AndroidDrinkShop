package com.ua.yuriihrechka.androiddrinkshop.Database.DataSource;

import com.ua.yuriihrechka.androiddrinkshop.Database.ModelDB.Cart;

import java.util.List;

import io.reactivex.Flowable;

public class CartRepository implements ICartDataSource {

    private ICartDataSource iCartDataSource;

    public CartRepository(ICartDataSource iCartDataSource) {
        this.iCartDataSource = iCartDataSource;
    }

    private static CartRepository instance;
    public static CartRepository getInstance(ICartDataSource iCartDataSource){

        if (instance == null){
            instance = new CartRepository(iCartDataSource);
        }
        return instance;
    }

    @Override
    public Flowable<List<Cart>> getCartItem() {
        return iCartDataSource.getCartItem();
    }

    @Override
    public Flowable<List<Cart>> getCartItemById(int cartItemId) {
        return iCartDataSource.getCartItemById(cartItemId);
    }

    @Override
    public int countCartItems() {
        return iCartDataSource.countCartItems();
    }

    @Override
    public void emptyCart() {
        iCartDataSource.emptyCart();

    }

    @Override
    public void insertToCart(Cart... carts) {
        iCartDataSource.insertToCart(carts);

    }

    @Override
    public void updateCart(Cart... carts) {

        iCartDataSource.updateCart(carts);
    }

    @Override
    public void deleteCartItem(Cart cart) {
        iCartDataSource.deleteCartItem(cart);
    }
}
