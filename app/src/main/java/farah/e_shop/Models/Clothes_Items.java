package farah.e_shop.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Clothes_Items implements Serializable {
    int img;
    String name;
    int price;
    int rate;
    List<String> size = new ArrayList<>();
    List<Integer> color = new ArrayList<>() ;
    boolean love;

    public Clothes_Items() {
    }


    public Clothes_Items(int img, String name, int price, int rate,List<String> size, List<Integer> color, boolean love) {
        this.img = img;
        this.name = name;
        this.price = price;
        this.rate = rate;
        this.size = size;
        this.color = color;
        this.love = love;
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
}
