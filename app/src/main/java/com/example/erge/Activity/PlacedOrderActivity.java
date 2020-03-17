package com.example.erge.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erge.Adapters.checkoutAdapter;
import com.example.erge.R;
import com.example.erge.resource.MobUser;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

public class PlacedOrderActivity extends AppCompatActivity {

    ListView placed_order_listview;
    List<MobUser> UserList;
    checkoutAdapter checkoutAdapter;
    Button placed_order_continuebtn;
    String additionalCharge;
    TextView placed_order_deliveryAddress;
    TextView placed_order_deliverytime;
    TextView placed_order_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placed_order);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.blue));
        }

        placed_order_deliveryAddress = findViewById(R.id.placed_order_deliveryAddress);
        placed_order_deliverytime = findViewById(R.id.placed_order_deliverytime);
        placed_order_id = findViewById(R.id.placed_order_id);
        placed_order_listview = findViewById(R.id.placed_order_listview);
        UserList = SQLite.select().from(MobUser.class).queryList();
        checkoutAdapter = new checkoutAdapter(this,UserList);
        placed_order_listview.setAdapter(checkoutAdapter);

        placed_order_continuebtn = findViewById(R.id.placed_order_continuebtn);
        placed_order_continuebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Delete.table(MobUser.class);

                Intent intent = new Intent(PlacedOrderActivity.this,HomeScreenActivity.class);
                startActivity(intent);
                finish();
            }
        });

        try {
        SharedPreferences amount = getSharedPreferences("Total_Amount", 0);
        String defaultVal = "amt";
        additionalCharge = amount.getString("additionalCharge", defaultVal);

        if(additionalCharge.equals("9.99"))
        {
            placed_order_deliverytime.setText("Within 1 hour selected $9.99");
        }
        else if(additionalCharge.equals("7.99"))
        {
            placed_order_deliverytime.setText("Within 2 hour selected $7.99");
        }
        else if(additionalCharge.equals("5.99"))
        {
            placed_order_deliverytime.setText("Within 5 hour selected $5.99");
        }
        else if (additionalCharge.equals("0.00"))
        {
            placed_order_deliverytime.setText("Within 1 day selected $0.00");
        }

        }
        catch (NumberFormatException e) {
            e.printStackTrace();
        }

        SharedPreferences address = getSharedPreferences("address", 0);
        String defaultVal = "Select Address";
        String a1 = address.getString("a1", defaultVal);
        String a2 = address.getString("a2", defaultVal);
        String l1 = address.getString("l1", defaultVal);
        String l2 = address.getString("l2", defaultVal);
        String l3 = address.getString("l3", defaultVal);
        String l4 = address.getString("l4", defaultVal);
        String full_address = a1 + " " + a2 + " " + l1 + " " + l2 + " " + l3 + " " + l4;

        SharedPreferences amount = getSharedPreferences("Total_Amount", 0);
        placed_order_id.setText(amount.getString("order_id",""));
        placed_order_deliveryAddress.setText(full_address);

    }
    @Override
    public void onBackPressed()
    {
            super.onBackPressed();
            Intent intent = new Intent(PlacedOrderActivity.this,HomeScreenActivity.class);
            startActivity(intent);
            finish();
    }
}
