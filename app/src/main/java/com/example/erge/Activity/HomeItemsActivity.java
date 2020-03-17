package com.example.erge.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erge.Fragments.Mycart_Fragment;
import com.example.erge.Fragments.Notification_Fragment;
import com.example.erge.R;
import com.example.erge.resource.MobUser;
import com.example.erge.resource.MobUser_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomeItemsActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView image;
    TextView name,price,description,stoke;
    RatingBar rating;
    Button cartbtn,buyNowbtn;

    String tempimage ;
    String tempname;
    String tempprice;
    String temprating;
    String tempdesc;
    String tempstoke;
    String tempid;
    String tempqty;

    Intent i;
    List<MobUser> UserList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_items);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        image = findViewById(R.id.currentimage);
        name = findViewById(R.id.currentname);
        price = findViewById(R.id.currentprice);
        description = findViewById(R.id.currentdescription);
        rating = findViewById(R.id.currentrating);
        stoke = findViewById(R.id.currentstock);
        cartbtn = findViewById(R.id.currentCartbtn);
        buyNowbtn = findViewById(R.id.currentBuybtn);

        i = getIntent();
        tempid = i.getStringExtra("id");
        tempimage = i.getStringExtra("image");
        tempname = i.getStringExtra("name");
        tempprice = i.getStringExtra("price");
        temprating = i.getStringExtra("rating");
        tempdesc = i.getStringExtra("description");
        tempqty = i.getStringExtra("qty");
        tempstoke = i.getStringExtra("stock");

        name.setText(tempname);
        price.setText(tempprice);
        description.setText(tempdesc);
        rating.setRating(Float.parseFloat(temprating));
        stoke.setText(tempstoke);
        Picasso.get().load(tempimage).into(image);

        cartbtn.setOnClickListener(this);
        buyNowbtn.setOnClickListener(this);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
        {
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.blue));
        }

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.currentCartbtn:
            {
//                UserList = SQLite.select().from(MobUser.class).queryList();
//                String DBname;
//                for(MobUser mu : UserList)
//                {
//                    DBname = mu.getName();
//
//                    String myname = name.getText().toString();
//                    if(DBname == myname)
//                    {
//                        Toast.makeText(this, "Already exists in cart", Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    {
//                        MobUser mobUser = new MobUser();
//                        mobUser.InsertData(tempimage,tempname,tempprice,tempdesc,temprating,tempstoke);
//                        boolean checkSave =  mobUser.save();
//                        Toast.makeText(this, "Added to Cart "+checkSave, Toast.LENGTH_SHORT).show();
//                        break;
//                    }
//                }
                int id = Integer.parseInt(tempid);
                //UserList = SQLite.select().from(MobUser.class).where(MobUser_Table.id.eq(id) .queryList();
                UserList = SQLite.select().from(MobUser.class).where(MobUser_Table.productid.eq(tempid)).queryList();
                if(UserList.size() == 0 )
                {
                    MobUser mobUser = new MobUser();
                    mobUser.InsertData(tempid,tempimage,tempname,tempprice,tempdesc,temprating,tempqty,tempstoke);
                    boolean checkSave =  mobUser.save();
                    Toast.makeText(this, "Added to Cart ", Toast.LENGTH_SHORT).show();
                    break;
                }
                else
                {
                       int q = Integer.parseInt(UserList.get(0).getQty());
                       q = q + 1;
                       String w = String.valueOf(q);
                       SQLite.update(MobUser.class).set(MobUser_Table.qty.eq(w)).where(MobUser_Table.productid.eq(tempid)).execute();
                       Toast.makeText(this, "Already exists in cart, Added one more Item !!!", Toast.LENGTH_SHORT).show();

                    //myqty = myqty + 1;
                    //String myquantity = String.valueOf(myqty);
                    //SQLite.update(MobUser.class).set(MobUser_Table.qty.eq(myquantity) ).execute();
                    //Toast.makeText(this, "Already exists in cart", Toast.LENGTH_SHORT).show();

                }
//                int DBid;
//                for(MobUser mu : UserList)
//                {
//                    DBid = mu.getId();
//                    if(DBid == id)
//                    {
//                        Toast.makeText(this, "Already exists in cart", Toast.LENGTH_SHORT).show();
//                    }
//                    else
//                    {
//
//                    }
//                }
            }
            case R.id.currentBuybtn:
            {
                int id = Integer.parseInt(tempid);
                UserList = SQLite.select().from(MobUser.class).where(MobUser_Table.productid.eq(tempid)).queryList();
                if(UserList.size() == 0 )
                {
                    MobUser mobUser = new MobUser();
                    mobUser.InsertData(tempid,tempimage,tempname,tempprice,tempdesc,temprating,tempqty,tempstoke);
                    boolean checkSave =  mobUser.save();
                    Toast.makeText(this, "Added to Cart ", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    int q = Integer.parseInt(UserList.get(0).getQty());
                    q = q + 1;
                    String w = String.valueOf(q);
                    SQLite.update(MobUser.class).set(MobUser_Table.qty.eq(w)).where(MobUser_Table.productid.eq(tempid)).execute();
                    Toast.makeText(this, "Already exists in cart", Toast.LENGTH_SHORT).show();
                }
                Intent intent = new Intent(HomeItemsActivity.this,CheckoutActivity.class);
                startActivity(intent);

            }
        }


    }
}
