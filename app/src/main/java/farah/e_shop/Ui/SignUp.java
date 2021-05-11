package farah.e_shop.Ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import farah.e_shop.MainActivity;
import farah.e_shop.Models.UserModel;
import farah.e_shop.R;
import farah.e_shop.Utiles.Constants;

public class SignUp extends AppCompatActivity {
    EditText name, email, pass1, pass2;
    MaterialRippleLayout signUp, face;
    TextView haveAccount;
    ImageButton eye1, eye2;

    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        initviews();
    }

    private void initviews() {
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        pass1 = findViewById(R.id.pass1);
        pass2 = findViewById(R.id.pass2);
        signUp = findViewById(R.id.signUp);
        face = findViewById(R.id.facebook);
        haveAccount = findViewById(R.id.haveAccount);
        eye1 = findViewById(R.id.eye1);
        eye2 = findViewById(R.id.eye2);

        auth = FirebaseAuth.getInstance();


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name = name.getText().toString();
                String Email = email.getText().toString();
                String Pass1 = pass1.getText().toString();
                String Pass2 = pass2.getText().toString();

                if (Name.isEmpty() || Email.isEmpty() || Pass1.isEmpty()) {
                    Toast.makeText(SignUp.this, "enter valid Data please !!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!Pass2.equals(Pass1)) {
                    Toast.makeText(SignUp.this, "Password is wrong", Toast.LENGTH_SHORT).show();
                    return;
                }

                AuthWithEmail(Name, Email, Pass1);
            }
        });

        haveAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SignUp.this,Login.class));
            }
        });
        eye1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowHidePass1();
            }
        });
        eye2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowHidePass2();
            }
        });


    }



    private void AuthWithEmail(String name, String email, String pass1) {

        auth.createUserWithEmailAndPassword(email, pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    CreateUserDataBase(name, email, task.getResult().getUser().getUid(),null);
                    Toast.makeText(SignUp.this, "Created User Successfully ", Toast.LENGTH_SHORT).show();
                    auth.signInWithEmailAndPassword(email, pass1)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
//                                        to change profile directly :)
                                        Constants.SAVEid(SignUp.this, task.getResult().getUser().getUid());

                                        Toast.makeText(SignUp.this, "Signed in Successfully ", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(SignUp.this, MainActivity.class));

                                    } else {
                                        Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                } else {
                    Toast.makeText(SignUp.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void CreateUserDataBase(String name, String email, String uid, final String image){

        firebaseDatabase =FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        final UserModel userModel = new UserModel(name, email, uid,image);
        databaseReference.child("UserData").child(uid).setValue(userModel)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){
                            databaseReference.child("UserData").addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    Toast.makeText(SignUp.this, "data done", Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(SignUp.this, "there is an account already ", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                });

    }



    private void ShowHidePass1() {
        if(pass1.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
            eye1.setImageResource(R.drawable.ic_blind);
            //Show Password
            pass1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        else{
            eye1.setImageResource(R.drawable.ic_eye);
            //Hide Password
            pass1.setTransformationMethod(PasswordTransformationMethod.getInstance());

        }
    }

    private void ShowHidePass2() {

        if(pass2.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
            eye2.setImageResource(R.drawable.ic_blind);
            //Show Password
            pass2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        else{
            eye2.setImageResource(R.drawable.ic_eye);
            //Hide Password
            pass2.setTransformationMethod(PasswordTransformationMethod.getInstance());

        }

    }



}