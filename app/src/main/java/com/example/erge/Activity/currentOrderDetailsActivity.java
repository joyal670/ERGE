package com.example.erge.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erge.Adapters.currentOrderDetailsAdapter;
import com.example.erge.ApiModel.currentOrderModel;
import com.example.erge.R;

import java.util.ArrayList;
import java.util.List;

public class currentOrderDetailsActivity extends AppCompatActivity  {

    ListView order_Details_Listview;
    currentOrderDetailsAdapter DetailsAdapter;
    currentOrderModel.UpcommingBean model = new currentOrderModel.UpcommingBean();
    String Delivery_Charge, Total_Amount;
    TextView total,delivery,finalAmt;
    double tempFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order_details);

        total = findViewById(R.id.currentOrdertotalprice);
        delivery = findViewById(R.id.currentOrderdeliverycharge);
        finalAmt = findViewById(R.id.currrentOrderAmtPayable);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.blue));
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        order_Details_Listview = findViewById(R.id.order_Details_Listview);

        try {
            Delivery_Charge = getIntent().getExtras().getString("Delivery_Charge", "");
            Total_Amount = getIntent().getExtras().getString("Total_Amount", "");
            total.setText(Total_Amount);
            delivery.setText(Delivery_Charge);

            try {
                tempFinal = Double.parseDouble(Delivery_Charge) + Double.parseDouble(Total_Amount);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            finalAmt.setText(tempFinal + "");
        } catch (Exception e) {
            e.printStackTrace();
        }

        model = (currentOrderModel.UpcommingBean) getIntent().getSerializableExtra("serialzable");
        DetailsAdapter = new currentOrderDetailsAdapter(this,model.getOrder_data());
        order_Details_Listview.setAdapter(DetailsAdapter);


    }

//    @Override
//    public void Details(int position)
//    {
////        ApiInterface apiInterface = ApiClient.getClient(getApplicationContext()).create(ApiInterface.class);
////        Call<currentOrderModel> currentOrder = apiInterface.currentOrder(user_token);
////        currentOrder.enqueue(new Callback<currentOrderModel>() {
////            @Override
////            public void onResponse(Call<currentOrderModel> call, Response<currentOrderModel> response) {
////                if (response.body().isStatus())
////                {
////                    upcomming.addAll(response.body().getUpcomming());
////                    DetailsAdapter = new currentOrderDetailsAdapter(getApplicationContext(),upcomming);
////                    order_Details_Listview.setAdapter(DetailsAdapter);
////                }
////            }
////
////            @Override
////            public void onFailure(Call<currentOrderModel> call, Throwable t) {
////
////            }
////        });
//
//    }

//    @Override
//    public void Details(String name, String price, String quantity, String stock, String medium_image, String customer_rating)
//    {
//        DetailsAdapter = new currentOrderDetailsAdapter(this, name, price, quantity, stock, medium_image, customer_rating);
//    }

//    @Override
//    public void Details(List<currentOrderModel.UpcommingBean.OrderDataBean> order_data)
//    {
//       DetailsAdapter = new currentOrderDetailsAdapter(getApplicationContext(),order_data);
//        order_Details_Listview.setAdapter(DetailsAdapter);
//    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.items,menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        switch (item.getItemId())
//        {
//            case R.id.item_notification:
//                Toast.makeText(this, "Notifications", Toast.LENGTH_SHORT).show();
//                return true;
//            case R.id.item_cart:
////                Intent intent = new Intent(HomeItemsActivity.this,Mycart_Fragment.class);
////                startActivity(intent);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//
//    }

//    @Override
//    public void currentItem(List<currentOrderModel.UpcommingBean.OrderDataBean> item)
//    {
//        DetailsAdapter = new currentOrderDetailsAdapter(this,item);
//        order_Details_Listview.setAdapter(DetailsAdapter);
//
//    }
}
