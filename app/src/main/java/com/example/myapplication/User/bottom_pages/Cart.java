package com.example.myapplication.User.bottom_pages;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.myapplication.Designer.ItemModel;
import com.example.myapplication.R;
import com.example.myapplication.User.MainScreen;
import com.example.myapplication.User.bottom_pages.Create.Create;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Cart extends Fragment {

    private static Cart instance;

    Button create;
    Button catalog;
    Button add_btn;
    RelativeLayout empty_cart;

    ArrayList<ItemModel> arrayList;
    CartAdapter modelAdapter2;
    GridView mlistViewArticle;
    public FirebaseFirestore db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        instance = this;
        View v=inflater.inflate(R.layout.fragment_cart, container, false);
        create=v.findViewById(R.id.create);
        catalog=v.findViewById(R.id.catalog);
        db= FirebaseFirestore.getInstance();

        mlistViewArticle = v.findViewById(R.id.grwid_orders);

        arrayList = new ArrayList<>();
        modelAdapter2 = new CartAdapter(getActivity(), R.layout.item, arrayList);

        mlistViewArticle.setAdapter(modelAdapter2);
        EventChangeListener();

        return v;
    }
    public void EventChangeListener() {
        FirebaseAuth mAuth;
        FirebaseUser user;
        FirebaseFirestore fStore;
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        user=mAuth.getCurrentUser();
        String user_id=user.getUid();
        db.collection("orders").orderBy("order_term", Query.Direction.ASCENDING).whereEqualTo("status","cart").whereEqualTo("user_id",user_id)
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
    public static Cart GetInstance() {
        return instance;
    }
}