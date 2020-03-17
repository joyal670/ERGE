package com.example.erge.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.erge.ApiModel.currentOrderModel;
import com.example.erge.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class previousOrderDetailsAdapter extends BaseAdapter {

    private Context context;
    List<currentOrderModel.DeliverdBean.OrderDataBeanX> deliveredBean;

    public previousOrderDetailsAdapter(Context context, List<currentOrderModel.DeliverdBean.OrderDataBeanX> deliveredBean) {
        this.context = context;
        this.deliveredBean = deliveredBean;
    }

    @Override
    public int getCount() {
        return deliveredBean.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.previous_order_listview_itms, null);

        TextView name,price,status;
        RatingBar ratingBar;
        ImageView image;

        image = convertView.findViewById(R.id.previous_order_details_image);
        name = convertView.findViewById(R.id.previous_order_details_name);
        price = convertView.findViewById(R.id.previous_order_details_price);
        status = convertView.findViewById(R.id.previous_order_details_status);
        ratingBar = convertView.findViewById(R.id.previous_order_details_ratingbar);

        try
        {
            Picasso.get().load(deliveredBean.get(position).getMedium_image()).into(image);
            try {
                ratingBar.setRating(Float.parseFloat(deliveredBean.get(position).getCustomer_rating()));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            name.setText(deliveredBean.get(position).getName());
            price.setText(deliveredBean.get(position).getPrice());
            status.setText(deliveredBean.get(position).getStock());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }
}
