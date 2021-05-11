package farah.e_shop.Utiles;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Constants {

    private static SharedPreferences sharedPreferences;
    private static DatabaseReference databaseReference;

    public static DatabaseReference initRef(){

        if (databaseReference == null){
            databaseReference= FirebaseDatabase.getInstance().getReference();
        }
        return databaseReference;
    }

    public static void SAVEid(Activity activity, String id){
        sharedPreferences=activity.getSharedPreferences("ID", Context.MODE_PRIVATE);

        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("uid",id);
        editor.apply();
    }

    public static String GETid(Activity activity){
        sharedPreferences=activity.getSharedPreferences("ID",Context.MODE_PRIVATE);
        return sharedPreferences.getString("uid","empty");

    }
}
