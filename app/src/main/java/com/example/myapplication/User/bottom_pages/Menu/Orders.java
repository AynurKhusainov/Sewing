package com.example.myapplication.User.bottom_pages.Menu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.myapplication.Designer.ItemModel;
import com.example.myapplication.R;
import com.example.myapplication.User.bottom_pages.CartAdapter;
import com.example.myapplication.User.bottom_pages.CatalogAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Orders extends AppCompatActivity {

    ArrayList<ItemModel> arrayList;
    OrdersAdapter modelAdapter2;

    GridView mlistViewArticle;
    public FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.orders);
        getWindow().setNavigationBarColor(getResources().getColor(R.color.white));

        db= FirebaseFirestore.getInstance();

        mlistViewArticle = findViewById(R.id.grwid_orders);

        arrayList = new ArrayList<>();
        modelAdapter2 = new OrdersAdapter(Orders.this, R.layout.item, arrayList);

        mlistViewArticle.setAdapter(modelAdapter2);
        EventChangeListener();


    }
    public void EventChangeListener() {
        FirebaseAuth mAuth;
        FirebaseUser user;
        FirebaseFirestore fStore;
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        user=mAuth.getCurrentUser();
        String user_id=user.getUid();
        db.collection("orders").orderBy("order_term", Query.Direction.ASCENDING).whereEqualTo("user_id",user_id)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                        if (error != null) {

                            Log.e("Firestore error",error.getMessage());
                            return;
                        }
                        for (DocumentChange dc: value.getDocumentChanges()){
                            ItemModel dataModal = dc.getDocument().toObject(ItemModel.class);
                            if (dc.getType()==DocumentChange.Type.ADDED)
                                dataModal.setDocumentId(dc.getDocument().getId());

                            arrayList.add(dataModal);
                        }
                        modelAdapter2.notifyDataSetChanged();

                    }
                });
    }
}