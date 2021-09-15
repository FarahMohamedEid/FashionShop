package farah.e_shop.Ui;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


import farah.e_shop.Data.WomenClothes;
import farah.e_shop.Models.Clothes_Items;
import farah.e_shop.R;
import farah.e_shop.Utiles.Constants;

public class WomenTab extends Fragment implements Serializable {
    View v;
    RecyclerView RV;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.women_tab,container,false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RV =v.findViewById(R.id.Women_RV);
        List<Clothes_Items> women = new WomenClothes().SetWomenClothes();
        GetWomenClothes(women);

    }

    @Override
    public void onStart() {
        super.onStart();
        Constants.GETid(requireActivity());
    }

    public void  GetWomenClothes(List<Clothes_Items> list) {

        if(list == null){
            Toast.makeText(getContext(), "null", Toast.LENGTH_LONG).show();
        }else {

            RV.setAdapter(new WomenAdapter(list));
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
            RV.setLayoutManager(mLayoutManager);
        }
    }


    public class WomenAdapter extends RecyclerView.Adapter<WomenHolder>{
        List<Clothes_Items> womenItem ;

        public WomenAdapter(List<Clothes_Items> womenItem) {
            this.womenItem = womenItem;
            notifyDataSetChanged();

        }

        @NonNull
        @Override
        public WomenHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.cloths_item,parent,false);
            return new WomenHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull WomenHolder holder, int position) {
            Clothes_Items Women = womenItem.get(position);
            String price = String.valueOf(Women.getPrice());
            holder.name.setText(Women.getName());
            holder.price.setText(price+" $");
            Picasso.get()
                    .load(Women.getImg())
                    .fit()
                    .into(holder.imageView);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(getContext(),OffersDetails.class);
                    intent.putExtra("offer", Women);
                    startActivity(intent);
                }
            });


            setLikesColor(Women, holder.love);

            holder.love.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    setLikes(Women, holder.love);
                }
            });
        }

        @Override
        public int getItemCount() {
            return womenItem.size();
        }
    }



    public class WomenHolder extends RecyclerView.ViewHolder{
        ImageView imageView ,love;
        TextView name;
        TextView price;


        public WomenHolder(@NonNull View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.clo_img);
            name =itemView.findViewById(R.id.clo_name);
            price =itemView.findViewById(R.id.clo_price);
            love =itemView.findViewById(R.id.love);
        }
    }




    void setLikes(final Clothes_Items item, final ImageView imageView) {

        Constants.initRef().child("Likes").child(Constants.GETid(requireActivity())).addListenerForSingleValueEvent(new ValueEventListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if (snapshot.hasChild(item.getName()))
                {
                    ColorStateList csl = AppCompatResources.getColorStateList(requireContext(), R.color.gray);
                    imageView.setImageTintList(csl);
                    Constants.initRef().child("Likes").child(Constants.GETid(requireActivity())).child(item.getName()).removeValue();
                } else
                {
                    ColorStateList csl = AppCompatResources.getColorStateList(requireContext(), R.color.red);
                    imageView.setImageTintList(csl);
                    Constants.initRef().child("Likes").child(Constants.GETid(requireActivity())).child(item.getName()).setValue(item);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }
        });
    }


    public boolean isAttachedToActivity() {
        boolean attached = isVisible() && getActivity() != null;
        return attached;
    }

// updated from farah
    void setLikesColor(Clothes_Items item, final ImageView imageView) {

        Constants.initRef().child("Likes").child(Constants.GETid(requireActivity())).addValueEventListener(new ValueEventListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if (isAttachedToActivity()) {
                    if (snapshot.hasChild(item.getName())) {
                        ColorStateList csl = AppCompatResources.getColorStateList(requireContext(), R.color.red);
                        imageView.setImageTintList(csl);
                    } else {
                        ColorStateList csl = AppCompatResources.getColorStateList(requireContext(), R.color.gray);
                        imageView.setImageTintList(csl);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error)
            {

            }


        });


    }




}


