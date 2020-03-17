package com.example.erge.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erge.Adapters.PlaceAutoSuggestAdapter;
import com.example.erge.Adapters.addressAdapter;
import com.example.erge.ApiClient.ApiClient;
import com.example.erge.ApiInterface.ApiInterface;
import com.example.erge.ApiModel.DefaultAddressModel;
import com.example.erge.ApiModel.addAddressModel;
import com.example.erge.ApiModel.viewAddressModel;
import com.example.erge.R;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class changeAddressActivity extends AppCompatActivity {

    FloatingActionButton floatingActionButton;
    ListView lv;
    TextView city,state,country,l1,l2,pincode;
    List<viewAddressModel.DataBean> address =new ArrayList<>();
    addressAdapter addressAdapter;
    final Context context = this;
    double latitude;
    double longitude;

    public ProgressDialog progressDialog;

    AutoCompleteTextView addline1;
    EditText addline2;
    EditText addcity;
    EditText addstate;
    EditText addcountry;
    EditText addpincode;
    Button newsave;
    Button newcancel;

    Geocoder mGeocoder;
    SharedPreferences sharedPreferences;
    String user_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_address);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.blue));
        }

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");

        floatingActionButton = findViewById(R.id.floatingActionButton);
        lv = findViewById(R.id.chgAddrListView);
        city = findViewById(R.id.chgAddrCity);
        state = findViewById(R.id.chgAddrState);
        country = findViewById(R.id.chgAddrCountry);
        l1 = findViewById(R.id.chgAddraddressLine1);
        l2 = findViewById(R.id.chgAddraddressLine2);
        pincode = findViewById(R.id.chgAddrPincode);

        SharedPreferences sha = getSharedPreferences("user_token",0);
        String def = "can't get user token";
        user_token = sha.getString("user_token",def);

//        address = SQLite.select().from(MyAddress.class).queryList();
//        addressAdapter = new addressAdapter(this,address);
//        lv.setAdapter(addressAdapter);

        viewAddressApi();


        sharedPreferences = getSharedPreferences("address",0);
        String a1 = sharedPreferences.getString("a1","");
        String a2 = sharedPreferences.getString("a2","");
        String a3 = sharedPreferences.getString("l1","");
        String a4 = sharedPreferences.getString("l2","");
        String a5 = sharedPreferences.getString("l3","");
        String a6 = sharedPreferences.getString("l4","");
        l1.setText(a1);
        l2.setText(a2);
        city.setText(a3);
        state.setText(a4);
        country.setText(a5);
        pincode.setText(a6);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewAddress();
            }
        });

    }

    private void viewAddressApi() {
        progressDialog.show();
        try {
            final ApiInterface apiInterface = ApiClient.getClient(this).create(ApiInterface.class);
            Call<viewAddressModel> viewAddress = apiInterface.viewAddress(user_token);
            viewAddress.enqueue(new Callback<viewAddressModel>() {
                @Override
                public void onResponse(Call<viewAddressModel> call, Response<viewAddressModel> response) {
                    progressDialog.dismiss();
                    if (response.body().isStatus()) {
                        address.addAll(response.body().getData());
                        addressAdapter = new addressAdapter(getApplicationContext(), address);
                        lv.setAdapter(addressAdapter);
                        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                            {
                                String tempaddress, temloc, tempId, templat, templong;
                                tempaddress = address.get(position).getAddress();
                                temloc = address.get(position).getLocation();
                                tempId = address.get(position).getId();
                                templat = address.get(position).getLat();
                                templong = address.get(position).getLongX();

                                String[] addr = tempaddress.split(",");
                                String[] loc = temloc.split(",");

                                try {

                                    l1.setText(addr[0]);
                                    l2.setText(addr[1]);
                                    city.setText(loc[0]);
                                    state.setText(loc[1]);
                                    country.setText(loc[2]);
                                    pincode.setText(loc[3]);

                                    SharedPreferences sharedPreferences = getSharedPreferences("address", 0);
                                    SharedPreferences.Editor editor = sharedPreferences.edit();
                                    editor.putString("a1", addr[0]);
                                    editor.putString("a2", addr[1]);
                                    editor.putString("l1", loc[0]);
                                    editor.putString("l2", loc[1]);
                                    editor.putString("l3", loc[2]);
                                    editor.putString("l4", loc[3]);
                                    editor.putString("id",tempId);
                                    editor.putString("lat",templat);
                                    editor.putString("long",templong);
                                    editor.apply();

                                    changeDefaultAddressApi(tempId);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                    else {
                        String msg = response.body().getMessage();
                        Toast.makeText(context, ""+msg, Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<viewAddressModel> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(context, ""+t, Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void changeDefaultAddressApi(String tempId)
    {
        progressDialog.show();
        final ApiInterface apiInterface = ApiClient.getClient(getApplicationContext()).create(ApiInterface.class);
        Call<DefaultAddressModel> defaultAddress = apiInterface.defaultAddress(user_token, tempId);
        defaultAddress.enqueue(new Callback<DefaultAddressModel>()
        {
            @Override
            public void onResponse(Call<DefaultAddressModel> call, Response<DefaultAddressModel> response) {
                progressDialog.dismiss();
                if (response.body().isStatus())
                {
                    Toast.makeText(changeAddressActivity.this, "Changed Default Address", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(changeAddressActivity.this,CheckoutActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<DefaultAddressModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(context, "" + t, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addNewAddress()
    {
        LayoutInflater li = LayoutInflater.from(context);
        View newAddress = li.inflate(R.layout.prompt,null);
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setView(newAddress);
        addline1 = newAddress.findViewById(R.id.newaddline1);
        addline2 = newAddress.findViewById(R.id.newaddline2);
        addcity = newAddress.findViewById(R.id.newaddcity);
        addstate = newAddress.findViewById(R.id.newaddstate);
        addcountry = newAddress.findViewById(R.id.newaddcountry);
        addpincode = newAddress.findViewById(R.id.newaddpincode);
        newsave = newAddress.findViewById(R.id.newaddsavebtn);
        newcancel = newAddress.findViewById(R.id.newaddcancelbtn);
        addline1.setAdapter(new PlaceAutoSuggestAdapter(this,android.R.layout.simple_list_item_1));

        addline1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                getLatLng(addline1.getText().toString());
            }
        });

        final AlertDialog alertDialog = alertBuilder.create();

        newsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String t1, t2, t3, t4, t5, t6, housename, location, lat, longi;
                t1 = addline1.getText().toString();
                t2 = addline2.getText().toString();
                t3 = addcity.getText().toString();
                t4 = addstate.getText().toString();
                t5 = addcountry.getText().toString();
                t6 = addpincode.getText().toString();
                if (t1.isEmpty() || t2.isEmpty() || t3.isEmpty() || t4.isEmpty() || t5.isEmpty() || t6.isEmpty())
                {
                    Toast.makeText(context, "All Fileds are Required", Toast.LENGTH_SHORT).show();
                }
                else
                    {
                    housename = t1 + "," + t2;
                    location = t3 + "," + t4 + "," + t5 + "," + t6;
                    lat = String.valueOf(latitude);
                    longi = String.valueOf(longitude);

                    final ApiInterface apiInterface = ApiClient.getClient(getApplicationContext()).create(ApiInterface.class);
                        progressDialog.show();
                        Call<addAddressModel> addAddress = apiInterface.addAddress(user_token, location, lat, longi, housename, "1");
                    addAddress.enqueue(new Callback<addAddressModel>()
                    {
                        @Override
                        public void onResponse(Call<addAddressModel> call, Response<addAddressModel> response)
                        {
                            progressDialog.dismiss();
                            if (response.body().isStatus())
                            {
                                Toast.makeText(context, "" + response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                alertDialog.dismiss();

                                Call<viewAddressModel> viewAddress = apiInterface.viewAddress(user_token);
                                viewAddress.enqueue(new Callback<viewAddressModel>()
                                {
                                    @Override
                                    public void onResponse(Call<viewAddressModel> call, Response<viewAddressModel> response)
                                    {
                                        if (response.body().isStatus())
                                        {
                                            address.clear();
                                            address.addAll(response.body().getData());
                                            addressAdapter = new addressAdapter(getApplicationContext(), address);
                                            lv.setAdapter(addressAdapter);
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<viewAddressModel> call, Throwable t)
                                    {
                                        progressDialog.dismiss();
                                    }
                                });

                            }
                        }

                        @Override
                        public void onFailure(Call<addAddressModel> call, Throwable t) {
                            progressDialog.dismiss();
                            Toast.makeText(context, "" + t, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        newcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });

//        alertBuilder
//                .setCancelable(false)
//                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which)
//                    {
//                        String templine1, templine2, tempcity, tempstate, tempcountry, temppincode;
//                        templine1 = line1.getText().toString();
//                        templine2 = line2.getText().toString();
//                        tempcity = city.getText().toString();
//                        tempstate = state.getText().toString();
//                        tempcountry = country.getText().toString();
//                        temppincode = pincode.getText().toString();
//
//                        if(templine1.isEmpty() || templine2.isEmpty() || tempcity.isEmpty() || tempstate.isEmpty() || tempcountry.isEmpty() || temppincode.isEmpty())
//                        {
//                            Toast.makeText(changeAddressActivity.this,"All Fields are Required",Toast.LENGTH_SHORT).show();
//                        }
//                        else {
////                            MyAddress myAddress = new MyAddress();
////                            myAddress.InsertAddress(templine1, templine2, tempcity, tempstate, tempcountry, temppincode);
////                            boolean checkSave = myAddress.save();
////                            addressAdapter.notifyDataSetChanged();
////                            Toast.makeText(changeAddressActivity.this, "Address Added", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                })
//                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
        alertDialog.show();
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

                        try {
                            getCityNameByCoordinates(a.getLatitude(), a.getLongitude());
                        } catch (Exception e) {
                            e.printStackTrace();
                            getLatLng(location);
                        }
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

    private String getCityNameByCoordinates(double lat, double lon) throws IOException {
        mGeocoder = new Geocoder(this, Locale.getDefault());
        List<Address> addresses = mGeocoder.getFromLocation(lat, lon, 1);
        if (addresses != null && addresses.size() > 0) {
            System.out.println("HAHAHA place details: country " + addresses.get(0).getCountryName() + " PostalCode " + addresses.get(0).getPostalCode() + " getLocality " + addresses.get(0).getLocality() + " getLocale " + addresses.get(0).getLocale() + " getSubLocality " + addresses.get(0).getAdminArea());
            try {
                addline2.setText(addline1.getText().toString().split(", ")[1]);
                addline1.setText(addline1.getText().toString().split(", ")[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            addcity.setText(addresses.get(0).getLocality());
            addcity.setSelection(addcity.getText().toString().length());
            addstate.setText(addresses.get(0).getAdminArea());
            addstate.setSelection(addstate.getText().toString().length());
            addpincode.setText(addresses.get(0).getPostalCode());
            addpincode.setSelection(addpincode.getText().toString().length());
            addcountry.setText(addresses.get(0).getCountryName());
            addcountry.setSelection(addcountry.getText().toString().length());
            addcity.setError(null);
            addstate.setError(null);
            addpincode.setError(null);
            addcountry.setError(null);

            return addresses.get(0).getLocality();
        }
        return null;
    }

}
