package com.example.myassignment.Adapter.ItemAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myassignment.Models.ShopItem;
import com.example.myassignment.R;

import java.util.List;

public class ShopItemAdapter extends RecyclerView.Adapter<ShopItemViewHolder> {

    private Context context;
    private List<ShopItem> itemList;

    private OnItemClickListener listener;

    public ShopItemAdapter(Context context, List<ShopItem> itemList, OnItemClickListener listener) {
        this.context = context;
        this.itemList = itemList;
        this.listener = listener;
    }

    public void setFilteredList(List<ShopItem> filteredList){
        this.itemList = filteredList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShopItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShopItemViewHolder(LayoutInflater.from(context).inflate(R.layout.shop_item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ShopItemViewHolder holder, int position) {
        if(itemList.get(position).getCount() != 0){
            //showing add remove opt
            holder.card_02.setVisibility(View.VISIBLE);
            holder.card_01.setVisibility(View.GONE);
        }
        else {
            //hiding add remove opt
            holder.card_02.setVisibility(View.GONE);
            holder.card_01.setVisibility(View.VISIBLE);
        }

        holder.itemCount.setText(String.valueOf(itemList.get(position).getCount()));
        holder.itemName.setText(itemList.get(position).getName());
        holder.itemPrice.setText(String.valueOf(itemList.get(position).getPrice()));
        holder.itemQuantity.setText(itemList.get(position).getQuantity());
        holder.itemImage.setImageResource(itemList.get(position).getImage());

        holder.card_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showing add remove opt
                holder.card_02.setVisibility(View.VISIBLE);
                holder.card_01.setVisibility(View.GONE);

                itemList.get(position).addCount();
                holder.itemCount.setText(String.valueOf(itemList.get(position).getCount()));

                //add product
                listener.onItemAdd(itemList.get(position));
            }
        });

        holder.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                itemList.get(position).addCount();
                holder.itemCount.setText(String.valueOf(itemList.get(position).getCount()));

                //add product
                listener.onItemAdd(itemList.get(position));
            }
        });

        holder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(itemList.get(position).getCount() > 1){
                    itemList.get(position).removeCount();
                    holder.itemCount.setText(String.valueOf(itemList.get(position).getCount()));

                    //remove product
                    listener.onItemRemove(itemList.get(position));
                }
                else {
                    //showing add remove opt
                    holder.card_02.setVisibility(View.GONE);
                    holder.card_01.setVisibility(View.VISIBLE);

                    itemList.get(position).removeCount();
                    holder.itemCount.setText(String.valueOf(itemList.get(position).getCount()));

                    //remove product
                    listener.onItemRemove(itemList.get(position));
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public interface OnItemClickListener {
        void onItemAdd(ShopItem shopItem);
        void onItemRemove(ShopItem shopItem);
    }
}
