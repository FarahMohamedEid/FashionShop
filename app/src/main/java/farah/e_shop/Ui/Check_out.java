package farah.e_shop.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.balysv.materialripple.MaterialRippleLayout;

import farah.e_shop.R;

public class Check_out extends AppCompatActivity {
    MaterialRippleLayout continue_to_payment_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        initViews();
    }

    private void initViews() {
        continue_to_payment_btn = findViewById(R.id.continue_to_payment_btn);
        continue_to_payment_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Check_out.this, Payment.class));
            }
        });
    }

}