package com.example.erge.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erge.Adapters.checkoutAdapter;
import com.example.erge.ApiClient.ApiClient;
import com.example.erge.ApiInterface.ApiInterface;
import com.example.erge.ApiModel.searchStoreModel;
import com.example.erge.R;
import com.example.erge.resource.MobUser;
import com.google.android.gms.maps.model.LatLng;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckoutActivity extends AppCompatActivity {
    ListView listView;
    checkoutAdapter checkoutAdapter;
    List<MobUser> UserList;
    TextView totalPrice,deliveryCharge,deliveryCity,deliveryState,deliveryCountry,deliveryl1,deliveryl2,deliverypincode;
    Button Finalcheckout,addressChange;
    int tempId,tempQty;
    float tempPrice,total;

    public ProgressDialog progressDialog;

    final Context context = this;

    double latitude;
    double longitude;

    int distanceCharge = 0;
    double additionalCharge;

    RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.blue));
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        totalPrice = findViewById(R.id.chekouttotalprice);
        deliveryCharge = findViewById(R.id.chekoutdeliverycharge);
        Finalcheckout = findViewById(R.id.chekoutbutton);
        deliveryCity = findViewById(R.id.chekoutcity);
        deliveryState = findViewById(R.id.chekoutstate);
        deliveryCountry = findViewById(R.id.chekoutcountry);
        addressChange = findViewById(R.id.chekoutdeliveryaddressChangeBtn);
        deliveryl1 = findViewById(R.id.checkoutaddressLine1);
        deliveryl2 = findViewById(R.id.checkoutaddressLine2);
        deliverypincode = findViewById(R.id.chekoutpincode);

        Finalcheckout.setVisibility(View.INVISIBLE);

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");

        UserList = SQLite.select().from(MobUser.class).queryList();
        listView = findViewById(R.id.checkoutListView);
        checkoutAdapter = new checkoutAdapter(this,UserList);
        listView.setAdapter(checkoutAdapter);

        try
        {
        List<MobUser> UserCheckout = SQLite.select().from(MobUser.class).queryList();
        for(MobUser mu : UserCheckout)
        {
            tempQty = 0;
            tempPrice = 0;
            try
                {
                    tempId = mu.getId();
                    //tempPrice = Integer.parseInt(mu.getPrice());
                    tempQty =  tempQty + Integer.parseInt(mu.getQty());
                    tempPrice = tempPrice + Float.parseFloat(mu.getPrice());
                    float p = tempPrice * tempQty;
                    total = total + p ;
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
        }
            totalPrice.setText(total + "");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        Finalcheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalCheckout();
            }
        });

        addressChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeAddress();
            }
        });
    }

    private void checkForStore(final double latitude, final double longitude)
    {
        progressDialog.show();
        ApiInterface apiInterface = ApiClient.getHomeClient(this).create(ApiInterface.class);
        final Call<List<searchStoreModel>> searchStore = apiInterface.searchStore("stores?format=json&lat="+latitude+"&lon="+longitude+"&apiKey=353ajygzfjxw5jd9dvgzawfh");
        searchStore.enqueue(new Callback<List<searchStoreModel>>() {
            @Override
            public void onResponse(Call<List<searchStoreModel>> call, Response<List<searchStoreModel>> response)
            {
                progressDialog.dismiss();
                try
                {
                    if (response.body() == null || response.body().size() == 0) {
                        Toast.makeText(CheckoutActivity.this, "No nearby stores", Toast.LENGTH_SHORT).show();
                        Finalcheckout.setVisibility(View.INVISIBLE);

                    } else {

//                        Toast.makeText(CheckoutActivity.this, "Store Found", Toast.LENGTH_SHORT).show();
                        Finalcheckout.setVisibility(View.VISIBLE);
                        Location locationA = new Location("point A");
                        locationA.setLatitude(latitude);
                        locationA.setLongitude(longitude);

                        Location locationB = new Location("point B");
                        locationB.setLatitude(response.body().get(0).getCoordinates().get(1));
                        locationB.setLongitude(response.body().get(0).getCoordinates().get(0));

                        float distance = locationA.distanceTo(locationB);

                        int temp = (int) (distance/1000);
                        distanceCharge = temp;
                        deliveryCharge.setText(distanceCharge+"");

                        SharedPreferences sharedPreferences = getSharedPreferences("shop", 0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("no", String.valueOf(response.body().get(0).getNo()));
                        editor.putString("name",response.body().get(0).getName());
                        editor.putString("country",response.body().get(0).getCountry());
                        editor.putString("coordinate", String.valueOf(response.body().get(0).getCoordinates()));
                        editor.putString("streetAddress",response.body().get(0).getStreetAddress());
                        editor.putString("city",response.body().get(0).getCity());
                        editor.putString("stateProvCode",response.body().get(0).getStateProvCode());
                        editor.putString("zip",response.body().get(0).getZip());
                        editor.putString("phoneNumber",response.body().get(0).getPhoneNumber());
                        editor.apply();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<List<searchStoreModel>> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private void changeAddress()
    {
        Intent intent = new Intent(this,changeAddressActivity.class);
        startActivity(intent);
    }

    private void finalCheckout()
    {
        try {
            LayoutInflater li = LayoutInflater.from(context);
            View deliveryTime = li.inflate(R.layout.choose_delivery_time, null);
            AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
            alertBuilder.setView(deliveryTime);

            Button chooseDeliveryCancelBtn = deliveryTime.findViewById(R.id.chooseDeliverycancelbtn);
            Button chooseDeliverySaveBtn = deliveryTime.findViewById(R.id.chooseDeliverysavebtn);

            final AlertDialog alertDialog = alertBuilder.create();

            radioGroup = deliveryTime.findViewById(R.id.radioGroup);

            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.oneHour:
                            additionalCharge = 9.99;
                            break;
                        case R.id.twoHour:
                            additionalCharge = 7.99;
                            break;
                        case R.id.fiveHour:
                            additionalCharge = 5.99;
                            break;
                        case R.id.oneDay:
                            additionalCharge = 0.00;
                            break;
                    }
                }
            });

            chooseDeliveryCancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.cancel();
                }
            });
            chooseDeliverySaveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences sharedPreferences = getSharedPreferences("Total_Amount", 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("totalPrice", totalPrice.getText().toString());
                    editor.putString("deliveryCharge", deliveryCharge.getText().toString());
                    editor.putString("additionalCharge", String.valueOf(additionalCharge));
                    editor.apply();

                    alertDialog.cancel();

                    Intent intent = new Intent(CheckoutActivity.this,PaymentActivity.class);
                    startActivity(intent);
                }
            });
            alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
//                Toast.makeText(this, "Cart", Toast.LENGTH_SHORT).show();
////                Intent intent = new Intent(HomeScreenActivity.this,Mycart_Fragment.class);
////                startActivity(intent);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//
//    }

    @Override
    protected void onResume()
    {
        super.onResume();
        try {
            File f = new File("/data/data/com.example.erge/shared_prefs/address.xml");
            if(f.exists()) {
                SharedPreferences sharedPreferences = getSharedPreferences("address", 0);
                String defaultVal = "0";
                String a1 = sharedPreferences.getString("a1", defaultVal);
                String a2 = sharedPreferences.getString("a2", defaultVal);
                String l1 = sharedPreferences.getString("l1", defaultVal);
                String l2 = sharedPreferences.getString("l2", defaultVal);
                String l3 = sharedPreferences.getString("l3", defaultVal);
                String l4 = sharedPreferences.getString("l4", defaultVal);

                getLatLng(a1 + a2 + l1 + l2 + l3);

                deliveryl1.setText(a1);
                deliveryl2.setText(a2);
                deliveryCity.setText(l1);
                deliveryState.setText(l2);
                deliveryCountry.setText(l3);
                deliverypincode.setText(l4);

                try {
                    checkForStore(latitude, longitude);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else {
                deliveryl1.setText("USC Village");
                deliveryl2.setText("South Hoover Street");
                deliveryCity.setText("Los Angeles");
                deliveryState.setText("California");
                deliveryCountry.setText("United States");
                deliverypincode.setText("90007");
            }

        } catch (NumberFormatException e) {
            e.printStackTrace();

        }
    }

    public void getLatLng(String location) {
        if (Geocoder.isPresent()) {
            try {
                Geocoder gc = new Geocoder(this);
                List<Address> addresses = gc.getFromLocationName(location, 5); // get the found Address Objects
                List<LatLng> ll = new ArrayList<LatLng>(addresses.size()); // A list to save the coordinates if they are available
                for (Address a : addresses) {
                    if (a.hasLatitude() && a.hasLongitude()) {
                        ll.add(new LatLng(a.getLatitude(), a.getLongitude()));
                        System.out.println("HAHAHA  loc lat: " + a.getLatitude() + " " + a.getLongitude());
                        latitude = a.getLatitude();
                        longitude = a.getLongitude();

                    }
                }
                if (addresses.size() < 1) {
                    getLatLng(location);
                }
            } catch (Exception e) {
                getLatLng(location);
            }
        }
    }

}
