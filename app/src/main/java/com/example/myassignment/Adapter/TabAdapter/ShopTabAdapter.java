package com.example.myassignment.Adapter.TabAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myassignment.Views.TabFrag.BeveragesFragment;
import com.example.myassignment.Views.TabFrag.OtherFragment;
import com.example.myassignment.Views.TabFrag.SnacksFragment;

public class ShopTabAdapter extends FragmentStateAdapter {

    private SnacksFragment snacksFragment;
    private BeveragesFragment beveragesFragment;
    private OtherFragment otherFragment;

    public ShopTabAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle,
                          SnacksFragment snacksFragment, BeveragesFragment beveragesFragment, OtherFragment otherFragment) {
        super(fragmentManager, lifecycle);

        this.snacksFragment = snacksFragment;
        this.beveragesFragment = beveragesFragment;
        this.otherFragment = otherFragment;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 1){
            return beveragesFragment;
        }
        else if(position == 2){
            return otherFragment;
        }
        else {
            return snacksFragment;
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
