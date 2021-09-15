package farah.e_shop.Ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.balysv.materialripple.MaterialRippleLayout;

import farah.e_shop.MainActivity;
import farah.e_shop.R;

public class Delivery extends AppCompatActivity {
    MaterialRippleLayout continue_to_Shopping_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);

        continue_to_Shopping_btn=findViewById(R.id.continue_to_Shopping_btn);
        continue_to_Shopping_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Delivery.this, MainActivity.class));
            }
        });
    }
}