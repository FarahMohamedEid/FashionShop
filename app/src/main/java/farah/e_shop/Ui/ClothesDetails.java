package farah.e_shop.Ui;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
import android.graphics.Color;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import farah.e_shop.Models.Clothes_Items;
import farah.e_shop.R;
import farah.e_shop.Utiles.Constants;

public class ClothesDetails extends AppCompatActivity {

    ImageView img ,love ;
    TextView name,price ,plus,subtract,numberOfPieces;
    RatingBar ratingBar;
    MaterialRippleLayout cart;
    RecyclerView sizeRV,colorRV;
    Clothes_Items clothes_items;
    int finalColorPosition ,finalNumberOfPieces,finalSizePosition ;
    int currentNumber = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clothes_details);
        initviews();
    }

    private void initviews() {
        img =findViewById(R.id.clo_img2);
        name =findViewById(R.id.clo_name2);
        price =findViewById(R.id.clo_price2);
        ratingBar =findViewById(R.id.rating2);
        sizeRV =findViewById(R.id.size_RV);
        colorRV =findViewById(R.id.color_RV);
        love =findViewById(R.id.love);
        cart =findViewById(R.id.addToCart);
        plus=findViewById(R.id.btn_more);
        subtract=findViewById(R.id.btn_less);
        numberOfPieces=findViewById(R.id.tv_number);

        clothes_items =(Clothes_Items) getIntent().getSerializableExtra("women");
        img.setImageResource(clothes_items.getImg());
        name.setText(clothes_items.getName());
        price.setText(clothes_items.getPrice()+" $");
        ratingBar.setNumStars(clothes_items.getRate());
        colorRV.setAdapter( new ColorAdapter(clothes_items.getColor()));
        colorRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        getNumberOfPieces();

        sizeRV.setAdapter( new SizeAdapter(clothes_items.getSize()));
        sizeRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        setLikesColor(clothes_items, love);
        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setLikes(clothes_items, love);
            }
        });


        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Constants.initRef().child("Cart").child(Constants.GETid(ClothesDetails.this)).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Constants.initRef().child("Cart").child(Constants.GETid(ClothesDetails.this)).child(clothes_items.getName()).setValue(clothes_items);


                        Constants.initRef().child("Cart").child(Constants.GETid(ClothesDetails.this)).child(clothes_items.getName()).child(String.valueOf(clothes_items.getColor().get(finalColorPosition))).setValue(true);


                        // TODO henaaaa   3ayza a search 3la position el color

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

//                Bundle bundle=new Bundle();
//                bundle.putString("message", "From Activity");
//                //set Fragmentclass Arguments
//                tab3 fragobj=new tab3();
//                fragobj.setArguments(bundle);
//
//
//                getSupportFragmentManager().beginTransaction()
//                        .add(android.R.id.content, new tab3()).commit();
            }
        });

    }

    private void getNumberOfPieces() {
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                currentNumber++;
                numberOfPieces.setText(currentNumber+"");
                finalNumberOfPieces=currentNumber;
            }
        });

        subtract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentNumber !=0)
                currentNumber--;
                numberOfPieces.setText(currentNumber+"");
                finalNumberOfPieces=currentNumber;
            }
        });
    }


    public class ColorAdapter extends RecyclerView.Adapter<colorHolder> {
        int selectedColorPosition = -1 ;// no selection by default
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
                finalColorPosition= position;
            }else
                holder.img.setAlpha((float) 1);


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
        int selectedSizePosition = -1 ;// no selection by default
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
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedSizePosition = position;
//                    to refresh adapter :)
                    notifyDataSetChanged();
                }
            });

            if (selectedSizePosition == position) {
                holder.tv.setTextColor(Color.BLACK);
                finalSizePosition = position ;
            }else
                holder.tv.setTextColor(Color.LTGRAY);
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


    void setLikes(final Clothes_Items item, final ImageView imageView) {

        Constants.initRef().child("Likes").child(Constants.GETid(ClothesDetails.this)).addListenerForSingleValueEvent(new ValueEventListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if (snapshot.hasChild(item.getName()))
                {
                    ColorStateList csl = AppCompatResources.getColorStateList(getApplicationContext(), R.color.gray);
                    imageView.setImageTintList(csl);
                    Constants.initRef().child("Likes").child(Constants.GETid(ClothesDetails.this)).child(item.getName()).removeValue();

                } else
                {
                    ColorStateList csl = AppCompatResources.getColorStateList(getApplicationContext(), R.color.red);
                    imageView.setImageTintList(csl);
                    Constants.initRef().child("Likes").child(Constants.GETid(ClothesDetails.this)).child(item.getName()).setValue(item);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
    }


    void setLikesColor(Clothes_Items item, final ImageView imageView) {
        Constants.initRef().child("Likes").child(Constants.GETid(ClothesDetails.this)).addValueEventListener(new ValueEventListener()
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