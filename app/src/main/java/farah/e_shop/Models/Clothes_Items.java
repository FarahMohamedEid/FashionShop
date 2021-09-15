package farah.e_shop.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Clothes_Items implements Serializable {
    int img;
    String name;
    int price,old_price,percent;
    int rate;
    List<String> size = new ArrayList<>();
    List<Integer> color = new ArrayList<>() ;
    boolean love;
    String SelectedSize;
    int SelectedColor;
    int SelectedQuantity;


    public Clothes_Items() {
    }


    public Clothes_Items(int img, String name, int price, int rate,List<String> size, List<Integer> color, boolean love,String SelectedSize,int SelectedColor,int SelectedQuantity ) {
        this.img = img;
        this.name = name;
        this.price = price;
        this.rate = rate;
        this.size = size;
        this.color = color;
        this.love = love;
        this.SelectedSize = SelectedSize;
        this.SelectedColor = SelectedColor;
        this.SelectedQuantity = SelectedQuantity;
    }

    public Clothes_Items(int img, String name, int old_price, int price , int percent, int rate, List<String> size, List<Integer> color, boolean love,String SelectedSize, int SelectedColor,int SelectedQuantity) {
        this.img = img;
        this.name = name;
        this.old_price = old_price;
        this.price = price;
        this.percent = percent;
        this.rate = rate;
        this.size = size;
        this.color= color;
        this.love = love;
        this.SelectedSize = SelectedSize;
        this.SelectedColor = SelectedColor;
    }

    public int getOld_price() {
        return old_price;
    }

    public void setOld_price(int old_price) {
        this.old_price = old_price;
    }

    public int getPercent() {
        return percent;
    }

    public void setPercent(int percent) {
        this.percent = percent;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public List<String> getSize() {
        return size;
    }

    public void setSize(List<String> size) {
        this.size = size;
    }

    public List<Integer> getColor() {
        return color;
    }

    public void setColor(List<Integer> color) {
        this.color = color;
    }

    public boolean isLove() {
        return love;
    }

    public void setLove(boolean love) {
        this.love = love;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
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

    public String getSelectedSize() {
        return SelectedSize;
    }

    public void setSelectedSize(String selectedSize) {
        SelectedSize = selectedSize;
    }

    public int getSelectedColor() {
        return SelectedColor;
    }

    public void setSelectedColor(int selectedColor) {
        SelectedColor = selectedColor;
    }

    public int getSelectedQuantity() {
        return SelectedQuantity;
    }

    public void setSelectedQuantity(int selectedQuantity) {
        SelectedQuantity = selectedQuantity;
    }
}
