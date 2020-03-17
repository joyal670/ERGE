package com.example.erge.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Build;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.erge.Adapters.currentOrderDetailsAdapter;
import com.example.erge.Adapters.previousOrderDetailsAdapter;
import com.example.erge.ApiModel.currentOrderModel;
import com.example.erge.R;

public class previousOrderDetailsActivity extends AppCompatActivity {

    ListView previous_Details_Listview;
    previousOrderDetailsAdapter previousOrderDetailsAdapter;
    currentOrderModel.DeliverdBean model = new currentOrderModel.DeliverdBean();
    String Delivery_Charge, Total_Amount;
    TextView total,delivery,finalAmt;
    double tempFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_order_details);

        total = findViewById(R.id.previousOrdertotalprice);
        delivery = findViewById(R.id.previousOrderdeliverycharge);
        finalAmt = findViewById(R.id.previousOrderAmtPayable);
        previous_Details_Listview = findViewById(R.id.previous_Details_Listview);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.blue));
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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
        model = (currentOrderModel.DeliverdBean) getIntent().getSerializableExtra("serialzableqw");
        previousOrderDetailsAdapter = new previousOrderDetailsAdapter(this,model.getOrder_data());
        previous_Details_Listview.setAdapter(previousOrderDetailsAdapter);

    }
}
