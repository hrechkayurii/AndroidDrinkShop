package com.ua.yuriihrechka.androiddrinkshop.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.squareup.picasso.Picasso;
import com.ua.yuriihrechka.androiddrinkshop.Interface.IItemClickListener;
import com.ua.yuriihrechka.androiddrinkshop.Model.Drink;
import com.ua.yuriihrechka.androiddrinkshop.R;

import java.util.List;

public class DrinkAdapter extends RecyclerView.Adapter<DrinkViewHolder> {

    Context context;
    List<Drink> drinkList;

    public DrinkAdapter(Context context, List<Drink> drinkList) {
        this.context = context;
        this.drinkList = drinkList;
    }

    @NonNull
    @Override
    public DrinkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.drink_item_layout, null);
        return new DrinkViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DrinkViewHolder holder, final int position) {

        Picasso.with(context).load(drinkList.get(position).link)
                .into(holder.img_product);

        holder.txt_drink_name.setText(drinkList.get(position).name);
        holder.txt_price.setText(new StringBuilder("$").append(drinkList.get(position).price).toString());


        holder.btn_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddToCartDialog(position);
            }
        });

        holder.setItemClickListener(new IItemClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void showAddToCartDialog(int position) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.add_to_cart_layout, null);

        ImageView img_product_dialog = (ImageView)itemView.findViewById(R.id.img_cart_product);
        ElegantNumberButton txt_count = (ElegantNumberButton)itemView.findViewById(R.id.txt_count);
        TextView txt_product_dialog = (TextView)itemView.findViewById(R.id.txt_cart_product_name);
        EditText edt_coment = (EditText)itemView.findViewById(R.id.edt_comment);

        RadioButton rdi_size_M = (RadioButton)itemView.findViewById(R.id.rdi_size_M);
        RadioButton rdi_size_L = (RadioButton)itemView.findViewById(R.id.rdi_size_L);





    }

    @Override
    public int getItemCount() {
        return drinkList.size();
    }
}
