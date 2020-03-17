package com.example.erge.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.erge.ApiModel.viewNotificationModel;
import com.example.erge.Fragments.Notification_Fragment;
import com.example.erge.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class notificationAdapter extends BaseAdapter {

    private Context context;
    List<viewNotificationModel.DataBean> notifications;


    public notificationAdapter(Context context, List<viewNotificationModel.DataBean> notifications) {
        this.context = context;
        this.notifications =notifications;
    }

    @Override
    public int getCount() {
        return notifications.size();
    }

    @Override
    public Object getItem(int position) {
        return position ;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.notification_listview_items, null);

        TextView driver_name, orderId, OrderDate;
        CircleImageView image;
        Button accept, reject;
        driver_name = convertView.findViewById(R.id.notification_driver_name);
        orderId = convertView.findViewById(R.id.notification_orderId);
        OrderDate = convertView.findViewById(R.id.notification_orderDate);
        accept = convertView.findViewById(R.id.notification_accept);
        reject = convertView.findViewById(R.id.notification_reject);
        image = convertView.findViewById(R.id.notificationitemImage);
        try {
            orderId.setText(notifications.get(position).getDrivers_list().get(0).getOrder_id());
            OrderDate.setText(notifications.get(position).getDrivers_list().get(0).getOrder_time());
            driver_name.setText(notifications.get(position).getDrivers_list().get(0).getDriver_details().getFirst_name() +" has requested to deliver your order");
            Picasso.get().load(notifications.get(position).getDrivers_list().get(0).getDriver_details().getProfile_pic()).into(image);
        } catch (Exception e) {
            e.printStackTrace();
        }
        final String OrderId, DriverId;
        OrderId = notifications.get(position).getDrivers_list().get(0).getOrder_id();
        DriverId = notifications.get(position).getDrivers_list().get(0).getDriver_details().getId();

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification_Fragment.notificationInterface.accept(OrderId, DriverId);
            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Notification_Fragment.notificationInterface.reject(OrderId, DriverId);
            }
        });
        return convertView;
    }
}
