package com.example.erge.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.erge.Adapters.PreviousOrderAdapter;
import com.example.erge.ApiModel.currentOrderModel;
import com.example.erge.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class previous_order_Fragment extends Fragment {

    ListView listView;
    PreviousOrderAdapter PreviousOrderAdapter;
    String user_token;
    List<currentOrderModel.DeliverdBean> deliverd = new ArrayList<>();;

    public previous_order_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_previous_order_, container, false);
        listView = view.findViewById(R.id.previous_order_listview);
        listView.setAdapter(PreviousOrderAdapter);

        SharedPreferences sha = this.getActivity().getSharedPreferences("user_token",0);
        String def = "can't get user token";
        user_token = sha.getString("user_token",def);


        return view;
    }

}
