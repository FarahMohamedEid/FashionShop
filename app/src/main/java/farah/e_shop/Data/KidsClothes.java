package farah.e_shop.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import farah.e_shop.Models.Clothes_Items;
import farah.e_shop.R;

public class KidsClothes implements Serializable {

    public List<Clothes_Items> SetKidsClothes() {

        List<Clothes_Items> kid = new ArrayList<>();
        kid.add(new Clothes_Items(R.drawable.kdress1,"drees1",50,3, Arrays.asList("S","M","XL"), Arrays.asList(R.color.gold, R.color.black, R.color.dark_gray,R.color.purple_700),true));
        kid.add(new Clothes_Items(R.drawable.kdress2,"drees2",30,3, Arrays.asList("S","M","XXL"),Arrays.asList(R.color.red, R.color.green, R.color.gold),true));
        kid.add(new Clothes_Items(R.drawable.kdress3,"drees3",40,3, Arrays.asList("M","XL","XXL"),Arrays.asList(R.color.black, R.color.dark_gray, R.color.pink,R.color.white),true));
        kid.add(new Clothes_Items(R.drawable.kdress4,"drees4",44,3,Arrays.asList("S","L","XL","XXL"),Arrays.asList(R.color.orange, R.color.dark_gray,R.color.pink),true));
        kid.add(new Clothes_Items(R.drawable.kdress5,"drees5",68,3, Arrays.asList("L","XL"),Arrays.asList(R.color.black, R.color.orange, R.color.gold,R.color.pink),true));
        kid.add(new Clothes_Items(R.drawable.kdress6,"drees6",66,3, Arrays.asList("M","L","XL","XXL"),Arrays.asList(R.color.red, R.color.dark_gray, R.color.gold,R.color.purple_200),true));
        kid.add(new Clothes_Items(R.drawable.kdress7,"drees7",100,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.dark_gray, R.color.gold),true));
        kid.add(new Clothes_Items(R.drawable.kshirt1,"shirt1",70,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.green, R.color.pink, R.color.gold),true));
        kid.add(new Clothes_Items(R.drawable.kshirt2,"shirt2",37,3, Arrays.asList("S","L","XL"),Arrays.asList(R.color.purple_500, R.color.dark_gray, R.color.gold),true));
        kid.add(new Clothes_Items(R.drawable.kshirt3,"shirt3",50,3, Arrays.asList("S","M","XL","XXL"),Arrays.asList(R.color.black, R.color.dark_gray, R.color.orange),true));
        kid.add(new Clothes_Items(R.drawable.kshirt4,"shirt4",1500,3,Arrays.asList("M","XL"),Arrays.asList(R.color.black, R.color.dark_gray,R.color.purple_700, R.color.teal_200),true));
        kid.add(new Clothes_Items(R.drawable.kshirt5,"shirt5",750,3, Arrays.asList("XL","XXL","XXXL"),Arrays.asList(R.color.purple_700, R.color.dark_gray, R.color.gold),true));
        kid.add(new Clothes_Items(R.drawable.kshirt6,"shirt6",2250,3, Arrays.asList("S","L","XL"),Arrays.asList(R.color.black, R.color.dark_gray, R.color.gold),true));
        kid.add(new Clothes_Items(R.drawable.kshirt7,"shirt7",590,3, Arrays.asList("S","M","L"),Arrays.asList(R.color.red,R.color.black, R.color.purple_700, R.color.gold),true));
        kid.add(new Clothes_Items(R.drawable.kshirt8,"shirt8",310,3,Arrays.asList("S","M","L"),Arrays.asList(R.color.black, R.color.dark_gray, R.color.gold),true));
        kid.add(new Clothes_Items(R.drawable.kshirt9,"shirt9",620,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.green, R.color.gold),true));
        kid.add(new Clothes_Items(R.drawable.kshirt10,"shirt10",250,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.dark_gray, R.color.gold),true));
        kid.add(new Clothes_Items(R.drawable.kshirt11,"shirt11",950,3, Arrays.asList("M","L","XL"),Arrays.asList(R.color.black,R.color.pink, R.color.dark_gray, R.color.gold),true));
        kid.add(new Clothes_Items(R.drawable.kshirt12,"shirt12",450,3, Arrays.asList("M","XL"),Arrays.asList(R.color.black, R.color.dark_gray, R.color.gold),true));
        kid.add(new Clothes_Items(R.drawable.ksute1,"suite1",350,3, Arrays.asList("S","XL"),Arrays.asList(R.color.white, R.color.red, R.color.gold),true));
        kid.add(new Clothes_Items(R.drawable.ksute2,"suite2",820,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.pink,R.color.black, R.color.dark_gray, R.color.gold),true));
        kid.add(new Clothes_Items(R.drawable.ksute3,"suite3",199,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.teal_700,R.color.pink, R.color.gold),true));
        kid.add(new Clothes_Items(R.drawable.ksute4,"suite4",183,3,Arrays.asList("S","M","XL"),Arrays.asList(R.color.black,R.color.purple_700, R.color.dark_gray, R.color.gold),true));
        kid.add(new Clothes_Items(R.drawable.ksute5,"suite5",320,3, Arrays.asList("M","XL"),Arrays.asList(R.color.orange, R.color.dark_gray, R.color.white),true));
        kid.add(new Clothes_Items(R.drawable.ksute6,"suite6",125,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.green, R.color.gold),true));
        kid.add(new Clothes_Items(R.drawable.ksute7,"suite7",150,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.green, R.color.gold),true));
        kid.add(new Clothes_Items(R.drawable.ksute8,"suite8",125,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.green, R.color.gold),true));
        kid.add(new Clothes_Items(R.drawable.kpants1,"pants1",250,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.green, R.color.gold),true));
        kid.add(new Clothes_Items(R.drawable.kpants2,"pants2",50,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.green, R.color.gold),true));
        kid.add(new Clothes_Items(R.drawable.kpants3,"pants3",50,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.green, R.color.gold),true));
        kid.add(new Clothes_Items(R.drawable.kpants4,"pants4",150,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.green, R.color.gold),true));
        kid.add(new Clothes_Items(R.drawable.kpants5,"pants5",150,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.green, R.color.gold),true));
        kid.add(new Clothes_Items(R.drawable.kpants6,"pants6",120,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.green, R.color.gold),true));
        kid.add(new Clothes_Items(R.drawable.kshort1,"short1",125,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.green, R.color.gold),true));
        kid.add(new Clothes_Items(R.drawable.kshort2,"short1",50,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.green, R.color.gold),true));
        kid.add(new Clothes_Items(R.drawable.kshort3,"short1",190,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.green, R.color.gold),true));
        kid.add(new Clothes_Items(R.drawable.kshort5,"short1",90,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.green, R.color.gold),true));
        return kid;
    }
}
