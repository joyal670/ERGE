package com.example.erge.Fragments;


import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.example.erge.ApiClient.ApiClient;
import com.example.erge.ApiInterface.ApiInterface;
import com.example.erge.ApiModel.acceptDriverModel;
import com.example.erge.ApiModel.rejectDriverModel;
import com.example.erge.ApiModel.viewNotificationModel;
import com.example.erge.R;
import com.example.erge.Adapters.notificationAdapter;
import com.example.erge.Interface.notificationInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class Notification_Fragment extends Fragment implements notificationInterface {

    List<viewNotificationModel.DataBean> notifications =new ArrayList<viewNotificationModel.DataBean>();
    List<viewNotificationModel.DataBean> Datanotifications =new ArrayList<viewNotificationModel.DataBean>();
    notificationAdapter notificationAdapter;
    String user_token;

    public ProgressDialog progressDialog;

    ListView listView;
    public static notificationInterface notificationInterface;
    public Notification_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_notification_,container,false);
        listView = view.findViewById(R.id.notification_listview);

        notificationInterface = this;

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");

        SharedPreferences sha = getActivity().getSharedPreferences("user_token",0);
        String def = "can't get user token";
        user_token = sha.getString("user_token",def);

//        listView.setAdapter(new notificationAdapter(getActivity()));
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });

        viewNotifications();

        return view;
    }

    private void viewNotifications()
    {
        progressDialog.show();
        ApiInterface apiInterface = ApiClient.getClient(getContext()).create(ApiInterface.class);
        Call<viewNotificationModel> viewNotifications = apiInterface.notifications(user_token);
        viewNotifications.enqueue(new Callback<viewNotificationModel>() {
            @Override
            public void onResponse(Call<viewNotificationModel> call, Response<viewNotificationModel> response) {
                progressDialog.dismiss();
                if(response.body().isStatus())
                {
                    notifications.addAll(response.body().getData());
                    for(int i=0; i<response.body().getData().size(); i++)
                    {
                        if(notifications.get(i).getDrivers_list().size() != 0)
                        {
                            Datanotifications.add(response.body().getData().get(i));
                        }
                    }
                    notificationAdapter = new notificationAdapter(getContext(), Datanotifications);
                    listView.setAdapter(notificationAdapter);
                }
            }

            @Override
            public void onFailure(Call<viewNotificationModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), ""+t, Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public void accept(String orderId, String driverId)
    {
        progressDialog.show();
        ApiInterface apiInterface = ApiClient.getClient(getContext()).create(ApiInterface.class);
        Call<acceptDriverModel> acceptDriver = apiInterface.acceptDriver(user_token, driverId, orderId);
        acceptDriver.enqueue(new Callback<acceptDriverModel>() {
            @Override
            public void onResponse(Call<acceptDriverModel> call, Response<acceptDriverModel> response) {
                progressDialog.dismiss();
                if(response.body().isStatus())
                {
                    notificationAdapter.notifyDataSetChanged();
                    Toast.makeText(getContext(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getContext(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<acceptDriverModel> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    @Override
    public void reject(String orderId, String driverId)
    {
        progressDialog.show();
        ApiInterface apiInterface = ApiClient.getClient(getContext()).create(ApiInterface.class);
        Call<rejectDriverModel> rejectDriver = apiInterface.rejectDriver(user_token, driverId, orderId);
        rejectDriver.enqueue(new Callback<rejectDriverModel>() {
            @Override
            public void onResponse(Call<rejectDriverModel> call, Response<rejectDriverModel> response) {
                progressDialog.dismiss();
                if(response.body().isStatus())
                {
                    Toast.makeText(getContext(), ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    notificationAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<rejectDriverModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), ""+t, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
