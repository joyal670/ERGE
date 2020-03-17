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

public class currentOrderDetailsAdapter extends BaseAdapter {

    private Context context;
//    List<currentOrderModel.UpcommingBean.OrderDataBean> upcomming;
    List<currentOrderModel.UpcommingBean.OrderDataBean> order_data;
//    String name1;
//    String price1;
//    String quantity1;
//    String stock1;
//    String medium_image1;
//    String customer_rating1;

//    public currentOrderDetailsAdapter(Context context, String name1, String price1, String quantity1, String stock1, String medium_image1, String customer_rating1)
//    {
//        this.context = context;
//        this.name1 = name1;
//        this.price1 = price1;
//        this.quantity1 = quantity1;
//        this.stock1 = stock1;
//        this.medium_image1= medium_image1;
//        this.customer_rating1 = customer_rating1;
//    }

    public currentOrderDetailsAdapter(Context context, List<currentOrderModel.UpcommingBean.OrderDataBean> order_data) {
        this.context = context;
        this.order_data = order_data;
    }

    @Override
    public int getCount() {
        return order_data.size();
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
        convertView = inflater.inflate(R.layout.current_order_listview_itms, null);

        TextView name,price,status;
        RatingBar ratingBar;
        ImageView image;

        int tot = 0;

        image = convertView.findViewById(R.id.current_order_details_image);
        name = convertView.findViewById(R.id.current_order_details_name);
        price = convertView.findViewById(R.id.current_order_details_price);
        status = convertView.findViewById(R.id.current_order_details_status);
        ratingBar = convertView.findViewById(R.id.current_order_details_ratingbar);

        try
        {
            Picasso.get().load(order_data.get(position).getMedium_image()).into(image);
            try {
                ratingBar.setRating(Float.parseFloat(order_data.get(position).getCustomer_rating()));
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            name.setText(order_data.get(position).getName());
            price.setText(order_data.get(position).getPrice());
            status.setText(order_data.get(position).getStock());

        } catch (Exception e) {
            e.printStackTrace();
        }
            return convertView;
    }
}
