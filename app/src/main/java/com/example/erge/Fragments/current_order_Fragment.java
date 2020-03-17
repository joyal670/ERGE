package com.example.erge.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.erge.Adapters.currentOrderAdapter;
import com.example.erge.ApiClient.ApiClient;
import com.example.erge.ApiInterface.ApiInterface;
import com.example.erge.ApiModel.currentOrderModel;
import com.example.erge.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class current_order_Fragment extends Fragment {

    ListView listView;
    currentOrderAdapter currentOrderAdapter;
    String user_token;
    List<currentOrderModel.UpcommingBean> upcomming = new ArrayList<>();

    public current_order_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_current_order_,container,false);

        listView = view.findViewById(R.id.current_order_listview);
//        currentOrderAdapter = new currentOrderAdapter();
        listView.setAdapter(currentOrderAdapter);

        SharedPreferences sha = this.getActivity().getSharedPreferences("user_token",0);
        String def = "can't get user token";
        user_token = sha.getString("user_token",def);

//        callOrderApi();
        return view;
    }

    private void callOrderApi()
    {
        ApiInterface apiInterface = ApiClient.getClient(getActivity()).create(ApiInterface.class);
        Call<currentOrderModel> currentOrder = apiInterface.currentOrder(user_token);
        currentOrder.enqueue(new Callback<currentOrderModel>() {
            @Override
            public void onResponse(Call<currentOrderModel> call, Response<currentOrderModel> response) {
                if(response.body().isStatus())
                {
                    Toast.makeText(getActivity(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    upcomming.addAll(response.body().getUpcomming());
                    currentOrderAdapter = new currentOrderAdapter(getContext(),upcomming);
                    listView.setAdapter(currentOrderAdapter);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<currentOrderModel> call, Throwable t) {

            }
        });
    }

}
