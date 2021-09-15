package farah.e_shop.Data;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import farah.e_shop.Models.Clothes_Items;
import farah.e_shop.R;

public class Offers implements Serializable {

    @RequiresApi(api = Build.VERSION_CODES.R)
    public List<Clothes_Items> SetOffers() {

        List<Clothes_Items> offers = new ArrayList<>();
          offers.add(new Clothes_Items(R.drawable.kshirt12, "shirt12", 450, 405, 10, 3,Arrays.asList("S","L","XL") ,Arrays.asList(R.color.white, R.color.red, R.color.gold), true,"0",R.color.white,0));
        offers.add(new Clothes_Items(R.drawable.ksute1,"suite1",350,192,45,3, Arrays.asList("S","XL"),Arrays.asList(R.color.white, R.color.red, R.color.gold),true,"0",R.color.white,0));
        offers.add(new Clothes_Items(R.drawable.jewellery7,"Jewellery7",100,90,10,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.dark_gray, R.color.gold),true,"0",R.color.white,0));
        offers.add(new Clothes_Items(R.drawable.jewellery8,"Jewellery8",70,56,20,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.green, R.color.pink, R.color.gold),true,"0",R.color.white,0));
        offers.add(new Clothes_Items(R.drawable.jewellery10,"Jewellery9",37,32,15,3, Arrays.asList("S","L","XL"),Arrays.asList(R.color.purple_500, R.color.dark_gray, R.color.gold),true,"0",R.color.white,0));
        offers.add(new Clothes_Items(R.drawable.kdress6,"drees6",66,46,30,3, Arrays.asList("M","L","XL","XXL"),Arrays.asList(R.color.red, R.color.dark_gray, R.color.gold,R.color.purple_200),true,"0",R.color.white,0));
        offers.add(new Clothes_Items(R.drawable.kdress7,"drees7",100,50,50,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.dark_gray, R.color.gold),true,"0",R.color.white,0));
        offers.add(new Clothes_Items(R.drawable.kshirt1,"shirt1",70,49,30,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.green, R.color.pink, R.color.gold),true,"0",R.color.white,0));
        offers.add(new Clothes_Items(R.drawable.mjacket6,"jacket6",350,123,65,3, Arrays.asList("S","XL"),Arrays.asList(R.color.white, R.color.red, R.color.gold),true,"0",R.color.white,0));
        offers.add(new Clothes_Items(R.drawable.msport,"sport",820,468,43,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.pink,R.color.black, R.color.dark_gray, R.color.gold),true,"0",R.color.white,0));
        offers.add(new Clothes_Items(R.drawable.mpants1,"pants1",199,160,20,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.teal_700,R.color.pink, R.color.gold),true,"0",R.color.white,0));
        offers.add(new Clothes_Items(R.drawable.dress7,"cotton dress",250,220,12,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.dark_gray, R.color.gold),true,"0",R.color.white,0));
        offers.add(new Clothes_Items(R.drawable.jump1,"jumpsuit",950,600,37,3, Arrays.asList("M","L","XL"),Arrays.asList(R.color.black,R.color.pink, R.color.dark_gray, R.color.gold),true,"0",R.color.white,0));
        offers.add(new Clothes_Items(R.drawable.jump2,"flowers jumpsuit",450,365,19,3, Arrays.asList("M","XL"),Arrays.asList(R.color.black, R.color.dark_gray, R.color.gold),true,"0",R.color.white,0));
        offers.add(new Clothes_Items(R.drawable.shoes10,"shoes10",243,195,20,3, Arrays.asList("S","M","XL","XXL"),Arrays.asList(R.color.teal_700,R.color.pink, R.color.dark_gray, R.color.gold),true,"0",R.color.white,0));
        offers.add(new Clothes_Items(R.drawable.shoes11,"shoes11",186,140,25,3, Arrays.asList("S","M","XL","XXL"),Arrays.asList(R.color.black, R.color.light_gray, R.color.gold),true,"0",R.color.white,0));
        offers.add(new Clothes_Items(R.drawable.pants2,"short pants",314,198,37,3, Arrays.asList("S","M","XL","XXL"),Arrays.asList(R.color.red, R.color.dark_gray, R.color.gold,R.color.pink),true,"0",R.color.white,0));
        offers.add(new Clothes_Items(R.drawable.pants3,"pants",186,165,11,3, Arrays.asList("S","M","XL","XXL"),Arrays.asList(R.color.black, R.color.light_gray, R.color.gold),true,"0",R.color.white,0));
        offers.add(new Clothes_Items(R.drawable.kshort5,"short1",90,76,15,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.green, R.color.gold),true,"0",R.color.white,0));
        return offers;
    }
}
