package com.example.erge.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.example.erge.ApiModel.SearchProductModel;
import com.example.erge.Fragments.Home_Fragment;
import com.example.erge.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class homeAdapter extends BaseAdapter
{
    private Context context;
    List<SearchProductModel.ItemsBean> items;


    public homeAdapter(Context context, List<SearchProductModel.ItemsBean> items)
    {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        if (convertView == null) {
            gridView = new View(context);
            gridView = inflater.inflate(R.layout.home_items, null);

        } else
            {
            gridView = (View) convertView;

            CardView cardView = gridView.findViewById(R.id.cardView);
            final ImageView imageView = gridView.findViewById(R.id.homeimgbtn);
            final TextView name = gridView.findViewById(R.id.homeitemname);
            final RatingBar rating = gridView.findViewById(R.id.homeratingbar);
            final TextView price = gridView.findViewById(R.id.cartprice);
            TextView orginal_price = gridView.findViewById(R.id.homeorginalprice);
            TextView discount = gridView.findViewById(R.id.homediscount);

            final String description = items.get(position).getShortDescription();
            final String stock = items.get(position).getStock();

            try {

                name.setText(items.get(position).getName());
                try {
                    rating.setRating(Float.parseFloat(items.get(position).getCustomerRating()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                price.setText(Double.toString(items.get(position).getSalePrice()));
                Picasso.get().load(items.get(position).getLargeImage()).into(imageView);

                final String tempid, tempimage, tempname, temprating, tempprice, tempdescription, tempqty, tempstoke;
                tempid = String.valueOf(items.get(position).getItemId());
                tempimage = items.get(position).getMediumImage();
                tempname = name.getText().toString();
                temprating = String.valueOf(rating.getRating());
                tempprice = price.getText().toString();
                tempdescription = description;
                tempqty = "1";
                tempstoke = stock;

                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Home_Fragment.myInterface.name(tempid, tempimage, tempname, temprating, tempprice, tempdescription, tempqty, tempstoke);
                    }
                });

            } catch (Exception e) {
                e.printStackTrace();
            }

            }
        return gridView;
    }}
