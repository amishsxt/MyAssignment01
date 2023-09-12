package com.example.myassignment.Adapter.ItemAdapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myassignment.R;

public class ShopItemViewHolder extends RecyclerView.ViewHolder {
    ImageView itemImage,addBtn,removeBtn;
    TextView itemName,itemPrice,itemQuantity,itemCount;
    CardView card_01,card_02;

    public ShopItemViewHolder(@NonNull View itemView) {
        super(itemView);

        itemImage=itemView.findViewById(R.id.item_image);
        itemPrice=itemView.findViewById(R.id.item_price);
        itemQuantity=itemView.findViewById(R.id.item_quatity);
        itemName=itemView.findViewById(R.id.item_name);
        itemCount=itemView.findViewById(R.id.item_count_text);
        card_01=itemView.findViewById(R.id.card_01);
        card_02=itemView.findViewById(R.id.card_02);
        addBtn=itemView.findViewById(R.id.add_btn);
        removeBtn=itemView.findViewById(R.id.remove_btn);

    }
}
