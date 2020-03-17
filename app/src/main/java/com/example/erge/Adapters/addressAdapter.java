package com.example.erge.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.erge.ApiModel.viewAddressModel;
import com.example.erge.R;

import java.util.ArrayList;
import java.util.List;

public class addressAdapter extends BaseAdapter {
    private Context context;
    List<viewAddressModel.DataBean> address;

    public addressAdapter(Context context, List<viewAddressModel.DataBean> address)
    {
        this.context = context;
        this.address = address;
    }

    @Override
    public int getCount() {
        return address.size();
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
        convertView = inflater.inflate(R.layout.change_address_list_items, null);

        TextView city,state,country,line1,line2,pincode;
        line1 = convertView.findViewById(R.id.chgAddritemaddressLine1);
        line2 = convertView.findViewById(R.id.chgAddritemaddressLine2);
        city = convertView.findViewById(R.id.chgAddritemCity);
        state = convertView.findViewById(R.id.chgAddritemState);
        country = convertView.findViewById(R.id.chgAddritemCountry);
        pincode = convertView.findViewById(R.id.chgAddritemPincode);

        try {
            String tempaddress, temloc, addrline1, addrline2, addrcity, addrstate, addrcountry, addrpincode;
            tempaddress = address.get(position).getAddress();
            temloc = address.get(position).getLocation();
            String[] addr = tempaddress.split(",");
            String[] loc = temloc.split(",");

//        addrcity = address.get(position).getLat();
//        addrstate = address.get(position).getLongX();
//        addrcountry = address.get(position).getId();

//        line1.setText(addrline1);
//        line2.setText(addrline2);
//        city.setText(addrcity);
//        state.setText(addrstate);
//        country.setText(addrcountry);

            line1.setText(addr[0]);
            line2.setText(addr[1]);
            city.setText(loc[0]);
            state.setText(loc[1]);
            country.setText(loc[2]);
            pincode.setText(loc[3]);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
    }
}
