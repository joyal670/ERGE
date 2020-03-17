package com.example.erge.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erge.ApiClient.ApiClient;
import com.example.erge.ApiInterface.ApiInterface;
import com.example.erge.ApiModel.PaymentResponseModel;
import com.example.erge.ApiModel.PlaceOrderModel;
import com.example.erge.ApiModel.orderJsonModel;
import com.example.erge.R;
import com.example.erge.resource.MobUser;
import com.google.gson.Gson;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.io.UnsupportedEncodingException;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {

    TextView paymentAmount;
    TextView payment_cardnumber,payment_exp_date,payment_CVV,payment_card_holder_name;
    String exp_details;
    String exp[];
    Button paymentBtn;

    String totalPrice;
    String deliveryCharge;
    String additionalCharge;

    double temptotalPrice;
    double tempdeliveryCharge;
    double tempadditionalCharge;

    double finalAmount;

    String user_token;
    String user_email;
    String user_id;
    String order_id;
    String delivery_charge;
    String total_amount;

    public ProgressDialog progressDialog;

    orderJsonModel orderJsonModel=new orderJsonModel();
    List<MobUser> UserList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.blue));
        }
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        progressDialog = new ProgressDialog(this);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");

        payment_cardnumber = findViewById(R.id.payment_cardnumber);
        payment_exp_date = findViewById(R.id.payment_exp_date);



        payment_CVV = findViewById(R.id.payment_CVV);
        payment_card_holder_name = findViewById(R.id.payment_card_holder_name);


        SharedPreferences sha = getSharedPreferences("user_token",0);
        final String def = "can't get user token";
        user_token = sha.getString("user_token",def);
        user_email = sha.getString("username",def);
        user_id = sha.getString("user_id",def);


        paymentAmount = findViewById(R.id.paymentAmount);
        paymentBtn = findViewById(R.id.paymentBtn);
        try {
            SharedPreferences sharedPreferences = getSharedPreferences("Total_Amount", 0);
            String defaultVal = "Select Address";
            totalPrice = sharedPreferences.getString("totalPrice", defaultVal);
            deliveryCharge = sharedPreferences.getString("deliveryCharge", defaultVal);
            additionalCharge = sharedPreferences.getString("additionalCharge", defaultVal);

            total_amount = sharedPreferences.getString("totalPrice", defaultVal);
            delivery_charge = sharedPreferences.getString("deliveryCharge", defaultVal);


            temptotalPrice = Double.parseDouble(totalPrice);
            tempdeliveryCharge = Double.parseDouble(deliveryCharge);
            tempadditionalCharge = Double.parseDouble(additionalCharge);

            finalAmount = temptotalPrice + tempdeliveryCharge + tempadditionalCharge;
            paymentAmount.setText(finalAmount+"");

        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        paymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String cardnumber, exp_date, CVV, card_holder_name;
                cardnumber = payment_cardnumber.getText().toString();
                exp_date = payment_exp_date.getText().toString();
                CVV = payment_CVV.getText().toString();
                card_holder_name = payment_card_holder_name.getText().toString();
                try {
                    if(cardnumber.equals("") || exp_date.equals("") || CVV.equals("") || card_holder_name.equals(""))
                    {
                        Toast.makeText(PaymentActivity.this, "Some fields are missing", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        getSharedPreferencesValues();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void getSharedPreferencesValues()
    {
        String defaultVal = "";
        try {
            SharedPreferences ShreAddress = getSharedPreferences("address", 0);
            String addressid = ShreAddress.getString("id", defaultVal);
            orderJsonModel.setAddress_id(addressid);


            SharedPreferences amount = getSharedPreferences("Total_Amount", 0);
            orderJsonModel.setDelivery_charge(amount.getString("deliveryCharge", defaultVal));


            SharedPreferences ShreShop = getSharedPreferences("shop", 0);
            String a1 = ShreShop.getString("streetAddress", defaultVal);
            String a2 = ShreShop.getString("city", defaultVal);
            String l1 = ShreShop.getString("stateProvCode", defaultVal);

            String full_address = a1 + " " + a2 + " " + l1;

            orderJsonModel.setShop_id(ShreShop.getString("no", defaultVal));
            orderJsonModel.setShop_name(ShreShop.getString("name", defaultVal));
            String tempcord = ShreShop.getString("coordinate", defaultVal);
            String[] addr = tempcord.split(",");

            orderJsonModel.setShop_lat(addr[1]);
            orderJsonModel.setShop_long(addr[0]);
            orderJsonModel.setShop_address(full_address);
            orderJsonModel.setShop_zip(ShreShop.getString("zip", defaultVal));
            orderJsonModel.setShop_phone(ShreShop.getString("phoneNumber", defaultVal));
            orderJsonModel.setDate("");
            orderJsonModel.setTime("");
            orderJsonModel.setInstructions("");

            UserList = SQLite.select().from(MobUser.class).queryList();
            String DBimage,DBname,DBprice,DBqty,DBid,DBdescription,DBrating,DBstoke;
            for(MobUser mu : UserList)
            {
                DBname = mu.getName();
                DBimage = mu .getImage();
                DBprice = mu.getPrice();
                DBid = mu.getProductid();
                DBqty = mu.getQty();
                DBdescription = mu.getDesciption();
                DBrating = mu.getRating();
                DBstoke = mu.getStoke();

                com.example.erge.ApiModel.orderJsonModel.ItemsBean itemsBean = new orderJsonModel.ItemsBean();
//                List<com.example.erge.ApiModel.orderJsonModel.ItemsBean> items = new ArrayList<>();

                itemsBean.setItemId(DBid);
                itemsBean.setMediumImage(DBimage);
                itemsBean.setSalePrice(Double.parseDouble(DBprice));
                itemsBean.setName(DBname);
                itemsBean.setPurchaseCount(Integer.parseInt(DBqty));
                itemsBean.setShortDescription(DBdescription);
                itemsBean.setCustomerRating(DBrating);
                itemsBean.setStock(DBstoke);

                itemsBean.setParentItemId("");
                itemsBean.setMsrp(0);
                itemsBean.setLongDescription("");
                itemsBean.setModelNumber("");
                itemsBean.setSellerInfo("");
                itemsBean.setNumReviews("");


                orderJsonModel.getItems().add(itemsBean);

            }

            Gson gson = new Gson();

            String json = gson.toJson(orderJsonModel);
//            System.out.println(json);

            RequestBody accessTokenValue = null;
            try {

                accessTokenValue = RequestBody.create(MediaType.parse("application/json"), json.getBytes("UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            final ApiInterface apiInterface = ApiClient.getClient(getApplicationContext()).create(ApiInterface.class);

            Call<PlaceOrderModel> placeOrder = apiInterface.placeOrder(user_token,accessTokenValue);
            progressDialog.show();
            placeOrder.enqueue(new Callback<PlaceOrderModel>() {
                @Override
                public void onResponse(Call<PlaceOrderModel> call, Response<PlaceOrderModel> response) {
                    progressDialog.dismiss();
                    if(response.body().isStatus())
                    {

                        SharedPreferences amount = getSharedPreferences("Total_Amount", 0);
                        SharedPreferences.Editor editor = amount.edit();
                        editor.putString("order_id",response.body().getOrder_id());
                        order_id = response.body().getOrder_id();
                        editor.apply();

                        PaymentApi();
                    }
                }

                @Override
                public void onFailure(Call<PlaceOrderModel> call, Throwable t) {
                    progressDialog.dismiss();
                    Toast.makeText(PaymentActivity.this, ""+t, Toast.LENGTH_SHORT).show();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void PaymentApi() {
        try {
            String card = payment_cardnumber.getText().toString();
            String cvv = payment_CVV.getText().toString();

            exp_details = payment_exp_date.getText().toString();
            exp = exp_details.split("/");
            String month = exp[0];
            String year = exp[1];

            progressDialog.show();
            ApiInterface apiInterface = ApiClient.getClient(getApplicationContext()).create(ApiInterface.class);
            Call<PaymentResponseModel> PaymentResponce = apiInterface.payment(user_email, order_id, delivery_charge, total_amount, card, month, year, cvv, user_token);
            PaymentResponce.enqueue(new Callback<PaymentResponseModel>() {
                @Override
                public void onResponse(Call<PaymentResponseModel> call, Response<PaymentResponseModel> response) {
                    progressDialog.dismiss();
                    if(response.body().isPaid()) {

                        Toast.makeText(PaymentActivity.this, "Orderd Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(PaymentActivity.this, PlacedOrderActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(PaymentActivity.this, "Payment Failed", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<PaymentResponseModel> call, Throwable t) {
                    progressDialog.dismiss();
                }
            });
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

}
