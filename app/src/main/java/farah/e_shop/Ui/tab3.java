package farah.e_shop.Ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.balysv.materialripple.MaterialRippleLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import farah.e_shop.Models.Clothes_Items;
import farah.e_shop.R;
import farah.e_shop.Utiles.Constants;

public class tab3 extends Fragment {
    View v;
    RecyclerView RV;
    List<Clothes_Items> clothesItems = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.tab3,container,false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RV = v.findViewById(R.id.cart_RV);
        getCart();
    }

// TODO lsa m4 3arfa agebha 3l4an mb3ota 8lt
    private void getCart() {
        Constants.initRef().child("Cart").child(Constants.GETid(requireActivity())).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (clothesItems != null) {
                    clothesItems.clear();

                    for (DataSnapshot d : snapshot.getChildren()) {
                        LinearLayout linearLayout =v.findViewById(R.id.no_cart_linear);
                        linearLayout.setVisibility(View.GONE);
                        Clothes_Items cloth = d.getValue(Clothes_Items.class);
                        clothesItems.add(cloth);


                    }
                }
                RV.setAdapter(new CartAdapter(clothesItems));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }


    public class CartAdapter extends RecyclerView.Adapter<CartHolder> {
        List<Clothes_Items> items;

        public CartAdapter(List<Clothes_Items> womenItem) {
            this.items = womenItem;
            notifyDataSetChanged();

        }

        @NonNull
        @Override
        public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.fav_item, parent, false);
            return new CartHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CartHolder holder, int position) {
            Clothes_Items loved = items.get(position);

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
                    Intent intent =new Intent(getContext(),ClothesDetails.class);
                    intent.putExtra("women", loved);
                    startActivity(intent);
                }
            });


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


    public class CartHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView price;
        MaterialRippleLayout more;


        public CartHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.fav_img);
            name = itemView.findViewById(R.id.fav_name);
            price = itemView.findViewById(R.id.fav_price);
            more= itemView.findViewById(R.id.fav_more);

        }
    }






}
