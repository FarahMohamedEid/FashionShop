package farah.e_shop.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import farah.e_shop.Models.Clothes_Items;
import farah.e_shop.R;

public class MenClothes implements Serializable {

    public List<Clothes_Items> SetMenClothes() {

        List<Clothes_Items> men = new ArrayList<>();
        men.add(new Clothes_Items(R.drawable.mshirt1,"shirt1",50,3, Arrays.asList("S","M","XL"), Arrays.asList(R.color.gold, R.color.black, R.color.dark_gray,R.color.purple_700),true));
        men.add(new Clothes_Items(R.drawable.mshirt2,"shirt2",30,3, Arrays.asList("S","M","XXL"),Arrays.asList(R.color.red, R.color.green, R.color.gold),true));
        men.add(new Clothes_Items(R.drawable.mshirt3,"shirt3",40,3, Arrays.asList("M","XL","XXL"),Arrays.asList(R.color.black, R.color.dark_gray, R.color.pink,R.color.white),true));
        men.add(new Clothes_Items(R.drawable.mshirt4,"shirt4",44,3,Arrays.asList("S","L","XL","XXL"),Arrays.asList(R.color.orange, R.color.dark_gray,R.color.pink),true));
        men.add(new Clothes_Items(R.drawable.mshirt5,"shirt5",68,3, Arrays.asList("L","XL"),Arrays.asList(R.color.black, R.color.orange, R.color.gold,R.color.pink),true));
        men.add(new Clothes_Items(R.drawable.mshirt6,"shirt6",66,3, Arrays.asList("M","L","XL","XXL"),Arrays.asList(R.color.red, R.color.dark_gray, R.color.gold,R.color.purple_200),true));
        men.add(new Clothes_Items(R.drawable.mshirt7,"shirt7",100,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.dark_gray, R.color.gold),true));
        men.add(new Clothes_Items(R.drawable.mshirt8,"shirt8",70,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.green, R.color.pink, R.color.gold),true));
        men.add(new Clothes_Items(R.drawable.mshort1,"short1",37,3, Arrays.asList("S","L","XL"),Arrays.asList(R.color.purple_500, R.color.dark_gray, R.color.gold),true));
        men.add(new Clothes_Items(R.drawable.mshort2,"short2",50,3, Arrays.asList("S","M","XL","XXL"),Arrays.asList(R.color.black, R.color.dark_gray, R.color.orange),true));
        men.add(new Clothes_Items(R.drawable.mshort3,"short3",1500,3,Arrays.asList("M","XL"),Arrays.asList(R.color.black, R.color.dark_gray,R.color.purple_700, R.color.teal_200),true));
        men.add(new Clothes_Items(R.drawable.mshort4,"short4",750,3, Arrays.asList("XL","XXL","XXXL"),Arrays.asList(R.color.purple_700, R.color.dark_gray, R.color.gold),true));
        men.add(new Clothes_Items(R.drawable.mshort5,"short5",2250,3, Arrays.asList("S","L","XL"),Arrays.asList(R.color.black, R.color.dark_gray, R.color.gold),true));
        men.add(new Clothes_Items(R.drawable.mshort6,"short6",590,3, Arrays.asList("S","M","L"),Arrays.asList(R.color.red,R.color.black, R.color.purple_700, R.color.gold),true));
        men.add(new Clothes_Items(R.drawable.mjacket1,"jacket1",310,3,Arrays.asList("S","M","L"),Arrays.asList(R.color.black, R.color.dark_gray, R.color.gold),true));
        men.add(new Clothes_Items(R.drawable.mjacket2,"jacket2",620,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.green, R.color.gold),true));
        men.add(new Clothes_Items(R.drawable.mjacket3,"jacket3",250,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.dark_gray, R.color.gold),true));
        men.add(new Clothes_Items(R.drawable.mjacket4,"jacket4",950,3, Arrays.asList("M","L","XL"),Arrays.asList(R.color.black,R.color.pink, R.color.dark_gray, R.color.gold),true));
        men.add(new Clothes_Items(R.drawable.mjacket5,"jacket5",450,3, Arrays.asList("M","XL"),Arrays.asList(R.color.black, R.color.dark_gray, R.color.gold),true));
        men.add(new Clothes_Items(R.drawable.mjacket6,"jacket6",350,3, Arrays.asList("S","XL"),Arrays.asList(R.color.white, R.color.red, R.color.gold),true));
        men.add(new Clothes_Items(R.drawable.msport,"sport",820,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.pink,R.color.black, R.color.dark_gray, R.color.gold),true));
        men.add(new Clothes_Items(R.drawable.mpants1,"pants1",199,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.teal_700,R.color.pink, R.color.gold),true));
        men.add(new Clothes_Items(R.drawable.mpants2,"pants2",183,3,Arrays.asList("S","M","XL"),Arrays.asList(R.color.black,R.color.purple_700, R.color.dark_gray, R.color.gold),true));
        men.add(new Clothes_Items(R.drawable.mpants3,"pants3",320,3, Arrays.asList("M","XL"),Arrays.asList(R.color.orange, R.color.dark_gray, R.color.white),true));
        men.add(new Clothes_Items(R.drawable.mpants4,"pants4",1250,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.green, R.color.gold),true));
        men.add(new Clothes_Items(R.drawable.mhoodie,"hoodie",1250,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.green, R.color.gold),true));
        return men;
    }
}
