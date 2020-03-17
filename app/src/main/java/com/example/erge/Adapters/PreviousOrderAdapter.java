package com.example.erge.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.erge.Activity.previousOrderDetailsActivity;
import com.example.erge.ApiModel.currentOrderModel;
import com.example.erge.R;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.List;

public class PreviousOrderAdapter extends BaseAdapter {

    private Context context;
    List<currentOrderModel.DeliverdBean> deliverd;

    public PreviousOrderAdapter(Context context, List<currentOrderModel.DeliverdBean> deliverd)
    {
        this.context = context;
        this.deliverd = deliverd;
    }

    @Override
    public int getCount() {
        return deliverd.size();
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
        convertView = inflater.inflate(R.layout.previous_order_items, null);
        final TextView name,price,price1,qty,status,paymenttype;
        ImageView imageView;
        RatingBar ratingBar;
        final CardView prvious_order_cardView;
        double totalPrice=0;

        name = convertView.findViewById(R.id.previous_order_name);
        price = convertView.findViewById(R.id.previous_order_price);
        price1 = convertView.findViewById(R.id.previous_order_price1);
        qty = convertView.findViewById(R.id.previous_order_qty);
        status = convertView.findViewById(R.id.previous_order_status);
        imageView = convertView.findViewById(R.id.previous_order_image);
        ratingBar = convertView.findViewById(R.id.previous_order_ratingbar);
        paymenttype = convertView.findViewById(R.id.previous_order_paymenttype);
        prvious_order_cardView = convertView.findViewById(R.id.previous_order_cardView);


        try
        {
        name.setText(deliverd.get(position).getOrder_data().get(0).getName());
        price.setText(deliverd.get(position).getOrder_data().get(0).getPrice());
        qty.setText(deliverd.get(position).getOrder_data().get(0).getQuantity());
        status.setText(deliverd.get(position).getPayment_status());
        paymenttype.setText(deliverd.get(position).getPayment_method());
        Picasso.get().load(deliverd.get(position).getOrder_data().get(0).getMedium_image()).into(imageView);

        try
        {
            ratingBar.setRating(Float.parseFloat(deliverd.get(position).getOrder_data().get(0).getCustomer_rating()));
        } catch (NumberFormatException e)
        {
            e.printStackTrace();
        }

        for(int i =0; i<=deliverd.get(position).getOrder_data().size() ;i++)
        {
            totalPrice = totalPrice + Double.parseDouble(deliverd.get(position).getOrder_data().get(i).getPrice()) * Double.parseDouble(deliverd.get(position).getOrder_data().get(i).getQuantity());
            price1.setText("" + totalPrice);
        }

        } catch (Exception e) {
            e.printStackTrace();
        }

        prvious_order_cardView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    Intent intent = new Intent(context, previousOrderDetailsActivity.class);
                    Bundle b = new Bundle();
                    b.putSerializable("serialzableqw", (Serializable) deliverd.get(position));
                    intent.putExtras(b);
                    intent.putExtra("Delivery_Charge",deliverd.get(position).getDelivery_charge());
                    intent.putExtra("Total_Amount",price1.getText().toString());
                    context.startActivity(intent);
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });

        return convertView;
    }
}
