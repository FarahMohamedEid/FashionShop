package farah.e_shop.Ui;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import com.balysv.materialripple.MaterialRippleLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import farah.e_shop.Models.Clothes_Items;
import farah.e_shop.R;
import farah.e_shop.Utiles.Constants;

public class tab2 extends Fragment implements Serializable {
    View v;
    RecyclerView RV;
    List<Clothes_Items> clothesItems = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tab2, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initviews();
    }

    private void initviews() {
        RV = v.findViewById(R.id.fav_RV);
        getFav();

    }

    private void getFav() {

        Constants.initRef().child("Likes").child(Constants.GETid(requireActivity())).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (clothesItems != null) {
                    clothesItems.clear();

                    for (DataSnapshot d : snapshot.getChildren()) {
                        LinearLayout linearLayout =v.findViewById(R.id.no_fav_linear);
                        linearLayout.setVisibility(View.GONE);
                        Clothes_Items cloth = d.getValue(Clothes_Items.class);
                        clothesItems.add(cloth);


                    }
                }
                    RV.setAdapter(new FavAdapter(clothesItems));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }


    public class FavAdapter extends RecyclerView.Adapter<FavHolder> {
        List<Clothes_Items> items;

        public FavAdapter(List<Clothes_Items> womenItem) {
            this.items = womenItem;
            notifyDataSetChanged();

        }

        @NonNull
        @Override
        public FavHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.fav_item, parent, false);
            return new FavHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull FavHolder holder, int position) {
            Clothes_Items loved = items.get(position);

            if(loved.getPercent()==0){
                holder.offerBackground.setVisibility(View.GONE);
                holder.old_price.setVisibility(View.GONE);
                String price = String.valueOf(loved.getPrice());
                holder.name.setText(loved.getName());
                holder.price.setText(price + " $");
                Picasso.get()
                        .load(loved.getImg())
                        .fit()
                        .into(holder.imageView);

                holder.more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent =new Intent(getContext(),OffersDetails.class);
                        intent.putExtra("offer", loved);
                        startActivity(intent);
                    }
                });
            } else {
                String old_price = String.valueOf(loved.getOld_price());
                String price = String.valueOf(loved.getPrice());
                holder.offerBackground.setText(loved.getPercent()+"% OFF");
                holder.name.setText(loved.getName());
                holder.price.setText(old_price+" $");
                holder.old_price.setText(price + " $");
                Picasso.get()
                        .load(loved.getImg())
                        .fit()
                        .into(holder.imageView);

                holder.more.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent =new Intent(getContext(),OffersDetails.class);
                        intent.putExtra("offer", loved);
                        startActivity(intent);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            if (items.size() == 0) {
                return 0;
            } else {
                return items.size();
            }

        }
    }


    public class FavHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView price;
        TextView old_price;
        TextView offerBackground;
        MaterialRippleLayout more;


        public FavHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.fav_img);
            name = itemView.findViewById(R.id.fav_name);
            price = itemView.findViewById(R.id.fav_price);
            old_price = itemView.findViewById(R.id.old_price);
            more= itemView.findViewById(R.id.fav_more);
            offerBackground= itemView.findViewById(R.id.offer_background);


            old_price.setPaintFlags(old_price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);


        }
    }


}
