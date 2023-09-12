package com.example.myassignment.Models;

public class ShopItem {

    String name,quantity;
    int image,price,count;

    boolean isSelected;

    public ShopItem(String name, int price, String quantity, int image) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.image = image;

        isSelected=false;
        count=0;
    }

    public ShopItem() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void addCount() {
        count++;
    }
    public void removeCount() {
        count--;
    }

}
