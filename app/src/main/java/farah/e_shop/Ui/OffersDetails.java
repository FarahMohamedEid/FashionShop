package farah.e_shop.Ui;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import farah.e_shop.Models.Clothes_Items;
import farah.e_shop.Models.Offers_Items;
import farah.e_shop.R;
import farah.e_shop.Utiles.Constants;

public class OffersDetails extends AppCompatActivity {
    ImageView img,love;
    TextView name,price1,price2,percent;
    RatingBar ratingBar;
    MaterialRippleLayout cart;
    RecyclerView sizeRV,colorRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers_details);
        initviews();
    }



    private void initviews() {
        img =findViewById(R.id.offer_img2);
        name =findViewById(R.id.offer_name2);
        price1 =findViewById(R.id.offer_price12);
        price2 =findViewById(R.id.offer_price22);
        percent =findViewById(R.id.offer_percent2);
        ratingBar =findViewById(R.id.rating2);
        sizeRV =findViewById(R.id.size_RV);
        colorRV =findViewById(R.id.color_RV);
        love =findViewById(R.id.heart_offer);
        cart =findViewById(R.id.addToCart);

        Offers_Items offers_items =(Offers_Items) getIntent().getSerializableExtra("offer");
        img.setImageResource(offers_items.getImg());
        name.setText(offers_items.getName());
        price1.setText(offers_items.getPrice1()+" $");
        price1.setPaintFlags(price1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        price2.setText(offers_items.getPrice()+" $");
        percent.setText(offers_items.getPercent()+" $");
        ratingBar.setNumStars(offers_items.getRate());
        colorRV.setAdapter( new ColorAdapter(offers_items.getColor()));
        colorRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        sizeRV.setAdapter( new SizeAdapter(offers_items.getSize()));
        sizeRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        setLikesColor(offers_items, love);

        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLikes(offers_items, love);
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                bundle.putString("message", "From Activity");
                //set Fragmentclass Arguments
                tab3 fragobj=new tab3();
                fragobj.setArguments(bundle);


                getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, new tab3()).commit();
            }
        });


    }


    public class ColorAdapter extends RecyclerView.Adapter<colorHolder> {

        List<Integer> colorlist ;


        public ColorAdapter(List<Integer> colorlist) {
            this.colorlist = colorlist;
        }


        @NonNull
        @Override
        public colorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.color_item,parent,false);
            return new colorHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull colorHolder holder, int position) {

            int colors = colorlist.get(position);
            holder.img.setImageResource(colors);


        }

        @Override
        public int getItemCount() {
            return colorlist.size();
        }
    }

    public class colorHolder extends RecyclerView.ViewHolder{
        CircleImageView img;

        public colorHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.color_img);
        }
    }





    public class SizeAdapter extends RecyclerView.Adapter<sizeHolder> {

        List<String> sizelist;

        public SizeAdapter(List<String> sizelist) {
            this.sizelist = sizelist;
        }

        @NonNull
        @Override
        public sizeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.size_item,parent,false);
            return new sizeHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull sizeHolder holder, int position) {
            String size = sizelist.get(position);
            holder.tv.setText(size);

        }


        @Override
        public int getItemCount() {
            return sizelist.size();
        }
    }

    public class sizeHolder extends RecyclerView.ViewHolder{
        TextView tv;

        public sizeHolder(@NonNull View itemView) {
            super(itemView);
            tv =  itemView.findViewById(R.id.size_tv);
        }
    }



    void setLikes(final Offers_Items item, final ImageView imageView) {
        Constants.initRef().child("Likes").child(Constants.GETid(OffersDetails.this)).addListenerForSingleValueEvent(new ValueEventListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if (snapshot.hasChild(item.getName()))
                {
                    ColorStateList csl = AppCompatResources.getColorStateList(getApplicationContext(), R.color.gray);
                    imageView.setImageTintList(csl);
                    Constants.initRef().child("Likes").child(Constants.GETid(OffersDetails.this)).child(item.getName()).removeValue();

                } else
                {
                    ColorStateList csl = AppCompatResources.getColorStateList(getApplicationContext(), R.color.red);
                    imageView.setImageTintList(csl);
                    Constants.initRef().child("Likes").child(Constants.GETid(OffersDetails.this)).child(item.getName()).setValue(item);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
    }




    void setLikesColor(Offers_Items item, final ImageView imageView) {
        Constants.initRef().child("Likes").child(Constants.GETid(OffersDetails.this)).addValueEventListener(new ValueEventListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if (snapshot.hasChild(item.getName()))
                {
                    ColorStateList csl = AppCompatResources.getColorStateList(getApplicationContext(), R.color.red);
                    imageView.setImageTintList(csl);
                } else
                {
                    ColorStateList csl = AppCompatResources.getColorStateList(getApplicationContext(), R.color.gray);
                    imageView.setImageTintList(csl);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
    }


}