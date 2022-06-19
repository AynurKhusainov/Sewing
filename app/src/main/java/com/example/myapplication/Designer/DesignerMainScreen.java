package com.example.myapplication.Designer;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.Entrance.SignInScreen;
import com.example.myapplication.User.bottom_pages.Create.OrderingScreen;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DesignerMainScreen extends AppCompatActivity {

    Button exit, my_orders;
    public static String PrefsSignUp = "prefs";
    SharedPreferences skip_log_in;
    public ArrayList<ItemModel> arrayList;
    public ItemAdapter modelAdapter;

    public FirebaseFirestore db;
    ImageView img_front;

    GridView mlistViewArticle;

    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseFirestore fStore;

    String myFormat2 = "yyyy-MM-dd HH:mm:SS";
    String myFormat = "MM.dd.yyyy";
    SimpleDateFormat dateFormat2, dateFormat;
    final Calendar myCalendar = Calendar.getInstance();
    Date date_now;
    public String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
        setContentView(R.layout.designer_main_screen);

        dateFormat2 = new SimpleDateFormat(myFormat2, Locale.getDefault());
        dateFormat = new SimpleDateFormat(myFormat, Locale.getDefault());
        date_now = myCalendar.getTime();

        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching data...");
        progressDialog.show();

        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        exit = findViewById(R.id.exit);
        my_orders = findViewById(R.id.my_orders);

        skip_log_in = getSharedPreferences(DesignerMainScreen.PrefsSignUp, 0);//push

        db=FirebaseFirestore.getInstance();

        exit.setOnClickListener(view -> {
            SharedPreferences.Editor editor = skip_log_in.edit();
            editor.putString("rolee", "0").apply();
            startActivity(new Intent(DesignerMainScreen.this, SignInScreen.class));
            finish();
        });
        my_orders.setOnClickListener(view -> {
            startActivity(new Intent(DesignerMainScreen.this, MyOrders.class));
        });

        mlistViewArticle = findViewById(R.id.article_list);
        img_front = findViewById(R.id.img_front);

        arrayList = new ArrayList<ItemModel>();
        modelAdapter = new ItemAdapter(DesignerMainScreen.this, R.layout.item, arrayList);

        mlistViewArticle.setAdapter(modelAdapter);

        EventChangeListener();
    }


    public void EventChangeListener() {
        db.collection("orders").orderBy("order_term", Query.Direction.ASCENDING).whereEqualTo("status","consideration")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {

                            if (progressDialog.isShowing())
                                progressDialog.dismiss();

                            Log.e("Firestore error",error.getMessage());
                            return;
                        }
                        for (DocumentChange dc: value.getDocumentChanges()){
                            ItemModel dataModal = dc.getDocument().toObject(ItemModel.class);
                            if (dc.getType()==DocumentChange.Type.ADDED)
                                dataModal.setDocumentId(dc.getDocument().getId());

                            arrayList.add(dataModal);
                        }
                        modelAdapter.notifyDataSetChanged();
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();

                    }
                });
    }

}