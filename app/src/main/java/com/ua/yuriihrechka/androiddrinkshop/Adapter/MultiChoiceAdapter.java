package com.ua.yuriihrechka.androiddrinkshop.Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;


import com.ua.yuriihrechka.androiddrinkshop.Model.Drink;
import com.ua.yuriihrechka.androiddrinkshop.R;
import com.ua.yuriihrechka.androiddrinkshop.Utils.Common;


import java.util.List;

public class MultiChoiceAdapter extends RecyclerView.Adapter<MultiChoiceAdapter.MultiChoiceViewHolder>{


    Context context;
    List<Drink> optionList;

    public MultiChoiceAdapter(Context context, List<Drink> optionList) {
        this.context = context;
        this.optionList = optionList;
    }

    @NonNull
    @Override
    public MultiChoiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.multi_check_layout, null);
        return new MultiChoiceViewHolder(itemView);
        //return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MultiChoiceViewHolder holder, final int position) {

        holder.checkBox.setText(optionList.get(position).name);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b){
                    Common.toppingAdded.add(compoundButton.getText().toString());
                    Common.toppingPrice += Double.parseDouble(optionList.get(position).price);
                }else {
                    Common.toppingAdded.remove(compoundButton.getText().toString());
                    Common.toppingPrice -= Double.parseDouble(optionList.get(position).price);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return optionList.size();
    }






     class MultiChoiceViewHolder extends RecyclerView.ViewHolder{

        CheckBox checkBox;

        public MultiChoiceViewHolder(View itemView) {
            super(itemView);

            checkBox = (CheckBox)itemView.findViewById(R.id.ckb_topping);
        }
    }
}
