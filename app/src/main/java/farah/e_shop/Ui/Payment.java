package farah.e_shop.Ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.balysv.materialripple.MaterialRippleLayout;

import farah.e_shop.R;

public class Payment extends AppCompatActivity {
    int selectedPosition = -1;
    LinearLayout creditBack ,cashBack;
    ImageView credit_img,cash_img;
    TextView credit_tv ,cash_tv;
    MaterialRippleLayout continue_to_delivery_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        initviews();
        OpenCategory(2);
    }

    private void initviews() {
        creditBack=findViewById(R.id.credit_backGround);
        cashBack=findViewById(R.id.cash_backGround);
        credit_img=findViewById(R.id.credit_img);
        cash_img=findViewById(R.id.cash_img);
        credit_tv=findViewById(R.id.credit_tv);
        cash_tv=findViewById(R.id.cash_tv);
        continue_to_delivery_btn = findViewById(R.id.continue_to_delivery_btn);

        continue_to_delivery_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Payment.this, Delivery.class));
            }
        });

        creditBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPosition = 0;
                OpenCategory(0);
            }
        });

        cashBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedPosition = 1;
                OpenCategory(1);
            }
        });
    }


    private void OpenCategory(int position) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.payment_frameLayout, new Credit());

        switch (position) {
            case 0:
                ft.replace(R.id.payment_frameLayout, new Credit());
                selectedPosition=position;
                break;
            case 1:
                ft.replace(R.id.payment_frameLayout, new Cash());
                selectedPosition=position;
                break;
            case 2:
                ft.replace(R.id.payment_frameLayout, new ChoosePaymentWay());
                selectedPosition=position;
                break;
        }
        ft.commit();

        if (selectedPosition == 0){
            creditBack.setBackgroundColor(Color.BLACK);
            credit_img.setImageResource(R.drawable.ic_credit_card_payment);
            credit_tv.setTextColor(Color.WHITE);

            cashBack.setBackgroundColor(Color.WHITE);
            cash_img.setImageResource(R.drawable.ic_mony_black);
            cash_tv.setTextColor(Color.BLACK);

        }else if (selectedPosition == 1){
            cashBack.setBackgroundColor(Color.BLACK);
            cash_img.setImageResource(R.drawable.ic_money);
            cash_tv.setTextColor(Color.WHITE);

            creditBack.setBackgroundColor(Color.WHITE);
            credit_img.setImageResource(R.drawable.ic_credit_card_payment_black);
            credit_tv.setTextColor(Color.BLACK);
        }
    }

}