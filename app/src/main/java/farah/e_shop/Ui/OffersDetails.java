package farah.e_shop.Ui;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import farah.e_shop.Models.Clothes_Items;
import farah.e_shop.R;
import farah.e_shop.Utiles.Constants;

public class OffersDetails extends AppCompatActivity {
    ImageView img, love, add, minus;
    TextView name, price1, price2, percent, quantity;
    RatingBar ratingBar;
    MaterialRippleLayout cart;
    RecyclerView sizeRV, colorRV;
    int finalColorPosition = -1, finalNumberOfPieces = 0, finalSizePosition = -1;
    Clothes_Items offers_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers_details);
        initviews();
    }


    private void initviews() {
        img = findViewById(R.id.offer_img2);
        name = findViewById(R.id.offer_name2);
        price1 = findViewById(R.id.offer_price12);
        price2 = findViewById(R.id.offer_price22);
        percent = findViewById(R.id.offer_percent2);
        ratingBar = findViewById(R.id.rating2);
        sizeRV = findViewById(R.id.size_RV);
        colorRV = findViewById(R.id.color_RV);
        love = findViewById(R.id.heart_offer);
        cart = findViewById(R.id.addToCart);
        add = findViewById(R.id.fav_btn_more);
        minus = findViewById(R.id.fav_btn_less);
        quantity = findViewById(R.id.quantity);

        offers_items = (Clothes_Items) getIntent().getSerializableExtra("offer");
        img.setImageResource(offers_items.getImg());
        name.setText(offers_items.getName());
        if(offers_items.getOld_price() != 0){
            price1.setText(offers_items.getOld_price() + " $");
        }else{
            price1.setVisibility(View.GONE);
        }
        price1.setPaintFlags(price1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        price2.setText(offers_items.getPrice() + " $");
        if(offers_items.getPercent() != 0){
            percent.setText(offers_items.getPercent() + "%");
        }else{
            percent.setVisibility(View.GONE);
        }
        ratingBar.setNumStars(offers_items.getRate());
        colorRV.setAdapter(new ColorAdapter(offers_items.getColor()));
        colorRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        sizeRV.setAdapter(new SizeAdapter(offers_items.getSize()));
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

                if (finalSizePosition == -1){
                    Toast.makeText(OffersDetails.this, "Please Select Size", Toast.LENGTH_SHORT).show();
                }else if (finalColorPosition == -1){
                    Toast.makeText(OffersDetails.this, "Please Select Color", Toast.LENGTH_SHORT).show();
                }else if (finalNumberOfPieces == 0){
                    Toast.makeText(OffersDetails.this, "Please Select Quantity", Toast.LENGTH_SHORT).show();
                }else {
                    Constants.initRef().child("cart").child(Constants.GETid(OffersDetails.this)).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Constants.initRef().child("Cart").child(Constants.GETid(OffersDetails.this)).child(offers_items.getName()).setValue(offers_items);
                            Constants.initRef().child("Cart").child(Constants.GETid(OffersDetails.this)).child(offers_items.getName()).child("selectedSize").setValue(offers_items.getSize().get(finalSizePosition));
                            Constants.initRef().child("Cart").child(Constants.GETid(OffersDetails.this)).child(offers_items.getName()).child("selectedColor").setValue(offers_items.getColor().get(finalColorPosition));
                            Constants.initRef().child("Cart").child(Constants.GETid(OffersDetails.this)).child(offers_items.getName()).child("selectedQuantity").setValue(finalNumberOfPieces);

                            Bundle bundle = new Bundle();
                            bundle.putString("message", "From Activity");
                            tab3 fragObj = new tab3();
                            fragObj.setArguments(bundle);
                            getSupportFragmentManager().beginTransaction()
                                    .add(android.R.id.content, new tab3()).commit();
                            Toast.makeText(OffersDetails.this, "Added Successfully", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity(true);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quantity(false);
            }
        });


    }


    public class ColorAdapter extends RecyclerView.Adapter<colorHolder> {
        int selectedColorPosition = -1 ;
        List<Integer> colorlist;


        public ColorAdapter(List<Integer> colorlist) {
            this.colorlist = colorlist;
        }


        @NonNull
        @Override
        public colorHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.color_item, parent, false);
            return new colorHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull colorHolder holder, @SuppressLint("RecyclerView") int position) {

            int colors = colorlist.get(position);
            holder.img.setImageResource(colors);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedColorPosition = position;
//                    to refresh adapter :)
                    notifyDataSetChanged();
                }
            });

            if (selectedColorPosition == position) {
                holder.img.setAlpha((float) 0.5);
                finalColorPosition = position;
                holder.right.setVisibility(View.VISIBLE);


            } else {
                holder.img.setAlpha((float) 1);
                holder.right.setVisibility(View.GONE);
            }


        }

        @Override
        public int getItemCount() {
            return colorlist.size();
        }
    }

    public class colorHolder extends RecyclerView.ViewHolder {
        CircleImageView img;
        ImageView right;

        public colorHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.color_img);
            right = itemView.findViewById(R.id.right);

        }
    }


    public class SizeAdapter extends RecyclerView.Adapter<sizeHolder> {
        int selectedSizePosition = -1 ;
        List<String> sizeList;

        public SizeAdapter(List<String> sizeList) {
            this.sizeList = sizeList;
        }

        @NonNull
        @Override
        public sizeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.size_item, parent, false);
            return new sizeHolder(view);
        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void onBindViewHolder(@NonNull sizeHolder holder, @SuppressLint("RecyclerView") int position) {
            String size = sizeList.get(position);
            holder.tv.setText(size);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedSizePosition = position;
//                    to refresh adapter :)
                    notifyDataSetChanged();
                }
            });

            if (selectedSizePosition == position) {
                holder.img.setAlpha((float) 0.5);
                holder.img.setImageResource(R.color.black);
                finalSizePosition = position;
            } else {
                holder.img.setAlpha((float) 1);
                holder.img.setImageResource(R.color.white);
            }

        }


        @Override
        public int getItemCount() {
            return sizeList.size();
        }
    }

    public class sizeHolder extends RecyclerView.ViewHolder {
        TextView tv;
        CircleImageView img;

        public sizeHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.size_tv);
            img = itemView.findViewById(R.id.size_img);
        }
    }


    void setLikes(final Clothes_Items item, final ImageView imageView) {
        Constants.initRef().child("Likes").child(Constants.GETid(OffersDetails.this)).addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(item.getName())) {
                    ColorStateList csl = AppCompatResources.getColorStateList(getApplicationContext(), R.color.gray);
                    imageView.setImageTintList(csl);
                    Constants.initRef().child("Likes").child(Constants.GETid(OffersDetails.this)).child(item.getName()).removeValue();

                } else {
                    ColorStateList csl = AppCompatResources.getColorStateList(getApplicationContext(), R.color.red);
                    imageView.setImageTintList(csl);
                    Constants.initRef().child("Likes").child(Constants.GETid(OffersDetails.this)).child(item.getName()).setValue(item);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    void setLikesColor(Clothes_Items item, final ImageView imageView) {
        Constants.initRef().child("Likes").child(Constants.GETid(OffersDetails.this)).addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.hasChild(item.getName())) {
                    ColorStateList csl = AppCompatResources.getColorStateList(getApplicationContext(), R.color.red);
                    imageView.setImageTintList(csl);
                } else {
                    ColorStateList csl = AppCompatResources.getColorStateList(getApplicationContext(), R.color.gray);
                    imageView.setImageTintList(csl);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    void quantity(boolean isAdd) {

        if (isAdd == true) {
            finalNumberOfPieces++;
        } else {
            finalNumberOfPieces--;
        }

        if (finalNumberOfPieces<=0){
            finalNumberOfPieces=0;
        }else if(finalNumberOfPieces>=10){
            finalNumberOfPieces=10;
        }

        quantity.setText(finalNumberOfPieces + "");

    }


}