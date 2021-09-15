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

import farah.e_shop.Data.MenClothes;
import farah.e_shop.Data.WomenClothes;
import farah.e_shop.Models.Clothes_Items;
import farah.e_shop.R;
import farah.e_shop.Utiles.Constants;

public class MenTab extends Fragment {
    View v;
    RecyclerView RV;
//    AlertDialog alertDialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.mens_tab,container,false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        RV =v.findViewById(R.id.men_RV);
        List<Clothes_Items> men = new MenClothes().SetMenClothes();
        GetMenClothes(men);

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

        Constants.GETid(requireActivity());
    }

    public void  GetMenClothes(List<Clothes_Items> list) {

        if(list == null){
            Toast.makeText(getContext(), "null", Toast.LENGTH_LONG).show();
        }else {
            RV.setAdapter(new MenAdapter(list));
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 2);
            RV.setLayoutManager(mLayoutManager);
        }
    }


    public class MenAdapter extends RecyclerView.Adapter<MenHolder>{
        List<Clothes_Items> menItem ;

        public MenAdapter(List<Clothes_Items> menItem) {
            this.menItem = menItem;
            notifyDataSetChanged();

        }

        @NonNull
        @Override
        public MenHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.cloths_item,parent,false);
            return new MenHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MenHolder holder, int position) {
            Clothes_Items Men = menItem.get(position);
            String price = String.valueOf(Men.getPrice());
            holder.name.setText(Men.getName());
            holder.price.setText(price+" $");
            Picasso.get()
                    .load(Men.getImg())
                    .fit()
                    .into(holder.imageView);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent =new Intent(getContext(),OffersDetails.class);
                    intent.putExtra("offer", Men);
                    startActivity(intent);
                }
            });


            setLikesColor(Men, holder.love);

            holder.love.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    setLikes(Men, holder.love);
                }
            });
        }

        @Override
        public int getItemCount() {
            return menItem.size();
        }
    }



    public class MenHolder extends RecyclerView.ViewHolder{
        ImageView imageView ,love;
        TextView name;
        TextView price;


        public MenHolder(@NonNull View itemView) {
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


    void setLikesColor(Clothes_Items item, final ImageView imageView) {
        Constants.initRef().child("Likes").child(Constants.GETid(requireActivity())).addValueEventListener(new ValueEventListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot)
            {
                if (isAttachedToActivity()) {
                    if (snapshot.hasChild(item.getName()))
                    {
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
