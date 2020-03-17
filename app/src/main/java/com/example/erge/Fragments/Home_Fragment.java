package com.example.erge.Fragments;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.erge.Activity.HomeItemsActivity;
import com.example.erge.ApiClient.ApiClient;
import com.example.erge.ApiInterface.ApiInterface;
import com.example.erge.ApiModel.SearchProductModel;
import com.example.erge.R;
import com.example.erge.Adapters.homeAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */

public class Home_Fragment extends Fragment implements MyInterface, View.OnClickListener {

    GridView gridLayout;
    SearchView search_Products;
    List<SearchProductModel.ItemsBean> items = new ArrayList<>();
    homeAdapter homeAdapter;
    Intent i;
    CardView mobile, tv, clothing, laptop, camera, kitchen, toys, ac;
    SharedPreferences searched_items;
    int searchCount = 1;
    boolean isApiRunning = false;
    boolean isApiCompleted = false;

    public ProgressDialog progressDialog;

    String searchKey = "";

    public static MyInterface myInterface;

    public Home_Fragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_, container, false);
        myInterface = this;

        mobile = view.findViewById(R.id.HomeMobile);
        tv = view.findViewById(R.id.HomeTV);
        clothing = view.findViewById(R.id.HomeClothing);
        laptop = view.findViewById(R.id.HomeLaptop);
        camera = view.findViewById(R.id.HomeCamera);
        gridLayout = view.findViewById(R.id.home_grid);
        search_Products = view.findViewById(R.id.search_Products);
        kitchen = view.findViewById(R.id.HomeKitchen);
        toys = view.findViewById(R.id.HomeToys);
        ac = view.findViewById(R.id.HomeAirconditioner);

        mobile.setOnClickListener(this);
        tv.setOnClickListener(this);
        clothing.setOnClickListener(this);
        laptop.setOnClickListener(this);
        camera.setOnClickListener(this);
        kitchen.setOnClickListener(this);
        toys.setOnClickListener(this);
        ac.setOnClickListener(this);

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");

        homeAdapter = new homeAdapter(getActivity(), items);
        gridLayout.setAdapter(homeAdapter);

        searched_items = this.getActivity().getSharedPreferences("searched_items", 0);
        String defaultVal = "tv";
        searchKey = searched_items.getString("item", defaultVal);

        gridLayout.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (firstVisibleItem + visibleItemCount == totalItemCount) {
                    if (!isApiRunning) {
                        isApiRunning = true;
                        SearchProductApi(searchKey);
                        searchCount = searchCount + 10;
                    }
                }

            }
        });


        search_Products.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                items.clear();
                homeAdapter.notifyDataSetChanged();
                SearchProductApi(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

//        gridLayout.setAdapter(new homeAdapter(getActivity(), items));
        gridLayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //startActivity(new Intent(getActivity(),HomeItemsActivity.class));
            }

        });

//        setClickEvent(gridLayout);
//
//        l1 = view.findViewById(R.id.homeitem);
//        l1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), HomeItemsActivity.class);
//                startActivity(intent);
//            }
//        });
        return view;
    }


//    private void setClickEvent(final GridLayout gridLayout) {
//        for (int i = 0; i < gridLayout.getChildCount(); i++) {
//            final CardView cardView = (CardView) gridLayout.getChildAt(i);
//            final int finalI = i;
//            cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(getActivity(), "" + finalI, Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getActivity(), HomeItemsActivity.class);
//                    startActivity(intent);
//
//                }
//            });
//        }
//    }

    @Override
    public void name(String id, String imageView, String name, String rating, String price, String description, String qty, String stock) {
        i = new Intent(getActivity().getBaseContext(), HomeItemsActivity.class);
        i.putExtra("id", id);
        i.putExtra("image", imageView);
        i.putExtra("name", name);
        i.putExtra("rating", rating);
        i.putExtra("price", price);
        i.putExtra("description", description);
        i.putExtra("qty", qty);
        i.putExtra("stock", stock);
        getActivity().startActivity(i);

        //startActivity(new Intent(getActivity(), HomeItemsActivity.class));
    }

    @Override
    public void onClick(View v) {
        searchCount = 1;
        switch (v.getId()) {
            case R.id.HomeMobile: {
                search_Products.setQuery("mobile", false);
                searchKey = "mobile";
                items.clear();
                homeAdapter.notifyDataSetChanged();
                SearchProductApi(searchKey);
                break;
            }
            case R.id.HomeTV: {
                search_Products.setQuery("tv", false);
                searchKey = "tv";
                items.clear();
                homeAdapter.notifyDataSetChanged();
                SearchProductApi(searchKey);
                break;
            }
            case R.id.HomeClothing: {
                search_Products.setQuery("cloth", false);
                searchKey = "cloth";
                items.clear();
                homeAdapter.notifyDataSetChanged();
                SearchProductApi(searchKey);
                break;
            }
            case R.id.HomeLaptop: {
                search_Products.setQuery("laptop", false);
                searchKey = "laptop";
                items.clear();
                homeAdapter.notifyDataSetChanged();
                SearchProductApi(searchKey);
                break;
            }
            case R.id.HomeCamera: {
                search_Products.setQuery("camera", false);
                searchKey = "camera";
                items.clear();
                homeAdapter.notifyDataSetChanged();
                SearchProductApi(searchKey);
                break;
            }
            case R.id.HomeKitchen: {
                search_Products.setQuery("kitchen", false);
                searchKey = "kitchen";
                items.clear();
                homeAdapter.notifyDataSetChanged();
                SearchProductApi(searchKey);
                break;
            }
            case R.id.HomeToys: {
                search_Products.setQuery("toys", false);
                searchKey = "toys";
                items.clear();
                homeAdapter.notifyDataSetChanged();
                SearchProductApi(searchKey);
                break;
            }
            case R.id.HomeAirconditioner: {
                search_Products.setQuery("ac", false);
                searchKey = "ac";
                items.clear();
                homeAdapter.notifyDataSetChanged();
                SearchProductApi(searchKey);
                break;
            }
        }
    }

    private void SearchProductApi(String current_search_item) {
        progressDialog.show();
        ApiInterface apiInterface = ApiClient.getHomeClient(getActivity()).create(ApiInterface.class);
        Call<SearchProductModel> searchProduct = apiInterface.searchProduct("search?query=" + current_search_item + "&start=" + searchCount + "&numItems=10&format=json&apiKey=353ajygzfjxw5jd9dvgzawfh");
        searchProduct.enqueue(new Callback<SearchProductModel>() {
            @Override
            public void onResponse(Call<SearchProductModel> call, Response<SearchProductModel> response) {
                progressDialog.dismiss();
                try {
                    items.addAll(response.body().getItems());

                    //items.clear();
                    homeAdapter.notifyDataSetChanged();


                    gridLayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
                isApiRunning=false;
            }

            @Override
            public void onFailure(Call<SearchProductModel> call, Throwable t) {
                progressDialog.dismiss();
                isApiRunning=false;
                try {
                    System.out.println(t.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public void onPause() {
        super.onPause();
        String crnt_itm = searchKey;
        searched_items = this.getActivity().getSharedPreferences("searched_items", 0);
        SharedPreferences.Editor editor = searched_items.edit();
        editor.putString("item", crnt_itm);
        editor.apply();
    }


}
