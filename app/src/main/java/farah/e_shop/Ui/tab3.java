package farah.e_shop.Ui;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import farah.e_shop.Models.Clothes_Items;
import farah.e_shop.R;
import farah.e_shop.Utiles.Constants;

public class tab3 extends Fragment {
    View v;
    RecyclerView RV;
    List<Clothes_Items> clothesItems = new ArrayList<>();
    LinearLayout noCartLinear;
    LinearLayout total_linear;
    int total;
    TextView total_tv;
    MaterialRippleLayout continue_btn;

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
        noCartLinear =v.findViewById(R.id.no_cart_linear);
        total_linear =v.findViewById(R.id.total_linear);
        total_tv =v.findViewById(R.id.total_tv);
        continue_btn =v.findViewById(R.id.continue_btn);
        getTotal();
        getCart();

        continue_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Check_out.class));
            }
        });

    }

    private void getCart() {
        Constants.initRef().child("Cart").child(Constants.GETid(requireActivity())).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (clothesItems != null) {
                    clothesItems.clear();
                    for (DataSnapshot d : snapshot.getChildren()) {
                        noCartLinear.setVisibility(View.GONE);
                        total_linear.setVisibility(View.VISIBLE);
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
            View view = getLayoutInflater().inflate(R.layout.cart_item, parent, false);
            return new CartHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CartHolder holder, int position) {
            Clothes_Items cart = items.get(position);

            holder.selectedSize.setText(cart.getSelectedSize());
            int price = cart.getPrice()* cart.getSelectedQuantity();
            holder.name.setText(cart.getName());
            holder.price.setText(price + " $");
            Picasso.get()
                    .load(cart.getImg())
                    .fit()
                    .into(holder.imageView);
            holder.selectedColor.setImageResource(cart.getSelectedColor());
            holder.num_of_pieces.setText("x "+cart.getSelectedQuantity());

            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Constants.initRef().child("Cart").child(Constants.GETid(getActivity())).child(cart.getName()).removeValue();
                }
            });

            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(getContext(),OffersDetails.class);
                    intent.putExtra("offer", cart);
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
        ImageView delete;
        TextView name;
        TextView price;
        TextView selectedSize;
        TextView num_of_pieces;
        MaterialRippleLayout edit;
        CircleImageView selectedColor;



        public CartHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cart_img);
            delete = itemView.findViewById(R.id.delete);
            name = itemView.findViewById(R.id.cart_name);
            price = itemView.findViewById(R.id.cart_price);
            edit= itemView.findViewById(R.id.cart_edit);
            selectedSize= itemView.findViewById(R.id.cart_selected_size);
            selectedColor= itemView.findViewById(R.id.cart_selected_color);
            num_of_pieces= itemView.findViewById(R.id.num_of_pieces);
        }
    }



    private void getTotal(){
        Constants.initRef().child("Cart").child(Constants.GETid(requireActivity())).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                total = 0;
                for (DataSnapshot d : snapshot.getChildren()) {
                    Clothes_Items cloth = d.getValue(Clothes_Items.class);
                    int price = (cloth.getPrice()*cloth.getSelectedQuantity());
                    total=total+price;
                }
                total_tv.setText(total+" $");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        }



}
