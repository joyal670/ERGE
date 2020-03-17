package com.example.erge.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.erge.Activity.CheckoutActivity;
import com.example.erge.R;
import com.example.erge.resource.MobUser;
import com.squareup.picasso.Picasso;

import java.util.List;

public class checkoutAdapter extends BaseAdapter {
    private Context context;
    List<MobUser> count;


    @Override
    public int getCount() {
        return count.size();
    }

    public checkoutAdapter(Context context, List<MobUser> count)
    {
        this.context = context;
        this.count=count;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.checkout_items, null);

        ImageButton add, remove;
        TextView qty;
        ImageView image;
        TextView name, price;

//        add = convertView.findViewById(R.id.chekoutaddqty);
//        remove = convertView.findViewById(R.id.chekoutremoveqty);
        qty = convertView.findViewById(R.id.checkoutitemqty);
        image = convertView.findViewById(R.id.checkoutitemImage);
        name = convertView.findViewById(R.id.checkoutitemname);
        price = convertView.findViewById(R.id.checkoutitemprice);

        String DBimage,DBname,DBprice,DBqty;

        DBimage = count.get(position).getImage();
        DBname = count.get(position).getName();
        DBprice = count.get(position).getPrice();
        DBqty = count.get(position).getQty();

        Picasso.get().load(DBimage).into(image);
        name.setText(DBname);
        qty.setText(DBqty);
        price.setText(DBprice);


        return convertView;
    }
}
