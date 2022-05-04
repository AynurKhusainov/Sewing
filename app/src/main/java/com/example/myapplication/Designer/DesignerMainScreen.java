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
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.R;
import com.example.myapplication.Entrance.SignInScreen;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DesignerMainScreen extends AppCompatActivity {

    Button exit, my_orders;
    public static String PrefsSignUp = "prefs";
    SharedPreferences skip_log_in;
    ArrayList<ItemModel> arrayList;
    ItemAdapter modelAdapter,adapter;

    FirebaseFirestore db;

    GridView mlistViewArticle;
    String header,desc,type,price;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));
        setContentView(R.layout.designer_main_screen);

        progressDialog=new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching data...");
        progressDialog.show();

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

        arrayList = new ArrayList<>();
        modelAdapter = new ItemAdapter(DesignerMainScreen.this, R.layout.item, arrayList);

        EventChangeListener();

        mlistViewArticle.setAdapter(modelAdapter);

        mlistViewArticle.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final int etem=i;

                new AlertDialog.Builder(DesignerMainScreen.this)
                        .setMessage("Вы хотите выполнить этот заказ?")
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                SharedPreferences.Editor editor = skip_log_in.edit();
                                editor.putString("adding_in_mmy_orders", "1").apply();

                                arrayList.remove(etem);
                                modelAdapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Нет", null)
                        .show();

                return true;
            }
        });
        mlistViewArticle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                header = arrayList.get(i).getHeader();
                desc = arrayList.get(i).getDescription();
                type = arrayList.get(i).getType();
                price = arrayList.get(i).getType();

                Intent intent = new Intent(DesignerMainScreen.this, ItemDetailActivity.class);

                intent.putExtra("Header", header);
                intent.putExtra("Description", desc);
                intent.putExtra("Type", type);
                intent.putExtra("Price", price);
                startActivity(intent);
            }
        });
    }

    private void EventChangeListener() {
        db.collection("orders").orderBy("header", Query.Direction.ASCENDING)
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
                            if (dc.getType()==DocumentChange.Type.ADDED)
                                arrayList.add(dc.getDocument().toObject(ItemModel.class));
                        }
                        modelAdapter.notifyDataSetChanged();
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();

                    }
                });
    }
}