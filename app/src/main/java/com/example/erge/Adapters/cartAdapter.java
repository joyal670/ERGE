package com.example.erge.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.erge.Fragments.Mycart_Fragment;
import com.example.erge.R;
import com.example.erge.resource.MobUser;
import com.example.erge.resource.MobUser_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.squareup.picasso.Picasso;

import java.util.List;

public class cartAdapter extends BaseAdapter {
    private Context context;
//    List<PriceModel> count;
    List<MobUser> count;


    public cartAdapter(Context context, List<MobUser> count)
    {
        this.context = context;
        this.count=count;
    }

    @Override
    public int getCount() {
        return count.size();
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
    public View getView(final int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.cart_listview_items, null);


        ImageButton add, remove;
        TextView qty;
        ImageView image;
        TextView name, price;
        Button itembtn;

        add = convertView.findViewById(R.id.addqty);
        remove = convertView.findViewById(R.id.removeqty);
        qty = convertView.findViewById(R.id.cartqty);
        image = convertView.findViewById(R.id.cartitemImage);
        name = convertView.findViewById(R.id.cartname);
        price = convertView.findViewById(R.id.mycartprice);
        itembtn = convertView.findViewById(R.id.cartitemremovebtn);

        //qty.setText(count.get(position).getQty()+"");

        List<MobUser> UserList = SQLite.select().from(MobUser.class).queryList();

        String DBimage,DBname,DBprice,DBqty,tempid;

            DBimage = count.get(position).getImage();
            DBname = count.get(position).getName();
            DBprice = count.get(position).getPrice();
            DBqty = count.get(position).getQty();


            Picasso.get().load(DBimage).into(image);
            name.setText(DBname);
            qty.setText(DBqty);
            price.setText(DBprice);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mycart_Fragment.cartInterface.add(position);
//                notifyDataSetChanged();
            }
        });

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Mycart_Fragment.cartInterface.remove(position);
            }
        });

            ImageView ll_outer = convertView.findViewById(R.id.cartitemImage);
                ll_outer.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {
                    Mycart_Fragment.cartInterface.cart();
                }
            });

                itembtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Mycart_Fragment.cartInterface.removeitem(position);
                    }
                });

        return convertView;
    }
}
