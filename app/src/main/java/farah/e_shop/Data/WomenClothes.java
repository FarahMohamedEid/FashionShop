package farah.e_shop.Data;

import android.graphics.Color;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import farah.e_shop.Models.Clothes_Items;
import farah.e_shop.R;
import farah.e_shop.Ui.WomenTab;

import static androidx.core.content.res.TypedArrayUtils.getString;

public class WomenClothes implements Serializable {

    public List<Clothes_Items> SetWomenClothes() {

        List<Clothes_Items> women = new ArrayList<>();
        women.add(new Clothes_Items(R.drawable.shirt1,"blouse",50,3, Arrays.asList("S","M","XL"), Arrays.asList(R.color.gold, R.color.black, R.color.dark_gray,R.color.purple_700),true));
        women.add(new Clothes_Items(R.drawable.shirt2,"simon shirt",30,3, Arrays.asList("S","M","XXL"),Arrays.asList(R.color.red, R.color.green, R.color.gold),true));
        women.add(new Clothes_Items(R.drawable.shirt3,"moon shirt",40,3, Arrays.asList("M","XL","XXL"),Arrays.asList(R.color.black, R.color.dark_gray, R.color.pink,R.color.white),true));
        women.add(new Clothes_Items(R.drawable.shirt4,"stylish blouse",44,3,Arrays.asList("S","L","XL","XXL"),Arrays.asList(R.color.orange, R.color.dark_gray,R.color.pink),true));
        women.add(new Clothes_Items(R.drawable.shirt5,"denim blouse",68,3, Arrays.asList("L","XL"),Arrays.asList(R.color.black, R.color.orange, R.color.gold,R.color.pink),true));
        women.add(new Clothes_Items(R.drawable.shirt6,"short with belt",66,3, Arrays.asList("M","L","XL","XXL"),Arrays.asList(R.color.red, R.color.dark_gray, R.color.gold,R.color.purple_200),true));
        women.add(new Clothes_Items(R.drawable.shirt7,"fabric pants",100,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.dark_gray, R.color.gold),true));
        women.add(new Clothes_Items(R.drawable.shirt9,"long blouse",70,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.green, R.color.pink, R.color.gold),true));
        women.add(new Clothes_Items(R.drawable.shirt10,"Jeans shorts",37,3, Arrays.asList("S","L","XL"),Arrays.asList(R.color.purple_500, R.color.dark_gray, R.color.gold),true));
        women.add(new Clothes_Items(R.drawable.shirt11,"skirt",50,3, Arrays.asList("S","M","XL","XXL"),Arrays.asList(R.color.black, R.color.dark_gray, R.color.orange),true));
        women.add(new Clothes_Items(R.drawable.dress1,"dress toll",1500,3,Arrays.asList("M","XL"),Arrays.asList(R.color.black, R.color.dark_gray,R.color.purple_700, R.color.teal_200),true));
        women.add(new Clothes_Items(R.drawable.dress2,"short dress",750,3, Arrays.asList("XL","XXL","XXXL"),Arrays.asList(R.color.purple_700, R.color.dark_gray, R.color.gold),true));
        women.add(new Clothes_Items(R.drawable.dress3,"long dress",2250,3, Arrays.asList("S","L","XL"),Arrays.asList(R.color.black, R.color.dark_gray, R.color.gold),true));
        women.add(new Clothes_Items(R.drawable.dress4,"V-cut dress",590,3, Arrays.asList("S","M","L"),Arrays.asList(R.color.red,R.color.black, R.color.purple_700, R.color.gold),true));
        women.add(new Clothes_Items(R.drawable.dress5,"flower dress",310,3,Arrays.asList("S","M","L"),Arrays.asList(R.color.black, R.color.dark_gray, R.color.gold),true));
        women.add(new Clothes_Items(R.drawable.dress6,"denim dress",620,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.green, R.color.gold),true));
        women.add(new Clothes_Items(R.drawable.dress7,"cotton dress",250,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.dark_gray, R.color.gold),true));
        women.add(new Clothes_Items(R.drawable.jump1,"jumpsuit",950,3, Arrays.asList("M","L","XL"),Arrays.asList(R.color.black,R.color.pink, R.color.dark_gray, R.color.gold),true));
        women.add(new Clothes_Items(R.drawable.jump2,"flowers jumpsuit",450,3, Arrays.asList("M","XL"),Arrays.asList(R.color.black, R.color.dark_gray, R.color.gold),true));
        women.add(new Clothes_Items(R.drawable.jump3,"jumpsuit",350,3, Arrays.asList("S","XL"),Arrays.asList(R.color.white, R.color.red, R.color.gold),true));
        women.add(new Clothes_Items(R.drawable.jump4,"denim jumpsuit",820,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.pink,R.color.black, R.color.dark_gray, R.color.gold),true));
        women.add(new Clothes_Items(R.drawable.hoodie1,"Hoodie",199,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.teal_700,R.color.pink, R.color.gold),true));
        women.add(new Clothes_Items(R.drawable.hoodie2,"Hoodie zipup",183,3,Arrays.asList("S","M","XL"),Arrays.asList(R.color.black,R.color.purple_700, R.color.dark_gray, R.color.gold),true));
        women.add(new Clothes_Items(R.drawable.hoodie4,"colorful Hoodie",320,3, Arrays.asList("M","XL"),Arrays.asList(R.color.orange, R.color.dark_gray, R.color.white),true));
        women.add(new Clothes_Items(R.drawable.coat1,"coat",1250,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.black, R.color.green, R.color.gold),true));
        women.add(new Clothes_Items(R.drawable.pants1,"casual pants",360,3, Arrays.asList("S","M","XL"),Arrays.asList(R.color.teal_200, R.color.dark_gray, R.color.gold),true));
        women.add(new Clothes_Items(R.drawable.pants2,"short pants",314,3, Arrays.asList("S","M","XL","XXL"),Arrays.asList(R.color.red, R.color.dark_gray, R.color.gold,R.color.pink),true));
        women.add(new Clothes_Items(R.drawable.pants3,"pants",186,3, Arrays.asList("S","M","XL","XXL"),Arrays.asList(R.color.black, R.color.light_gray, R.color.gold),true));
        women.add(new Clothes_Items(R.drawable.pants4,"cotton pants",243,3, Arrays.asList("S","M","XL","XXL"),Arrays.asList(R.color.teal_700,R.color.pink, R.color.dark_gray, R.color.gold),true));
        return women;
    }


}
