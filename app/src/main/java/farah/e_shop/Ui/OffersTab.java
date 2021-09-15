package farah.e_shop.Ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.List;

import farah.e_shop.Data.Offers;
import farah.e_shop.Models.Clothes_Items;
import farah.e_shop.R;
import farah.e_shop.Utiles.Constants;

public class OffersTab extends Fragment {
    View v;
    RecyclerView RV;
//    AlertDialog alertDialog;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.offers_tab,container,false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RV =v.findViewById(R.id.offer_RV);
        List<Clothes_Items> offers_items = new Offers().SetOffers();
        GetOffers(offers_items);
    }

    @Override
    public void onStart() {
        super.onStart();
        ViewGroup viewGroup = v.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.wait, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialog);
        builder.setView(dialogView);
//        alertDialog = builder.create();
//        alertDialog.show();

    }



    public void  GetOffers(List<Clothes_Items> list) {

        if(list == null){
            Toast.makeText(getContext(), "null", Toast.LENGTH_LONG).show();
        }else {
            RV.setAdapter(new OffersAdapter(list));
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
            RV.setLayoutManager(mLayoutManager);
        }
    }


    public class OffersAdapter extends RecyclerView.Adapter<OffersHolder>{
        List<Clothes_Items> Item ;

        public OffersAdapter(List<Clothes_Items> menItem) {
            this.Item = menItem;
            notifyDataSetChanged();

        }

        @NonNull
        @Override
        public OffersHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.offers_item,parent,false);
            return new OffersHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull OffersHolder holder, int position) {
            Clothes_Items items = Item.get(position);
            String price1 = String.valueOf(items.getOld_price());
            String price2 = String.valueOf(items.getPrice());
            String percent = String.valueOf(items.getPercent());
            holder.price1.setText(price1+" $");
            holder.price2.setText(price2+" $");
            holder.percent.setText(percent+"% OFF");
            holder.name.setText(items.getName());
            Picasso.get()
                    .load(items.getImg())
                    .fit()
                    .into(holder.imageView);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(getContext(),OffersDetails.class);
                    intent.putExtra("offer", items);
                    startActivity(intent);
                }
            });

            if (requireContext() != null){
                setLikesColor(items, holder.love);
            }



            holder.love.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    setLikes(items, holder.love);
                }
            });
        }

        @Override
        public int getItemCount() {
            return Item.size();
        }
    }




    public class OffersHolder extends RecyclerView.ViewHolder{
        ImageView imageView ,love;
        TextView name;
        TextView price1,price2,percent;


        public OffersHolder(@NonNull View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.offer_img);
            name =itemView.findViewById(R.id.offer_name);
            price1 =itemView.findViewById(R.id.offer_price);
            price2 =itemView.findViewById(R.id.offer_price2);
            percent =itemView.findViewById(R.id.offer_percent);
            love =itemView.findViewById(R.id.love_offer);

            price1.setPaintFlags(price1.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        }
    }




    void setLikes(final Clothes_Items items, final ImageView imageView)
    {
        Constants.initRef().child("Likes").child(Constants.GETid(requireActivity())).addListenerForSingleValueEvent(new ValueEventListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if (snapshot.hasChild(items.getName()))
                {
                    ColorStateList csl = AppCompatResources.getColorStateList(requireContext(), R.color.gray);
                    imageView.setImageTintList(csl);

                    Constants.initRef().child("Likes").child(Constants.GETid(requireActivity())).child(items.getName()).removeValue();
                } else
                {
                    ColorStateList csl = AppCompatResources.getColorStateList(requireContext(), R.color.red);
                    imageView.setImageTintList(csl);

                    Constants.initRef().child("Likes").child(Constants.GETid(requireActivity())).child(items.getName()).setValue(items);
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

    void setLikesColor(Clothes_Items items, final ImageView imageView)
    {
        Constants.initRef().child("Likes").child(Constants.GETid(requireActivity())).addValueEventListener(new ValueEventListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if (isAttachedToActivity()){
                if (snapshot.hasChild(items.getName()))
                {
                    ColorStateList csl = AppCompatResources.getColorStateList(requireContext(), R.color.red);
                    imageView.setImageTintList(csl);
                } else
                {
                    ColorStateList csl = AppCompatResources.getColorStateList(requireContext() , R.color.gray);
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
