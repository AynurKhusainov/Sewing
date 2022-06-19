package com.example.myapplication.Designer;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyOrderNow extends ArrayAdapter<ItemModel> {

    private Context mContext;
    private int mResource;

    String docId;
    String order_term,desc,type,date_order;
    int price;
    FirebaseFirestore fStore;
    PorterDuffColorFilter greyFilter;

    public MyOrderNow(@NonNull Context context, int resource, @NonNull ArrayList<ItemModel> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource, parent,false);

        ItemModel itemModel=getItem(position);
        RelativeLayout item=convertView.findViewById(R.id.item);
        MyOrderNowPage myOrderNowPage=MyOrderNowPage.GetInstance();

        item.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                new AlertDialog.Builder(mContext)
                        .setMessage("Вы выполнили этот заказ?")
                        .setPositiveButton("Да", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                myOrderNowPage.arrayList.clear();

                                myOrderNowPage.modelAdapter2.notifyDataSetChanged();
                                docId=itemModel.getDocumentId();
                                completed(docId);
                                myOrderNowPage.arrayList.clear();
                                myOrderNowPage.modelAdapter2.notifyDataSetChanged();
                                myOrderNowPage.EventChangeListener();
                                myOrderNowPage.mlistViewArticle.setAdapter(myOrderNowPage.modelAdapter2);


                                myOrderNowPage.arrayList.clear();
                                myOrderNowPage.modelAdapter2.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("Нет", null)
                        .show();
                return false;
            }
        });
        item.setOnClickListener(view -> {
            desc = getItem(position).getDescription();
            type = getItem(position).getType();
            date_order = getItem(position).getOrder_term();
            price = getItem(position).getPrice();
            String img_frontS = getItem(position).getImg_print();
            String status = getItem(position).getStatus();
            String img_colorS = getItem(position).getImg_color();

            Intent intent = new Intent(mContext, ItemDetailActivity.class);

            intent.putExtra("order_term", order_term);
            intent.putExtra("Description", desc);
            intent.putExtra("Type", type);
            intent.putExtra("price", price);
            intent.putExtra("img_frontS", img_frontS);
            intent.putExtra("status", status);
            intent.putExtra("img_colorS", img_colorS);
            mContext.startActivity(intent);
        });

        ImageView img_front = convertView.findViewById(R.id.img_front);
        TextView txtType = convertView.findViewById(R.id.txt_articleType);

        TextView date_order = convertView.findViewById(R.id.date_order);

        txtType.setText(getItem(position).getType());
        Picasso.get().load(getItem(position).getImg_print()).into(img_front);

        date_order.setText("Срок заказа: "+getItem(position).getOrder_term());

        ImageView img_color = convertView.findViewById(R.id.img_color);
        String img_colorS = getItem(position).getImg_color();
        greyFilter= new PorterDuffColorFilter(Color.parseColor(img_colorS), PorterDuff.Mode.SRC_ATOP);

        img_color.setColorFilter(greyFilter);

        return convertView;
    }

    public  void completed(String id){
        FirebaseAuth mAuth;
        FirebaseUser user;
        mAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();

        user=mAuth.getCurrentUser();
        String user_id=user.getUid();

        DocumentReference db=fStore.collection("orders").document(id);
        db.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()){
                        db.update("status","completed");
                        db.update("sewer",user_id);
                    }
                } else {
                    Toast.makeText(mContext, "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
