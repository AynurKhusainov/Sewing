package com.example.myapplication.User.bottom_pages.Menu;

import android.content.Context;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Designer.ItemDetailActivity;
import com.example.myapplication.Designer.ItemModel;
import com.example.myapplication.Designer.MyOrderNowPage;
import com.example.myapplication.R;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class OrdersAdapter extends ArrayAdapter<ItemModel> {
    private Context mContext;
    private int mResource;

    String docId;
    String order_term,desc,type,date_order;
    int price;
    FirebaseFirestore fStore;
    PorterDuffColorFilter greyFilter;

    public OrdersAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ItemModel> objects) {
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
            intent.putExtra("status", status);
            intent.putExtra("img_frontS", img_frontS);
            intent.putExtra("img_colorS", img_colorS);
            mContext.startActivity(intent);
        });

        ImageView img_front = convertView.findViewById(R.id.img_front);
        TextView txtType = convertView.findViewById(R.id.txt_articleType);

        TextView date_order = convertView.findViewById(R.id.date_order);

        txtType.setText(getItem(position).getType());
        Picasso.get().load(getItem(position).getImg_print()).into(img_front);
        ImageView img_color = convertView.findViewById(R.id.img_color);
        String img_colorS = getItem(position).getImg_color();
        greyFilter= new PorterDuffColorFilter(Color.parseColor(img_colorS), PorterDuff.Mode.SRC_ATOP);

        img_color.setColorFilter(greyFilter);
        return convertView;
    }
}
