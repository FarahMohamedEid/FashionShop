package farah.e_shop.Ui;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.res.ColorStateList;
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

import farah.e_shop.Data.KidsClothes;
import farah.e_shop.Data.MenClothes;
import farah.e_shop.Models.Clothes_Items;
import farah.e_shop.R;
import farah.e_shop.Utiles.Constants;

public class KidsTab extends Fragment {
    View v;
    RecyclerView RV;
//    AlertDialog alertDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.kids_tab,container,false);
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RV =v.findViewById(R.id.kids_RV);
        List<Clothes_Items> kids = new KidsClothes().SetKidsClothes();
        GetMenClothes(kids);

        ViewGroup viewGroup = v.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.wait, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialog);
        builder.setView(dialogView);
//        alertDialog = builder.create();
//        alertDialog.show();

    }

    @Override
    public void onStart() {
        super.onStart();
        Constants.GETid(requireActivity());
    }

    public void  GetMenClothes(List<Clothes_Items> list) {

        if(list == null){
            Toast.makeText(getContext(), "null", Toast.LENGTH_LONG).show();
        }else {
            RV.setAdapter(new KidsAdapter(list));
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
            RV.setLayoutManager(mLayoutManager);
        }
    }


    public class KidsAdapter extends RecyclerView.Adapter<KidsHolder>{
        List<Clothes_Items> kidsItem ;

        public KidsAdapter(List<Clothes_Items> kidsItem) {
            this.kidsItem = kidsItem;
            notifyDataSetChanged();

        }

        @NonNull
        @Override
        public KidsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.cloths_item,parent,false);
            return new KidsHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull KidsHolder holder, int position) {
            Clothes_Items kid = kidsItem.get(position);
            String price = String.valueOf(kid.getPrice());
            holder.name.setText(kid.getName());
            holder.price.setText(price+" $");
            Picasso.get()
                    .load(kid.getImg())
                    .fit()
                    .into(holder.imageView);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(getContext(),ClothesDetails.class);
                    intent.putExtra("women", kid);
                    startActivity(intent);
                }
            });


            setLikesColor(kid, holder.love);


            holder.love.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    setLikes(kid, holder.love);
                }
            });
        }

        @Override
        public int getItemCount() {
            return kidsItem.size();
        }
    }


    public class KidsHolder extends RecyclerView.ViewHolder{
        ImageView imageView ,love;
        TextView name;
        TextView price;


        public KidsHolder(@NonNull View itemView) {
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
//                    SendToFav(item);
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
//                    alertDialog.dismiss();

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
