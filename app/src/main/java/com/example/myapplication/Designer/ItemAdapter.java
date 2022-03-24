package com.example.myapplication.Designer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<ItemModel> {

    private Context mContext;
    private int mResource;

    public ItemAdapter(@NonNull Context context, int resource, @NonNull ArrayList<ItemModel> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        convertView = layoutInflater.inflate(mResource, parent,false);
        ImageView imageView = convertView.findViewById(R.id.imageView_article);
        TextView txtHeader = convertView.findViewById(R.id.txt_articleHeader);
        TextView txtType = convertView.findViewById(R.id.txt_articleType);

        imageView.setImageResource(getItem(position).getImage());
        txtHeader.setText(getItem(position).getHeader());
        txtType.setText(getItem(position).getType());

        return convertView;
    }

}
