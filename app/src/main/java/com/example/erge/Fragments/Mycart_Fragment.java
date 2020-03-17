package com.example.erge.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.erge.Activity.CheckoutActivity;
import com.example.erge.Activity.HomeItemsActivity;
import com.example.erge.R;
import com.example.erge.Adapters.cartAdapter;
import com.example.erge.Interface.cartInterface;
import com.example.erge.resource.MobUser;
import com.example.erge.resource.MobUser_Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static com.raizlabs.android.dbflow.sql.language.SQLite.delete;


/**
 * A simple {@link Fragment} subclass.
 */
public class Mycart_Fragment extends Fragment implements cartInterface {

    ListView listView;
    public static cartInterface cartInterface;
    TextView qty,price;
    Button  cartCheckout;
    ImageView image;


    int count = 1;
    cartAdapter  cartAdapter;
    //List<PriceModel> priceModels;
    List<MobUser> UserList;
    ;

    public Mycart_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_mycart_,container,false);
        cartInterface = this;

        qty = view.findViewById(R.id.cartqty);
        price = view.findViewById(R.id.mycartprice);
        cartCheckout = view.findViewById(R.id.cartChechOut);
        image = view.findViewById(R.id.emptyCart);
        cartCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartChekout();
            }
        });
//        priceModels=new ArrayList<>();
//        PriceModel PriceModel=new PriceModel();
//        PriceModel.setQunty(1);
//        priceModels.add(PriceModel);
//        PriceModel PriceModel2=new PriceModel();
//        PriceModel.setQunty(1);
//        priceModels.add(PriceModel2);

        UserList = SQLite.select().from(MobUser.class).queryList();
        List<MobUser> UserCheckout = SQLite.select().from(MobUser.class).queryList();
        if(UserCheckout.size() == 0 )
        {

        }
        else
        {
            image.setVisibility(View.INVISIBLE);
        }

        listView = view.findViewById(R.id.cartListView);
        cartAdapter=new cartAdapter(getActivity(),UserList);
//        image.setVisibility(View.INVISIBLE);
//        listView.setVisibility(View.VISIBLE);
//        checkout.setVisibility(View.VISIBLE);
        listView.setAdapter(cartAdapter);
        return view;
    }

    private void cartChekout()
    {
//        List<MobUser> UserCheckout = SQLite.select().from(MobUser.class).queryList();
//        if(UserCheckout.size() == 0 )
//        {
//            //no items in cart
//        }
//        else
//        {
//            for(MobUser mu : UserCheckout)
//            {
//                int tempId,tempQty,tempPrice;
//                String qty,price;
//                try
//                {
//                    tempId = mu.getId();
//                    qty = mu.getQty();
//                    price = mu.getPrice();
//                    Toast.makeText(getActivity(), ""+price, Toast.LENGTH_SHORT).show();
//                }
//                catch (Exception e)
//                {
//                    e.printStackTrace();
//                }
//            }
//        }
        List<MobUser> UserCheckout = SQLite.select().from(MobUser.class).queryList();
        if(UserCheckout.size() == 0 )
        {

        }
        else
        {
            image.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(getActivity(), CheckoutActivity.class);
            startActivity(intent);
        }
    }


    @Override
    public void cart()
    {
        startActivity(new Intent(getActivity(), HomeItemsActivity.class));
    }

    @Override
    public void add(int position)
    {
        String q = UserList.get(position).getQty();
        int qq = Integer.parseInt(q);
        qq = qq + 1;

//        int myprice = Integer.parseInt(price.getText().toString());
//        myprice = myprice * qq;
//        price.setText(myprice);
//        int myprice = Integer.parseInt(UserList.get(position).getPrice());
//        myprice = myprice * qq;
//        price.setText(myprice);

        SQLite.update(MobUser.class).set(MobUser_Table.qty.eq(String.valueOf(qq))).where(MobUser_Table.productid.eq(UserList.get(position).getProductid())) .execute();
        UserList.get(position).setQty(String.valueOf(qq));
        cartAdapter.notifyDataSetChanged();

//        count = count + 1;
//        UserList.get(position).setQunty(count);
//       cartAdapter.notifyDataSetChanged();
    }

    @Override
    public void remove(int position)
    {
        String q = UserList.get(position).getQty();
        int qq = Integer.parseInt(q);
        qq = qq - 1;
        if(qq <= 1)
        {
            qq = 1;
            Toast.makeText(getActivity(), "Qty less then zero", Toast.LENGTH_SHORT).show();
        }
        SQLite.update(MobUser.class).set(MobUser_Table.qty.eq(String.valueOf(qq))).where(MobUser_Table.productid.eq(UserList.get(position).getProductid())).execute();
        UserList.get(position).setQty(String.valueOf(qq));
        cartAdapter.notifyDataSetChanged();

//        count--;
//        if(count <= 1)
//        {
//            count = 1;
//            Toast.makeText(getActivity(), "Qty less then zero", Toast.LENGTH_SHORT).show();
//        }
//        UserList.get(position).setQunty(count);
//        cartAdapter.notifyDataSetChanged();

    }

    @Override
    public void removeitem(int position)
    {
        UserList.get(position).delete();
        UserList.remove(position);
        Toast.makeText(getActivity(), "Item Removed", Toast.LENGTH_SHORT).show();
        cartAdapter.notifyDataSetChanged();
    }
}
