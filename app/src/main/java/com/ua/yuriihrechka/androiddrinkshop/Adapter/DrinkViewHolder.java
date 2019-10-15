package com.ua.yuriihrechka.androiddrinkshop.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ua.yuriihrechka.androiddrinkshop.Interface.IItemClickListener;
import com.ua.yuriihrechka.androiddrinkshop.R;

public class DrinkViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView img_product;
    TextView txt_drink_name;
    TextView txt_price;

    ImageButton btn_add_to_cart, btn_add_to_favorite;

    IItemClickListener itemClickListener;

    public void setItemClickListener(IItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public DrinkViewHolder(View itemView) {
        super(itemView);

        img_product = (ImageView)itemView.findViewById(R.id.image_product);
        txt_drink_name = (TextView)itemView.findViewById(R.id.txt_drink_name);
        txt_price = (TextView)itemView.findViewById(R.id.txt_price);

        btn_add_to_cart = (ImageButton)itemView.findViewById(R.id.btn_add_cart);
        btn_add_to_favorite = (ImageButton)itemView.findViewById(R.id.btn_favorite);

        itemView.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view);
    }
}
