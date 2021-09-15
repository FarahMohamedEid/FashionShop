package farah.e_shop.Ui;

import static com.facebook.FacebookSdk.setAutoLogAppEventsEnabled;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.balysv.materialripple.MaterialRippleLayout;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.LoginStatusCallback;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;

import farah.e_shop.MainActivity;
import farah.e_shop.Models.UserModel;
import farah.e_shop.R;
import farah.e_shop.Utiles.Constants;

import com.facebook.FacebookSdk;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.Arrays;
import java.util.Objects;

public class Login extends AppCompatActivity {
    TextView goToSignUp,forget;
    EditText email,password;
    MaterialRippleLayout login ,fb ;
    FirebaseAuth auth;
    ImageButton eye;

    CallbackManager mCallbackManager;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        facebook
        FacebookSdk.sdkInitialize(Login.this);

        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();

        firebaseStorage=FirebaseStorage.getInstance();
        storageReference=firebaseStorage.getReference();

        goToSignUp = findViewById(R.id.dontHave);
        forget = findViewById(R.id.forget);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        eye = findViewById(R.id.eye);


        auth= FirebaseAuth.getInstance();



        goToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,SignUp.class));
            }
        });

        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,ForgetPassword.class));
            }
        });

        eye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShowHidePass();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString();
                String Pass = password.getText().toString();

                if (Email.isEmpty() || Pass.isEmpty()) {
                    Toast.makeText(Login.this, "Complete your data please", Toast.LENGTH_LONG).show();
                    return;
                }

                SignWithEmail(Email, Pass);

            }
        });



        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        fb=findViewById(R.id.fb);
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logInWithReadPermissions(Login.this, Arrays.asList("email", "public_profile"));
                LoginManager.getInstance().registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        handleFacebookAccessToken(loginResult.getAccessToken());
                        setAutoLogAppEventsEnabled(true);

                    }

                    @Override
                    public void onCancel() {

                        Toast.makeText(Login.this, "cancel", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(Login.this, error+"", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void handleFacebookAccessToken(AccessToken token) {
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = auth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Login.this, "Authentication failed."+task.getException(),
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }


    private void updateUI(FirebaseUser user) {

        if (user == null)
            Toast.makeText(this, "null", Toast.LENGTH_SHORT).show();

        String name = Objects.requireNonNull(user).getDisplayName();
        String email = user.getEmail();
        String id = user.getUid();
        String img = Objects.requireNonNull(user.getPhotoUrl()).toString();

        CreateUserDataBase(name,email,id, img);
        Constants.SAVEid(Login.this,id);
        startActivity(new Intent(Login.this, MainActivity.class));

    }





    private void SignWithEmail(String email, String pass) {

        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Constants.SAVEid(Login.this, Objects.requireNonNull(Objects.requireNonNull(task.getResult()).getUser()).getUid());
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }else {
                    Toast.makeText(Login.this, Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void ShowHidePass() {
        if(password.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
            eye.setImageResource(R.drawable.ic_blind);
            //Show Password
            password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
        }
        else{
            eye.setImageResource(R.drawable.ic_eye);
            //Hide Password
            password.setTransformationMethod(PasswordTransformationMethod.getInstance());

        }

    }



    private void CreateUserDataBase(String name, String email, String uid, final String image){

        firebaseDatabase = FirebaseDatabase.getInstance();
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

                                    Toast.makeText(Login.this, "data done", Toast.LENGTH_LONG).show();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Toast.makeText(Login.this, "there is an account already ", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    }
                });

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        startActivity(new Intent(Login.this,MainActivity.class));
    }


}