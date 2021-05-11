package farah.e_shop.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Offers_Items implements Serializable {
    int img;
    String name;
    int price1, price, percent;
    int rate;
    List<String> size = new ArrayList<>();
    List<Integer> color = new ArrayList<>();
    boolean love;

    public Offers_Items() {
    }

    public Offers_Items(int img, String name, int price1, int price, int percent, int rate, List<String> size, List<Integer> color, boolean love) {
        this.img = img;
        this.name = name;
        this.price1 = price1;
        this.price = price;
        this.percent = percent;
        this.rate = rate;
        this.size = size;
        this.color = color;
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

    public int getPrice1() {
        return price1;
    }

    public void setPrice1(int price1) {
        this.price1 = price1;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price= price;
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
}