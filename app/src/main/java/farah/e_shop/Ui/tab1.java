package farah.e_shop.Ui;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.balysv.materialripple.MaterialRippleLayout;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import farah.e_shop.Data.WomenClothes;
import farah.e_shop.MainActivity;
import farah.e_shop.Models.Categories;
import farah.e_shop.Models.Clothes_Items;
import farah.e_shop.R;

import static farah.e_shop.R.layout.filter;

public class tab1 extends Fragment {

    LinearLayout linearLayout;
    View v;
    ViewFlipper viewFlipper;
    RecyclerView RV;

    TextView categoryLabel;
    ImageView filter;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tab1, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        InItViews();
        SetCategory();
        OpenCategory(0);

    }


    private void InItViews() {

        categoryLabel = v.findViewById(R.id.categoryLable);
        RV = v.findViewById(R.id.Categories_RV);
        linearLayout = v.findViewById(R.id.tab1_linear);
        filter = v.findViewById(R.id.filter);

//        to put images on slider
        viewFlipper = v.findViewById(R.id.flipper);
        int Images[] = {R.drawable.i, R.drawable.ii, R.drawable.d, R.drawable.k, R.drawable.dd, R.drawable.ddd};
        for (int images : Images) {
            FlipImages(images);
        }


        //Load animation
        Animation slide_up = AnimationUtils.loadAnimation(getContext(),
                R.anim.up);
        // Start animation
        linearLayout.startAnimation(slide_up);




    }


    // to flip images on slider
    private void FlipImages(int images) {

        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(images);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        viewFlipper.addView(imageView);
        viewFlipper.setFlipInterval(4000); // 4 sec
        viewFlipper.setAutoStart(true);
        viewFlipper.isAutoStart();
        viewFlipper.startFlipping();
        viewFlipper.setInAnimation(getActivity(), android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(getActivity(), android.R.anim.slide_out_right);
    }


    private void SetCategory() {

        List<Categories> category = new ArrayList<>();
        category.add(new Categories(R.color.white, "Offers"));
        category.add(new Categories(R.color.white, "Women"));
        category.add(new Categories(R.color.white, "Men"));
        category.add(new Categories(R.color.white, "Kids"));
        category.add(new Categories(R.color.white, "Acessories"));

        RV.setAdapter(new CategoriesAdapter(category));

//        to make recycle view Horizontal :)
        RV.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

    }


    public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesHolder> {

        int selectedPosition = -1 ;// no selection by default
        List<Categories> categoriesList;


        public CategoriesAdapter(List<Categories> categoriesList) {
            this.categoriesList = categoriesList;
        }

        @NonNull
        @Override
        public CategoriesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = getLayoutInflater().inflate(R.layout.categories_item, parent, false);
            return new CategoriesHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CategoriesHolder holder, @SuppressLint("RecyclerView") int position) {
            Categories CurrentCategory = categoriesList.get(position);
            holder.textView.setText(CurrentCategory.getCategoryName());
            Picasso.get()
                    .load(CurrentCategory.getBackGround())
                    .fit()
                    .into(holder.imageView);

            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    OpenCategory(position);
                    selectedPosition = position;

//                    to refresh adapter :)
                    notifyDataSetChanged();
                }
            });


            if (selectedPosition == position) {
                holder.imageView.setImageResource(R.drawable.radio_checked);
                holder.textView.setTextColor(Color.WHITE);
                holder.seeall.setTextColor(Color.WHITE);

            } else {
                holder.imageView.setImageResource(R.drawable.radio_normal);
                holder.textView.setTextColor(Color.BLACK);
                holder.seeall.setTextColor(Color.BLACK);
            }

        }


        @Override
        public int getItemCount() {
            return categoriesList.size();
        }
    }

    public class CategoriesHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        TextView seeall;
        CardView cardView;


        public CategoriesHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.backGround);
            textView = itemView.findViewById(R.id.categoryName);
            seeall = itemView.findViewById(R.id.seeAll_text);
            cardView = itemView.findViewById(R.id.card_category);


        }
    }


    private void OpenCategory(int position) {

        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, new OffersTab());

        switch (position) {
            case 0:
                ft.replace(R.id.frameLayout, new OffersTab());
                categoryLabel.setText(" Today Offers");
                filter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ClickOnFilter(position);
                    }
                });
                break;
            case 1:
                ft.replace(R.id.frameLayout, new WomenTab());
                categoryLabel.setText("Women's clothes");
                filter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ClickOnFilter(position);
                    }
                });
                break;
            case 2:
                ft.replace(R.id.frameLayout, new MenTab());
                categoryLabel.setText("Men's clothes");
                filter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ClickOnFilter(position);
                    }
                });
                break;
            case 3:
                ft.replace(R.id.frameLayout, new KidsTab());
                categoryLabel.setText("Kid's clothes");
                filter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ClickOnFilter(position);
                    }
                });
                break;
            case 4:
                ft.replace(R.id.frameLayout, new AcessoriesTab());
                categoryLabel.setText("Accessories");
                filter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ClickOnFilter(position);
                    }
                });
                break;
        }
        ft.commit();
    }


    private void ClickOnFilter(int position) {
//        to create custom dialog :)
        ViewGroup viewGroup = v.findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(getContext()).inflate(R.layout.filter, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialog);
        builder.setView(dialogView);
        AlertDialog alertDialog = builder.create();

        TextView cancel = dialogView.findViewById(R.id.cancel_filter);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        TextView confirm = dialogView.findViewById(R.id.confirm_filter);
        RadioGroup price = dialogView.findViewById(R.id.price);
        RadioGroup size = dialogView.findViewById(R.id.size);
        RadioGroup sort = dialogView.findViewById(R.id.sort);


        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              to get value from radioButtons :)
                int selectedPriceGroup = price.getCheckedRadioButtonId();
                RadioButton selectedPrice = price.findViewById(selectedPriceGroup);

                int selectedSizeGroup = size.getCheckedRadioButtonId();
                RadioButton selectedSize = size.findViewById(selectedSizeGroup);

                int selectedSortGroup = sort.getCheckedRadioButtonId();
                RadioButton selectedSort = sort.findViewById(selectedSortGroup);

                if (selectedPrice == null || selectedSize == null || selectedSort == null) {
                    Toast.makeText(getContext(), "please complete filter", Toast.LENGTH_LONG).show();
                    return;
                }


//                to get list of clothes to sort it :)
//                List<Clothes_Items> women = new WomenClothes().SetWomenClothes();
//
//                if (selectedPriceGroup == R.id.low) {
//
////                    price from low to high :)
//                    Collections.sort(women, new Comparator<Clothes_Items>() {
//                        @Override
//                        public int compare(Clothes_Items c1, Clothes_Items c2) {
//                            if (c1.getPrice() == c2.getPrice())
//                                return 0;
//                            else if (c2.getPrice() < c1.getPrice()) {
//                                return 1;
//                            } else
//                                return -1;
//                        }
//                    });
//                    new WomenTab().GetWomenClothes(women);
//                    alertDialog.dismiss();
//
//                } else if (selectedPriceGroup == R.id.high) {
//
//                    //        price from high to low :)
//                    Collections.sort(women, new Comparator<Clothes_Items>() {
//                        @Override
//                        public int compare(Clothes_Items c1, Clothes_Items c2) {
//                            if (c1.getPrice() == c2.getPrice())
//                                return 0;
//                            else if (c2.getPrice() > c1.getPrice()) {
//                                return 1;
//                            } else
//                                return -1;
//                        }
//                    });
//                    new WomenTab().GetWomenClothes(women);
//                    alertDialog.dismiss();
//                }


            }
        });



        alertDialog.show();

    }



}
