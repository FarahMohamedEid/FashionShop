package farah.e_shop.Ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import farah.e_shop.Models.UserModel;
import farah.e_shop.R;
import farah.e_shop.Utiles.Constants;

public class tab4 extends Fragment {
    View v;
    UserModel userModel;
    CircleImageView img;
    TextView name, email, signOut;


    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tab4, container, false);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        name = v.findViewById(R.id.profile_name);
        email = v.findViewById(R.id.profile_email);
        img = v.findViewById(R.id.profile_image_done);
        signOut = v.findViewById(R.id.signOut);


        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();

        databaseReference.child("UserData").child(Constants.GETid(getActivity())).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.getValue(UserModel.class) == null ) {
                    startActivity(new Intent(getContext(), Login.class));
                }
                else {
                    userModel = snapshot.getValue(UserModel.class);
                    name.setText(userModel.getName());
                    email.setText(userModel.getEmail());
                    if (userModel.getImg() == null) {
                        img.setImageResource(R.drawable.ic_user);
                    } else {
                        Picasso.get().load(userModel.getImg()).into(img);
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginManager.getInstance().logOut();
                FirebaseAuth.getInstance().signOut();
                Constants.SAVEid(getActivity(),null);

                startActivity(new Intent(getContext(), Login.class));

                // disallow to backstack
                NavController controller = Navigation.findNavController(view);
                controller.popBackStack(R.id.tab4, true);
            }
        });

    }

}
