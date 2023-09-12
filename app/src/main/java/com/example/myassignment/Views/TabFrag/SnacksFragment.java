package com.example.myassignment.Views.TabFrag;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myassignment.Adapter.ItemAdapter.ShopItemAdapter;
import com.example.myassignment.Models.ShopItem;
import com.example.myassignment.R;

import java.util.ArrayList;
import java.util.List;

public class SnacksFragment extends Fragment {


    private RecyclerView recyclerView;
    private ShopItemAdapter shopItemAdapter;
    private List<ShopItem> shopItemList;

    private OnDataSentListener callback;

    public SnacksFragment() {
        // Required empty public constructor
    }

    public SnacksFragment(List<ShopItem> shopItemList) {
        this.shopItemList=shopItemList;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            // Initialize the interface callback in onAttach
            callback = (OnDataSentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnDataSentListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_snacks, container, false);

        //casting views
        recyclerView=view.findViewById(R.id.recycler_view);

        //vertical recycler Adapter
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));

        setAdapter();

        return view;
    }

    private void setAdapter(){
        shopItemAdapter = new ShopItemAdapter(getActivity(), shopItemList, new ShopItemAdapter.OnItemClickListener() {
            @Override
            public void onItemAdd(ShopItem shopItem) {

                callback.onDataAddSent(shopItem);

            }

            @Override
            public void onItemRemove(ShopItem shopItem) {

                callback.onDataRemoveSent(shopItem);
            }
        });
        recyclerView.setAdapter(shopItemAdapter);
    }

    private void filterList(String str){
        List<ShopItem> filteredList = new ArrayList<>();

        for (ShopItem item : shopItemList){
            if(item.getName().toLowerCase().contains(str.toLowerCase())){
                filteredList.add(item);
            }
        }

        if (filteredList.isEmpty()){
            Toast.makeText(getActivity(), "No data found!", Toast.LENGTH_SHORT).show();
        }else {
            shopItemAdapter.setFilteredList(filteredList);
        }
    }

    public void filterData(String query) {
        // Implement filtering logic here and update your RecyclerView's adapter
        filterList(query);
    }

    public interface OnDataSentListener {
        void onDataAddSent(ShopItem data);
        void onDataRemoveSent(ShopItem data);
    }

}