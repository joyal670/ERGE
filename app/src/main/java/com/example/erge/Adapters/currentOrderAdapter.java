package com.example.erge.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.example.erge.Activity.GoogleMapActivity;
import com.example.erge.Activity.currentOrderDetailsActivity;
import com.example.erge.ApiModel.currentOrderModel;
import com.example.erge.Fragments.Myorders_Fragment;
import com.example.erge.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class currentOrderAdapter extends BaseAdapter {

    private Context context;
    List<currentOrderModel.UpcommingBean> upcomming;


    public currentOrderAdapter(Context context, List<currentOrderModel.UpcommingBean> upcomming)
    {
        this.context = context;
        this.upcomming = upcomming;
    }

    @Override
    public int getCount()
    {
        return upcomming.size();
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
        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.current_order_items, null);
        final TextView name,price,price1,qty,status,paymenttype;
        ImageView imageView;
        RatingBar ratingBar;
        final CardView current_order_cardView;
        double totalPrice=0;
        Button trackBtn;

        String tempname="",tempprice="",tempqty="",tempstatus="",tempimage="",temprating="";


        name = convertView.findViewById(R.id.current_order_name);
        price = convertView.findViewById(R.id.current_order_price);
        price1 = convertView.findViewById(R.id.current_order_price1);
        qty = convertView.findViewById(R.id.current_order_qty);
        status = convertView.findViewById(R.id.current_order_status);
        imageView = convertView.findViewById(R.id.current_order_image);
        ratingBar = convertView.findViewById(R.id.current_order_ratingbar);
        paymenttype = convertView.findViewById(R.id.current_order_paymenttype);
        current_order_cardView = convertView.findViewById(R.id.current_order_cardView);
        trackBtn = convertView.findViewById(R.id.current_order_trackBtn);

        try {

            name.setText(upcomming.get(position).getOrder_data().get(0).getName());
            tempname = upcomming.get(position).getOrder_data().get(0).getName();

            price.setText(upcomming.get(position).getOrder_data().get(0).getPrice());
            tempprice = upcomming.get(position).getOrder_data().get(0).getPrice();

            qty.setText(upcomming.get(position).getOrder_data().get(0).getQuantity());
            tempqty = upcomming.get(position).getOrder_data().get(0).getQuantity();

            status.setText(upcomming.get(position).getPayment_status());
            tempstatus = upcomming.get(position).getOrder_data().get(0).getStock();

            paymenttype.setText(upcomming.get(position).getPayment_method());

            Picasso.get().load(upcomming.get(position).getOrder_data().get(0).getMedium_image()).into(imageView);
            tempimage = upcomming.get(position).getOrder_data().get(0).getMedium_image();


            try {
                ratingBar.setRating(Float.parseFloat(upcomming.get(position).getOrder_data().get(0).getCustomer_rating()));
                temprating = upcomming.get(position).getOrder_data().get(0).getCustomer_rating();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }



            for(int i =0; i<=upcomming.get(position).getOrder_data().size() ;i++)
            {
                totalPrice = totalPrice + Double.parseDouble(upcomming.get(position).getOrder_data().get(i).getPrice()) * Double.parseDouble(upcomming.get(position).getOrder_data().get(i).getQuantity());
                price1.setText("" + totalPrice);
            }
//            for(int i =0; i<=position; i++)
//            {
//            try {
//            tempprice = upcomming.get(position).getOrder_data().get(0).getPrice();
//            tempqty = upcomming.get(position).getOrder_data().get(0).getQuantity();
//
//
//                if (tempprice.contains(".")) {
//                    String[] temp = tempprice.split(".");
//
//                    totalPrice = Integer.parseInt(temp[0]) * Integer.parseInt(tempqty);
//                    price1.setText("$" + totalPrice);
//                    price1.setText(Integer.parseInt(upcomming.get(position).getOrder_data().get(0).getPrice()) * Integer.parseInt(upcomming.get(position).getOrder_data().get(0).getQuantity()));
//                } else {
//                    totalPrice = Integer.parseInt(tempprice) * Integer.parseInt(tempqty);
//                    price1.setText("$" + totalPrice);
//                }
//            } catch (NumberFormatException e) {
//                e.printStackTrace();
//            }
//            }
//        }
        } catch (Exception e) {
            e.printStackTrace();
        }

//        current_order_cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();

//                currentOrderDetailsActivity.currentOrderInterface.Details(
//                        upcomming.get(position).getOrder_data().get(0).getName()
//                        ,upcomming.get(position).getOrder_data().get(0).getPrice()
//                        ,upcomming.get(position).getOrder_data().get(0).getQuantity()
//                        ,upcomming.get(position).getOrder_data().get(0).getStock()
//                        ,upcomming.get(position).getOrder_data().get(0).getMedium_image()
//                        ,upcomming.get(position).getOrder_data().get(0).getCustomer_rating() );

//                try {
//                    currentOrderDetailsActivity.currentOrderInterface.Details(upcomming.get(position).getOrder_data());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
        current_order_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
//                currentOrderDetailsActivity.orderInterface.currentItem(upcomming.get(position).getOrder_data());
                try {
                    Intent intent = new Intent(context, currentOrderDetailsActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("serialzable", (Serializable) upcomming.get(position));
                    intent.putExtras(b);
                    intent.putExtra("Delivery_Charge",upcomming.get(position).getDelivery_charge());
                    intent.putExtra("Total_Amount",price1.getText().toString());
                    context.startActivity(intent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        trackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent =  new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("geo:47.4925,19.0513"));
//                Intent chooser = Intent.createChooser(intent,"Launch Maps");
//                context.startActivity(chooser);
                Intent intent =new Intent(context, GoogleMapActivity.class);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
