package com.example.myapplication.Designer;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.myapplication.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class AllMyOrders extends Fragment {

    private static AllMyOrders instance;

    ArrayList<ItemModel> arrayList;
    AllOrdersAdapter modelAdapter2;

    GridView mlistViewArticle;
    public FirebaseFirestore db;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.all_my_orders, container, false);

        instance = this;
        db= FirebaseFirestore.getInstance();

        mlistViewArticle = v.findViewById(R.id.grwid_orders);

        arrayList = new ArrayList<>();
        modelAdapter2 = new AllOrdersAdapter(getActivity(), R.layout.item, arrayList);

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
        db.collection("orders").orderBy("order_term", Query.Direction.ASCENDING).whereEqualTo("sewer",user_id)
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
    public static AllMyOrders GetInstance() {
        return instance;
    }

}