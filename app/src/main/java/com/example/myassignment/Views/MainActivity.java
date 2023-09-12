package com.example.myassignment.Views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myassignment.Adapter.TabAdapter.ShopTabAdapter;
import com.example.myassignment.Models.ShopItem;
import com.example.myassignment.R;
import com.example.myassignment.Views.TabFrag.BeveragesFragment;
import com.example.myassignment.Views.TabFrag.OtherFragment;
import com.example.myassignment.Views.TabFrag.SnacksFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SnacksFragment.OnDataSentListener {

    private List<ShopItem> shopItemList;
    private SearchView searchBar;
    private ImageView favBtn;
    private TextView totalPrice,totalProduct;
    private LinearLayout viewOrder;
    private TabLayout tabLayout;
    private ViewPager2 viewPager2;
    private ShopTabAdapter shopTabAdapter;
    private boolean favState = false;

    private int totalProductCount = 0;
    private int totalPriceCount = 0;
    private AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //casting views
        searchBar=findViewById(R.id.search_bar);
        favBtn=findViewById(R.id.fav_btn);
        viewOrder=findViewById(R.id.view_order_layout);
        totalPrice=findViewById(R.id.total_price);
        totalProduct=findViewById(R.id.total_product);
        tabLayout=findViewById(R.id.tab_layout);
        viewPager2=findViewById(R.id.view_pager);
        builder=new AlertDialog.Builder(this);

        //data
        fillData();

        //init
        SnacksFragment snacksFragment = new SnacksFragment(shopItemList);
        BeveragesFragment beveragesFragment = new BeveragesFragment();
        OtherFragment otherFragment = new OtherFragment();

        //adding tabs
        addTabs();

        //viewpager
        FragmentManager fm = getSupportFragmentManager();
        shopTabAdapter = new ShopTabAdapter(fm, getLifecycle(),snacksFragment,beveragesFragment,otherFragment);
        viewPager2.setAdapter(shopTabAdapter);

        //tabLayout
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //viewPager sliding
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        //search bar
        searchBar.clearFocus();
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                snacksFragment.filterData(s);
                return true;
            }
        });

        //fav btn
        favBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(favState){
                    favBtn.setImageResource(R.drawable.fav_filled_ic);
                    favState = false;
                }
                else {
                    favBtn.setImageResource(R.drawable.fav_outlined_ic);
                    favState = true;
                }
            }
        });

        //view order
        viewOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                builder.setTitle("View Order")
                        .setMessage("Your total product is: " + totalProductCount +"\n"
                        + "Your total price is: \u20B9 " + totalPriceCount)
                        .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        })
                        .show();
            }
        });

    }

    private void fillData(){
        //filling data
        shopItemList = new ArrayList<>();
        shopItemList.add(new ShopItem("Red lable tea",200,"(500 gm)",R.drawable.item1_img));
        shopItemList.add(new ShopItem("Head & Shoulder shampoo",300,"(750 ml)",R.drawable.item2_img));
        shopItemList.add(new ShopItem("Detol",100,"(500 ml)",R.drawable.item3_img));
        shopItemList.add(new ShopItem("Corn flakes",50,"(750 gm)",R.drawable.item4_img));
        shopItemList.add(new ShopItem("Tomato ketchup",250,"(400 ml)",R.drawable.item5_img));
        shopItemList.add(new ShopItem("Aashirvaad Shudh Chakki Atta",350,"(10 kg)",R.drawable.item6_img));
        shopItemList.add(new ShopItem("Maggi",10,"(1 packet)",R.drawable.item7_img));
        shopItemList.add(new ShopItem("Oreo",20,"(1 packet)",R.drawable.item8_img));

    }

    private void addTabs(){
        tabLayout.addTab(tabLayout.newTab().setText("Snacks"));
        tabLayout.addTab(tabLayout.newTab().setText("Beverages"));
        tabLayout.addTab(tabLayout.newTab().setText("Other Grocieries"));
    }

    @Override
    public void onDataAddSent(ShopItem shopItem) {
        viewOrder.setVisibility(View.VISIBLE);

        if(shopItem.getCount() == 1){
            totalProductCount++;
        }

        //calculating total price
        totalPriceCount = totalPriceCount + shopItem.getPrice();

        totalProduct.setText(String.valueOf(totalProductCount) + " Products");
        totalPrice.setText(String.valueOf(totalPriceCount));

    }

    @Override
    public void onDataRemoveSent(ShopItem shopItem) {
        if (shopItem.getCount() == 0) {
            totalProductCount--;
        }

        //calculating total price
        totalPriceCount = totalPriceCount - shopItem.getPrice();

        totalProduct.setText(String.valueOf(totalProductCount) + " Products");
        totalPrice.setText(String.valueOf(totalPriceCount));

        if (totalProductCount == 0) {
            viewOrder.setVisibility(View.GONE);
        }
    }
}