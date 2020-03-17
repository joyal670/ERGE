package com.example.erge.Fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erge.Activity.currentOrderDetailsActivity;
import com.example.erge.Adapters.PreviousOrderAdapter;
import com.example.erge.Adapters.currentOrderAdapter;
import com.example.erge.ApiClient.ApiClient;
import com.example.erge.ApiInterface.ApiInterface;
import com.example.erge.ApiModel.currentOrderModel;
import com.example.erge.Interface.cartInterface;
import com.example.erge.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.erge.R.id.current_order_page;
import static com.example.erge.R.id.previous_order_page;
import static com.example.erge.R.id.up;


/**
 * A simple {@link Fragment} subclass.
 */
public class Myorders_Fragment extends Fragment
{
    TextView t1,t2,t3,t4;
    Layout l1,l2;
    View v1,v2;

    ListView list,deliverdList;
    currentOrderAdapter currentOrderAdapter;
    PreviousOrderAdapter previousOrderAdapter;
    String user_token;
    List<currentOrderModel.UpcommingBean> upcomming = new ArrayList<>();
    List<currentOrderModel.DeliverdBean> deliverd = new ArrayList<>();

    String allOrders = "" ;


    public Myorders_Fragment()
    {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_myorders_,container,false);

        t1 = view.findViewById(R.id.current_order_btn);
        t2 = view.findViewById(R.id.previous_order_btn);
        v1 = view.findViewById(R.id.current_order_page);
        v2 = view.findViewById(R.id.previous_order_page);
        //t3 = v1.findViewById(R.id.myorder);
        //t4 = v2.findViewById(R.id.previousorder);

        list = view.findViewById(R.id.current_order_listview);
        deliverdList = view.findViewById(R.id.previous_order_listview);
        list.setAdapter(currentOrderAdapter);
        SharedPreferences sha = this.getActivity().getSharedPreferences("user_token",0);
        String def = "can't get user token";
        user_token = sha.getString("user_token",def);

        FnCurrentOrder();

        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FnCurrentOrder();
            }
        });

        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FnPreviousOrder();
            }
        });
        return view;

}

    private void FnPreviousOrder()
    {
        v1.setVisibility(View.GONE);
        v2.setVisibility(View.VISIBLE);
        t2.setTextColor(getResources().getColor(R.color.blue));
        t1.setTextColor(getResources().getColor(R.color.black));

        ApiInterface apiInterface = ApiClient.getClient(getActivity()).create(ApiInterface.class);
        Call<currentOrderModel> currentOrder = apiInterface.currentOrder(user_token);
        currentOrder.enqueue(new Callback<currentOrderModel>() {
            @Override
            public void onResponse(Call<currentOrderModel> call, Response<currentOrderModel> response) {
                if(response.body().isStatus())
                {
                    Toast.makeText(getActivity(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    if(deliverd.size() == 0)
                    {
                        deliverd.addAll(response.body().getDeliverd());
                        previousOrderAdapter = new PreviousOrderAdapter(getActivity(),deliverd);
                        deliverdList.setAdapter(previousOrderAdapter);
                    }
                    else {
                        deliverd.clear();
                        previousOrderAdapter.notifyDataSetChanged();
                        deliverd.addAll(response.body().getDeliverd());
                        previousOrderAdapter = new PreviousOrderAdapter(getActivity(),deliverd);
                        deliverdList.setAdapter(previousOrderAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<currentOrderModel> call, Throwable t) {

            }
        });
    }

    private void FnCurrentOrder() {
        v1.setVisibility(View.VISIBLE);
        v2.setVisibility(View.GONE);
        t1.setTextColor(getResources().getColor(R.color.blue));
        t2.setTextColor(getResources().getColor(R.color.black));

        try {
            final ApiInterface apiInterface = ApiClient.getClient(getActivity()).create(ApiInterface.class);
            final Call<currentOrderModel> currentOrder = apiInterface.currentOrder(user_token);
            currentOrder.enqueue(new Callback<currentOrderModel>() {
                @Override
                public void onResponse(Call<currentOrderModel> call, Response<currentOrderModel> response) {
                    if (response.body().isStatus())
                    {
                        Toast.makeText(getActivity(), "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        if(upcomming.size() == 0)
                        {
                            upcomming.addAll(response.body().getUpcomming());
                            currentOrderAdapter = new currentOrderAdapter(getActivity(), upcomming);
                            list.setAdapter(currentOrderAdapter);
                        }
                        else
                        {
                            upcomming.clear();
                            currentOrderAdapter.notifyDataSetChanged();upcomming.addAll(response.body().getUpcomming());
                            currentOrderAdapter = new currentOrderAdapter(getActivity(), upcomming);
                            list.setAdapter(currentOrderAdapter);
                        }



//                        for(int i=0; i<=upcomming.size(); i++)
//                        {
//                            allOrders = allOrders + upcomming.get(i).getOrder_id() + ",";
//                        }
//                        String[] tempAllOrders = allOrders.split(",");
//                        int orderSize = upcomming.size();
//                        String currentOrderId =  tempAllOrders[orderSize];


                        try {
                            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                    Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
//
//                                    Intent intent = new Intent(getActivity(), currentOrderDetailsActivity.class);
//                                    Bundle b = new Bundle();
//                                    b.putSerializable("serialzable", upcomming.get(position));
//                                    intent.putExtras(b);
//                                    startActivity(intent);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<currentOrderModel> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    private void orderDetailsApi()
//    {
//
//        ApiInterface apiInterface = ApiClient.getClient(getActivity()).create(ApiInterface.class);
//        Call<OrderDetailsModel> orderDetails = apiInterface.orderDetails(this.currentOrderId,user_token);
//        orderDetails.enqueue(new Callback<OrderDetailsModel>() {
//            @Override
//            public void onResponse(Call<OrderDetailsModel> call, Response<OrderDetailsModel> response)
//            {
//                if(response.body().isStatus())
//                {
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<OrderDetailsModel> call, Throwable t) {
//
//            }
//        });
//
//    }

}
